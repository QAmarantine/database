package net.mypaper.www.controller;

import JwtUtils.JwtUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.mypaper.www.pojo.PaperSearchForm;
import net.mypaper.www.pojo.research_direction;
import net.mypaper.www.pojo.webusers;
import net.mypaper.www.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    PaperService paperService;
    @Autowired
    NoteService noteService;
    @Autowired
    CitingService citingService;
    @Autowired
    PdService pdService;
    @Autowired
    CommentService commentService;
    @Autowired
    ResearchService researchService;


    @GetMapping("/admin")
    public boolean isAccess(@RequestParam("token") String token){
        String uid = JwtUtils.getUidByToken(token);
        return adminService.isAdmin(uid);
    }

    @GetMapping("/admin/viewUser")
    public List<webusers> seeAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/admin/deleteUser")
    public void deleteOneUser(@RequestParam("uid") String uid){
        adminService.deleteOneUser(uid);
    }

    @GetMapping("/admin/giveAccess")
    public void giveAccessToUser(@RequestParam("uid") String uid){
        adminService.giveAccess(uid);
    }

    @GetMapping("/admin/takeAccess")
    public void takeAccessFromUser(@RequestParam("uid") String uid){
        adminService.takeAccess(uid);
    }

    @PostMapping("/admin/viewPaper")
    public JSONArray getPaperList(@RequestBody PaperSearchForm paperSearchForm){
        return JSONArray.
                parseArray(JSON.toJSONString(paperService.searchPaperList(paperSearchForm)));
    }

    @GetMapping("/admin/deletePaper")
    public void  deletePaper(@RequestParam("paperId") String paperId) {
        paperService.deletePaper(paperId);
        noteService.deleteNoteByPaper(paperId);
        pdService.deletePdByPaperId(paperId);
        commentService.deleteCommentsByPaper(paperId);
        citingService.deleteCitingByPaperId(paperId);
    }

    @GetMapping("/admin/viewDirections")
    public List<research_direction> seeDirections(){
        return researchService.getAllRd();
    }
    @GetMapping("/admin/deleteDirection")
    public void deleteOneDirection(@RequestParam("name") String name){
        research_direction rd = researchService.getOneRdByName(name);
        researchService.deleteRd(rd);
    }

    @GetMapping("/admin/addDirection")
    public boolean addOneDirection(@RequestParam("name") String name,
                                   @RequestParam("fatherName") String fatherName){
        research_direction father = researchService.getOneRdByName(fatherName);
        if (father==null) return false;
        research_direction rd = new research_direction();
        rd.setName(name);
        return researchService.addRd(rd,father);
    }

    @GetMapping("/admin/alterDirection")
    public boolean alterOneDirection(@RequestParam("name") String name,
                                     @RequestParam("newName") String newName){
        return researchService.alterRd(name,newName);
    }

}
