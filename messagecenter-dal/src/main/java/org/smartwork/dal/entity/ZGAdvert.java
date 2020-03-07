package org.smartwork.dal.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.comm.annotations.ValidUnique;
import org.forbes.comm.constant.SaveValid;
import org.forbes.comm.constant.UpdateValid;
import org.forbes.comm.entity.BaseEntity;

import javax.validation.constraints.NotEmpty;
/**
 * Table: fb_zg_advert
 */
@Data
@ApiModel(description="广告位")
@TableName("fb_zg_advert")
public class ZGAdvert extends BaseEntity {
    /**
     * 广告位编码
     *
     * Table:     fb_zg_advert
     * Column:    ad_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "广告位编码",example="")
    @ValidUnique(column = "ad_code",bizCode = "006001001",bizErrorMsg = "%s编码已经存在")
    @NotEmpty(message="广告位编码为空",groups = {UpdateValid.class, SaveValid.class})
    private String adCode;

    /**
     * 备注说明
     *
     * Table:     fb_zg_advert
     * Column:    remarks
     * Nullable:  true
     */
    @ApiModelProperty(value = "备注说明",example="")
    private String remarks;
}