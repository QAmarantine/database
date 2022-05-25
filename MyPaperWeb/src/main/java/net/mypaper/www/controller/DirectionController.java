package net.mypaper.www.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.mypaper.www.pojo.paper;
import net.mypaper.www.pojo.research_direction;
import net.mypaper.www.service.PaperService;
import net.mypaper.www.service.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DirectionController {
    @Autowired
    ResearchService researchService;
    @Autowired
    PaperService paperService;

    @GetMapping("/ResearchDirection")
    public List<research_direction> getDirection(){
        return researchService.getAllRd();
    }

    @GetMapping("/ResearchDirection/{dirId}")
    public List<paper> getPaperByDir(@PathVariable("dirId") Integer dirId){
        return paperService.getPaperByDirId(dirId);
    }
}
