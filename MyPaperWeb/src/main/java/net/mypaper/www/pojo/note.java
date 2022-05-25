package net.mypaper.www.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Clob;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class note {
    @TableId(type = IdType.ASSIGN_ID)
    private String note_id;
    private String paper_id;
    public String title;
    private Clob content;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp date;

    public void setContent(Clob content) {
        this.content = content;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public Clob getContent() {
        return content;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "note{" +
                "note_id=" + note_id +
                ", paper_id=" + paper_id +
                ", content=" + content +
                ", title=" + title +
                ", date=" + date +
                '}';
    }
}
