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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    private PasswordEncoder encoder;
    PostDaoInterface postDao;
    ProfileDaoInterface profileDao;
    HashTagDaoInterface hashTagDao;
    LocalDate today = LocalDate.now();
    List<Post> blogList;
    List<Post> pageList;
    Profile currentProfile;
    boolean isLoggedIn = false;
    int postID;

    @Inject
    public MainController(PostDaoInterface PostDao, ProfileDaoInterface ProfileDao, HashTagDaoInterface HashTagDao, PasswordEncoder encoder) {
        this.postDao = PostDao;
        this.profileDao = ProfileDao;
        this.hashTagDao = HashTagDao;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome(Model model) {
        List<Post> pendingList = new ArrayList();
        blogList = postDao.getAllBlogPosts();
        for (Post currentPost : blogList) {
            if (currentPost.isPending() == true) {
                pendingList.add(currentPost);
            }
        }

        blogList.removeAll(pendingList);
        pageList = postDao.getAllStaticPages();
        Collections.reverse(blogList);
        model.addAttribute("profile", currentProfile);
        model.addAttribute("pageList", pageList);
        model.addAttribute("blogList", blogList);
        model.addAttribute("isLoggedIn", isLoggedIn);

        return "index";
    }

    @RequestMapping(value = "static/addProfilePage", method = RequestMethod.GET)

    public String displayAddProfilePage(Model model, Principal principal) {
        String name = principal.getName();
        
        model.addAttribute("userName", name);

        return "addProfile";
    }
    
    @RequestMapping(value = "/displayRemoveUserPage", method = RequestMethod.GET)

    public String displayRemoveUserPage(Model model, Principal principal) {
        String name = principal.getName();
        
        List<Profile> users =  profileDao.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("userName", name);

        return "removeUser";
    }
    
    

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String displayLoginPage(Model model
    ) {
        return "loginPage";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.GET)
    public String addPost(Model model, Principal principal) {
        String name = principal.getName();
        
        
        model.addAttribute("userName", name);
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "addPost";
    }

    @RequestMapping(value = "addBlogPost", method = RequestMethod.POST)
    public String addBlog(HttpServletRequest request, Model model, Principal principal) {
        String name = principal.getName();
        currentProfile = profileDao.getProfileByUserName(name);
        List<HashTag> hashList = new ArrayList<>();
        HashTag hashTag = new HashTag();
        String[] tags = request.getParameterValues("tags");
        for (int i = 0; i < tags.length; i++) {
            hashTag = hashTagDao.getHashTagByTag(tags[i]);
            if (hashTag == null) {
                HashTag hash = new HashTag();
                hash.setTag(tags[i]);
                hashTagDao.addHashTag(hash);
                hashList.add(hash);
            } else {
                hashList.add(hashTag);
            }
        }
        Post blogPost = new Post();
        blogPost.setBlog(true);
        blogPost.setTitle(request.getParameter("title"));
        blogPost.setDescription(request.getParameter("texteditor"));
        blogPost.setProfile(currentProfile);
        blogPost.setHashTags(hashList);
        blogPost.setDate(today);
        blogPost.setPicture(request.getParameter("picture"));
        if (currentProfile.isAdminID() == false) {
            blogPost.setPending(true);
        } else {

            blogPost.setPending(false);
        }
        postDao.addPost(blogPost);
        

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("profile", currentProfile);
        return "redirect:/";

    }

    @RequestMapping(value = "goLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model
    ) {
        String userName = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        try {
            Profile profile = profileDao.getProfileByUserName(userName);
            if (profile.getPassword().equals(password)) {
                currentProfile = profile;
                isLoggedIn = true;
                return "redirect:/";
            }
        } catch (EmptyResultDataAccessException e) {
            return "redirect:/loginPage";
        }

        return "redirect:/loginPage";
    }
    
    @RequestMapping(value = "removeUser", method = RequestMethod.POST)
    public String removeUser(HttpServletRequest request, Model model
    ) {
        int userID = parseInt(request.getParameter("userSelect"));
        
        List<Post> userPosts = postDao.getPostsByProfileID(userID);
        
        for(Post post:userPosts){
            postDao.deletePost(post.getPostID());
        }
        
        profileDao.deleteProfile(userID);
        

        return "redirect:/displayRemoveUserPage";
    }

    @RequestMapping(value = "static/createProfile", method = RequestMethod.POST)
    public String createProfile(HttpServletRequest request, Model model
    ) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String hashPw = encoder.encode(password);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthday;
        try {
        String retrievedDate = request.getParameter("datepicker");
        birthday = LocalDate.parse(retrievedDate, formatter);
        } catch (DateTimeParseException ex) {
            birthday = null;
        }

        Profile profile = new Profile();
        profile.setUserName(userName);
        profile.setPassword(hashPw);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setEmail(email);
        profile.setBirthday(birthday);
        profile.setAdminID(false);

        profileDao.addProfile(profile);

        return "redirect:/loginPage";
    }

    @RequestMapping(value = "categorizeBlogPosts", method = RequestMethod.GET)
    public String categorizeBlogPosts(HttpServletRequest request, Model model
    ) {
        HashTag hashTag = new HashTag();
        hashTag = hashTagDao.getHashTagByTag(request.getParameter("hashTagSearch"));
        if (hashTag == null) {
            return "redirect:/";
        } else {
            blogList = postDao.getPostsByHashTagID(hashTag.getHashTagID());
            model.addAttribute("profile", currentProfile);
            model.addAttribute("blogList", blogList);
            model.addAttribute("isLoggedIn", isLoggedIn);
            return "index";
        }
    }

    @RequestMapping(value = "goHome", method = RequestMethod.GET)
    public String goHome(Model model
    ) {
        blogList = postDao.getAllBlogPosts();

        return "redirect:/";
    }

    @RequestMapping(value = "static/deletePost", method = RequestMethod.POST)
    public String deleteBlog(HttpServletRequest request, Principal principal) {
        Post post = new Post();
        String name = principal.getName();
        currentProfile = profileDao.getProfileByUserName(name);
        post = postDao.getPostByID(Integer.parseInt(request.getParameter("deleteButton")));
        if (currentProfile.isAdminID() == true) {
            postDao.deletePost(Integer.parseInt(request.getParameter("deleteButton")));
        } else {
            post.setPending(true);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "updatePost", method = RequestMethod.GET)
    public String updatePost(HttpServletRequest request, Model model, Principal principal) {
        Post post = new Post();
        post = postDao.getPostByID(Integer.parseInt(request.getParameter("editButton")));
        String name = principal.getName();
        
        model.addAttribute("userName", name);
        model.addAttribute("post", post);
        return "editPost";
    }

    @RequestMapping(value = "editPost", method = RequestMethod.POST)
    public String editBlog(HttpServletRequest request, Model model, Principal principal) {
        Post post = postDao.getPostByID(Integer.parseInt(request.getParameter("id")));
        String name = principal.getName();
        currentProfile = profileDao.getProfileByUserName(name);
        post.setTitle(request.getParameter("title"));
        post.setDescription(request.getParameter("texteditor"));
        if (currentProfile.isAdminID() == false) {
            post.setPending(true);
        }
        postDao.editPost(post);

        return "redirect:/";
    }

    @RequestMapping(value = "/static/pendingList", method = RequestMethod.GET)
    public String displayPendingList(HttpServletRequest request, Model model, Principal principal) {
        List<Post> pendingList = new ArrayList();
        blogList = postDao.getAllBlogPosts();
        for (Post currentPost : blogList) {
            if (currentPost.isPending() == false) {
                pendingList.add(currentPost);
            }
        }
        blogList.removeAll(pendingList);

        String name = principal.getName();
        
        model.addAttribute("userName", name);
        model.addAttribute("blogList", blogList);
        return "pending";
    }

    @RequestMapping(value = "static/approvePost", method = RequestMethod.POST)
    public String approvePost(HttpServletRequest request, Model model
    ) {
        Post post = postDao.getPostByID(Integer.parseInt(request.getParameter("approveButton")));
        post.setPending(false);
        postDao.editPost(post);

        return "redirect:/";
    }

    @RequestMapping(value = "displayBlogPost", method = RequestMethod.POST)
    public String displayBlogPost(HttpServletRequest request, Model model) {
        postID = parseInt(request.getParameter("blogInput"));
        return "redirect:/displayBlogPostPage";
    }

    @RequestMapping(value = "/displayBlogPostPage", method = RequestMethod.GET)
    public String displayBlogPostPage(HttpServletRequest request, Model model, Principal principal) {
        String name = "";
        try {
        name = principal.getName();
        }catch (NullPointerException ex) {
            //Catch if no one is signed in...
        }
        
        
        model.addAttribute("userName", name);
        model.addAttribute("pageList", pageList);
        model.addAttribute("post", postDao.getPostByID(postID));
        return "displayBlogPost";
    }

    @RequestMapping(value = "logOut", method = RequestMethod.GET)
    public String logOut(Model model) {
        isLoggedIn = false;

        return "redirect:/";
    }

    @RequestMapping(value = "/static/account", method = RequestMethod.GET)
    public String displayAccount(HttpServletRequest request, Model model, Principal principal) {
        String name = principal.getName();
        currentProfile = profileDao.getProfileByUserName(name);

        model.addAttribute("profile", currentProfile);
        return "account";
    }
    
    @RequestMapping(value="static/saveAccount", method = RequestMethod.GET)
    public String saveAccount(HttpServletRequest request, Model model, Principal principal) {
        String name = principal.getName();
        Profile profile = profileDao.getProfileByUserName(name);
        profile.setFirstName(request.getParameter("firstName"));
        profile.setLastName(request.getParameter("lastName"));
        profileDao.updateProfile(profile);
        
        return "static/account";
    }

}
