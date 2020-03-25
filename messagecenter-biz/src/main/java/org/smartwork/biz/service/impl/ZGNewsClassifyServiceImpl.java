package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.comm.exception.ForbesException;
import org.smartwork.biz.service.IZGNewsClassifyService;
import org.smartwork.comm.constant.AdConstant;
import org.smartwork.comm.constant.NewsConstant;
import org.smartwork.comm.enums.MsgBizResultEnum;
import org.smartwork.dal.entity.ZGAd;
import org.smartwork.dal.entity.ZGNews;
import org.smartwork.dal.entity.ZGNewsClassify;
import org.smartwork.dal.mapper.ZGNewsClassifyMapper;
import org.smartwork.dal.mapper.ZGNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

@Service
public class ZGNewsClassifyServiceImpl extends ServiceImpl<ZGNewsClassifyMapper, ZGNewsClassify> implements IZGNewsClassifyService {

    @Autowired
    ZGNewsMapper zgNewsMapper;

    /***
     * 删除对象
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        Integer count=zgNewsMapper.selectCount(new QueryWrapper<ZGNews>().eq(NewsConstant.CLASSIFYID,id));
        if (count>0){
            throw new ForbesException(MsgBizResultEnum.NEW_HAVE_RELEASED_DEL.getBizCode()
                    ,String.format(MsgBizResultEnum.NEW_HAVE_RELEASED_DEL.getBizMessage()));
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
            Integer count=zgNewsMapper.selectCount(new QueryWrapper<ZGNews>().eq(NewsConstant.CLASSIFYID,id));
            if (count>0){
                throw new ForbesException(MsgBizResultEnum.NEW_HAVE_RELEASED_DEL.getBizCode()
                        ,String.format(MsgBizResultEnum.NEW_HAVE_RELEASED_DEL.getBizMessage()));
            }
        });
        boolean delBool =  SqlHelper.retBool(baseMapper.deleteBatchIds(idList));
        return delBool;
    }
}