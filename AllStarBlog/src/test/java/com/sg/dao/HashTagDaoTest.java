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
public class HashTagDaoTest {

    PostDaoInterface postDao;
    ProfileDaoInterface profileDao;
    HashTagDaoInterface hashTagDao;

    public HashTagDaoTest() {
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
     * Test of addHashTag method, of class HashTagDao.
     */
    @Test
    public void testAddHashTag() {

        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();

        HashTag hashtag = new HashTag();
        hashtag.setTag("Tag");
        hashTagDao.addHashTag(hashtag);
        HashTag fromDao = hashTagDao.getHashTagByID(hashtag.getHashTagID());
        assertEquals(fromDao, hashtag);

    }

    /**
     * Test of deleteHashTag method, of class HashTagDao.
     */
    @Test
    public void testDeleteHashTag() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();

        HashTag hashtag = new HashTag();
        hashtag.setTag("Tag");
        hashTagDao.addHashTag(hashtag);
        hashTagDao.deleteHashTag(hashtag.getHashTagID());
        assertNull(hashTagDao.getHashTagByID(hashtag.getHashTagID()));

    }

    /**
     * Test of getHashTagByID method, of class HashTagDao.
     */
    @Test
    public void testGetHashTagByID() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();

        HashTag hashtag = new HashTag();
        hashtag.setTag("Tag");
        hashTagDao.addHashTag(hashtag);
        HashTag fromDao = hashTagDao.getHashTagByID(hashtag.getHashTagID());
        assertEquals(fromDao, hashtag);
    }

    /**
     * Test of getAllHashTags method, of class HashTagDao.
     */
    @Test
    public void testGetAllHashTags() {
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();

        HashTag hashtag = new HashTag();
        hashtag.setTag("Tag");
        hashTagDao.addHashTag(hashtag);
        List<HashTag> hashtags = hashTagDao.getAllHashTags();

    }

    /**
     * Test of getAllHashTagsByPostID method, of class HashTagDao.
     */
    @Test
    public void testGetAllHashTagsByPostID() {
        LocalDate today = LocalDate.now();
        List<HashTag> hList = new ArrayList<>();
        HashTag hash = new HashTag();
        HashTag hashtag = new HashTag();
        hashtag.setTag("Tag");
        hashTagDao.addHashTag(hashtag);

        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(today);
        profile.setAdminID(true);

        profileDao.addProfile(profile);

        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("This is a test");
        post.setPending(true);
        post.setBlog(true);
        post.setDate(today);
        post.setProfile(profile);
        post.setHashTags(hList);

        postDao.addPost(post);

        Profile fromDao = profileDao.getProfileByPostID(post.getPostID());
        assertEquals(profile, fromDao);
    }
}



