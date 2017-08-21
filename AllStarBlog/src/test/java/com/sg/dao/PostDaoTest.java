/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.model.HashTag;
import com.sg.model.Post;
import com.sg.model.Profile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Becca
 */
public class PostDaoTest {
    
    PostDaoInterface postDao;
    ProfileDaoInterface profileDao;
    HashTagDaoInterface hashTagDao;
    
    public PostDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        postDao = ctx.getBean("PostDao", PostDaoInterface.class);
        profileDao = ctx.getBean("ProfileDao", ProfileDaoInterface.class);
        hashTagDao = ctx.getBean("HashTagDao", HashTagDaoInterface.class);
        
        //Delete All Posts
        List<Post> postList = postDao.getAllBlogPosts();
        for (Post currentPost : postList) {
            postDao.deletePost(currentPost.getPostID());
        }
        
        //Delete All Profiles
        List<Profile> profileList = profileDao.getAllProfiles();
        for (Profile currentProfile : profileList) {
            profileDao.deleteProfile(currentProfile.getProfileID());
        }
        
        //Delete All HashTags
        List<HashTag> hList = hashTagDao.getAllHashTags();
        for (HashTag currentHash : hList) {
            hashTagDao.deleteHashTag(currentHash.getHashTagID());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPost method, of class PostDao.
     */
    @Test
    public void testAddPost() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();
        hash.setTag("Heh");
        
        hashTagDao.addHashTag(hash);
        hList.add(hash);
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(date);
        profile.setAdminID(true);
        
        profileDao.addProfile(profile);
        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("post");
        post.setPending(false);
        post.setBlog(true);
        post.setDate(today);
        post.setProfile(profile);
        post.setHashTags(hList);
        post.setPicture("picture");
        
        postDao.addPost(post);
        Post fromDao = postDao.getPostByID(post.getPostID());
        assertEquals(fromDao, post);
    }

    /**
     * Test of deletePost method, of class PostDao.
     */
    @Test
    public void testDeletePost() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();
        hash.setTag("Heh");
        
        hashTagDao.addHashTag(hash);
        hList.add(hash);
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(date);
        profile.setAdminID(true);
        
        profileDao.addProfile(profile);
        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("post");
        post.setPending(false);
        post.setBlog(true);
        post.setDate(today);
        post.setProfile(profile);
        post.setHashTags(hList);
        post.setPicture("picture");
        
