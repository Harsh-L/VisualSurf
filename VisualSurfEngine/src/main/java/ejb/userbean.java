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
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author harsh
 */
//@RolesAllowed("User")
@Stateless
public class userbean implements userbeanLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    public boolean insertUser(String username, String name, String email, String password, Integer roleID) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = new Usertb();
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            List<Roletb> role = em.createNamedQuery("Roletb.findByRoleID").setParameter("roleID", roleID).getResultList();
            user.setRoleID(role.get(0));
            em.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(int id, String username, String name, String email) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, id);
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            em.merge(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id, Usertb user) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            if (id == user.getUserID()) {
                Usertb user_data = em.find(Usertb.class, id);
                em.remove(user_data);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Usertb> searchUserByName(String username) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            List<Usertb> users = em.createNamedQuery("Usertb.findByUsername").setParameter("username", username).getResultList();
            return users;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usertb searchUserById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, id);
            System.err.print("user found");
            return user;
        } catch (Exception e) {
            System.err.print("not found");
            return null;
        }
    }

    @Override
    public Collection<Usertb> getAllUsers() {
        try {
            Collection<Usertb> users = em.createNamedQuery("Usertb.findAll").getResultList();
            if (!users.isEmpty()) {

                return users;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e);
            return null;
        }
    }

    @Override
    public boolean setFavouriteCategory(Integer userid, List<Integer> category) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Collection<Categorytb> categories = new ArrayList<>();
            for (int index = 0; index < category.size(); index++) {
                Categorytb cat = em.find(Categorytb.class, category.get(index));
                categories.add(cat);
            }
            Usertb user = em.find(Usertb.class, userid);
            user.setCategorytbCollection(categories);
            em.merge(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Collection<Categorytb> getFavouriteCategory(Integer userid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, userid);
            return user.getCategorytbCollection();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean createBoard(Integer userid, String boardName) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, userid);
            Boardtb board = new Boardtb();
            board.setBoardName(boardName);
            board.setUserID(user);
            Collection<Boardtb> boards = new ArrayList<Boardtb>();
            boards.add(board);
            user.setBoardtbCollection(boards);
            em.persist(board);
            em.merge(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateBoardName(Integer userid, Integer boardid, String boardName) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Boardtb> getUserBoards(Integer userid) {
        try {
            Usertb user = em.find(Usertb.class, userid);
            Collection<Boardtb> boards = user.getBoardtbCollection();
            return (List<Boardtb>) boards;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boardtb getBoard(Integer userid, Integer boardID) {
        try {
            Usertb user = em.find(Usertb.class, userid);
            Collection<Boardtb> boards = user.getBoardtbCollection();
            for (Boardtb board : boards) {
                if (board.getBoardid() == boardID) {
                    return em.find(Boardtb.class, board.getBoardid());
                } else {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean setImageBoard(Integer userid, Integer imageID, Integer boardID) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, userid);
            Boardtb board = em.find(Boardtb.class, boardID);
            Imagetb image = em.find(Imagetb.class, imageID);

            Collection<Boardtb> boards = user.getBoardtbCollection();
            for (Boardtb b : boards) {
                if (boardID == b.getBoardid()) {
                    Collection<Imagetb> images_in_board = b.getImagetbCollection();
                    images_in_board.add(image);
                    b.setImagetbCollection(images_in_board);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Imagetb getImage(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Imagetb image = em.find(Imagetb.class, id);
            return image;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean uploadImage(Integer userid, String title, String description, String imageUrl, String tags) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, userid);
            Imagetb image = new Imagetb();
            image.setCreationDate(new Date());
            image.setTitle(title);
            image.setDescription(description);
            image.setImageUrl(imageUrl);
//            String tagString = "";
//            ArrayList<String> tagList = (ArrayList<String>) tags;
//            for (int count = 0; count < tagList.size(); count++) {
//                tagString = tagString + tagList.get(count) + ", ";
//            }
            image.setTags(tags);
            Collection<Imagetb> imageColl = user.getImagetbCollection1();
            imageColl.add(image);
            user.setImagetbCollection1(imageColl);
            em.merge(user);
            em.merge(image);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean editImage(Integer id, Integer userid, String title, String description, String imageUrl, Collection<String> tags) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
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
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    // TODO
    @Override
    public boolean deleteImage(Integer userid, Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, userid);
            Imagetb image = em.find(Imagetb.class, id);
            Collection<Imagetb> userImages = user.getImagetbCollection();
            userImages.remove(image);
            user.setImagetbCollection(userImages);
            em.remove(image);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // TODO
    @Override
    public boolean likeImage(Integer id, Integer userid, Integer likerid) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Imagetb image = em.find(Imagetb.class, id);
            Usertb creator = em.find(Usertb.class, userid);
            Usertb liker = em.find(Usertb.class, likerid);
            Integer totalLikes = image.getLikeCount();
            image.setLikeCount(totalLikes + 1);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Imagetb> searchImage(String searchQuery) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //List<Imagetb> images = em.createNamedQuery("Imagetb.findAll").getResultList();
        try {
            List<Imagetb> images = new ArrayList<Imagetb>();
            String[] splited_search = searchQuery.split("\\s+");
            for (String str : splited_search) {
                images.addAll(em.createNamedQuery("Imagetb.findByTitle").setParameter("title", str).getResultList());
                images.addAll(em.createNamedQuery("Imagetb.findByDescription").setParameter("description", str).getResultList());
//            List<Imagetb> image = em.createNamedQuery("Imagetb.findByTitle").setParameter("Imagetb.findByTitle", str).getResultList();
            }
            return images;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean followUser(Integer follower, Integer follow) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, follow);
            Usertb user_follower = em.find(Usertb.class, follower);
            Collection<Usertb> followee = user.getUsertbCollection();
            followee.add(user_follower);
            em.merge(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Usertb login(String username, String password) {
        try {
            Query user_query = em.createQuery("SELECT u FROM Usertb u WHERE u.username = :username AND u.password = :password").
                    setParameter("username", username).setParameter("password", password);
//            Query user_query = em.createQuery("SELECT u FROM Usertb u WHERE u.username = " + username +" AND u.password = " + password + "").
//                    setParameter("username", username).setParameter("password", password);
            Usertb user = (Usertb) user_query.getSingleResult();
            if (user != null) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer getMaxImageID() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Integer count = (Integer) em.createQuery("SELECT MAX(imageID) FROM Imagetb").getSingleResult();
            return count;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Collection<Imagetb> getRandomImages() {
        try {
            Collection<Imagetb> randomImages = em.createQuery("SELECT img FROM Imagetb img ORDER BY RAND()", Imagetb.class).setMaxResults(100).getResultList();
            return randomImages;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
