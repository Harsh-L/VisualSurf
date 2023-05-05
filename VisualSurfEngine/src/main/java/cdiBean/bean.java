/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdiBean;

import ejb.adminbeanLocal;
import ejb.userbeanLocal;
import entity.Accounttypetb;
import entity.Boardtb;
import entity.Categorytb;
import entity.Imagetb;
import entity.Roletb;
import entity.Usertb;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import restClient.RESTClient;

/**
 *
 * @author harsh
 */
@Named(value = "bean")
@SessionScoped
public class bean implements Serializable {

    @EJB
    adminbeanLocal admin;
    @EJB
    userbeanLocal user;

    @Inject
    private HttpServletRequest request;

    Collection<Accounttypetb> accTypes; // DONE
    GenericType<Collection<Accounttypetb>> gen_accTypes; // done

    Collection<Boardtb> boards; // done
    GenericType<Collection<Boardtb>> gen_boards; // done

    Collection<Categorytb> categories; // done
    GenericType<Collection<Categorytb>> gen_categories; //done

    Collection<Imagetb> images; // done
    GenericType<Collection<Imagetb>> gen_images; // done

    Collection<Roletb> roles; // done
    GenericType<Collection<Roletb>> gen_roles; // done

    Collection<Usertb> users; // done
    GenericType<Collection<Usertb>> gen_users; //done

    Boardtb boardData; // done
    GenericType<Boardtb> gen_boardData; // done

    Imagetb imageData; // done
    GenericType<Imagetb> gen_imageData; // done

    Usertb userData; //done
    GenericType<Usertb> gen_userData; // done

    RESTClient client;
    Response res;
    boolean isAdmin = false;

    /**
     * Creates a new instance of bean
     */
    public bean() {
        accTypes = new ArrayList<>();
        boards = new ArrayList<>();
        categories = new ArrayList<>();
        images = new ArrayList<>();
        roles = new ArrayList<>();
        users = new ArrayList<>();
        gen_accTypes = new GenericType<Collection<Accounttypetb>>() {
        };
        gen_boards = new GenericType<Collection<Boardtb>>() {
        };
        gen_categories = new GenericType<Collection<Categorytb>>() {
        };
        gen_images = new GenericType<Collection<Imagetb>>() {
        };
        gen_roles = new GenericType<Collection<Roletb>>() {
        };
        gen_users = new GenericType<Collection<Usertb>>() {
        };
        client = new RESTClient();
        boardData = new Boardtb();
        gen_boardData = new GenericType<Boardtb>() {
        };
        imageData = new Imagetb();
        gen_imageData = new GenericType<Imagetb>() {
        };
        userData = new Usertb();
        gen_userData = new GenericType<Usertb>() {
        };
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Collection<Usertb> getAllUsers() {
        res = client.getAllUsers(Response.class);
        Collection<Usertb> tempUsers = new ArrayList<>();
        if (res == Response.noContent().build()) {
            return null;
        } else if (res == Response.ok(tempUsers).build()) {
            return res.readEntity(gen_users);
        } else {
            return null;
        }
    }

    public Usertb getUserData() {
        return userData;
    }

    public void setUserData(Usertb userData) {
        this.userData = userData;
    }

//    public Usertb getUserData(int userid) {
//        return user.searchUserById(userid);
//    }
//    public void setUserData(Usertb userData) {
////        String password = userData.getPassword();
////        // encrypt password
////        Roletb role = userData.getRoleID();
////        user.insertUser(userData.getUsername(), userData.getName(), userData.getEmail(), password, role.getRoleID());
//    }
    public Boardtb getBoardData(Integer userid, Integer boardId) {
        return user.getBoard(userid, boardId);
    }

    public void setBoardData(Integer userid, String boardName) {
        user.createBoard(userid, boardName);
    }

    public Imagetb getImageData() {
        return imageData;
    }

    public void setImageData(Imagetb imageData) {
        this.imageData = imageData;
    }

    public String login() {
        res = client.login(userData);
        if (res == Response.ok().build()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", userData.getUsername());
            session.setAttribute("userid", userData.getUserID());
            session.setAttribute("role", userData.getRoleID().getRoleName());
            return "home.jsf";
        } else {
            return "login.jsf";
        }
    }

    public String logout() {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", "");
        session.setAttribute("userid", "");
        session.setAttribute("role", "");
        session.invalidate();
        return "login.jsf";
    }

    public String registerUser() {
        Roletb role_data = admin.getUserRoleid();
        userData.setRoleID(role_data);
        res = client.insertUser(userData);
        if (res == Response.ok().build()) {
            return "login.jsf";
        } else {
            return "User_pages/register.jsf";
        }
    }

    public String registerAdmin() {
        if (isAdmin) {
            Roletb role_data = admin.getAdminRoleid();
            userData.setRoleID(role_data);
        } else {
            Roletb role_data = admin.getUserRoleid();
            userData.setRoleID(role_data);
        }
        res = client.insertUser(userData);
        if (res == Response.ok().build()) {
            return "Admin_Pages/registerAdmin.jsf";
        } else {
            return "Admin_Pages/registerAdmin.jsf";
        }
    }

    public Collection<Usertb> findByUsername() {
        res = client.findUserByName(Response.class, userData.getName());
        if (res == Response.ok().build()) {
            Collection<Usertb> users = res.readEntity(gen_users);
            return users;
        } else {
            return null;
        }
    }

    public Collection<Usertb> findByID() {
        res = client.findUserByID(Response.class, userData.getUserID().toString());
        if (res == Response.ok().build()) {
            Collection<Usertb> users = res.readEntity(gen_users);
            return users;
        } else {
            return null;
        }
    }

    public Collection<Boardtb> findUserBoards() {
        res = client.findUserBoards(Response.class, userData.getUserID().toString());
        if (res == Response.ok().build()) {
            Collection<Boardtb> boards = res.readEntity(gen_boards);
            return boards;
        } else {
            return null;
        }
    }

}
