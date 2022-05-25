package net.mypaper.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mypaper.www.mapper.PdMapper;
import net.mypaper.www.pojo.paper_dir;
import net.mypaper.www.pojo.research_direction;
import net.mypaper.www.service.PdService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdServiceImpl extends ServiceImpl<PdMapper, paper_dir> implements PdService {
    public void postPaperDir(String paperId, List<research_direction> rds){
        PdMapper pdMapper = getBaseMapper();
        paper_dir pd = new paper_dir();
        pd.setPaper_id(paperId);
        for(research_direction rd:rds){
            pd.setDir_id(rd.getDirId());
            pdMapper.insert(pd);
        }
    }

    public void deletePdByPaperId(String paperId)
    {
        PdMapper pdMapper = getBaseMapper();
        pdMapper.deleteBatchIds(getPdByPaperId(paperId));
    }

    public void deletePdByDirId(Integer DirId)
    {
        PdMapper pdMapper = getBaseMapper();
        pdMapper.deleteBatchIds(getPdByDirId(DirId));
    }

    public List<paper_dir> getPdByPaperId(String paperId)
    {
        PdMapper pdMapper = getBaseMapper();
        QueryWrapper<paper_dir> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id",paperId);
        return pdMapper.selectList(wrapper);
    }

    public List<paper_dir> getPdByDirId(Integer DirId)
    {
        PdMapper pdMapper = getBaseMapper();
        QueryWrapper<paper_dir> wrapper = new QueryWrapper<>();
        wrapper.eq("dir_id",DirId);
        return pdMapper.selectList(wrapper);
    }
}
