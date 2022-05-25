package net.mypaper.www.controller;

import JwtUtils.JwtUtils;
import net.mypaper.www.pojo.paper;
import net.mypaper.www.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PaperService paperService;
    @Autowired
    NoteService noteService;
    @Autowired
    CitingService citingService;
    @Autowired
    PdService pdService;
    @Autowired
    CommentService commentService;

    @GetMapping()
    public List<paper> getMyPaper(@RequestParam("token")String token){
        String uid = JwtUtils.getUidByToken(token);
        return paperService.getPaperByUid(uid);
    }

    @GetMapping("/user/delete")
    public void  deletePaper(@RequestParam("paperId") String paperId) {
        paperService.deletePaper(paperId);
        noteService.deleteNoteByPaper(paperId);
        pdService.deletePdByPaperId(paperId);
        commentService.deleteCommentsByPaper(paperId);
        citingService.deleteCitingByPaperId(paperId);
    }



}
