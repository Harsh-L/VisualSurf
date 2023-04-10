/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author harsh
 */
@Entity
@Table(name = "imagetb")
@NamedQueries({
    @NamedQuery(name = "Imagetb.findAll", query = "SELECT i FROM Imagetb i"),
    @NamedQuery(name = "Imagetb.findByImageID", query = "SELECT i FROM Imagetb i WHERE i.imageID = :imageID"),
    @NamedQuery(name = "Imagetb.findByTitle", query = "SELECT i FROM Imagetb i WHERE i.title = :title"),
    @NamedQuery(name = "Imagetb.findByDescription", query = "SELECT i FROM Imagetb i WHERE i.description = :description"),
    @NamedQuery(name = "Imagetb.findByImageUrl", query = "SELECT i FROM Imagetb i WHERE i.imageUrl = :imageUrl"),
    @NamedQuery(name = "Imagetb.findByCreationDate", query = "SELECT i FROM Imagetb i WHERE i.creationDate = :creationDate"),
    @NamedQuery(name = "Imagetb.findByLikeCount", query = "SELECT i FROM Imagetb i WHERE i.likeCount = :likeCount")})
public class Imagetb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "imageID")
    private Integer imageID;
    @Size(max = 25)
    @Column(name = "title")
    private String title;
    @Size(max = 150)
    @Column(name = "description")
    private String description;
    @Size(max = 100)
    @Column(name = "image_url")
    private String imageUrl;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "tags")
    private String tags;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creationDate")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "likeCount")
    private Integer likeCount;
    @ManyToMany(mappedBy = "imagetbCollection")
    private Collection<Usertb> usertbCollection;
    @ManyToMany(mappedBy = "imagetbCollection")
    private Collection<Boardtb> boardtbCollection;
    @JoinColumn(name = "userID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private Usertb userID;

    public Imagetb() {
    }

    public Imagetb(Integer imageID) {
        this.imageID = imageID;
    }

    public Imagetb(Integer imageID, Date creationDate) {
        this.imageID = imageID;
        this.creationDate = creationDate;
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Collection<Usertb> getUsertbCollection() {
        return usertbCollection;
    }

    public void setUsertbCollection(Collection<Usertb> usertbCollection) {
        this.usertbCollection = usertbCollection;
    }

    public Collection<Boardtb> getBoardtbCollection() {
        return boardtbCollection;
    }

    public void setBoardtbCollection(Collection<Boardtb> boardtbCollection) {
        this.boardtbCollection = boardtbCollection;
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
        hash += (imageID != null ? imageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagetb)) {
            return false;
        }
        Imagetb other = (Imagetb) object;
        if ((this.imageID == null && other.imageID != null) || (this.imageID != null && !this.imageID.equals(other.imageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Imagetb[ imageID=" + imageID + " ]";
    }
    
}
