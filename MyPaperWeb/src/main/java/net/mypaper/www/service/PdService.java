package net.mypaper.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mypaper.www.pojo.paper_dir;
import net.mypaper.www.pojo.research_direction;

import java.util.List;

public interface PdService extends IService<paper_dir> {
     public void postPaperDir(String paperId, List<research_direction> rds);
     public void deletePdByPaperId(String paperId);
     public void deletePdByDirId(Integer DirId);
     public List<paper_dir> getPdByPaperId(String paperId);
     public List<paper_dir> getPdByDirId(Integer DirId);
}
