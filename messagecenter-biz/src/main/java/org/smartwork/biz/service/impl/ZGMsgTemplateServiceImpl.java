package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGMsgTemplateService;
import org.smartwork.dal.entity.ZGMsgTemplate;
import org.smartwork.dal.mapper.ZGMsgTemplateMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGMsgTemplateServiceImpl extends ServiceImpl<ZGMsgTemplateMapper, ZGMsgTemplate> implements IZGMsgTemplateService {
}