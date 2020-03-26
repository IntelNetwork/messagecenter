package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.annotations.QueryColumn;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * Table: fb_zg_msg_log
 */
@Data
@ApiModel(description="消息日志")
@TableName("fb_zg_msg_log")
public class ZGMsgLog extends BaseEntity {
    /**
     * 0-短信1-公众号2-异步消息
     *
     * Table:     fb_zg_msg_log
     * Column:    msg_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-短信1-公众号2-异步消息",example="")
    @QueryColumn(column = "ad_type",sqlKeyword = SqlKeyword.EQ)
    private Integer msgType;

    /**
     * 发送内容
     *
     * Table:     fb_zg_msg_log
     * Column:    content
     * Nullable:  true
     */
    @ApiModelProperty(value = "发送内容",example="",required = true)
    @NotEmpty(message = "发送内容不能为空")
    @QueryColumn(column = "content",sqlKeyword = SqlKeyword.LIKE)
    private String content;

    /**
     * 0-成功1-失败
     *
     * Table:     fb_zg_msg_log
     * Column:    success_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-成功1-失败",example="")
    private Integer successState;

    /**
     * 重试次数默认为0不包含第一次
     *
     * Table:     fb_zg_msg_log
     * Column:    retry_count
     * Nullable:  true
     */
    @ApiModelProperty(value = "重试次数默认为0不包含第一次",example="0")
    private Integer retryCount;

    /**
     * 失败次数
     *
     * Table:     fb_zg_msg_log
     * Column:    failure_count
     * Nullable:  true
     */
    @ApiModelProperty(value = "失败次数",example="0")
    private Integer failureCount;

    /**
     * 失败返回结果
     *
     * Table:     fb_zg_msg_log
     * Column:    failure_result
     * Nullable:  true
     */
    @ApiModelProperty(value = "失败返回结果",example="")
    private String failureResult;


    @ApiModelProperty(value = "下次执行时间",example="")
    private Date nextExeTime;

    @ApiModelProperty(value = "消息ID",example="")
    private String  msgId;
}