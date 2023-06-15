package com.mobile.service;


import com.mobile.repository.MobilePhoneInventory;
import com.mobile.entity.BookedBy;
import com.mobile.entity.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// MobilePhoneBooking class
@Service
public class MobilePhoneBookingService {
    private MobilePhoneInventory inventory;

    @Autowired
    public MobilePhoneBookingService(MobilePhoneInventory inventory) {
        this.inventory = inventory;
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

    public void returnPhone(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && !phone.isAvailable()) {
                phone.returned();
                System.out.println(modelName + " is returned");
                return;
            }
        }
        System.out.println("Phone not found for return: " + modelName);
    }

    public MobilePhone getPhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName)) {
                return phone;
            }
        }
        return null;
    }

    public MobilePhone getAvailablePhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && phone.isAvailable()) {
                return phone;
            }
        }
        return null;
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

    public MobilePhone getBookedPhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && !phone.isAvailable()) {
                return phone;
            }
        }
        return null;
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