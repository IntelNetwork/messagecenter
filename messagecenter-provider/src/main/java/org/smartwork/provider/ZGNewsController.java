package org.smartwork.provider;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.Result;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGNewsService;
import org.smartwork.comm.enums.MsgBizResultEnum;
import org.smartwork.comm.enums.ReleaseStateEnum;
import org.smartwork.dal.entity.ZGAd;
import org.smartwork.dal.entity.ZGNews;
import org.springframework.web.bind.annotation.*;

@Api(tags={"新闻管理"})
@RestController
@RequestMapping("/zgnews")
public class ZGNewsController extends BaseProvider<IZGNewsService, ZGNews> {


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
    @RequestMapping(value = "/change-release-state",method = RequestMethod.PUT)
    public Result<ZGNews> changeReleaseState(@PathVariable Long id, @RequestParam(value = "releaseState") Integer releaseState){
        ZGNews news = baseService.getById(id);
        Result<ZGNews> result = new Result<ZGNews>();
        if(ConvertUtils.isEmpty(news)){
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
                && !ReleaseStateEnum.NOT_ISSUE.getCode().equals(news.getReleaseState())){
            result.setBizCode(MsgBizResultEnum.N_RELEASE_OR_CANCELLED.getBizCode());
            result.setMessage(String.format(MsgBizResultEnum.N_RELEASE_OR_CANCELLED.getBizFormateMessage(),news.getTitle()));
            return result;
        }
        if(ReleaseStateEnum.CANCELLED.getCode().equals(releaseState)
                && ReleaseStateEnum.CANCELLED.getCode().equals(news.getReleaseState())){
            result.setBizCode(MsgBizResultEnum.N_CANCELLED.getBizCode());
            result.setMessage(String.format(MsgBizResultEnum.N_CANCELLED.getBizFormateMessage(),news.getTitle()));
            return result;
        }
        baseService.update(new UpdateWrapper<ZGNews>().set(true,"release_state",releaseState));
        return result;
    }
}