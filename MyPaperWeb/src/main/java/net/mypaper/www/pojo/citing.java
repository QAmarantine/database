package net.mypaper.www.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class citing {
    @TableField(value = "paper_id")
    private String paperId;
    @TableField(value = "citing_id")
    public String citingId;

    public String getCitingId() {
        return citingId;
    }

    public void setCitingId(String citingId) {
        this.citingId = citingId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paper_id) {
        this.paperId = paper_id;
    }

    @Override
    public String toString() {
        return "citing{" +
                "paper_id=" + paperId +
                ", citing_id=" + citingId +
                '}';
    }
}
