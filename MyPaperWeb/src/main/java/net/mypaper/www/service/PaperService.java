package net.mypaper.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mypaper.www.pojo.PaperSearchForm;
import net.mypaper.www.pojo.paper;

import java.util.List;

public interface PaperService extends IService<paper> {
    public paper getPaperById(String paperId);
    public List<paper> getPaperByUid(String uid);
    public paper getPaperByTitle(String title);
    public paper postPaper(paper p);
    public void updatePaperInfo( paper newP);
    public void deletePaper(String paperId);
    public List<paper> searchPaperList(PaperSearchForm p);
    public String getPaperUid(String paperId);
    public List<paper> getPaperByDirId(Integer dirId);
}
