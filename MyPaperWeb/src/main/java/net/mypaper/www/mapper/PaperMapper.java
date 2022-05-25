package net.mypaper.www.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.mypaper.www.pojo.paper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PaperMapper extends BaseMapper<paper> {
    @Select("<script>" +
            "select paper.paper_id, title, author, paper.uid, conference, publish_date, upload_date, summary," +
            "type " +
            "from paper, paper_dir, webusers , research_direction" +
            "<where>" +
            "paper.paper_id = paper_dir.paper_id and paper.uid=webusers.uid " +
            "and paper_dir.dir_id= research_direction.dir_id" +
            "<if test =\"title != null and title !=''\"> and title like concat('%',#{title},'%')</if>" +
            "<if test =\"author != null and author !=''\"> and author like concat('%',#{author},'%')</if>" +
            "<if test =\"account != null and account !=''\"> and account like concat('%',#{account},'%')</if>" +
            "<if test =\"conference != null and conference !=''\"> and conference like concat('%',#{conference},'%')</if>" +
            "<if test =\"summary != null and summary !=''\"> and summary like concat('%',#{summary},'%')</if>" +
            "<if test =\"type != null and type !=''\"> and type = #{type}</if>" +
            "<if test =\"dir != null and dir !=''\"> and research_direction.name like concat('%',#{dir},'%')</if>" +
            "</where >" +
            "</script>")
    List<paper> searchPaper(@Param("title") String title, @Param("author") String author,@Param("account") String account,@Param("conference") String conference,
                            @Param("summary") String summary, @Param("type") String type, @Param("dir") String dir);
    @Select("<script>" +
            "select paper.* " +
            "from paper, paper_dir" +
            "<where>" +
            "paper.paper_id = paper_dir.paper_id " +
            "and dir_id = #{dirId}" +
            "</where >" +
            "</script>")
    List<paper> getPaperByDirId(@Param("dirId") Integer dirId);



}
