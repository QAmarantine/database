package net.mypaper.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mypaper.www.pojo.citing;

import java.util.List;

public interface CitingService extends IService<citing> {
    public void postPaperCiting(String paperId, List<String> citingIds);
    public List<citing> getCitingByPaperId(String paperId);
    public List<citing> getCitingByCitingId(String citingId);
    public void deleteCitingByPaperId(String paperId);
    public void deleteCitingByCitingId(String citingId);
}
