package net.mypaper.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mypaper.www.mapper.CitingMapper;
import net.mypaper.www.pojo.citing;
import net.mypaper.www.service.CitingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitingServiceImpl extends ServiceImpl<CitingMapper, citing> implements CitingService {
    public void postPaperCiting(String paperId, List<String> citingIds)
    {
        if(citingIds.isEmpty()) return;
        CitingMapper citingMapper =getBaseMapper();
        citing c =new citing();
        c.setPaperId(paperId);
        for(String citingId:citingIds){
            c.setCitingId(citingId);
            citingMapper.insert(c);
        }
    }

    public List<citing> getCitingByPaperId(String paperId)
    {
        CitingMapper citingMapper =getBaseMapper();
        QueryWrapper<citing> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id",paperId);
        return citingMapper.selectList(wrapper);
    }

    public List<citing> getCitingByCitingId(String citingId)
    {
        CitingMapper citingMapper =getBaseMapper();
        QueryWrapper<citing> wrapper = new QueryWrapper<>();
        wrapper.eq("citing_id",citingId);
        return citingMapper.selectList(wrapper);
    }

    public void deleteCitingByPaperId(String paperId)
    {
        CitingMapper citingMapper =getBaseMapper();
        citingMapper.deleteBatchIds(getCitingByPaperId(paperId));
    }

    public void deleteCitingByCitingId(String citingId)
    {
        CitingMapper citingMapper =getBaseMapper();
        citingMapper.deleteBatchIds(getCitingByCitingId(citingId));
    }
}
