/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.allstarblog;
import com.sg.dao.HashTagDaoInterface;
import com.sg.dao.PostDaoInterface;
import com.sg.dao.ProfileDaoInterface;
import com.sg.model.HashTag;
import com.sg.model.Post;
import com.sg.model.Profile;
import static java.lang.Integer.parseInt;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/static"})
public class StaticController {
    PostDaoInterface postDao;
    ProfileDaoInterface profileDao;
    HashTagDaoInterface hashTagDao;
    LocalDate today = LocalDate.now();
    List<Post> postList;
    Profile currentProfile;
    boolean isLoggedIn = false;
    List<Post> staticPages;
    List<Post> allStaticPages;

    @Inject
    public StaticController(PostDaoInterface PostDao, ProfileDaoInterface ProfileDao, HashTagDaoInterface HashTagDao) {
        this.postDao = PostDao;
        this.profileDao = ProfileDao;
        this.hashTagDao = HashTagDao;
    }
    
    
    @RequestMapping(value="addStaticPost", method=RequestMethod.GET)
    public String addStaticPage(Model model, Principal principal) {
        String name = principal.getName();
        
        model.addAttribute("userName", name);
        return "addStaticPost";
    }
    
    

    @RequestMapping(value="displayStaticPage", method=RequestMethod.POST)
    public String displayStaticPage(HttpServletRequest request, Model model, Principal principal) {
        int hashID = parseInt(request.getParameter("staticPage"));
        List<Post> staticPages = postDao.getPostsByHashTagID(hashID);
        Post staticPage=new Post();
        for(Post post:staticPages){
            staticPage=post;
        }
        try{String name = principal.getName();
        model.addAttribute("userName", name);
        }catch(NullPointerException e){
            
        }
        
        allStaticPages = postDao.getAllStaticPages();
        model.addAttribute("page",staticPage);
        model.addAttribute("pageList",allStaticPages);

        
        return "staticPage";
    }
    
    @RequestMapping(value = "submitStaticPost", method = RequestMethod.POST)
    public String addStaticPost(HttpServletRequest request, Model model, Principal principal) {
        //this will need to go
        String name = principal.getName();
        currentProfile=profileDao.getProfileByUserName(name);
        List<HashTag> hashList = new ArrayList<>();
        HashTag hashTag = new HashTag();
        String link = request.getParameter("staticLink");
        hashTag.setTag(link);
        hashTagDao.addHashTag(hashTag);
        hashList.add(hashTag);
        Post staticPage = new Post();
        staticPage.setBlog(false);
        staticPage.setPending(Boolean.parseBoolean(request.getParameter("pending")));
        staticPage.setDescription(request.getParameter("texteditor"));
        staticPage.setProfile(currentProfile);
        staticPage.setHashTags(hashList);
        staticPage.setDate(today);
        postDao.addPost(staticPage);

        return "redirect:/";
    }
}
