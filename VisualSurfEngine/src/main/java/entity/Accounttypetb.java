/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
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
@Table(name = "accounttypetb")
@NamedQueries({
    @NamedQuery(name = "Accounttypetb.findAll", query = "SELECT a FROM Accounttypetb a"),
    @NamedQuery(name = "Accounttypetb.findByAccID", query = "SELECT a FROM Accounttypetb a WHERE a.accID = :accID"),
    @NamedQuery(name = "Accounttypetb.findByAccName", query = "SELECT a FROM Accounttypetb a WHERE a.accName = :accName")})
public class Accounttypetb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accID")
    private Integer accID;
    @Size(max = 20)
    @Column(name = "accName")
    private String accName;
    @JsonbTransient
    @OneToMany(mappedBy = "accountID")
    private Collection<Usertb> usertbCollection;

    public Accounttypetb() {
    }

    public Accounttypetb(Integer accID) {
        this.accID = accID;
    }

    public Integer getAccID() {
        return accID;
    }

    public void setAccID(Integer accID) {
        this.accID = accID;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
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
        hash += (accID != null ? accID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounttypetb)) {
            return false;
        }
        Accounttypetb other = (Accounttypetb) object;
        if ((this.accID == null && other.accID != null) || (this.accID != null && !this.accID.equals(other.accID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Accounttypetb[ accID=" + accID + " ]";
    }
    
}
