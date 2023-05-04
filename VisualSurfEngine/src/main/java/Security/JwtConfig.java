///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
// */
//package Security;
//
//import ejb.userbean;
//import java.io.Serializable;
//import javax.ejb.EJB;
//import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.security.enterprise.AuthenticationException;
//import javax.security.enterprise.AuthenticationStatus;
//import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
//import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
//import javax.security.enterprise.credential.Credential;
//import javax.security.enterprise.credential.Password;
//import javax.security.enterprise.credential.UsernamePasswordCredential;
//import javax.security.enterprise.identitystore.CredentialValidationResult;
//import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
//import javax.security.enterprise.identitystore.IdentityStore;
//import javax.security.enterprise.identitystore.IdentityStoreHandler;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import record.KeepRecord;
///**
// *
// * @author harsh
// */
//@Named()
//@RequestScoped
//public class JwtConfig implements HttpAuthenticationMechanism, Serializable {
//    
//    @Inject IdentityStoreHandler handler;
//    @Inject CredentialValidationResult result;
//    @Inject AuthenticationStatus status;
//    
//    @Inject userbean user;
//    
//    
//    @Override
//    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext ctx) throws AuthenticationException {
//        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        try {
//            if(request.getRequestURI().contains("Logout")){
//                request.logout();
//                KeepRecord.reset();
//                response.sendRedirect("login.jsf");
//                return ctx.doNothing();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        try {
//            if(request.getHeader("username") != null) {
//                String username = request.getHeader("username");
//                String password = request.getHeader("password");
//                
//                Credential credential = new UsernamePasswordCredential(username, new Password(password));
//                result = handler.validate(credential);
//                
//                if(result.getStatus() == Status.VALID){
//                    status = ctx.notifyContainerAboutLogin(result);
//                    
//                    if(result.getCallerGroups().contains("Admin")){
//                        request.getRequestDispatcher("admin/Admin.jsf").forward(request, response);
//                    }
//                    if(result.getCallerGroups().contains("User")){
//                        request.getRequestDispatcher("user/Home.jsf").forward(request, response);
//                    }
//                    return status;
//                } else{
//                    response.sendRedirect("login.jsf");
//                    return AuthenticationStatus.SEND_FAILURE;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ctx.doNothing();
//    }
//    
//
//    /**
//     * Creates a new instance of JwtConfig
//     */
//    
//}
