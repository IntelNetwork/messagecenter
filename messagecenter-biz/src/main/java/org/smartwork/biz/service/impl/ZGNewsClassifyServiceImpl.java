package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGNewsClassifyService;
import org.smartwork.dal.entity.ZGNewsClassify;
import org.smartwork.dal.mapper.ZGNewsClassifyMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGNewsClassifyServiceImpl extends ServiceImpl<ZGNewsClassifyMapper, ZGNewsClassify> implements IZGNewsClassifyService {
}