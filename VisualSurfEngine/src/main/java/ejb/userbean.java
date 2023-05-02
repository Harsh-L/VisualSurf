/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Boardtb;
import entity.Categorytb;
import entity.Imagetb;
import entity.Roletb;
import entity.Usertb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author harsh
 */
@RolesAllowed("User")
@Stateless
public class userbean implements userbeanLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    @Override
    public void insertUser(String username, String name, String email, String password, Integer roleID) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = new Usertb();
        user.setUsername(username);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        List<Roletb> role = em.createNamedQuery("Roletb.findByRoleID").setParameter("roleID", roleID).getResultList();
        user.setRoleID(role.get(0));
        em.persist(user);
    }

    @Override
    public void updateUser(int id, String username, String name, String email) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, id);
        user.setUsername(username);
        user.setName(name);
        user.setEmail(email);
        em.merge(user);
    }

    @Override
    public void deleteUser(int id, Usertb user) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if(id == user.getUserID()){
            Usertb user_data = em.find(Usertb.class, id);
            em.remove(user_data);
        }
    }

    @Override
    public List<Usertb> searchUserByName(String username) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        List<Usertb> users = em.createNamedQuery("Usertb.findByUsername").setParameter("username", username).getResultList();
        return users;
    }

    @Override
    public Usertb searchUserById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, id);
        return user;
    }
    
    @Override
    public List<Usertb> getAllUsers(){
        return em.createNamedQuery("Usertb.findAll").getResultList();
    }

    @Override
    public void setFavouriteCategory(Integer userid, List<Integer> category) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Collection<Categorytb> categories = new ArrayList<>();
        for (int index = 0; index < category.size(); index++) {
            Categorytb cat = em.find(Categorytb.class, category.get(index));
            categories.add(cat);
        }
        Usertb user = em.find(Usertb.class, userid);
        user.setCategorytbCollection(categories);
        em.merge(user);
    }

    @Override
    public Collection<Categorytb> getFavouriteCategory(Integer userid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, userid);
        return user.getCategorytbCollection();
    }

    @Override
    public void createBoard(Integer userid, String boardName) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, userid);
        Boardtb board = new Boardtb();
        board.setBoardName(boardName);
        board.setUserID(user);
        Collection<Boardtb> boards = new ArrayList<Boardtb>();
        boards.add(board);
        user.setBoardtbCollection(boards);
        em.persist(board);
        em.merge(user);
    }
    
    @Override
    public void updateBoardName(Integer userid, Integer boardid, String boardName) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, userid);
        Boardtb board = em.find(Boardtb.class, boardid);
        board.setBoardName(boardName);
        Collection<Boardtb> boards = user.getBoardtbCollection();
        // TODO
        Collection<Boardtb> new_boards = new ArrayList<>();
        for (Boardtb b : boards) {
            if (b.getBoardid() == boardid) {
                b.setBoardName(boardName);
            }
            new_boards.add(b);
        }
        em.merge(board);
    }
    
    @Override
    public List<Boardtb> getUserBoards(Integer userid){
        Usertb user = em.find(Usertb.class, userid);
        Collection<Boardtb> boards = user.getBoardtbCollection();
        return (List<Boardtb>) boards;
    }
    
    @Override
    public Boardtb getBoard(Integer userid, Integer boardID){
        Usertb user = em.find(Usertb.class, userid);
        Collection<Boardtb> boards = user.getBoardtbCollection();
        for(Boardtb board : boards){
            if(board.getBoardid() == boardID){
                return em.find(Boardtb.class, board.getBoardid());
            }else{
                return null;
            }
        }
        return null;
    }
    
    @Override
    public void setImageBoard(Integer userid, Integer imageID, Integer boardID) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, userid);
        Boardtb board = em.find(Boardtb.class, boardID);
        Imagetb image = em.find(Imagetb.class, imageID);

        Collection<Boardtb> boards = user.getBoardtbCollection();
        for (Boardtb b : boards) {
            if (board == b) {

            }
        }

    }
    
    @Override
    public Imagetb getImage(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Imagetb image = em.find(Imagetb.class, id);
        return image;
    }

    @Override
    public void uploadImage(Integer userid, String title, String description, String imageUrl, Collection<String> tags) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, userid);
        Imagetb image = new Imagetb();
        image.setCreationDate(new Date());
        image.setTitle(title);
        image.setDescription(description);
        image.setImageUrl(imageUrl);
        String tagString = "";
        ArrayList<String> tagList = (ArrayList<String>) tags;
        for (int count = 0; count < tagList.size(); count++) {
            tagString = tagString + tagList.get(count) + ", ";
        }
        image.setTags(tagString);
        Collection<Imagetb> imageColl = user.getImagetbCollection1();
        imageColl.add(image);
        user.setImagetbCollection1(imageColl);
        em.merge(user);
        em.merge(image);
    }

    @Override
    public void editImage(Integer id, Integer userid, String title, String description, String imageUrl, Collection<String> tags) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, userid);
        Imagetb image = em.find(Imagetb.class, id);
        if (image.getUserID() == user) {
            image.setTitle(title);
            image.setDescription(description);
            image.setImageUrl(imageUrl);
            String tagString = "";
            ArrayList<String> tagList = (ArrayList<String>) tags;
            for (int count = 0; count < tagList.size(); count++) {
                tagString = tagString + tagList.get(count) + ", ";
            }
            image.setTags(tagString);
            Collection<Imagetb> imageColl = user.getImagetbCollection1();
            imageColl.add(image);
            user.setImagetbCollection1(imageColl);
            em.merge(user);
            em.merge(image);
        }

    }

    // TODO
    @Override
    public void deleteImage(Integer userid, Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, userid);
        Imagetb image = em.find(Imagetb.class, id);
        Collection<Imagetb> userImages =  user.getImagetbCollection();
        userImages.remove(image);
        em.remove(image);
    }
    
    // TODO
    @Override
    public void likeImage(Integer id, Integer userid, Integer likerid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Imagetb image = em.find(Imagetb.class, id);
        Usertb creator = em.find(Usertb.class, userid);
        Usertb liker = em.find(Usertb.class, likerid);
        Integer totalLikes = image.getLikeCount();
        image.setLikeCount(totalLikes + 1);        
    }

    @Override
    public List<Imagetb> searchImage(String searchQuery) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //List<Imagetb> images = em.createNamedQuery("Imagetb.findAll").getResultList();
        List<Imagetb> images = new ArrayList<Imagetb>();
        String[] splited_search = searchQuery.split("\\s+");
        for (String str : splited_search) {
            images.addAll(em.createNamedQuery("Imagetb.findByTitle").setParameter("title", str).getResultList());
            images.addAll(em.createNamedQuery("Imagetb.findByDescription").setParameter("description", str).getResultList());
//            List<Imagetb> image = em.createNamedQuery("Imagetb.findByTitle").setParameter("Imagetb.findByTitle", str).getResultList();
        }
        return images;
    }
    
    @Override
    public void followUser(Integer follower, Integer follow) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, follow);
        Usertb user_follower = em.find(Usertb.class, follower);
        Collection<Usertb> followee = user.getUsertbCollection();
        followee.add(user_follower);
        em.merge(user);
    }
    
    @Override
    public boolean login(String username, String password){
        try{
            Query user_query = em.createQuery("SELECT u FROM Usertb u WHERE u.username = :username AND u.password = :password").
                    setParameter("username", username).setParameter("password", password);
            Usertb user = (Usertb) user_query.getResultList().get(0);
            
            return user != null;
        }catch(Exception e){
            return false;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
}
