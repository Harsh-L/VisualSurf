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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author harsh
 */
@Entity
@Table(name = "categorytb")
@NamedQueries({
    @NamedQuery(name = "Categorytb.findAll", query = "SELECT c FROM Categorytb c"),
    @NamedQuery(name = "Categorytb.findByCatID", query = "SELECT c FROM Categorytb c WHERE c.catID = :catID"),
    @NamedQuery(name = "Categorytb.findByCatName", query = "SELECT c FROM Categorytb c WHERE c.catName = :catName")})
public class Categorytb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catID")
    private Integer catID;
    @Size(max = 50)
    @Column(name = "catName")
    private String catName;
    @JsonbTransient
    @ManyToMany(mappedBy = "categorytbCollection")
    private Collection<Usertb> usertbCollection;

    public Categorytb() {
    }

    public Categorytb(Integer catID) {
        this.catID = catID;
    }

    public Integer getCatID() {
        return catID;
    }

    public void setCatID(Integer catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
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
        hash += (catID != null ? catID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorytb)) {
            return false;
        }
        Categorytb other = (Categorytb) object;
        if ((this.catID == null && other.catID != null) || (this.catID != null && !this.catID.equals(other.catID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Categorytb[ catID=" + catID + " ]";
    }
    
}
