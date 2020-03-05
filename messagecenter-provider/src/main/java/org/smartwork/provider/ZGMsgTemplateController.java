package org.smartwork.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.forbes.comm.enums.BusCodeEnum;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.ResultEnum;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGMsgTemplateService;
import org.smartwork.comm.enums.MsgTypeEnum;
import org.smartwork.dal.entity.ZGMsgTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags={"消息模板管理"})
@RestController
@RequestMapping("/zgmsgtemplate")
public class ZGMsgTemplateController extends BaseProvider<IZGMsgTemplateService, ZGMsgTemplate> {


    /***
     * 消息类型
     * @return
     */
    @ApiOperation("消息类型")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/msg-types",method = RequestMethod.GET)
    public List<ResultEnum> msgTypes(){
        return MsgTypeEnum.resultEnums();
    }


    /***
     *
     * @return
     */
    @ApiOperation("业务编码")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    @RequestMapping(value = "/bus-codes",method = RequestMethod.GET)
    public List<ResultEnum> busCodes(){
        return BusCodeEnum.resultEnums();
    }
}