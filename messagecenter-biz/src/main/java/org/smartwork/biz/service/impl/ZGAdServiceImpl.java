package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.smartwork.biz.service.IZGAdService;
import org.smartwork.comm.enums.ReleaseStateEnum;
import org.smartwork.dal.entity.ZGAd;
import org.smartwork.dal.mapper.ZGAdMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class ZGAdServiceImpl extends ServiceImpl<ZGAdMapper, ZGAd> implements IZGAdService {

    /***
     * 删除对象
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        ZGAd zgAd = baseMapper.selectById(id);
        zgAd.getReleaseState();
        ReleaseStateEnum.HAVE_RELEASED.getCode();
        if (zgAd.getReleaseState().equals(ReleaseStateEnum.HAVE_RELEASED.getCode())){
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
        List<ZGAd> zgAds =  baseMapper.selectBatchIds(idList);
        long classIdCount =  zgAds.stream().filter(p -> !p.getReleaseState().equals(ReleaseStateEnum.HAVE_RELEASED.getCode())).count();
        if (classIdCount>0){
            throw new ForbesException(BizResultEnum.ADMIN_FLAG_EXISTS.getBizCode()
                    ,String.format(BizResultEnum.ADMIN_FLAG_EXISTS.getBizMessage()));
        }
        boolean delBool =  SqlHelper.retBool(baseMapper.deleteBatchIds(idList));
        return delBool;
    }
}