package com.mobile.repository;

import com.mobile.entity.MobileModel;
import com.mobile.entity.MobilePhone;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// MobilePhoneInventory singleton class
@Component
public class MobilePhoneInventory {
    private List<MobilePhone> phones;

    public MobilePhoneInventory() {
        phones = new ArrayList<>();
    }

    public void initializePhones() {
        MobileModel samsungS9 = MobileModel.builder().name("Samsung Galaxy S9").build();
        MobileModel samsungS8 = MobileModel.builder().name("Samsung Galaxy S8").build();
        MobileModel motorolaNexus6 = MobileModel.builder().name("Motorola Nexus 6").build();
        MobileModel onePlus9 = MobileModel.builder().name("OnePlus 9").build();
        MobileModel iPhone13 = MobileModel.builder().name("Apple iPhone 13").build();
        MobileModel iPhone12 = MobileModel.builder().name("Apple iPhone 12").build();
        MobileModel iPhone11 = MobileModel.builder().name("Apple iPhone 11").build();
        MobileModel iPhoneX = MobileModel.builder().name("iPhone X").build();
        MobileModel nokia3310 = MobileModel.builder().name("Nokia 3310").build();

        if (phones.isEmpty()) {
            phones.add(initializePhones(samsungS9));
            phones.add(initializePhones(samsungS8));
            phones.add(initializePhones(samsungS8));
            phones.add(initializePhones(motorolaNexus6));
            phones.add(initializePhones(onePlus9));
            phones.add(initializePhones(iPhone13));
            phones.add(initializePhones(iPhone12));
            phones.add(initializePhones(iPhone11));
            phones.add(initializePhones(iPhoneX));
            phones.add(initializePhones(nokia3310));
        }
    }

    private MobilePhone initializePhones(MobileModel mobileModel){
        return MobilePhone.builder()
                .id(UUID.randomUUID())
                .isAvailable(Boolean.TRUE)
                .model(mobileModel)
                .build();
    }
    public List<MobilePhone> getPhones() {
        return phones;
    }
}
