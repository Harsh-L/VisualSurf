/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package record;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Set;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;

/**
 *
 * @author harsh
 */
@Named
@SessionScoped
public class KeepRecord implements Serializable {

    /**
     * Creates a new instance of KeepRecord
     */
    private static CredentialValidationResult result;
    private static CallerPrincipal principal;
    private static Set<String> roles;
    private static String token;
    private static String username;
    private static String password;
    private static String errorStatus;
    private static Credential credential;

    public static String getErrorStatus() {
        return errorStatus;
    }

    public static Credential getCredential() {
        return credential;
    }

    public static void setCredential(Credential credential) {
        KeepRecord.credential = credential;
    }

    public static void setErrorStatus(String errorStatus) {
        KeepRecord.errorStatus = errorStatus;
    }

    public KeepRecord() {
        principal = null;
        token = null;
        username = null;
        password = null;
        token = null;
        errorStatus = "";
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        KeepRecord.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        KeepRecord.password = password;
    }

    public static CredentialValidationResult getResult() {
        return result;
    }

    public static void setResult(CredentialValidationResult result) {
        KeepRecord.result = result;
    }

    public static CallerPrincipal getPrincipal() {
        return principal;
    }

    public static void setPrincipal(CallerPrincipal principal) {
        KeepRecord.principal = principal;
    }

    public static Set<String> getRoles() {
        return roles;
    }

    public static void setRoles(Set<String> roles) {
        KeepRecord.roles = roles;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        KeepRecord.token = token;
    }

    public static void reset() {

        principal = null;
        token = null;
        username = null;
        password = null;
        token = null;
        errorStatus = "";
    }
}
