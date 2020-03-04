package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.smartwork.biz.service.IZGMsgLogService;
import org.smartwork.dal.entity.ZGMsgLog;
import org.smartwork.dal.mapper.ZGMsgLogMapper;
import org.springframework.stereotype.Service;

@Service
public class ZGMsgLogServiceImpl extends ServiceImpl<ZGMsgLogMapper, ZGMsgLog> implements IZGMsgLogService {
}