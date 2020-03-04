package org.smartwork.provider;

import io.swagger.annotations.Api;
import org.forbes.provider.BaseProvider;
import org.smartwork.biz.service.IZGNewsClassifyService;
import org.smartwork.dal.entity.ZGNewsClassify;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"新闻分类管理"})
@RestController
@RequestMapping("/zgnewsclassify")
public class ZGNewsClassifyController extends BaseProvider<IZGNewsClassifyService, ZGNewsClassify> {
}