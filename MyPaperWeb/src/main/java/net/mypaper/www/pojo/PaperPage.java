package net.mypaper.www.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperPage extends paper{
    private List<comment> comments;
    private List<note> notes;

    public void setComments(List<comment> comments) {
        this.comments = comments;
    }

    public void setNotes(List<note> notes) {
        this.notes = notes;
    }

    public void buildPaperPage(paper p, List<comment> cmts, List<note> notes){
        this.setPaperId(p.getPaperId());
        this.setComments(cmts);
        this.setNotes(notes);
        this.setConference(p.getConference());
        this.setTitle(p.getTitle());
        this.setContent(p.getContent());
        this.setAuthor(p.getAuthor());
        this.setLink(p.getLink());
        this.setPublish_date(p.getPublish_date());
        this.setSummary(p.getSummary());
        this.setType(p.getType());
        this.setUid(p.getUid());
    }

    @Override
    public String toString() {
        return "PaperPage{" +
                "comments=" + comments +
                ", notes=" + notes +
                ", paperId='" + paperId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", uid='" + uid + '\'' +
                ", conference='" + conference + '\'' +
                ", publish_date=" + publish_date +
                ", upload_date=" + upload_date +
                ", summary='" + summary + '\'' +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
