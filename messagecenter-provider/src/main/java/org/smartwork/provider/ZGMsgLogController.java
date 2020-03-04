package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGMsgLogService;
import org.smartwork.dal.entity.ZGMsgLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"消息日志管理"})
@RestController
@RequestMapping("/zgmsglog")
public class ZGMsgLogController extends BaseProvider<IZGMsgLogService, ZGMsgLog> {
}