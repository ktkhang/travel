package com.major.project.travel.common;

import com.major.project.travel.util.Utility;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by HUY on 9/9/2018
 */
@MappedSuperclass
public class CommonSerialize implements Serializable {

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate = new Date();

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "uid", length = Utility.UID_LENGTH, unique = true, nullable = false)
    private String uid;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
