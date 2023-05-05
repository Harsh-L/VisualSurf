///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
// */
//package IdentityStoreConfig;
//
////import javax.inject.Named;
//import javax.enterprise.context.ApplicationScoped;
//import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
//import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
//
///**
// *
// * @author harsh
// */
//@DatabaseIdentityStoreDefinition(
//        dataSourceLookup = "jdbc/__project",
//        callerQuery = "SELECT password FROM Usertb WHERE username = ?",
//        groupsQuery = "SELECT r.roleName FROM Usertb u JOIN Roletb r ON u.roleID.roleID = r.roleID WHERE u.UserID = ?",
//        hashAlgorithm = Pbkdf2PasswordHash.class,
//        priority = 30
//)
//
////@Named(value = "iDStore")
//@ApplicationScoped
//public class IDStore {
//    
//}
