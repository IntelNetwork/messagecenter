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
 * Table: fb_zg_news_attach
 */
@Data
@ApiModel(description="新闻附件")
@TableName("fb_zg_news_attach")
public class ZGNewsAttach extends BaseEntity {
    /**
     * 新闻ID
     *
     * Table:     fb_zg_news_attach
     * Column:    news_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "新闻ID",example="0")
    private Long newsId;

    /**
     * 中文名
     *
     * Table:     fb_zg_news_attach
     * Column:    cn_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "中文名",example="")
    @NotEmpty(message = "中文名为空",groups = {UpdateValid.class, SaveValid.class})
    private String cnName;

    /**
     * 后缀名
     *
     * Table:     fb_zg_news_attach
     * Column:    suffix
     * Nullable:  true
     */
    @ApiModelProperty(value = "后缀名",example="")
    @NotEmpty(message = "后缀名为空",groups = {UpdateValid.class, SaveValid.class})
    private String suffix;

    /**
     * 文件路径
     *
     * Table:     fb_zg_news_attach
     * Column:    file_path
     * Nullable:  true
     */
    @ApiModelProperty(value = "文件路径",example="")
    @NotEmpty(message = "文件路径为空",groups = {UpdateValid.class, SaveValid.class})
    private String filePath;
}