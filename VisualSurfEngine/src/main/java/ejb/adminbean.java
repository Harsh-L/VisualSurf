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
import javax.persistence.PersistenceContext;

/**
 *
 * @author harsh
 */
//@RolesAllowed("Admin")
@Stateless
public class adminbean implements adminbeanLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean insertUser(String username, String name, String email, String password, Integer roleID) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = new Usertb();
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            List<Roletb> role = em.createNamedQuery("Roletb.findByRoleID", Roletb.class).setParameter("roleID", roleID).getResultList();
            user.setRoleID(role.get(0));
            em.persist(user);
            return true;
        } catch (Exception e) {
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
    public boolean deleteUser(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, id);
            em.remove(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAdmin(int id, Usertb user) {
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
            List<Usertb> users = em.createNamedQuery("Usertb.findByUsername", Usertb.class).setParameter("username", username).getResultList();
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
            return user;
        } catch (Exception e) {
            return null;
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
    public boolean deleteImage(Integer userid, Integer id) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            Usertb user = em.find(Usertb.class, userid);
            Imagetb image = em.find(Imagetb.class, id);
            Collection<Imagetb> userImages = user.getImagetbCollection();
            if (userImages.contains(image)) {
                userImages.remove(image);
                em.remove(image);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Usertb> getAdminList() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            List<Roletb> roleid = em.createNamedQuery("Roletb.findByRoleName", Roletb.class).setParameter("roleName", "Admin").getResultList();
            return em.createQuery("SELECT * FROM Usertb WHERE roleID == " + roleid.get(0).getRoleID().toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Roletb getAdminRoleid() {
        try {
            List<Roletb> role = em.createNamedQuery("Roletb.findByRoleName", Roletb.class).setParameter("roleName", "Admin").getResultList();
            if (role != null) {
                return role.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Roletb getUserRoleid() {
        try {
            List<Roletb> role = em.createNamedQuery("Roletb.findByRoleName", Roletb.class).setParameter("roleName", "User").getResultList();
            if (role != null) {
                return role.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
