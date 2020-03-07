package org.smartwork.msg.consumer;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.msg.biz.SMSUtils;
import org.forbes.msg.biz.strategy.CryunSmsStrategy;
import org.forbes.msg.biz.strategy.YmrtSmsStrategy;
import org.smartwork.biz.service.IZGMsgLogService;
import org.smartwork.biz.service.IZGMsgTemplateService;
import org.smartwork.comm.enums.MsgTypeEnum;
import org.smartwork.comm.enums.SuccessStateEnum;
import org.smartwork.dal.entity.ZGMsgLog;
import org.smartwork.dal.entity.ZGMsgTemplate;
import org.smartwork.dal.mapper.ZGMsgTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

/****短信接收
 */
@Slf4j
@Component
public class SmsProvider {

    @Autowired
    IZGMsgTemplateService msgTemplateService;
    @Autowired
    IZGMsgLogService  msgLogService;


    /***接收kafka消息
     * @param consumerRecord
     */
    @KafkaListener(topics="topicSms")
    public void sendMsg(ConsumerRecord consumerRecord) {
        Optional<ConsumerRecord> kfkaMsg =  Optional.ofNullable(consumerRecord);
        if(kfkaMsg.isPresent()){
            ConsumerRecord obj  = kfkaMsg.get();
            String receJson = obj.value().toString();
            log.debug("====短信接收参数===="+receJson);
            Map<String,Object> receMap = JSON.parseObject(receJson,Map.class);
            String busCode = receMap.get("busCode").toString();
            String mobile = receMap.get("mobile").toString();
            String content = receMap.get("content").toString();
            String  msgId = receMap.get("msgId").toString();
            String resultCode = "";
            String resultMsg = "";
            ZGMsgTemplate msgTemplate = msgTemplateService.getOne(new QueryWrapper<ZGMsgTemplate>().eq("msg_type", MsgTypeEnum.SMS.getCode())
                    .eq("bus_code",busCode));
            if(ConvertUtils.isNotEmpty(msgTemplate)){
               String templateId =  msgTemplate.getTemplateId();
               if(ConvertUtils.isNotEmpty(templateId)){
                  Map<String,String> resultMap =  SMSUtils.SMSBuild.build(new CryunSmsStrategy())
                           .setMobiles(mobile.split(","))
                           .setTemplateId(templateId)
                           .setContent(content)
                            .sendSMS();
                  resultCode = resultMap.get("resultCode");
                  resultMsg = resultMap.get("resultMsg");
               } else {
                   String templateContent =  msgTemplate.getContent();
                   /**自定义消息模板**/
                   if(ConvertUtils.isNotEmpty(templateContent)){
                       content = String.format(templateContent,content.split(","));
                       Map<String,String> resultMap =  SMSUtils
                               .SMSBuild.build(new YmrtSmsStrategy())
                               .setMobiles(mobile.split(","))
                               .setContent(content)
                               .sendSMS();
                       resultCode = resultMap.get("resultCode");
                       resultMsg = resultMap.get("resultMsg");
                   }
               }
               this.addMsgLog(resultCode,resultMsg,msgId,content,msgTemplate);
            } else {
                log.error("{}=====暂无对应消息模板",busCode);
            }
            System.err.println(obj);
        } else {
            log.error("============="+JSON.toJSONString(consumerRecord));
        }
    }


    /***
     * 增加短信日志
     * @param resultCode
     * @param resultMsg
     * @param msgId
     * @param content
     * @param msgTemplate
     */
    private void addMsgLog(String resultCode,String resultMsg,String msgId,String content,ZGMsgTemplate msgTemplate){
        ZGMsgLog msgLog = msgLogService.getOne(new QueryWrapper<ZGMsgLog>().eq("msg_id",msgId));
        if(ConvertUtils.isNotEmpty(msgLog)){
            if("0000".equalsIgnoreCase(resultCode)){
                msgLogService.update(new UpdateWrapper<ZGMsgLog>()
                        .set("success_state",SuccessStateEnum.SUCCESS.getCode())
                        .set("failure_count",msgLog.getFailureCount()+1)
                        .set("failure_result",resultMsg)
                        .eq("msg_id",msgId));

            } else {
                if(ConvertUtils.isNotEmpty(msgTemplate.getRetryInterval())
                        && msgLog.getFailureCount() < msgTemplate.getRetryInterval()){
                    msgLogService.update(new UpdateWrapper<ZGMsgLog>()
                            .set("success_state",SuccessStateEnum.FAILURE.getCode())
                            .set("failure_count",msgLog.getFailureCount()+1)
                            .set("next_exe_time",DateUtils.addMinutes(new Date(),msgTemplate.getRetryInterval()))
                            .set("failure_result",resultMsg)
                            .eq("msg_id",msgId));
                }
            }
        } else {
            msgLog = new ZGMsgLog();
            msgLog.setMsgType(MsgTypeEnum.SMS.getCode());
            if("0000".equalsIgnoreCase(resultCode)){
                msgLog.setSuccessState(SuccessStateEnum.SUCCESS.getCode());
            } else {
                msgLog.setSuccessState(SuccessStateEnum.FAILURE.getCode());
                if(ConvertUtils.isNotEmpty(msgTemplate.getRetryInterval())){
                    msgLog.setNextExeTime(DateUtils.addMinutes(new Date(),msgTemplate.getRetryInterval()));
                }
            }
            msgLog.setRetryCount(msgTemplate.getRetryCount());
            msgLog.setFailureCount(0);
            msgLog.setFailureResult(resultMsg);
            msgLog.setMsgId(msgId);
            msgLog.setContent(content);
            msgLogService.save(msgLog);
        }
    }
}
