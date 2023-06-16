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

    /**
     * This API will book a given mobile phone model
     * @param modelName
     * @param bookedBy
     * @return
     */
    @PostMapping(value = "/book/{modelName}")
    public ResponseEntity<String> bookPhone(@ApiParam(allowableValues = "Samsung Galaxy S9,Samsung Galaxy S8,Motorola Nexus 6,OnePlus 9,Apple iPhone 13,Apple iPhone 12,Apple iPhone 11,iPhone X,Nokia 3310") @RequestParam("modelName") String modelName,
                                            @RequestParam("bookedBy") String bookedBy) {
        // Parse the request parameters and call the booking service
        BookedBy bookedByEntity = BookedBy.builder().name(bookedBy).build();

        String phoneId = bookingService.bookPhone(modelName, bookedByEntity);
        if (phoneId != null) {
            return ResponseEntity.ok("Phone booked successfully. Phone Model: " + modelName);
        } else {
            return ResponseEntity.badRequest().body("Phone not available for booking: " + modelName);
        }
    }

    /**
     * This API will return a given issued mobile model
     * @param modelName
     * @return
     */
    @PostMapping("/return/{modelName}")
    public ResponseEntity<String> returnPhone(@ApiParam(allowableValues = "Samsung Galaxy S9,Samsung Galaxy S8,Motorola Nexus 6,OnePlus 9,Apple iPhone 13,Apple iPhone 12,Apple iPhone 11,iPhone X,Nokia 3310") @RequestParam("modelName") String modelName) {
        // Parse the request parameter and call the booking service
        String returnedPhoneId = bookingService.returnPhone(modelName);
        if (returnedPhoneId!=null) {
            return ResponseEntity.ok("Phone returned successfully phone model name: " + modelName);
        }
        else {
            return ResponseEntity.ok("Invalid phone for return phone model name: " + modelName);
        }
    }

    /**This API will give list of available mobiles
     *
     * @return
     */
    @GetMapping("/availableList")
    public ResponseEntity<List<MobilePhone>> getAvailablePhones() {
        List<MobilePhone> availablePhones = bookingService.getAllAvailablePhones();
        return ResponseEntity.ok(availablePhones);
    }

    /**This API will give list of booked mobiles
     *
     * @return
     */
    @GetMapping("/bookedList")
    public ResponseEntity<List<MobilePhone>> getBookedPhones() {
        List<MobilePhone> bookedPhones = bookingService.getAllBookedPhones();
        return ResponseEntity.ok(bookedPhones);
    }

    /**This API will give list of all mobiles
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<MobilePhone>> getAllPhones() {
        List<MobilePhone> availablePhones = bookingService.getAllPhones();
        return ResponseEntity.ok(availablePhones);
    }
}
