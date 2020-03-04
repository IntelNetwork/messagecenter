package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGAdvertService;
import org.smartwork.dal.entity.ZGAdvert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"广告位管理"})
@RestController
@RequestMapping("/zgadvert")
public class ZGAdvertController extends BaseProvider<IZGAdvertService, ZGAdvert> {
}