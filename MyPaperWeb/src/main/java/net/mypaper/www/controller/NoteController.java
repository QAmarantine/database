package net.mypaper.www.controller;

import JwtUtils.JwtUtils;
import net.mypaper.www.pojo.note;
import net.mypaper.www.service.AdminService;
import net.mypaper.www.service.NoteService;
import net.mypaper.www.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {
    @Autowired
    NoteService noteService;
    @Autowired
    PaperService paperService;
    @Autowired
    AdminService adminService;

    @GetMapping("/search/getDetail/{paperId}/{noteId}")
    public note getNote(@PathVariable("noteId")String noteId){
        return noteService.getNoteById(noteId);
    }

    @GetMapping("/search/getDetail/{paperId}/postNote")
    public note postNote(@PathVariable("paperId")String paperId,note nt,@RequestParam("token") String token){
        String uid = JwtUtils.getUidByToken(token);
        String paperUid = paperService.getPaperUid(paperId);
        if(paperUid != uid || paperUid == null) return null;
        return noteService.postNote(paperId,nt);
    }

    @GetMapping("/search/getDetail/{paperId}/{noteId}/delete")
    public void deleteNote(@RequestParam("token") String token, @PathVariable("noteId")String noteId,@PathVariable("paperId")String paperId){
        String uid = JwtUtils.getUidByToken(token);
        String paperUid = paperService.getPaperUid(paperId);
        if((paperUid == uid && paperUid != null)||adminService.isAdmin(uid) )
        noteService.deleteNoteByNoteId(noteId);
    }
}
