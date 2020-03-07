package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;

/**
 * Table: fb_zg_news_classify
 */
@Data
@ApiModel(description="新闻分类")
@TableName("fb_zg_news_classify")
public class ZGNewsClassify extends BaseEntity {
    /**
     * 分类名称
     *
     * Table:     fb_zg_news_classify
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "分类名称",example="")
    @NotEmpty(message = "分类名称为空",groups = {UpdateValid.class, SaveValid.class})
    private String name;

    /**
     * 备注
     *
     * Table:     fb_zg_news_classify
     * Column:    remarks
     * Nullable:  true
     */
    @ApiModelProperty(value = "备注",example="")
    private String remarks;

    /**
     * Table:     fb_zg_news_classify
     * Column:    icon
     * Nullable:  true
     */
    @ApiModelProperty(value = "缩略图",example="")
    private String icon;
}