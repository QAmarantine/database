package net.mypaper.www.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import net.mypaper.www.mapper.ResearchMapper;
import net.mypaper.www.pojo.research_direction;

import java.util.Iterator;
import java.util.List;

public interface ResearchService extends IService<research_direction> {
    public research_direction getParent(research_direction rd);
    public List<research_direction> getDirectChildren(research_direction rd);
    public boolean addRd(research_direction rd, research_direction p);
    public boolean alterRd(String newRd, String oldRd);
    public List<research_direction> getAllRd();
    public research_direction getOneRdByName(String name);
    public boolean deleteRd(research_direction rd);
    public void updateByRgt(List<research_direction> list, int c);
    public void updateByLft(List<research_direction> list, int c);
    public void updateSubtree(research_direction rd);
}
