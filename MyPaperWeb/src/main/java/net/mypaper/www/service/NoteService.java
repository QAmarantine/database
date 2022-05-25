package net.mypaper.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mypaper.www.pojo.note;
import net.mypaper.www.pojo.paper;

import java.util.List;

public interface NoteService extends IService<note> {
    public note postNote(String paperId, note nt);
    public note getNoteById(String noteId);
    public void alterNote(note nt);
    public void deleteNoteByNoteId(String noteId);
    public boolean titleExisted(note nt);
    public List<note> getNoteByPaper(String paperId);
    public void deleteNoteByPaper(String paperId);
}
