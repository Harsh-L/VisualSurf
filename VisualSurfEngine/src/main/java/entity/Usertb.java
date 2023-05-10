/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author harsh
 */
@Entity
@Table(name = "usertb")
@NamedQueries({
    @NamedQuery(name = "Usertb.findAll", query = "SELECT u FROM Usertb u"),
    @NamedQuery(name = "Usertb.findByUserID", query = "SELECT u FROM Usertb u WHERE u.userID = :userID"),
    @NamedQuery(name = "Usertb.findByUsername", query = "SELECT u FROM Usertb u WHERE u.username = :username"),
    @NamedQuery(name = "Usertb.findByName", query = "SELECT u FROM Usertb u WHERE u.name = :name"),
    @NamedQuery(name = "Usertb.findByEmail", query = "SELECT u FROM Usertb u WHERE u.email = :email"),
    @NamedQuery(name = "Usertb.findByPassword", query = "SELECT u FROM Usertb u WHERE u.password = :password")})
public class Usertb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserID")
    private Integer userID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 35)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "password")
    private String password;
    @JoinTable(name = "followtb", joinColumns = {
        @JoinColumn(name = "user", referencedColumnName = "UserID")}, inverseJoinColumns = {
        @JoinColumn(name = "follower", referencedColumnName = "UserID")})
    @ManyToMany
    private Collection<Usertb> usertbCollection;
    @ManyToMany(mappedBy = "usertbCollection")
    private Collection<Usertb> usertbCollection1;
    @JoinTable(name = "like_tb", joinColumns = {
        @JoinColumn(name = "userID", referencedColumnName = "UserID")}, inverseJoinColumns = {
        @JoinColumn(name = "imageID", referencedColumnName = "imageID")})
    @ManyToMany
    private Collection<Imagetb> imagetbCollection;
    @JoinTable(name = "user_cattb", joinColumns = {
        @JoinColumn(name = "userID", referencedColumnName = "UserID")}, inverseJoinColumns = {
        @JoinColumn(name = "catID", referencedColumnName = "catID")})
    @ManyToMany
    private Collection<Categorytb> categorytbCollection;
    @JoinColumn(name = "accountID", referencedColumnName = "accID")
    @ManyToOne
    private Accounttypetb accountID;
    @JoinColumn(name = "roleID", referencedColumnName = "roleID")
    @ManyToOne(optional = false)
    private Roletb roleID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Boardtb> boardtbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Imagetb> imagetbCollection1;

    public Usertb() {
    }

    public Usertb(Integer userID) {
        this.userID = userID;
    }

    public Usertb(Integer userID, String username, String name, String password) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Usertb> getUsertbCollection() {
        return usertbCollection;
    }

    public void setUsertbCollection(Collection<Usertb> usertbCollection) {
        this.usertbCollection = usertbCollection;
    }

    public Collection<Usertb> getUsertbCollection1() {
        return usertbCollection1;
    }

    public void setUsertbCollection1(Collection<Usertb> usertbCollection1) {
        this.usertbCollection1 = usertbCollection1;
    }

    public Collection<Imagetb> getImagetbCollection() {
        return imagetbCollection;
    }

    public void setImagetbCollection(Collection<Imagetb> imagetbCollection) {
        this.imagetbCollection = imagetbCollection;
    }

    public Collection<Categorytb> getCategorytbCollection() {
        return categorytbCollection;
    }

    public void setCategorytbCollection(Collection<Categorytb> categorytbCollection) {
        this.categorytbCollection = categorytbCollection;
    }

    public Accounttypetb getAccountID() {
        return accountID;
    }

    public void setAccountID(Accounttypetb accountID) {
        this.accountID = accountID;
    }

    public Roletb getRoleID() {
        return roleID;
    }

    public void setRoleID(Roletb roleID) {
        this.roleID = roleID;
    }

    public Collection<Boardtb> getBoardtbCollection() {
        return boardtbCollection;
    }

    public void setBoardtbCollection(Collection<Boardtb> boardtbCollection) {
        this.boardtbCollection = boardtbCollection;
    }

    public Collection<Imagetb> getImagetbCollection1() {
        return imagetbCollection1;
    }

    public void setImagetbCollection1(Collection<Imagetb> imagetbCollection1) {
        this.imagetbCollection1 = imagetbCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertb)) {
            return false;
        }
        Usertb other = (Usertb) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usertb[ userID=" + userID + " ]";
    }

}
