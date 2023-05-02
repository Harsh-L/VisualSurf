/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package restApi;

import ejb.adminbean;
import ejb.adminbeanLocal;
import ejb.userbean;
import ejb.userbeanLocal;
import entity.Boardtb;
import entity.Imagetb;
import entity.Usertb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jdk.jfr.Frequency;

/**
 * REST Web Service
 *
 * @author harsh
 */
@Path("restApis")
@RequestScoped
public class RestApisResource {
    
    @EJB adminbeanLocal admin;
    @EJB userbeanLocal user;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestApisResource
     */
    public RestApisResource() {
    }

    /**
     * Retrieves representation of an instance of restApi.RestApisResource
     * @return an instance of java.lang.String
     */
    // GET METHODS
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    public Collection<Usertb> getAllUsers() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        try{
            if(!user.getAllUsers().isEmpty()){
                return user.getAllUsers();
            }else{
                return null;
            }
        
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getTest(){
        try{
            if(!user.getAllUsers().isEmpty()){
                return Response.ok(user.getAllUsers()).build();
//                return user.getAllUsers();
            }else{
                return Response.noContent().build();
            }
        
        }catch(Exception e){
            e.printStackTrace();
            return Response.noContent().build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/name/{username}")
    public Collection<Usertb> findUserByName(@PathParam("username") String username){
        return user.searchUserByName(username);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/id/{userid}")
    public Usertb findUserByID(@PathParam("userid") Integer userid){
        return user.searchUserById(userid);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}/boards")
    public List<Boardtb> findUserBoards(@PathParam("userid") Integer userid){
        return user.getUserBoards(userid);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}/boards/{boardid}")
    public Boardtb getBoard(@PathParam("userid") Integer userid, @PathParam("boardid") Integer boardid){
        return user.getBoard(userid, boardid);
    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/user/{userid}/boards/{boardid}/image/{imageid}")
//    public Imagetb getImage(@PathParam("userid") Integer userid, @PathParam("boardid") Integer boardid, @PathParam("imageid") Integer imageid){
//        Imagetb image = user.getImage(userid);
//        Usertb userdata = user.searchUserById(userid);
//        
//        
//    }
    
    /**
     * PUT method for updating or creating an instance of RestApisResource
     * @param content representation for the resource
     */
    
    // POST METHODS
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public void insertUser(@FormParam("username") String username, @FormParam("name") String name, @FormParam("email") String email, @FormParam("password") String password, @FormParam("roleid") Integer roleid) {
        user.insertUser(username, name, email, password, roleid);
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    public void uploadImage(@FormParam("userid") Integer userid, @FormParam("title") String title, @FormParam("description") String description, @FormParam("url") String imageUrl, @FormParam("tags") String Tags ){
        String[] tags_collection_temp =Tags.split(",");
        Collection<String> tags_collection = new ArrayList<>();
        for(String tag : tags_collection_temp){
            tags_collection.add(tag);
        }
        user.uploadImage(userid, title, description, imageUrl, tags_collection);
    }
    
    // PUT METHODS
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}")
    public void updateUser(@PathParam("userid") Integer userid, @FormParam("username") String username, @FormParam("name") String name, @FormParam("email") String email){
        user.updateUser(userid, username, name, email);
    }
    
    // DELETE METHODS
    @DELETE
    @Path("/user/{userid}")
    public void deleteUser(@PathParam("userid") Integer userid){
        Usertb userData = user.searchUserById(userid);
        user.deleteUser(userid, userData);
    }
    
    @DELETE
    @Path("/user/{userid}/image/{imageid}")
    public void deleteImage(@PathParam("userid") Integer userid, @PathParam("imageid") Integer imageid ){
        user.deleteImage(userid, imageid);
    }
    
    
    
    
    
}
