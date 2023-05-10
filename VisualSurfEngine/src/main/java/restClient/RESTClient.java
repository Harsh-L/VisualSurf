/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package restClient;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:RestApisResource
 * [restApis]<br>
 * USAGE:
 * <pre>
 *        RESTClient client = new RESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author harsh
 */
public class RESTClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/VisualSurfEngine/resources";

    public RESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("restApis");
    }

    public <T> T getAllUsers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("users");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getBoard(Class<T> responseType, String userid, String boardid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("user/{0}/boards/{1}", new Object[]{userid, boardid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response setImageBoard(Object requestEntity, String imageid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("image/{0}", new Object[]{imageid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public Response updateUser(Object requestEntity, String userid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("user/{0}", new Object[]{userid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T getAdmins(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("admins");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response deleteAdmin(String adminid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("admin/{0}", new Object[]{adminid})).request().delete(Response.class);
    }

    public Response updateBoardName(Object requestEntity, String userid, String boardid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("user/{0}/board/{1}", new Object[]{userid, boardid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public Response insertUser(Object requestEntity) throws ClientErrorException {
        return webTarget.path("addUser").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public Response createBoard(Object requestEntity, String userid, String boardname) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("user/{0}/board/{1}", new Object[]{userid, boardname})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T findUserByName(Class<T> responseType, String username) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("user/name/{0}", new Object[]{username}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getImageByName(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (name != null) {
            resource = resource.queryParam("name", name);
        }
        resource = resource.path("image");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response deleteUser(String userid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("user/{0}", new Object[]{userid})).request().delete(Response.class);
    }

    public <T> T findUserByID(Class<T> responseType, String userid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("user/id/{0}", new Object[]{userid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public Response deleteImage(String userid, String imageid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("user/{0}/image/{1}", new Object[]{userid, imageid})).request().delete(Response.class);
    }

    public Response uploadImage(Object requestEntity, String userid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("user/{0}/upload", new Object[]{userid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    public <T> T getImage(Class<T> responseType, String imageid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("image/{0}", new Object[]{imageid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findUserBoards(Class<T> responseType, String userid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("user/{0}/boards", new Object[]{userid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
