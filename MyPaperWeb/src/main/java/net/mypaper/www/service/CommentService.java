package net.mypaper.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.mypaper.www.pojo.comment;

import java.util.List;

public interface CommentService extends IService<comment> {
    public List<comment> getRootCommentsByPaperid(String paperId);
    public List<comment> getCommentsByPid(String pid);
    public List<comment> getSons(List<comment> parentComments);
    public List<comment> getAllCommentsByPaperId(String paperId);
    public String postRootComment(comment cmt, String uid, String paperId);
    public String postSonComment(comment cmt, String uid, String paperId, String pid);
    public void deleteRootComment(comment cmt, String uid);
    public void deleteCommentsByPaper(String paperId);
}
