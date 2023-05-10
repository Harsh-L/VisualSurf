/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Boardtb;
import entity.Categorytb;
import entity.Imagetb;
import entity.Roletb;
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
    public boolean insertUser(String username, String name, String email, String password, Integer role); // POST - DONE // READY
    public boolean updateUser(int id, String username, String name, String email); // PUT - DONE // READY
    public boolean deleteUser(int id, Usertb user); // DELETE - DONE // READY
    public List<Usertb> searchUserByName(String username); // GET - DONE // READY + ANOTHER METHOD CALLED searchUser BY QueryParam
    public Usertb searchUserById(int id); // GET - DONE // READY // READY 
    public Collection<Usertb> getAllUsers(); // GET - DONE // READY
    
    // category operations
    public boolean setFavouriteCategory(Integer userid, List<Integer> categoryID); // POST - OPTIONAL
    public Collection<Categorytb> getFavouriteCategory(Integer userid); // GET - OPTIONAL
    
    // board operations
    public boolean createBoard(Integer userid, String boardName); // POST - DONE // READY
    public boolean updateBoardName(Integer userid,Integer boardid ,String boardName); // PUT - DONE 
    public List<Boardtb> getUserBoards(Integer userid); // GET - DONE // READY 
    public Boardtb getBoard(Integer userid, Integer boardID); // GET - DONE // READY
    public boolean setImageBoard(Integer userid, Integer imageID, Integer boardID); // POST - DONE // READY
    
    // image operations
    public Imagetb getImage(Integer id); // GET - DONE // READY
    public boolean uploadImage(Integer userid, String title, String description, String imageUrl, String tags); // POST - DONE // READY
    public boolean editImage(Integer id, Integer userid ,String title, String description, String imageUrl, Collection<String> tags); // PUT - OPTIONAL
    public boolean deleteImage(Integer userid, Integer id); // DELETE - DONE // READY
    public boolean likeImage(Integer id, Integer userid, Integer likerid); // POST - DONE // READY
    public List<Imagetb> searchImage(String searchQuery); // GET - DONE // READY
    public Integer getMaxImageID(); // DONE
    public Collection<Imagetb> getRandomImages(); // GET - DONE
    
    // follow operations
    public boolean followUser(Integer follower, Integer follow); // POST - OPTIONAL
    
    // login
    public Usertb login(String username, String password); // GET~POST - DONE // SECURITY READY
}
