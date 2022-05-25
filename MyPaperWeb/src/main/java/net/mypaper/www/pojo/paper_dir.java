package net.mypaper.www.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class paper_dir {
    private String paper_id;
    private Integer dir_id;

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public Integer getDir_id() {
        return dir_id;
    }

    public void setDir_id(Integer dir_id) {
        this.dir_id = dir_id;
    }

    @Override
    public String toString() {
        return "paper_dir{" +
                "paper_id=" + paper_id +
                ", dir_id=" + dir_id +
                '}';
    }
}
