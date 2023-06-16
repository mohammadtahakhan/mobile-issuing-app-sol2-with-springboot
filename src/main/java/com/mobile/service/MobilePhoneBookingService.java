package com.mobile.service;


import com.mobile.repository.MobilePhoneInventory;
import com.mobile.entity.BookedBy;
import com.mobile.entity.MobilePhone;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// MobilePhoneBooking class
@Component
@AllArgsConstructor
public class MobilePhoneBookingService {
    //@Autowired
    private MobilePhoneInventory inventory;

    @PostConstruct
    public void initialize() {
        this.inventory.initializePhones();
    }

    public String bookPhone(String modelName, BookedBy bookedBy) {

        String bookedPhoneId=null;
        MobilePhone availablePhoneByModel = getAvailablePhoneByModel(modelName);
        if (null!=availablePhoneByModel){
            bookSelectedMobile(bookedBy, availablePhoneByModel);
            bookedPhoneId=availablePhoneByModel.getId().toString();

        }
        else {
            System.out.println("Phone not available for booking: " + modelName);
        }

        return bookedPhoneId;

    }

    private void bookSelectedMobile(BookedBy bookedBy, MobilePhone availablePhoneByModel) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getId()==availablePhoneByModel.getId()) {
                phone.book(bookedBy);
                System.out.println(phone.getModel().getName() + " is booked by " + bookedBy.getName()+" on "+phone.getBookedAt());
                return;
            }
        }
    }

    public String returnPhone(String modelName) {
      /*  List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && !phone.isAvailable()) {
                phone.returned();
                System.out.println(modelName + " is returned");
                return;
            }
        }
        System.out.println("Phone not found for return: " + modelName);*/

        String returnedMobileId=null;
        MobilePhone bookedPhoneByModel = getBookedPhoneByModel(modelName);
        if (null!=bookedPhoneByModel){
            returnSelectedMobile(bookedPhoneByModel);
            returnedMobileId=bookedPhoneByModel.getId().toString();

        }
        else {
            System.out.println("Invalid phone for return : " + modelName);
        }

        return returnedMobileId;


    }

    public MobilePhone getAvailablePhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().replaceAll(" ","").equals(modelName) && phone.isAvailable()) {
                return phone;
            }
        }
        return null;
    }

    public MobilePhone getBookedPhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().replaceAll(" ","").equals(modelName) && !phone.isAvailable()) {
                return phone;
            }
        }
        return null;
    }

    private void returnSelectedMobile(MobilePhone bookedPhoneByModel) {
     bookedPhoneByModel.returned();
        System.out.println(bookedPhoneByModel.getModel().getName()+" Returned successfully");
    }


    public List<MobilePhone> getAllBookedPhones() {
        List<MobilePhone> phones = inventory.getPhones();
        return phones.stream()
                .filter(a->!a.isAvailable())
                .collect(Collectors.toList());
    }

    public List<MobilePhone> getAllAvailablePhones() {
        List<MobilePhone> phones = inventory.getPhones();
        return phones.stream()
                .filter(a->a.isAvailable())
                .collect(Collectors.toList());
    }

    public List<MobilePhone> getAllPhones() {
        return inventory.getPhones();

    }

    public MobilePhone getAvailablePhoneById(UUID mobilePhoneId) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getId().equals(mobilePhoneId)) {
                return phone;
            }
        }
        return null;
    }
}