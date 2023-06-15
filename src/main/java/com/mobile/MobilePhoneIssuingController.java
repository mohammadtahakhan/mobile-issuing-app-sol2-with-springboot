package com.mobile;

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

    @PostMapping("/book")
    public ResponseEntity<String> bookPhone(@RequestParam("modelName") String modelName,
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

    @PostMapping("/return")
    public ResponseEntity<String> returnPhone(@RequestParam("modelName") String modelName) {
        // Parse the request parameter and call the booking service
        bookingService.returnPhone(modelName);
        return ResponseEntity.ok("Phone returned successfully: " + modelName);
    }

    @GetMapping("/available")
    public ResponseEntity<List<MobilePhone>> getAvailablePhones() {
        List<MobilePhone> availablePhones = bookingService.getAllPhones();
        return ResponseEntity.ok(availablePhones);
    }

    @GetMapping("/booked")
    public ResponseEntity<List<MobilePhone>> getBookedPhones() {
        List<MobilePhone> bookedPhones = bookingService.getAllBookedPhones();
        return ResponseEntity.ok(bookedPhones);
    }

    // Other endpoints and methods
}
