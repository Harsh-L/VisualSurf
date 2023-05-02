/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Imagetb;
import entity.Usertb;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author harsh
 */
@Local
public interface adminbeanLocal {
    
    public void insertUser(String username, String name, String email, String password, Integer role);
    public void updateUser(int id, String username, String name, String email);
    public void deleteAdmin(int id, Usertb user);
    public void deleteUser(int id);
    public List<Usertb> searchUserByName(String username);
    public Usertb searchUserById(int id);
    public Imagetb getImage(Integer id);
    public void deleteImage(Integer userid, Integer id);
    public List<Usertb> getAdminList();
}
