package org.smartwork.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.forbes.comm.utils.ConvertUtils;
import org.smartwork.biz.service.IZGNewsService;
import org.smartwork.dal.entity.ZGNews;
import org.smartwork.dal.entity.ZGNewsAttach;
import org.smartwork.dal.mapper.ZGNewsAttachMapper;
import org.smartwork.dal.mapper.ZGNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class ZGNewsServiceImpl extends ServiceImpl<ZGNewsMapper, ZGNews> implements IZGNewsService {


    @Autowired
    ZGNewsAttachMapper newsAttachMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean save(ZGNews news) {
        boolean isSave = SqlHelper.retBool(baseMapper.insert(news));
        List<ZGNewsAttach> newsAttachs = news.getNewsAttachs();
        if(ConvertUtils.isNotEmpty(newsAttachs)){
            Long newsId = news.getId();
            newsAttachs.forEach(newsAttach -> {
                newsAttach.setNewsId(newsId);
                newsAttachMapper.insert(newsAttach);
            });
        }
        return isSave;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updateById(ZGNews news) {
        boolean isUpdate =  SqlHelper.retBool(baseMapper.updateById(news));
        Long newsId = news.getId();
        newsAttachMapper.delete(new QueryWrapper<ZGNewsAttach>().eq("news_id",newsId));
        List<ZGNewsAttach> newsAttachs = news.getNewsAttachs();
        if(ConvertUtils.isNotEmpty(newsAttachs)){
            newsAttachs.forEach(newsAttach -> {
                newsAttach.setNewsId(newsId);
                newsAttachMapper.insert(newsAttach);
            });
        }
        return isUpdate;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean removeById(Serializable id) {
        boolean isDel =  SqlHelper.retBool(baseMapper.deleteById(id));
        newsAttachMapper.delete(new QueryWrapper<ZGNewsAttach>().eq("news_id",id));
        return isDel;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        boolean isDel =  CollectionUtils.isEmpty(idList) ? false : SqlHelper.retBool(baseMapper.deleteBatchIds(idList));
        newsAttachMapper.delete(new QueryWrapper<ZGNewsAttach>().in("news_id",idList));
        return isDel;
    }
}