package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGNewsAttachService;
import org.smartwork.dal.entity.ZGNewsAttach;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"新闻附件管理"})
@RestController
@RequestMapping("/zgnewsattach")
public class ZGNewsAttachController extends BaseProvider<IZGNewsAttachService, ZGNewsAttach> {
}