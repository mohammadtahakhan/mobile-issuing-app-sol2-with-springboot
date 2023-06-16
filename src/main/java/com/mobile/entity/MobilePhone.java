package com.mobile.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

// MobilePhone entity class
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobilePhone {
    private UUID id;
    private MobileModel model;
    private boolean isAvailable;
    private LocalDate bookedAt;
    private BookedBy bookedBy;

    public void book(BookedBy bookedBy) {
        
        this.isAvailable = false;
        this.bookedAt = LocalDate.now();
        this.bookedBy = bookedBy;
    }

    public void returned() {
        this.isAvailable = true;
        this.bookedAt = null;
        this.bookedBy = null;
    }
}

