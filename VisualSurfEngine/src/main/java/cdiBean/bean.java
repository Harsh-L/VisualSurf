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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import restClient.RESTClient;

/**
 *
 * @author harsh
 */
@Named(value = "bean")
@SessionScoped
public class bean implements Serializable {

    @EJB adminbeanLocal admin;
    @EJB userbeanLocal user;
    
    Collection<Accounttypetb> accTypes;
    Collection<Boardtb> boards;
    Collection<Categorytb> categories;
    Collection<Imagetb> images;
    Collection<Roletb> roles;
    Collection<Usertb> users;
    GenericType<Collection<Usertb>> gen_users;
    Usertb userData;
    GenericType<Usertb> gen_userData;
    Boardtb boardData;
    Imagetb imageData;
    RESTClient client;
    Response res;
    
    
    
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
        gen_users = new GenericType<Collection<Usertb>>(){};
        client = new RESTClient();
        userData = new Usertb();
        gen_userData = new GenericType<Usertb>(){};
    }

    public Collection<Usertb> getAllUsers(){
        res = client.getTest(Response.class);
        Collection<Usertb> tempUsers = new ArrayList<>();
        if(res == Response.noContent().build()){
            return null;
        }else if(res == Response.ok( tempUsers).build() ){
            return res.readEntity(gen_users);
        }else{
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
    
    public String login(){
        Form form = new Form();
        form.param("username", userData.getUsername());
        form.param("password", userData.getPassword());
        res = client.login(userData , Response.class);
        userData = res.readEntity(gen_userData);
        HttpServletRequest req = null;
        if(userData != null){
            HttpSession session = req.getSession(true);
            session.setAttribute("username", userData.getUsername());
            session.setAttribute("userid", userData.getUserID());
            session.setAttribute("role", userData.getRoleID().getRoleName());
            return "home.jsf";
        }else{
            return "login.jsf";
        }
    }
    
    public String logout(){
        HttpServletRequest req = null;
        HttpSession session = req.getSession(true);
        session.setAttribute("username", "");
        session.setAttribute("userid", "");
        session.setAttribute("role", "");
        session.invalidate();
        return "login.jsf";
    }
    
}
