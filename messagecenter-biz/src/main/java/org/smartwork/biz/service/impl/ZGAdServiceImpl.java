package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.smartwork.biz.service.IZGAdService;
import org.smartwork.comm.enums.MsgBizResultEnum;
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
        if (zgAd.getReleaseState().equals(ReleaseStateEnum.HAVE_RELEASED.getCode())){
            throw new ForbesException(MsgBizResultEnum.AD_HAVE_RELEASED_DEL.getBizCode()
                    ,String.format(MsgBizResultEnum.AD_HAVE_RELEASED_DEL.getBizMessage()));
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
        zgAds.stream().forEach(zgAd -> {
            if (zgAd.getReleaseState().equals(ReleaseStateEnum.HAVE_RELEASED.getCode())){
                throw new ForbesException(MsgBizResultEnum.AD_HAVE_RELEASED_DEL.getBizCode()
                        ,String.format(MsgBizResultEnum.AD_HAVE_RELEASED_DEL.getBizMessage()));
            }
        });
        boolean delBool =  SqlHelper.retBool(baseMapper.deleteBatchIds(idList));
        return delBool;
    }
}