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
    public boolean insertUser(String username, String name, String email, String password, Integer role); // POST - DONE // READY
    public boolean updateUser(int id, String username, String name, String email); // PUT - DONE // READY
    public boolean deleteUser(int id); // DELETE - DONE // READY
    public List<Usertb> searchUserByName(String username); // GET - DONE // READY
    public Usertb searchUserById(int id); // GET - DONE // READY
    // admin operations
    public boolean deleteAdmin(int id, Usertb user); // DELETE - DONE // READY
    public List<Usertb> getAdminList(); // GET - DONE // READY
    // image operations
    public Imagetb getImage(Integer id); // GET - DONE // READY
    public boolean deleteImage(Integer userid, Integer id); // DELETE - DONE // READY
    // used cdi bean 
    public Roletb getAdminRoleid(); // DONE
    public Roletb getUserRoleid(); // DONE
}
