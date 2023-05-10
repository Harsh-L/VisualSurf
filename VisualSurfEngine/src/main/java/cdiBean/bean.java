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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;
import javax.ws.rs.QueryParam;
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

    @Resource
    private UserTransaction utx;
    @Inject
    private Pbkdf2PasswordHash passwordHash;
    
    
    @EJB
    adminbeanLocal admin;
    @EJB
    userbeanLocal user;

    @Inject
    private HttpServletRequest request;

    @QueryParam("searchString")
    private String queryParam;

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

    Usertb posterData;

    RESTClient client;
    Response res;
    boolean isAdmin = false;
    private Part uploaded_image;

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
        posterData = new Usertb();
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    public Part getUploaded_image() {
        return uploaded_image;
    }

    public void setUploaded_image(Part uploaded_image) {
        this.uploaded_image = uploaded_image;
    }

    public Usertb getUserData() {
        return userData;
    }

    public void setUserData(Usertb userData) {
        this.userData = userData;
    }

    public Usertb getLikerData() {
        return posterData;
    }

    public void setLikerData(Usertb likerData) {
        this.posterData = likerData;
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

    public String registerUser() {
        Roletb role_data = admin.getUserRoleid();
        userData.setRoleID(role_data);
        // Hashing Password
        String hashedPassword =  passwordHash.generate(userData.getPassword().toCharArray());
        userData.setPassword(hashedPassword);
        
        res = client.insertUser(userData);
        if (res == Response.ok().build()) {
            userData = new Usertb();
            return "login.jsf";
        } else {
            return "User/register.jsf";
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
            userData = new Usertb();
            return "Admin/adminList.jsf";
        } else {
            return "Admin/registerAdmin.jsf";
        }
    }

    public Collection<Usertb> findByUsername() {
        res = client.findUserByName(Response.class, userData.getName());
        if (res == Response.ok().build()) {
            Collection<Usertb> users = res.readEntity(gen_users);
            userData = new Usertb();
            return users;
        } else {
            return null;
        }
    }

    public Collection<Usertb> findByID() {
        res = client.findUserByID(Response.class, userData.getUserID().toString());
        if (res == Response.ok().build()) {
            Collection<Usertb> users = res.readEntity(gen_users);
            userData = new Usertb();
            return users;
        } else {
            return null;
        }
    }

    public Collection<Boardtb> findUserBoards() {
        res = client.findUserBoards(Response.class, userData.getUserID().toString());
        if (res == Response.ok().build()) {
            Collection<Boardtb> boards = res.readEntity(gen_boards);
            userData = new Usertb();
            return boards;
        } else {
            return null;
        }
    }

    public Boardtb findBoard() {
        res = client.getBoard(Response.class, userData.getUserID().toString(), boardData.getBoardid().toString());
        if (res == Response.ok().build()) {
            Boardtb board = res.readEntity(gen_boardData);
            userData = new Usertb();
            boardData = new Boardtb();
            return board;
        } else {
            return null;
        }
    }

    public void likeImage() {
        if (user.likeImage(imageData.getImageID(), posterData.getUserID(), userData.getUserID()) == true) {
        }
    }

    public Imagetb getImage() {
        res = client.getImage(Response.class, imageData.getImageID().toString());
        if (res == Response.ok().build()) {
            Imagetb image = res.readEntity(gen_imageData);
            imageData = new Imagetb();
            return image;
        } else {
            return null;
        }
    }

    public Collection<Imagetb> searchImage() {
        res = client.getImageByName(Response.class, queryParam);
        if (res == Response.ok().build()) {
            Collection<Imagetb> images = res.readEntity(gen_images);
            return images;
        } else {
            return null;
        }
    }

    public Collection<Usertb> searchUser() {
        res = client.findUserByName(Response.class, queryParam);
        if (res == Response.ok().build()) {
            Collection<Usertb> users = res.readEntity(gen_users);
            return users;
        } else {
            return null;
        }
    }

    public String createBoard() {
        res = client.createBoard(boardData, userData.getUserID().toString(), boardData.getBoardName());
        if (res == Response.ok().build()) {
            userData = new Usertb();
            boardData = new Boardtb();
            return "./boards";
        } else {
            return "./";
        }
    }

    public String uploadImage() throws IOException {
        InputStream input = null;
        OutputStream output = null;
        FacesContext context = FacesContext.getCurrentInstance();
        String webAppRoot = context.getExternalContext().getRealPath("/");
        String relativePath = webAppRoot + "..\\..\\src\\main\\webapp\\resources\\img";

        try {
//            utx.begin();
            // insert image data
            res = client.uploadImage(imageData, String.valueOf(userData.getUserID()));
            if (res == Response.ok().build()) {
                int userid = userData.getUserID();
                userData = new Usertb();
                imageData = new Imagetb();

                // image upload
                input = uploaded_image.getInputStream();
                String file_name = uploaded_image.getSubmittedFileName();
                int lastIndex = file_name.lastIndexOf(".");
                String file_extension = file_name.substring(lastIndex + 1);
                double file_value = user.getMaxImageID();

                String file_new_name = String.valueOf(file_value) + "." + file_extension;
                output = new FileOutputStream(new File(relativePath, file_new_name));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
//                utx.commit();
                return "user/" + String.valueOf(userid);
            } else {
//                utx.rollback();
                return String.valueOf(res);
            }
        } catch (Exception e) {
            try {
//                utx.rollback();
                return String.valueOf(res);
            } catch (Exception ex) {
                return String.valueOf(res);
            }
        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

    public String setImageBoard() {
        res = client.setImageBoard(boardData, imageData.getImageID().toString());
        if (res == Response.ok().build()) {
            imageData = new Imagetb();
            return "./";
        } else {
            return "./";
        }
    }

    public String updateUser() {
        res = client.updateUser(userData, userData.getUserID().toString());
        if (res == Response.ok().build()) {
            userData = new Usertb();
            return "./";
        } else {
            return "./";
        }
    }

    public String deleteUser() {
        res = client.deleteUser(userData.getUserID().toString());
        if (res == Response.ok().build()) {
            userData = new Usertb();
            return "/main.jsf";
        } else {
            return "./";
        }
    }

    public String deleteImage() {
        res = client.deleteImage(userData.getUserID().toString(), imageData.getImageID().toString());
        if (res == Response.ok().build()) {
            userData = new Usertb();
            imageData = new Imagetb();
            return "./image";
        } else {
            return res.toString();
        }
    }

    public Collection<Usertb> getAdmins() {
        res = client.getAdmins(Response.class);
        Collection<Usertb> admins = new ArrayList<>();
        if (res == Response.ok(admins).build()) {
            return res.readEntity(gen_users);
        } else {
            return null;
        }
    }

    public String deleteAdmin() {
        res = client.deleteAdmin(userData.getUserID().toString());
        if (res == Response.ok().build()) {
            return "./";
        } else {
            return "./";
        }
    }
}
