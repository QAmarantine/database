package net.mypaper.www.controller;


import net.mypaper.www.pojo.comment;
import net.mypaper.www.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/search/getDetail/{paperId}/comment")
    public String postRootComment(@RequestParam("coontent")String content, @PathVariable("paperId") String paperId,@RequestParam("uid")String uid){
        comment ct = new comment();
        ct.setContent(content);
        return commentService.postRootComment(ct,uid,paperId);
    }

    @GetMapping("/search/getDetail/{paperId}/sonComment")
    public String postSonComment(@RequestParam("coontent")String content,
                               @PathVariable("paperId") String paperId,
                               @RequestParam("uid")String uid,
                               @RequestParam("pid")String pid
    ){
        comment ct = new comment();
        ct.setContent(content);
        return commentService.postSonComment(ct,uid,paperId,pid);
    }

    @GetMapping("/search/getDetail/{paperId}/deleteComment")
    public void deleteComment(@RequestParam("commenetId") String commentId,@RequestParam("uid") String uid){
        comment ct = new comment();
        ct.setCommentId(commentId);
        commentService.deleteRootComment(ct,uid);
    }
}
