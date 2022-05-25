package net.mypaper.www.controller;


import JwtUtils.JwtUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.mypaper.www.pojo.*;


import net.mypaper.www.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class PaperController {
    @Autowired
    PaperService paperService;
    @Autowired
    CommentService commentService;
    @Autowired
    NoteService noteService;
    @Autowired
    CitingService citingService;
    @Autowired
    PdService pdService;
    @Autowired
    ResearchService researchService;

    @PostMapping("/search")
    public JSONArray getPaperList(@RequestBody PaperSearchForm paperSearchForm){
        return JSONArray.
                parseArray(JSON.toJSONString(paperService.searchPaperList(paperSearchForm)));
    }

    @GetMapping("/search/getDetail/{paperId}")
    public PaperPage getDetailPage(@PathVariable("paperId") String paperId){
        System.out.println(paperId);
        paper p = paperService.getPaperById(paperId);
        List<comment> cmt = commentService.getAllCommentsByPaperId(paperId);
        List<note> notes = noteService.getNoteByPaper(paperId);
        PaperPage page =new PaperPage();
        page.buildPaperPage(p,cmt,notes);
        return page;
    }

    @PostMapping("/post")
    public paper postPaper(@RequestBody paper p){
        //p.setUid(JwtUtils.getUidByToken(token));
        return paperService.postPaper(p);
    }



    @PostMapping("/post/Citing")
    public void postCiting(@RequestParam("paperId") String paperId, @RequestParam("citings[]") List<String> citings){
        citingService.postPaperCiting(paperId,citings);
    }

    @PostMapping("/post/addPaperDirection")
    public void addPaperDirection(@RequestParam("paperId") String paperId,@RequestParam("dir[]") List<String> dirs){
        List<research_direction> rds = new ArrayList<>();

        for (String dir:dirs){
            research_direction rd = researchService.getOneRdByName(dir);
            rds.add(rd);
        }
        pdService.postPaperDir(paperId,rds);
    }


}
