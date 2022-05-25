package net.mypaper.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mypaper.www.mapper.ResearchMapper;
import net.mypaper.www.pojo.research_direction;
import net.mypaper.www.service.ResearchService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ResearchServiceImpl extends ServiceImpl<ResearchMapper, research_direction> implements ResearchService {
    public research_direction getParent(research_direction rd)
    {
        //ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        wrapper.lt("lft",rd.getLft()).gt("rgt",rd.getRgt())
                .eq("level",rd.getLevel()-1);
        return  getOne(wrapper);
    }

    public List<research_direction> getDirectChildren(research_direction rd)
    {
        ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        wrapper.gt("lft",rd.getLft()).lt("rgt",rd.getRgt())
                .eq("level",rd.getLevel()+1);
        return  researchMapper.selectList(wrapper);
    }

    public boolean addRd(research_direction rd, research_direction p)  //根据名字和父研究方向插入研究方向
    {
        ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        int  r = p.getRgt();
        rd.setRgt(r+1);
        rd.setLft(r);
        rd.setLevel(p.getLevel()+1);

        wrapper.eq("name",rd.getName());
        if(getOne(wrapper)!=null) return  false;
        wrapper.clear();

        wrapper.gt("lft",r);
        List<research_direction> queueRd = researchMapper.selectList(wrapper);
        updateByLft(queueRd, 2);
        wrapper.clear();

        wrapper.ge("rgt",r);
        List<research_direction> rl = researchMapper.selectList(wrapper);
        updateByRgt(rl, 2);


        researchMapper.insert(rd);
        return true;
    }

    public boolean alterRd(String newRd, String oldRd)
    {
        ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        wrapper.eq("name", newRd);
        research_direction old = getOne(wrapper);
        if (old==null) return false;
        old.setName(newRd);
        wrapper.clear();
        wrapper.eq("dir_id", old.getDirId());
        researchMapper.update(old,wrapper);
        return true;
    }

    public List<research_direction> getAllRd()
    {
        ResearchMapper researchMapper = getBaseMapper();
        return researchMapper.selectList(null);
    }

    public research_direction getOneRdByName(String name)
    {
        ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        wrapper.eq("name", name);
        return getOne(wrapper);
    }

    public boolean deleteRd(research_direction rd)
    {
        ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        wrapper.eq("name",rd.getName());
        rd = getOneRdByName(rd.getName());
        if(rd==null||rd.getLft()==1) return false;
        int l =rd.getLft(), r = rd.getRgt();

        wrapper.clear();
        wrapper.gt("lft",r);

        List<research_direction> listRd = researchMapper.selectList(wrapper);
        updateByLft(listRd, -2);
        wrapper.clear();

        wrapper.gt("rgt",r);
        List<research_direction> rl = researchMapper.selectList(wrapper);
        updateByRgt(rl, -2);
        wrapper.clear();

        updateSubtree(rd);

        wrapper.clear();
        wrapper.eq("dir_id",rd.getDirId());
        researchMapper.delete(wrapper);
        return true;
    }

    public void updateByLft(List<research_direction> list, int c)
    {
        if(list.isEmpty()) return;
        ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        Iterator<research_direction> iterator = list.listIterator();
        while (iterator.hasNext()){
            research_direction temp =iterator.next();
            temp.setLft(temp.getLft()+c);
            wrapper.clear();
            wrapper.eq("name",temp.getName());
            researchMapper.update(temp,wrapper);
        }
    }

    public void updateByRgt(List<research_direction> list, int c)
    {
        if(list.isEmpty()) return;
        ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        Iterator<research_direction> iterator = list.listIterator();

        while (iterator.hasNext()){
            research_direction temp =iterator.next();
            temp.setRgt(temp.getRgt()+c);
            wrapper.clear();
            wrapper.eq("name",temp.getName());
            researchMapper.update(temp,wrapper);
        }
    }

    public void updateSubtree(research_direction rd)
    {
        ResearchMapper researchMapper = getBaseMapper();
        QueryWrapper<research_direction> wrapper =new QueryWrapper<>();
        int l=rd.getLft(),r=rd.getRgt();
        wrapper.gt("lft",l).lt("rgt",r);
        List<research_direction> list = researchMapper.selectList(wrapper);
        if(list.isEmpty()) return;
        Iterator<research_direction> iterator = list.listIterator();
        while (iterator.hasNext()){
            research_direction temp =iterator.next();
            temp.setLft(temp.getLft()-1);
            temp.setRgt(temp.getRgt()-1);
            wrapper.clear();
            wrapper.eq("dir_id",temp.getDirId());
            researchMapper.update(temp,wrapper);
        }
    }

}