        postDao.addPost(post);
        Post fromDao = postDao.getPostByID(post.getPostID());
        assertEquals(fromDao, post);
        postDao.deletePost(post.getPostID());
        assertNull(postDao.getPostByID(post.getPostID()));
    }

    /**
     * Test of editPost method, of class PostDao.
     */
    @Test
    public void testEditPost() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();
        hash.setTag("Heh");
        
        hashTagDao.addHashTag(hash);
        hList.add(hash);
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(date);
        profile.setAdminID(true);
        
        profileDao.addProfile(profile);
        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("post");
        post.setPending(false);
        post.setBlog(true);
        post.setDate(today);
        post.setProfile(profile);
        post.setHashTags(hList);
        post.setPicture("picture");
        Post post2 = new Post();
        post2.setTitle("ha");
        post2.setDescription("post2");
        post2.setPending(true);
        post2.setBlog(true);
        post2.setDate(date);
        post2.setProfile(profile);
        post2.setHashTags(hList);
        post2.setPicture("picture");
        
        postDao.addPost(post);
        post2.setPostID(post.getPostID());
        Post fromDao = postDao.getPostByID(post.getPostID());
        assertEquals(fromDao, post);
        postDao.editPost(post2);
        fromDao = postDao.getPostByID(post.getPostID());
        assertEquals(fromDao, post2);
    }

    /**
     * Test of getAllPostsID method, of class PostDao.
     */
    @Test
    public void testGetAllPostsID() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();
        hash.setTag("Heh");
        
        hashTagDao.addHashTag(hash);
        hList.add(hash);
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(date);
        profile.setAdminID(true);
        
        profileDao.addProfile(profile);
        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("post");
        post.setPending(false);
        post.setBlog(true);
        post.setDate(today);
        post.setProfile(profile);
        post.setHashTags(hList);
        post.setPicture("picture");
        Post post2 = new Post();
        post2.setTitle("ha");
        post2.setDescription("post2");
        post2.setPending(true);
        post2.setBlog(true);
        post2.setDate(date);
        post2.setProfile(profile);
        post2.setHashTags(hList);
        post2.setPicture("picture");
        
        postDao.addPost(post);
        postDao.addPost(post2);
        List<Post> postList = postDao.getAllBlogPosts();
        assertEquals(2, postList.size());
    }

    /**
     * Test of getPostsByDate method, of class PostDao.
     */
    @Test
    public void testGetPostsByDate() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();
        hash.setTag("Heh");
        
        hashTagDao.addHashTag(hash);
        hList.add(hash);
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(date);
        profile.setAdminID(true);
        
        profileDao.addProfile(profile);
        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("post");
        post.setPending(false);
        post.setBlog(true);
        post.setDate(date);
        post.setProfile(profile);
        post.setHashTags(hList);
        post.setPicture("picture");
        Post post2 = new Post();
        post2.setTitle("ha");
        post2.setDescription("post2");
        post2.setPending(true);
        post2.setBlog(true);
        post2.setDate(date);
        post2.setProfile(profile);
        post2.setHashTags(hList);
        post2.setPicture("picture");
        
        postDao.addPost(post);
        postDao.addPost(post2);
        List<Post> postList = postDao.getPostsByDate(date);
        assertEquals(2, postList.size());
    }

    /**
     * Test of getPostsByHashTagID method, of class PostDao.
     */
    @Test
    public void testGetPostsByHashTagID() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();
        hash.setTag("Heh");
        
        hashTagDao.addHashTag(hash);
        hList.add(hash);
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(date);
        profile.setAdminID(true);
        
        profileDao.addProfile(profile);
        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("post");
        post.setPending(false);
        post.setBlog(true);
        post.setDate(today);
        post.setProfile(profile);
        post.setHashTags(hList);
        post.setPicture("picture");
        Post post2 = new Post();
        post2.setTitle("ha");
        post2.setDescription("post2");
        post2.setPending(true);
        post2.setBlog(true);
        post2.setDate(date);
        post2.setProfile(profile);
        post2.setHashTags(hList);
        post2.setPicture("picture");
        
        postDao.addPost(post);
        postDao.addPost(post2);
        List<Post> postList = postDao.getPostsByHashTagID(hash.getHashTagID());
        assertEquals(2, postList.size());
    }

    /**
     * Test of getPostsByProfileID method, of class PostDao.
     */
    @Test
    public void testGetPostsByProfileID() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();
        hash.setTag("Heh");
        
        hashTagDao.addHashTag(hash);
        hList.add(hash);
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse("2017-02-23", DateTimeFormatter.ISO_DATE);
        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(date);
        profile.setAdminID(true);
        
        profileDao.addProfile(profile);
        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("post");
        post.setPending(false);
        post.setBlog(true);
        post.setDate(today);
        post.setProfile(profile);
        post.setHashTags(hList);
        post.setPicture("picture");
        Post post2 = new Post();
        post2.setTitle("ha");
        post2.setDescription("post2");
        post2.setPending(true);
        post2.setBlog(true);
        post2.setDate(date);
        post2.setProfile(profile);
        post2.setHashTags(hList);
        post2.setPicture("picture");
        
        postDao.addPost(post);
        postDao.addPost(post2);
        List<Post> postList = postDao.getPostsByProfileID(profile.getProfileID());
        assertEquals(2, postList.size());
    }
    
}
