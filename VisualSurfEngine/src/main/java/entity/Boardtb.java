/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author harsh
 */
@Entity
@Table(name = "boardtb")
@NamedQueries({
    @NamedQuery(name = "Boardtb.findAll", query = "SELECT b FROM Boardtb b"),
    @NamedQuery(name = "Boardtb.findByBoardid", query = "SELECT b FROM Boardtb b WHERE b.boardid = :boardid"),
    @NamedQuery(name = "Boardtb.findByBoardName", query = "SELECT b FROM Boardtb b WHERE b.boardName = :boardName")})
public class Boardtb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "boardid")
    private Integer boardid;
    @Size(max = 30)
    @Column(name = "boardName")
    private String boardName;
    @JoinTable(name = "board_imagetb", joinColumns = {
        @JoinColumn(name = "boardID", referencedColumnName = "boardid")}, inverseJoinColumns = {
        @JoinColumn(name = "imageID", referencedColumnName = "imageID")})
    @ManyToMany
    private Collection<Imagetb> imagetbCollection;
    @JoinColumn(name = "userID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Boardtb() {
    }

    public Boardtb(Integer boardid) {
        this.boardid = boardid;
    }

    public Integer getBoardid() {
        return boardid;
    }

    public void setBoardid(Integer boardid) {
        this.boardid = boardid;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Collection<Imagetb> getImagetbCollection() {
        return imagetbCollection;
    }

    public void setImagetbCollection(Collection<Imagetb> imagetbCollection) {
        this.imagetbCollection = imagetbCollection;
    }

    public Usertb getUserID() {
        return userID;
    }

    public void setUserID(Usertb userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boardid != null ? boardid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boardtb)) {
            return false;
        }
        Boardtb other = (Boardtb) object;
        if ((this.boardid == null && other.boardid != null) || (this.boardid != null && !this.boardid.equals(other.boardid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Boardtb[ boardid=" + boardid + " ]";
    }
    
}
