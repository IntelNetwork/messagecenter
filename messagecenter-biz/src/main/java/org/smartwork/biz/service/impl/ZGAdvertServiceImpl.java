package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.smartwork.biz.service.IZGAdvertService;
import org.smartwork.comm.constant.AdConstant;
import org.smartwork.comm.enums.ReleaseStateEnum;
import org.smartwork.dal.entity.ZGAd;
import org.smartwork.dal.entity.ZGAdvert;
import org.smartwork.dal.mapper.ZGAdMapper;
import org.smartwork.dal.mapper.ZGAdvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class ZGAdvertServiceImpl extends ServiceImpl<ZGAdvertMapper, ZGAdvert> implements IZGAdvertService {

    @Autowired
    ZGAdMapper zgAdMapper;

    /***
     * 删除对象
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        Integer count=zgAdMapper.selectCount(new QueryWrapper<ZGAd>().eq(AdConstant.ADVERTID,id));
        if (count>0){
            throw new ForbesException(BizResultEnum.ADMIN_FLAG_EXISTS.getBizCode()
                    ,String.format(BizResultEnum.ADMIN_FLAG_EXISTS.getBizMessage()));
        }
        boolean delBool =  SqlHelper.retBool(baseMapper.deleteById(id));
        return delBool;
    }

    /**
     * 批量删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.stream().forEach(id ->{
            Integer count=zgAdMapper.selectCount(new QueryWrapper<ZGAd>().eq(AdConstant.ADVERTID,id));
            if (count>0){
                throw new ForbesException(BizResultEnum.ADMIN_FLAG_EXISTS.getBizCode()
                        ,String.format(BizResultEnum.ADMIN_FLAG_EXISTS.getBizMessage()));
            }
        });
        boolean delBool =  SqlHelper.retBool(baseMapper.deleteBatchIds(idList));
        return delBool;
    }
}