package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.*;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.ResultEnum;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGAdService;
import org.smartwork.comm.enums.AdTypeEnum;
import org.smartwork.comm.enums.MsgBizResultEnum;
import org.smartwork.comm.enums.ReleaseStateEnum;
import org.smartwork.dal.entity.ZGAd;
import org.smartwork.dal.entity.ZGNews;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={"广告管理"})
@RestController
@RequestMapping("/zgad")
public class ZGAdController extends BaseProvider<IZGAdService, ZGAd> {


    /***
     * 发布状态
     * @return
     */
    @ApiOperation("发布状态")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/releasestates",method = RequestMethod.GET)
    public  List<ResultEnum> releaseStates(){
        return ReleaseStateEnum.resultEnums();
    }



    /***
     * 发布状态
     * @return
     */
    @ApiOperation("广告类型")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/ad-types",method = RequestMethod.GET)
    public  List<ResultEnum> adTypes(){
        return AdTypeEnum.resultEnums();
    }

    /***
     *
     * @param id
     * @param releaseState
     * @return
     */
    @ApiOperation("改变发布状态")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/change-release-state/{id}",method = RequestMethod.PUT)
    public Result<ZGAd> changeReleaseState(@PathVariable Long id,@RequestParam(value = "releaseState") Integer releaseState){
        ZGAd ad = baseService.getById(id);
        Result<ZGAd> result = new Result<ZGAd>();
        if(ConvertUtils.isEmpty(ad)){
            result.setBizCode(BizResultEnum.ENTITY_EMPTY.getBizCode());
            result.setMessage(BizResultEnum.ENTITY_EMPTY.getBizMessage());
            return result;
        }
        if(!ReleaseStateEnum.existsCode(releaseState)){
            result.setBizCode(MsgBizResultEnum.RELEASE_STATE_NOT_EXISTS.getBizCode());
            result.setMessage(String.format(MsgBizResultEnum.RELEASE_STATE_NOT_EXISTS.getBizFormateMessage(),releaseState));
            return result;
        }
        // 发布
        if(ReleaseStateEnum.HAVE_RELEASED.getCode().equals(releaseState)
                && !ReleaseStateEnum.NOT_ISSUE.getCode().equals(ad.getReleaseState())){
            result.setBizCode(MsgBizResultEnum.RELEASE_OR_CANCELLED.getBizCode());
            result.setMessage(String.format(MsgBizResultEnum.RELEASE_OR_CANCELLED.getBizFormateMessage(),ad.getName()));
            return result;
        }
        if(ReleaseStateEnum.CANCELLED.getCode().equals(releaseState)
                && ReleaseStateEnum.CANCELLED.getCode().equals(ad.getReleaseState())){
            result.setBizCode(MsgBizResultEnum.CANCELLED.getBizCode());
            result.setMessage(String.format(MsgBizResultEnum.CANCELLED.getBizFormateMessage(),ad.getName()));
            return result;
        }
        baseService.update(new UpdateWrapper<ZGAd>().set("release_state",releaseState)
                .eq("id",ad.getId()));
        return result;
    }
    
}