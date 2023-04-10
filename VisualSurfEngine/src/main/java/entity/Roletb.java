/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author harsh
 */
@Entity
@Table(name = "roletb")
@NamedQueries({
    @NamedQuery(name = "Roletb.findAll", query = "SELECT r FROM Roletb r"),
    @NamedQuery(name = "Roletb.findByRoleID", query = "SELECT r FROM Roletb r WHERE r.roleID = :roleID"),
    @NamedQuery(name = "Roletb.findByRoleName", query = "SELECT r FROM Roletb r WHERE r.roleName = :roleName")})
public class Roletb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roleID")
    private Integer roleID;
    @Size(max = 20)
    @Column(name = "roleName")
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleID")
    private Collection<Usertb> usertbCollection;

    public Roletb() {
    }

    public Roletb(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<Usertb> getUsertbCollection() {
        return usertbCollection;
    }

    public void setUsertbCollection(Collection<Usertb> usertbCollection) {
        this.usertbCollection = usertbCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleID != null ? roleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roletb)) {
            return false;
        }
        Roletb other = (Roletb) object;
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Roletb[ roleID=" + roleID + " ]";
    }
    
}
