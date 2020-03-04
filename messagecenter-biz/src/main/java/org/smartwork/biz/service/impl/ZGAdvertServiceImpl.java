package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGAdvertService;
import org.smartwork.dal.entity.ZGAdvert;
import org.smartwork.dal.mapper.ZGAdvertMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGAdvertServiceImpl extends ServiceImpl<ZGAdvertMapper, ZGAdvert> implements IZGAdvertService {
}