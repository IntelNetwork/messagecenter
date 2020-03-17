package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.annotations.ValidEnum;
import org.forbes.comm.annotations.ValidUnique;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.entity.BaseEntity;
import org.forbes.comm.enums.BusCodeEnum;
import org.smartwork.comm.enums.MsgTypeEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table: fb_zg_msg_template
 */
@Data
@ApiModel(description="消息模板")
@TableName("fb_zg_msg_template")
public class ZGMsgTemplate extends BaseEntity {
    /**
     * 0-短信1-公众号2-邮件
     *
     * Table:     fb_zg_msg_template
     * Column:    msg_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-短信1-公众号2-邮件",example="0")
    @NotNull(message = "消息类型为空",groups = {UpdateValid.class, SaveValid.class})
    @ValidEnum(classzz = MsgTypeEnum.class,bizCode = "006002001",bizErrorMsg = "%s消息类型不存在")
    private Integer msgType;

    /**
     * 如：register会员注册
     *
     * Table:     fb_zg_msg_template
     * Column:    bus_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "如：register会员注册",example="")
    @NotEmpty(message = "业务编码为空",groups = {UpdateValid.class, SaveValid.class})
    @ValidEnum(classzz = BusCodeEnum.class,bizCode = "006002001",bizErrorMsg = "%s业务编码不存在")
    @ValidUnique(column = "bus_code",bizCode = "006002003",bizErrorMsg = "%s业务编码已存在")
    private String busCode;


    @ApiModelProperty(value = "重试次数默认为0不包含第一次",example="0")
    private Integer retryCount;

    /**
     * 模板内容
     *
     * Table:     fb_zg_msg_template
     * Column:    content
     * Nullable:  true
     */
    @ApiModelProperty(value = "模板内容",example="")
    private String content;


    @ApiModelProperty(value = "模板ID",example="")
    private String templateId;

    @ApiModelProperty(value = "重试时间间隔：单位分",example="0")
    private Integer retryInterval;

}