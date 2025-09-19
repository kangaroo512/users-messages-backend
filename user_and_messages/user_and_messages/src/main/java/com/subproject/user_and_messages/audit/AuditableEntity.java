package com.subproject.user_and_messages.audit;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    private Date firstCreated;


    @PrePersist
    public void onCreate() {
        Date date = new Date();
        this.firstCreated = date;
        this.lastUpdated = date;
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdated = new Date();
    }

    
}
