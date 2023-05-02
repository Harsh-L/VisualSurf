/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Imagetb;
import entity.Roletb;
import entity.Usertb;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author harsh
 */
@RolesAllowed("Admin")
@Stateless
public class adminbean implements adminbeanLocal {
    EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
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
    public void deleteUser(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, id);
        em.remove(user);
    }
    
    @Override
    public void deleteAdmin(int id, Usertb user) {
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
    public Imagetb getImage(Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Imagetb image = em.find(Imagetb.class, id);
        return image;
    }
    
    @Override
    public void deleteImage(Integer userid, Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Usertb user = em.find(Usertb.class, userid);
        Imagetb image = em.find(Imagetb.class, id);
        Collection<Imagetb> userImages =  user.getImagetbCollection();
        userImages.remove(image);
        em.remove(image);
    }

    @Override
    public List<Usertb> getAdminList() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Integer id = em.createQuery("SELECT roleID WHERE roleName == Admin").getFirstResult();
        return em.createQuery("SELECT * FROM Usertb WHERE roleID == " + id).getResultList();
    }
}
