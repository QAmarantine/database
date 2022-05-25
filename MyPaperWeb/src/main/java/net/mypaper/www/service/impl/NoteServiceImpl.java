package net.mypaper.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mypaper.www.mapper.NoteMapper;
import net.mypaper.www.pojo.note;
import net.mypaper.www.pojo.paper;
import net.mypaper.www.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, note> implements NoteService {

    public note postNote(String paperId, note nt)
    {
        NoteMapper noteMapper = this.getBaseMapper();
        QueryWrapper<note> wrapper = new QueryWrapper<>();
        nt.setPaper_id(paperId);
        noteMapper.insert(nt);
        wrapper.eq("paper_id",nt.getPaper_id()).eq("title",nt.getTitle());
        nt = noteMapper.selectOne(wrapper);
        return nt;
    }

    public note getNoteById(String noteId)
    {
        NoteMapper noteMapper = this.getBaseMapper();
        QueryWrapper<note> wrapper = new QueryWrapper<>();
        wrapper.eq("note_id",noteId);
        return noteMapper.selectOne(wrapper);
    }

    public List<note> getNoteByPaper(String paperId)
    {
        NoteMapper noteMapper = this.getBaseMapper();
        QueryWrapper<note> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id",paperId);
        return noteMapper.selectList(wrapper);
    }

    public void deleteNoteByPaper(String paperId){
        NoteMapper noteMapper = this.getBaseMapper();
        QueryWrapper<note> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id",paperId);
        noteMapper.delete(wrapper);
    }

    public void alterNote(note nt)
    {
        NoteMapper noteMapper = this.getBaseMapper();
        QueryWrapper<note> wrapper = new QueryWrapper<>();
        wrapper.eq("note_id",nt.getNote_id());
        noteMapper.update(nt,wrapper);
    }

    public void deleteNoteByNoteId(String noteId)
    {
        NoteMapper noteMapper = this.getBaseMapper();
        QueryWrapper<note> wrapper = new QueryWrapper<>();
        wrapper.eq("note_id",noteId);
        noteMapper.delete(wrapper);
    }

    public boolean titleExisted(note nt)
    {
        NoteMapper noteMapper = this.getBaseMapper();
        QueryWrapper<note> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id",nt.getPaper_id()).eq("title",nt.getTitle());
        nt = noteMapper.selectOne(wrapper);
        if (nt!=null) return true;
        return false;
    }




}
