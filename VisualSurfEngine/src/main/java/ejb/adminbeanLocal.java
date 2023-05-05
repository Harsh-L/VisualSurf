/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Imagetb;
import entity.Roletb;
import entity.Usertb;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author harsh
 */
@Local
public interface adminbeanLocal {
    
    // user operations
    public boolean insertUser(String username, String name, String email, String password, Integer role); // POST - DONE
    public boolean updateUser(int id, String username, String name, String email); // PUT - DONE
    public boolean deleteUser(int id); // DELETE - DONE
    public List<Usertb> searchUserByName(String username); // GET - DONE
    public Usertb searchUserById(int id); // GET - DONE
    // admin operations
    public boolean deleteAdmin(int id, Usertb user); // DELETE - DONE
    public List<Usertb> getAdminList(); // GET - 
    // image operations
    public Imagetb getImage(Integer id); // GET - DONE
    public boolean deleteImage(Integer userid, Integer id); // DELETE - DONE
    // used cdi bean 
    public Roletb getAdminRoleid(); // DONE
    public Roletb getUserRoleid(); // DONE
}
