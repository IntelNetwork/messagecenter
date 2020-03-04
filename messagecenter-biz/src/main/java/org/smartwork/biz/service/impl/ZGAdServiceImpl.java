package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGAdService;
import org.smartwork.dal.entity.ZGAd;
import org.smartwork.dal.mapper.ZGAdMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGAdServiceImpl extends ServiceImpl<ZGAdMapper, ZGAd> implements IZGAdService {
}