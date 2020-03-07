package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

import lombok.Data;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * Table: fb_zg_news
 */
@Data
@ApiModel(description="新闻")
@TableName("fb_zg_news")
public class ZGNews extends BaseEntity {
    /**
     * Table:     fb_zg_news
     * Column:    icon
     * Nullable:  true
     */
    @ApiModelProperty(value = "缩略图",example="")
    private String icon;

    /**
     * 标题
     *
     * Table:     fb_zg_news
     * Column:    title
     * Nullable:  true
     */
    @ApiModelProperty(value = "标题",example="")
    @NotEmpty(message = "标题为空",groups = {UpdateValid.class, SaveValid.class})
    private String title;

    /**
     * 0-未发布1-已发布2-取消
     *
     * Table:     fb_zg_news
     * Column:    release_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-未发布1-已发布2-取消",example="0")
    private Integer releaseState;

    /**
     * 发布时间
     *
     * Table:     fb_zg_news
     * Column:    release_time
     * Nullable:  true
     */
    @ApiModelProperty(value = "发布时间",example="")
    private Date releaseTime;

    /**
     * 内容
     *
     * Table:     fb_zg_news
     * Column:    content
     * Nullable:  true
     */
    @ApiModelProperty(value = "内容",example="")
    private String content;


    /****附件信息
     */
    @TableField(exist = false)
    List<ZGNewsAttach> newsAttachs;
}
