package net.mypaper.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mypaper.www.mapper.PaperMapper;
import net.mypaper.www.pojo.PaperSearchForm;
import net.mypaper.www.pojo.paper;
import net.mypaper.www.service.PaperService;
import net.mypaper.www.service.PdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, paper> implements PaperService {
    @Autowired
    PdService pdService;

    public paper getPaperById(String paperId){
        PaperMapper paperMapper = getBaseMapper();
        QueryWrapper<paper> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id",paperId);
        return paperMapper.selectOne(wrapper);
    }

    public List<paper> getPaperByUid(String uid){
        PaperMapper paperMapper = getBaseMapper();
        QueryWrapper<paper> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        return paperMapper.selectList(wrapper);
    }

    public paper getPaperByTitle(String title)
    {
        PaperMapper paperMapper = getBaseMapper();
        QueryWrapper<paper> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        return paperMapper.selectOne(wrapper);
    }

    public paper postPaper(paper p)
    {
        PaperMapper paperMapper = getBaseMapper();
        QueryWrapper<paper> wrapper = new QueryWrapper<>();
        wrapper.eq("title",p.getTitle());
        if(paperMapper.selectOne(wrapper)!=null||p.getTitle()==null) return null;
        paperMapper.insert(p);
        return paperMapper.selectOne(wrapper);
    }

    public void updatePaperInfo( paper newP)
    {
        PaperMapper paperMapper = getBaseMapper();
        paperMapper.updateById(newP);
    }

    public void deletePaper(String paperId)
    {
        PaperMapper paperMapper = getBaseMapper();
        QueryWrapper<paper> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id", paperId);
        paperMapper.delete(wrapper);
    }

    public List<paper> searchPaperList(PaperSearchForm p){
        PaperMapper paperMapper = getBaseMapper();
        return paperMapper.searchPaper(p.getTitle(),
                p.getAuthor(), p.getAccount(), p.getConference(),
                p.getSummary(),p.getType(),p.getDir());
    }

    public String getPaperUid(String paperId){
        PaperMapper paperMapper = getBaseMapper();
        QueryWrapper<paper> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id", paperId);
        paper p = paperMapper.selectOne(wrapper);
        if (p==null) return null;
        return p.getUid();
    }

    public List<paper> getPaperByDirId(Integer dirId){
        PaperMapper paperMapper = getBaseMapper();
        return paperMapper.getPaperByDirId(dirId);
    }


}
