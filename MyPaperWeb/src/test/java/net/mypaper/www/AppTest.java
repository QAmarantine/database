package net.mypaper.www;








import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.mypaper.www.mapper.CommentMapper;


import net.mypaper.www.mapper.PaperMapper;
import net.mypaper.www.pojo.paper;
import net.mypaper.www.pojo.research_direction;
import net.mypaper.www.pojo.webusers;
import net.mypaper.www.service.PaperService;
import net.mypaper.www.service.PdService;
import net.mypaper.www.service.UserService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    private PaperService paperService;

    @Test
    public void addUser(){
        System.out.println(paperMapper.searchPaper("","","","","","",""));
    }

}
