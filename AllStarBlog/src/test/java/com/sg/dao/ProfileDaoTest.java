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
public class ProfileDaoTest {

    PostDaoInterface postDao;
    ProfileDaoInterface profileDao;
    HashTagDaoInterface hashTagDao;

    public ProfileDaoTest() {

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

        List<Post> posts = postDao.getAllBlogPosts();
        for (Post currentPost : posts) {
            postDao.deletePost(currentPost.getPostID());
        }
        List<HashTag> hashTags = hashTagDao.getAllHashTags();
        for (HashTag currentHashTag : hashTags) {
            hashTagDao.deleteHashTag(currentHashTag.getHashTagID());
        }
        List<Profile> profiles = profileDao.getAllProfiles();
        for (Profile currentProfile : profiles) {
            profileDao.deleteProfile(currentProfile.getProfileID());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addProfile method, of class ProfileDao.
     */
    @Test
    public void testAddProfile() {
        LocalDate today = LocalDate.now();

        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(today);
        profile.setAdminID(true);

        profileDao.addProfile(profile);
        Profile fromDao = profileDao.getProfileByID(profile.getProfileID());

        assertEquals(profile, fromDao);
    }

    /**
     * Test of deleteProfile method, of class ProfileDao.
     */
    @Test
    public void testDeleteProfile() {
        LocalDate today = LocalDate.now();

        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(today);
        profile.setAdminID(true);

        profileDao.addProfile(profile);
        profileDao.deleteProfile(profile.getProfileID());

        assertNull(profileDao.getProfileByID(profile.getProfileID()));
    }

    /**
     * Test of updateProfile method, of class ProfileDao.
     */
    @Test
    public void testUpdateProfile() {
        LocalDate today = LocalDate.now();

        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(today);
        profile.setAdminID(true);

        profileDao.addProfile(profile);

        profile.setFirstName("Becca");
        profileDao.updateProfile(profile);

        assertEquals(profile, profileDao.getProfileByID(profile.getProfileID()));

    }

    /**
     * Test of getAllProfiles method, of class ProfileDao.
     */
    @Test
    public void testGetAllProfiles() {
        LocalDate today = LocalDate.now();

        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(today);
        profile.setAdminID(true);

        profileDao.addProfile(profile);

        Profile profileTwo = new Profile();
        profileTwo.setUserName("davidcrockett");
        profileTwo.setPassword("password");
        profileTwo.setFirstName("David");
        profileTwo.setLastName("Crockett");
        profileTwo.setEmail("dcrockett@gmail.com");
        profileTwo.setBirthday(today);
        profileTwo.setAdminID(false);

        profileDao.addProfile(profileTwo);

        List<Profile> profiles = profileDao.getAllProfiles();

        assertEquals(profiles.size(), 2);
    }

    /**
     * Test of getProfileByID method, of class ProfileDao.
     */
    @Test
    public void testGetProfileByID() {
        LocalDate today = LocalDate.now();

        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(today);
        profile.setAdminID(true);

        profileDao.addProfile(profile);
        
        Profile fromDao = profileDao.getProfileByID(profile.getProfileID());
        
        assertEquals(profile, fromDao);
    }

    /**
     * Test of getProfileByPostID method, of class ProfileDao.
     */
    @Test
    public void testGetProfileByPostID() {
       LocalDate today = LocalDate.now();

        Profile profile = new Profile();
        profile.setUserName("beccabroek");
        profile.setPassword("password");
        profile.setFirstName("Rebecca");
        profile.setLastName("Broekhuis");
        profile.setEmail("becca@gmail.com");
        profile.setBirthday(today);
        profile.setAdminID(true);

        profileDao.addProfile(profile);

        HashTag hashTag = new HashTag();
        ArrayList<HashTag> tags= new ArrayList<>();
        hashTag.setTag("Test");
        hashTagDao.addHashTag(hashTag);
        tags.add(hashTag);
//        
        Post post = new Post();
        post.setTitle("ha");
        post.setDescription("This is a test");
        post.setPending(true);
        post.setBlog(true);
        post.setDate(today);
        post.setProfile(profile);
        post.setHashTags(tags);
        
        postDao.addPost(post);
        
        Profile fromDao = profileDao.getProfileByPostID(post.getPostID());
        assertEquals(profile, fromDao);
    }
}
