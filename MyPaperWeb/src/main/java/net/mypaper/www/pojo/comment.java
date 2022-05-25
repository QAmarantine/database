package net.mypaper.www.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class comment {
    @TableId(type = IdType.ASSIGN_ID, value = "comment_id")
    private String commentId;
    @TableField(value = "paper_id")
    private String paperId;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp commentTime;
    private String content;
    private String uid;
    private String pid;
    private String status;

    @TableField(exist = false)
    private List<comment> sons;

    public List<comment> getSons() {
        return sons;
    }

    public void setSons(List<comment> sons) {
        this.sons = sons;
    }

    public String getPid() {
        return pid;
    }

    public String getStatus() {
        return status;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaperId(String paper_id) {
        this.paperId = paper_id;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public void setCommentId(String comment_id) {
        this.commentId = comment_id;
    }

    public Timestamp getComment_time() {
        return commentTime;
    }

    public void setComment_time(Timestamp comment_time) {
        this.commentTime = comment_time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "comment{" +
                "comment_id=" + commentId +
                ", paper_id=" + paperId +
                ", comment_time=" + commentTime +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                '}';
    }
}
