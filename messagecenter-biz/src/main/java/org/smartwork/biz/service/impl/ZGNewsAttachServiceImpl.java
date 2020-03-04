package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGNewsAttachService;
import org.smartwork.dal.entity.ZGNewsAttach;
import org.smartwork.dal.mapper.ZGNewsAttachMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGNewsAttachServiceImpl extends ServiceImpl<ZGNewsAttachMapper, ZGNewsAttach> implements IZGNewsAttachService {
}