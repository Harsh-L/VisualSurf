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
import java.math.BigDecimal;
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
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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

    @EJB
    adminbeanLocal admin;
    @EJB
    userbeanLocal user;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestApisResource
     */
    public RestApisResource() {
    }

    /**
     * Retrieves representation of an instance of restApi.RestApisResource
     *
     * @return an instance of java.lang.String
     */
    // USER METHODS
    // GET METHODS
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/users")
    public Response getAllUsers() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        JsonObject json = null;
        try {
            if (user.getAllUsers() != null) {
                return Response.ok(user.getAllUsers()).build();
            } else {
                json = Json.createObjectBuilder().add("error", "users not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Internal Server Error").build();
            return Response.status(500).entity(json).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/name/{username}")
    public Response findUserByName(@PathParam("username") String username) {
        JsonObject json = null;
        try {
            if (user.searchUserByName(username) != null) {
                return Response.ok(user.searchUserByName(username)).build();
            } else {

                json = Json.createObjectBuilder().add("error", "User not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error finding user").build();
            return Response.status(500).entity(json).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/id/{userid}")
    public Response findUserByID(@PathParam("userid") Integer userid) {
        JsonObject json = null;
        try {
            if (user.searchUserById(userid) != null) {
                return Response.ok(user.searchUserById(userid)).build();
            } else {
                json = Json.createObjectBuilder().add("error", "user not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error finding user").build();
            return Response.status(500).entity(json).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}/boards")
    public Response findUserBoards(@PathParam("userid") Integer userid) {
        JsonObject json = null;
        try {
            if (user.getUserBoards(userid) != null) {
                return Response.ok(user.getUserBoards(userid)).build();
            } else {

                json = Json.createObjectBuilder().add("error", "board not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error finding boards").build();
            return Response.status(500).entity(json).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}/boards/{boardid}")
    public Response getBoard(@PathParam("userid") Integer userid, @PathParam("boardid") Integer boardid) {
        JsonObject json = null;
        try {
            if (user.getBoard(userid, boardid) != null) {
                return Response.ok(user.getBoard(userid, boardid)).build();
            } else {
                json = Json.createObjectBuilder().add("error", "Board not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error finding board").build();
            return Response.status(500).entity(json).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/image/{imageid}")
    public Response getImage(@PathParam("imageid") Integer imageid) {
//        Imagetb image = user.getImage(userid);
//        Usertb userdata = user.searchUserById(userid);
        JsonObject json = null;
        try {
            if (user.getImage(imageid) != null) {
                return Response.ok(user.getImage(imageid)).build();
            } else {
                json = Json.createObjectBuilder().add("error", "Image not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error finding image").build();
            return Response.status(500).entity(json).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/image")
    public Response getImageByName(@QueryParam("name") String imagename) {
        JsonObject json = null;
        try {
            if (user.searchImage(imagename) != null) {
                return Response.ok(user.searchImage(imagename)).build();
            } else {
                json = Json.createObjectBuilder().add("error", "Image not found").build();
                return Response.status(404).entity(json).build();
            }

        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error finding image").build();
            return Response.status(500).entity(json).build();
        }
    }

    // POST METHODS
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        JsonObject json = null;
        if (user.login(username, password) != null) {
            return Response.ok(user.login(username, password)).build();
        } else {
            json = Json.createObjectBuilder().add("error", "Error occured while Logging in").build();
            return Response.status(404).entity(json).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public Response insertUser(@FormParam("username") String username, @FormParam("name") String name, @FormParam("email") String email, @FormParam("password") String password, @FormParam("roleid") Integer roleid) {
        JsonObject json = null;

        if (user.insertUser(username, name, email, password, roleid) == true) {
            return Response.ok().build();
        } else {
            json = Json.createObjectBuilder().add("error", "Error occured while inserting User").build();
            return Response.status(501).entity(json).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}/board/{boardname}")
    public Response createBoard(@PathParam("userid") Integer userid, @FormParam("boardname") String boardname) {
        JsonObject json = null;
        if (user.createBoard(userid, boardname) == true) {
            return Response.ok().build();
        } else {
            json = Json.createObjectBuilder().add("error", "Error creating board").build();
            return Response.status(500).entity(json).build();
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    public Response uploadImage(@FormParam("userid") Integer userid, @FormParam("title") String title, @FormParam("description") String description, @FormParam("url") String imageUrl, @FormParam("tags") String Tags) {
        JsonObject json = null;

        String[] tags_collection_temp = Tags.split(",");
        Collection<String> tags_collection = new ArrayList<>();
        for (String tag : tags_collection_temp) {
            tags_collection.add(tag);
        }
        if (user.uploadImage(userid, title, description, imageUrl, tags_collection) == true) {
            return Response.ok().build();
        } else {
            json = Json.createObjectBuilder().add("error", "Error occured while uploading image").build();
            return Response.status(501).entity(json).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/image/{imageid}")
    public Response setImageBoard(@PathParam("imageid") Integer imageid, @FormParam("userid") Integer userid, @FormParam("boardid") Integer boardid) {
        JsonObject json = null;
        try {
            if (user.setImageBoard(userid, imageid, boardid) == true) {
                return Response.ok().build();
            } else {
                json = Json.createObjectBuilder().add("error", "Error occured while uploading image").build();
                return Response.status(501).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error occured while uploading image").build();
            return Response.status(501).entity(json).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/image/{imageid}/like")
    public Response likeImage(@PathParam("imageid") Integer imageid, @FormParam("uploader") Integer uploaderid, @FormParam("liker") Integer likerid) {
        JsonObject json = null;
        try {
            if (user.likeImage(imageid, uploaderid, likerid) == true) {
                return Response.ok().build();
            } else {
                json = Json.createObjectBuilder().add("error", "Error occured while liking image").build();
                return Response.status(501).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error occured while liking image").build();
            return Response.status(501).entity(json).build();
        }
    }

    // PUT METHODS
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}")
    public Response updateUser(@PathParam("userid") Integer userid, @FormParam("username") String username, @FormParam("name") String name, @FormParam("email") String email) {
        JsonObject json = null;
        try {
            if (user.searchUserById(userid) != null) {
                if (user.updateUser(userid, username, name, email)) {
                    return Response.ok().build();
                } else {
                    json = Json.createObjectBuilder().add("error", "Error occured while updating user").build();
                    return Response.status(500).entity(json).build();
                }
            } else {
                json = Json.createObjectBuilder().add("error", "User not Found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error occured while updating user").build();
            return Response.status(500).entity(json).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}/board")
    public Response updateBoardName(@PathParam("userid") Integer userid, @FormParam("boardid") Integer boardid, @FormParam("boardName") String boardname) {
        JsonObject json = null;
        try {
            if (user.updateBoardName(userid, boardid, boardname) == true) {
                return Response.ok().build();
            } else {
                json = Json.createObjectBuilder().add("error", "Board Not Found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error occured while updating board").build();
            return Response.status(501).entity(json).build();
        }
    }

    // DELETE METHODS
    @DELETE
    @Path("/user/{userid}")
    public Response deleteUser(@PathParam("userid") Integer userid) {
        JsonObject json = null;
        try {
            if (user.searchUserById(userid) != null) {
                Usertb userData = user.searchUserById(userid);
                if (user.deleteUser(userid, userData) == true) {
                    return Response.ok().build();
                } else {
                    json = Json.createObjectBuilder().add("error", "Error occured while deleting user").build();
                    return Response.status(500).entity(json).build();
                }
            } else {
                json = Json.createObjectBuilder().add("error", "User not Found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error occured while deleting user").build();
            return Response.status(500).entity(json).build();
        }
    }

    @DELETE
    @Path("/user/{userid}/image/{imageid}")
    public Response deleteImage(@PathParam("userid") Integer userid, @PathParam("imageid") Integer imageid) {
        JsonObject json = null;
        try {
            if (user.getImage(imageid) != null) {
                if (user.deleteImage(userid, imageid)) {
                    return Response.ok().build();
                } else {
                    json = Json.createObjectBuilder().add("error", "Error occured while deleting image").build();
                    return Response.status(500).entity(json).build();
                }
            } else {
                json = Json.createObjectBuilder().add("error", "Image not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Image not found").build();
            return Response.status(404).entity(json).build();
        }
    }

    // ADMIN METHODS
    // GET METHODS
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/admins")
    public Response getAdmins() {
        JsonObject json = null;
        try {
            if (admin.getAdminList()!= null) {
                return Response.ok(admin.getAdminList()).build();
            } else {
                json = Json.createObjectBuilder().add("error", "admins not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Internal Server Error").build();
            return Response.status(500).entity(json).build();
        }
    }

    // DELETE METHODS
    @DELETE
    @Path("/admin/{adminid}")
    public Response deleteAdmin(@PathParam("adminid") Integer adminid) {
        JsonObject json = null;
        try {
            Usertb admin_data = admin.searchUserById(adminid);
            if (admin_data != null) {
                if (admin.deleteAdmin(adminid, admin_data) == true) {
                    return Response.ok().build();
                } else {
                    json = Json.createObjectBuilder().add("error", "Error deleting admin").build();
                    return Response.status(500).entity(json).build();
                }
            } else {
                json = Json.createObjectBuilder().add("error", "Admin not found").build();
                return Response.status(404).entity(json).build();
            }
        } catch (Exception e) {
            json = Json.createObjectBuilder().add("error", "Error deleting admin").build();
            return Response.status(500).entity(json).build();
        }
    }

}
