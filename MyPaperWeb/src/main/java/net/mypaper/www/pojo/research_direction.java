package net.mypaper.www.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class research_direction {

    @TableId(value = "dir_id", type = IdType.AUTO)
    private Integer dirId;
    private int lft;
    private int rgt;
    private String name;
    private int level;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setDirId(Integer dir_id) {
        this.dirId = dir_id;
    }

    public Integer getDirId() {
        return dirId;
    }

    public String getName() {
        return name;
    }

    public int getLft() {
        return lft;
    }

    public int getRgt() {
        return rgt;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    @Override
    public String toString() {
        return "research_direction{" +
                "dir_id=" + dirId +
                ", lft=" + lft +
                ", rgt=" + rgt +
                ", name='" + name + '\'' +
                '}';
    }
}
