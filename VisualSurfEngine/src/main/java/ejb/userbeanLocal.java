/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Boardtb;
import entity.Categorytb;
import entity.Imagetb;
import entity.Usertb;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author harsh
 */
@Local
public interface userbeanLocal {
    
    // user operations
    public void insertUser(String username, String name, String email, String password, Integer role);
    public void updateUser(int id, String username, String name, String email);
    public void deleteUser(int id, Usertb user);
    public List<Usertb> searchUserByName(String username);
    public Usertb searchUserById(int id);
    public List<Usertb> getAllUsers();
    
    // category operations
    public void setFavouriteCategory(Integer userid, List<Integer> categoryID);
    public Collection<Categorytb> getFavouriteCategory(Integer userid);
    
    // board operations
    public void createBoard(Integer userid, String boardName);
    public void updateBoardName(Integer userid,Integer boardid ,String boardName);
    public List<Boardtb> getUserBoards(Integer userid);
    public Boardtb getBoard(Integer userid, Integer boardID);
    public void setImageBoard(Integer userid, Integer imageID, Integer boardID);
    
    // image operations
    public Imagetb getImage(Integer id);
    public void uploadImage(Integer userid, String title, String description, String imageUrl, Collection<String> tags);
    public void editImage(Integer id, Integer userid ,String title, String description, String imageUrl, Collection<String> tags);
    public void deleteImage(Integer userid, Integer id);
    public void likeImage(Integer id, Integer userid, Integer likerid);
    public List<Imagetb> searchImage(String searchQuery);
    
    // follow operations
    public void followUser(Integer follower, Integer follow);
}
