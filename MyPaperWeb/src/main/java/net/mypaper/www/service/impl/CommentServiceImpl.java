package net.mypaper.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.mypaper.www.mapper.CommentMapper;
import net.mypaper.www.pojo.comment;
import net.mypaper.www.service.AdminService;
import net.mypaper.www.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, comment> implements CommentService {
    @Autowired
    AdminService adminService;

    public List<comment> getRootCommentsByPaperid(String paperId){
        CommentMapper commentMapper = getBaseMapper();
        QueryWrapper<comment> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id", paperId).eq("status", "0");
        return commentMapper.selectList(wrapper);
    }

    public void deleteCommentsByPaper(String paperId){
        CommentMapper commentMapper = getBaseMapper();
        QueryWrapper<comment> wrapper = new QueryWrapper<>();
        wrapper.eq("paper_id", paperId);
        commentMapper.delete(wrapper);
    }

    public List<comment> getCommentsByPid(String pid){
        CommentMapper commentMapper = getBaseMapper();
        QueryWrapper<comment> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid).eq("status", "1");
        return  commentMapper.selectList(wrapper);
    }

    public List<comment> getSons(List<comment> parentComments){
        if (parentComments == null|| parentComments.size()==0)
            return null;
        for(comment parentComment: parentComments){
            String pid = parentComment.getCommentId();
            List<comment> sonComments = getCommentsByPid(pid);
            parentComment.setSons(sonComments);
        }
        return parentComments;
    }

    public List<comment> getAllCommentsByPaperId(String paperId){
        List<comment> comments = getRootCommentsByPaperid(paperId);
        List<comment> paperComments = getSons(comments);
        return paperComments;
    }

    public String postRootComment(comment cmt, String uid, String paperId)
    {
        CommentMapper commentMapper = getBaseMapper();
        QueryWrapper<comment> wrapper = new QueryWrapper<>();
        cmt.setPaperId(paperId);
        cmt.setUid(uid);
        cmt.setStatus("0");
        wrapper.eq("paper_id",paperId).eq("uid",uid).eq("content",cmt.getContent());
        if(!commentMapper.exists(wrapper)) commentMapper.insert(cmt);
        else return null;
        return commentMapper.selectOne(wrapper).getCommentId();
    }

    public String postSonComment(comment cmt, String uid, String paperId, String pid)
    {
        CommentMapper commentMapper = getBaseMapper();
        QueryWrapper<comment> wrapper = new QueryWrapper<>();
        cmt.setPaperId(paperId);
        cmt.setUid(uid);
        cmt.setStatus("1");
        cmt.setPid(pid);
        wrapper.eq("pid",pid).eq("paper_id",paperId).eq("uid",uid).eq("content",cmt.getContent());
        if(!commentMapper.exists(wrapper))commentMapper.insert(cmt);
        else return null;
        return  commentMapper.selectOne(wrapper).getCommentId();
    }

    public void deleteRootComment(comment cmt, String uid)
    {
        CommentMapper commentMapper = getBaseMapper();
        QueryWrapper<comment> wrapper = new QueryWrapper<>();
        wrapper.eq("comment_id",cmt.getCommentId());
        cmt = commentMapper.selectOne(wrapper);
        if(cmt == null) return;
        if(cmt.getUid()==uid||adminService.isAdmin(uid)) {
            List<comment> sonComments = getCommentsByPid(cmt.getCommentId());
            if (!sonComments.isEmpty())
                for (comment sonComment : sonComments)
                    commentMapper.deleteById(sonComment.getCommentId());
            commentMapper.deleteById(cmt);
        }
    }
}
