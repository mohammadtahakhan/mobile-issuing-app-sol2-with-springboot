package com.mobile.controller;

import com.mobile.entity.BookedBy;
import com.mobile.entity.MobilePhone;
import com.mobile.service.MobilePhoneBookingService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// MobilePhoneController class
@RestController
@RequestMapping("/phones")
public class MobilePhoneIssuingController {
    private MobilePhoneBookingService bookingService;

    @Autowired
    public MobilePhoneIssuingController(MobilePhoneBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(value = "/book/{modelName}")
    public ResponseEntity<String> bookPhone(@ApiParam(allowableValues = "Samsung Galaxy S9,Samsung Galaxy S8,Motorola Nexus 6,OnePlus 9,Apple iPhone 13,Apple iPhone 12,Apple iPhone 11,iPhone X,Nokia 3310") @RequestParam("modelName") String modelName,
                                            @RequestParam("bookedBy") String bookedBy) {
        // Parse the request parameters and call the booking service
        BookedBy bookedByEntity = new BookedBy(bookedBy);
        String phoneId = bookingService.bookPhone(modelName, bookedByEntity);
        if (phoneId != null) {
            return ResponseEntity.ok("Phone booked successfully. Phone ID: " + phoneId);
        } else {
            return ResponseEntity.badRequest().body("Phone not available for booking: " + modelName);
        }
    }

    @PostMapping("/return/{modelName}")
    public ResponseEntity<String> returnPhone(@ApiParam(allowableValues = "Samsung Galaxy S9,Samsung Galaxy S8,Motorola Nexus 6,OnePlus 9,Apple iPhone 13,Apple iPhone 12,Apple iPhone 11,iPhone X,Nokia 3310") @RequestParam("modelName") String modelName) {
        // Parse the request parameter and call the booking service
        bookingService.returnPhone(modelName);
        return ResponseEntity.ok("Phone returned successfully: " + modelName);
    }

    @GetMapping("/availablephones")
    public ResponseEntity<List<MobilePhone>> getAvailablePhones() {
        List<MobilePhone> availablePhones = bookingService.getAllAvailablePhones();
        return ResponseEntity.ok(availablePhones);
    }

    @GetMapping("/bookedphones")
    public ResponseEntity<List<MobilePhone>> getBookedPhones() {
        List<MobilePhone> bookedPhones = bookingService.getAllBookedPhones();
        return ResponseEntity.ok(bookedPhones);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MobilePhone>> getAllPhones() {
        List<MobilePhone> availablePhones = bookingService.getAllPhones();
        return ResponseEntity.ok(availablePhones);
    }
}
