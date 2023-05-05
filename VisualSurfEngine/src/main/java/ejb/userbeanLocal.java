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
    public boolean insertUser(String username, String name, String email, String password, Integer role); // POST - DONE
    public boolean updateUser(int id, String username, String name, String email); // PUT - DONE
    public boolean deleteUser(int id, Usertb user); // DELETE - DONE
    public List<Usertb> searchUserByName(String username); // GET - DONE
    public Usertb searchUserById(int id); // GET - DONE
    public List<Usertb> getAllUsers(); // GET - DONE
    
    // category operations
    public boolean setFavouriteCategory(Integer userid, List<Integer> categoryID); // POST - OPTIONAL
    public Collection<Categorytb> getFavouriteCategory(Integer userid); // GET - OPTIONAL
    
    // board operations
    public boolean createBoard(Integer userid, String boardName); // POST - DONE
    public boolean updateBoardName(Integer userid,Integer boardid ,String boardName); // PUT - DONE
    public List<Boardtb> getUserBoards(Integer userid); // GET - DONE
    public Boardtb getBoard(Integer userid, Integer boardID); // GET - DONE
    public boolean setImageBoard(Integer userid, Integer imageID, Integer boardID); // POST - DONE
    
    // image operations
    public Imagetb getImage(Integer id); // GET - DONE
    public boolean uploadImage(Integer userid, String title, String description, String imageUrl, Collection<String> tags); // POST - DONE
    public boolean editImage(Integer id, Integer userid ,String title, String description, String imageUrl, Collection<String> tags); // PUT - OPTIONAL
    public boolean deleteImage(Integer userid, Integer id); // DELETE - DONE
    public boolean likeImage(Integer id, Integer userid, Integer likerid); // POST - DONE
    public List<Imagetb> searchImage(String searchQuery); // GET - DONE
    
    // follow operations
    public boolean followUser(Integer follower, Integer follow); // POST - OPTIONAL
    
    // login
    public Usertb login(String username, String password); // GET~POST - DONE
}
