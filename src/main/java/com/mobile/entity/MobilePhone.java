package com.mobile.entity;

import java.time.LocalDateTime;
import java.util.UUID;

// MobilePhone entity class
public class MobilePhone {
    private UUID id;
    private MobileModel model;
    private boolean isAvailable;
    private LocalDateTime bookedAt;
    private BookedBy bookedBy;


    public MobilePhone(MobileModel model) {
        this.id = UUID.randomUUID();
        this.model = model;
        this.isAvailable = true;
        this.bookedAt = null;
        this.bookedBy = null;

    }

    public UUID getId() {
        return id;
    }

    public MobileModel getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public BookedBy getBookedBy() {
        return bookedBy;
    }

    public void book(BookedBy bookedBy) {
        this.isAvailable = false;
        this.bookedAt = LocalDateTime.now();
        this.bookedBy = bookedBy;
    }

    public void returned() {
        this.isAvailable = true;
        this.bookedAt = null;
        this.bookedBy = null;
    }
}

