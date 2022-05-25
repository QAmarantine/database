package net.mypaper.www.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class paper {
    @TableId(type = IdType.ASSIGN_ID, value = "paper_id")
    protected String paperId;
    protected String title;
    protected String author;
    protected String uid;
    protected String conference;
    protected Timestamp publish_date;
    @TableField(fill = FieldFill.INSERT)
    protected Timestamp upload_date;
    protected String summary;
    protected String type;
    protected String content;
    protected String link;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Timestamp getPublish_date() {
        return publish_date;
    }

    public Timestamp getUpload_date() {
        return upload_date;
    }

    public String getAuthor() {
        return author;
    }

    public String getConference() {
        return conference;
    }

    public String getLink() {
        return link;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPublish_date(Timestamp publish_date) {
        this.publish_date = publish_date;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpload_date(Timestamp upload_date) {
        this.upload_date = upload_date;
    }

    @Override
    public String toString() {
        return "paper{" +
                "paper_id=" + paperId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", uid=" + uid +
                ", conference='" + conference + '\'' +
                ", publish_date=" + publish_date +
                ", upload_date=" + upload_date +
                ", summary='" + summary + '\'' +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
