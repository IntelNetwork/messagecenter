package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import org.forbes.comm.annotations.QueryColumn;
import org.forbes.comm.annotations.ValidEnum;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.entity.BaseEntity;
import org.smartwork.comm.enums.AdTypeEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Table: fb_zg_ad
 */
@Data
@ApiModel(description="广告")
@TableName("fb_zg_ad")
public class ZGAd extends BaseEntity {
    /**
     * 广告编码
     *
     * Table:     fb_zg_ad
     * Column:    ad_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "广告编码",example="")
    @NotEmpty(message="广告编码为空",groups = {UpdateValid.class, SaveValid.class})
    @QueryColumn(column = "ad_code",sqlKeyword = SqlKeyword.LIKE)
    private String adCode;

    /**
     * 广告名称
     *
     * Table:     fb_zg_ad
     * Column:    name
     * Nullable:  true
     */
    @ApiModelProperty(value = "广告名称",example="")
    @NotEmpty(message="广告名称为空",groups = {UpdateValid.class, SaveValid.class})
    @QueryColumn(column = "name",sqlKeyword = SqlKeyword.LIKE)
    private String name;

    /**
     * 0-未发布1-已发布2-已取消
     *
     * Table:     fb_zg_ad
     * Column:    release_state
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-未发布1-已发布2-已取消",example="0")
    private Integer releaseState;

    /**
     * 到期时间
     *
     * Table:     fb_zg_ad
     * Column:    expire_date
     * Nullable:  true
     */
    @ApiModelProperty(value = "到期时间",example="")
    @NotNull(message ="到期时间为空",groups = {UpdateValid.class, SaveValid.class})
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expireDate;

    /**
     * 0-滚动文字1-轮播图2-静态图
     *
     * Table:     fb_zg_ad
     * Column:    ad_type
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-滚动文字1-轮播图2-静态图",example="0")
    @ValidEnum(classzz = AdTypeEnum.class,bizCode = "006001002",bizErrorMsg = "%s广告类型不存在")
    @NotNull(message ="广告类型为空",groups = {UpdateValid.class, SaveValid.class})
    @QueryColumn(column = "ad_type",sqlKeyword = SqlKeyword.EQ)
    private Integer adType;

    /**
     * 图片地址
     *
     * Table:     fb_zg_ad
     * Column:    img_path
     * Nullable:  true
     */
    @ApiModelProperty(value = "图片地址",example="")
    private String imgPath;

    /**
     * 0-否1-是
     *
     * Table:     fb_zg_ad
     * Column:    new_win
     * Nullable:  true
     */
    @ApiModelProperty(value = "0-否1-是",example="0")
    private Integer newWin;

    /**
     * 请求地址
     *
     * Table:     fb_zg_ad
     * Column:    req_url
     * Nullable:  true
     */
    @ApiModelProperty(value = "请求地址",example="")
    private String reqUrl;

    /**
     * 排序
     *
     * Table:     fb_zg_ad
     * Column:    orders
     * Nullable:  true
     */
    @ApiModelProperty(value = "排序",example="0")
    private Integer orders;

    /**
     * 广告位id
     *
     * Table:     fb_zg_ad
     * Column:    orders
     * Nullable:  true
     */
    @ApiModelProperty(value = "广告位id",example="0")
    private Long advertId;
}