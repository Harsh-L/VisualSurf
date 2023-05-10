/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Security;

import JWTrest.Constants;
import static JWTrest.Constants.AUTHORIZATION_HEADER;
import static JWTrest.Constants.BEARER;
import JWTrest.JWTCredential;
import JWTrest.TokenProvider;
import ejb.userbean;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import record.KeepRecord;

/**
 *
 * @author harsh
 */
@Named
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism, Serializable {

    @Inject
    IdentityStoreHandler handler;
    CredentialValidationResult result;
    AuthenticationStatus status;
    @Inject
    TokenProvider tokenProvider;
//    @Inject
//    userbean userBean;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext ctx) throws AuthenticationException {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        try {
            if (request.getRequestURI().contains("Logout")) {
                request.logout();
                KeepRecord.reset();
                response.sendRedirect("login.jsf");
                return ctx.doNothing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String token = extractToken(ctx);
        try {
//            System.out.println("Name = " + request.getParameter("username"));
            if (token == null && request.getParameter("username") != null) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
//                System.out.println("In Auth");
//                String username = userBean.getUsername();
//                String password = userBean.getPassword();
//                
                Credential credential = new UsernamePasswordCredential(username, new Password(password));
                result = handler.validate(credential);

                if (result.getStatus() == Status.VALID) {
                    KeepRecord.setErrorStatus("");
                    AuthenticationStatus status = createToken(result, ctx);

                    status = ctx.notifyContainerAboutLogin(result);

                    //   KeepRecord.setUsername(username);
                    //   KeepRecord.setPassword(password);
                    KeepRecord.setPrincipal(result.getCallerPrincipal());
                    KeepRecord.setRoles(result.getCallerGroups());
                    KeepRecord.setCredential(credential);

                    if (result.getCallerGroups().contains("Admin")) {
                        request.getRequestDispatcher("admin/Admin.jsf").forward(request, response);
                    }
                    if (result.getCallerGroups().contains("Supervisor")) {
                        request.getRequestDispatcher("user/User.jsf").forward(request, response);
                    }

                    return status;

                } else {
                    KeepRecord.setErrorStatus("Either Username or Password is wrong !");
                    response.sendRedirect("login.jsf");

                    //lbean.setStatus(AuthenticationStatus.SEND_FAILURE);
                    return ctx.doNothing();
                    // request.getServletContext().getRequestDispatcher("/Login.jsf").forward(request, response);
                }
            }

            if (KeepRecord.getToken() != null) {
                  Credential credential1 = new UsernamePasswordCredential(KeepRecord.getUsername(), new Password(KeepRecord.getPassword()));
                result = handler.validate(KeepRecord.getCredential());
                AuthenticationStatus status = createToken(result, ctx);
                
                if(request.getRequestURI().contains("Admin") && result.getCallerGroups().contains("User"))
                {
                    ctx.responseUnauthorized();
                }
               else if(request.getRequestURI().contains("User") && result.getCallerGroups().contains("Admin"))
               {
                  ctx.responseUnauthorized();
               }

                ctx.notifyContainerAboutLogin(KeepRecord.getPrincipal(), KeepRecord.getRoles());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (token != null) {
            // validation of the jwt credential

            return validateToken(token, ctx);
        } else if (ctx.isProtected()) {
            // A protected resource is a resource for which a constraint has been defined.
            // if there are no credentials and the resource is protected, we response with unauthorized status
            return ctx.responseUnauthorized();
        }
        return ctx.doNothing();
    }

    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
                // System.out.println("JWTAuthenticationMechanism-Token Validated");
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());

            }
            // if token invalid, response with unauthorized status
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
            //LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
            return context.responseUnauthorized();
        }
    }

    /**
     * Create the JWT using CredentialValidationResult received from
     * IdentityStoreHandler
     *
     * @param result the result from validation of UsernamePasswordCredential
     * @param context
     * @return the AuthenticationStatus to notify the container
     */
    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        // if (!isRememberMe(context)) {
        // if (true) {
        String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
        //context.getRequest().getSession().setAttribute("token", jwt);
        KeepRecord.setToken(jwt);
            context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
        System.out.println("Token Value" + jwt);

        //}
        System.out.println("JWTAuthenticationMechanism - Token Created");

        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    /**
     * To extract the JWT from Authorization HTTP header
     *
     * @param context
     * @return The JWT access tokens
     */
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
            KeepRecord.setToken(token);
            //  System.out.println("JWTAuthenticationMechanism - Extract Tokens");
            return token;
        }
        return null;
    }
    
//        public Boolean isRememberMe(HttpMessageContext context) {
//        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
//    }

    /**
     * Creates a new instance of JwtConfig
     */
}
