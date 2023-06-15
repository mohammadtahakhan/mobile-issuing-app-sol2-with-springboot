package com.mobile.repository;

import com.mobile.entity.MobileModel;
import com.mobile.entity.MobilePhone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// MobilePhoneInventory singleton class
@Service
public class MobilePhoneInventory {
    private static MobilePhoneInventory instance;
    private List<MobilePhone> phones;

    private MobilePhoneInventory() {
        phones = new ArrayList<>();
    }

    public static MobilePhoneInventory getInstance() {
        if (instance == null) {

            if (instance == null) {
                instance = new MobilePhoneInventory();
            }

        }
        return instance;
    }

    public void initializePhones() {
        MobileModel samsungS9 = MobileModel.createModel("Samsung Galaxy S9");
        MobileModel samsungS8 = MobileModel.createModel("Samsung Galaxy S8");
        MobileModel motorolaNexus6 = MobileModel.createModel("Motorola Nexus 6");
        MobileModel onePlus9 = MobileModel.createModel("OnePlus 9");
        MobileModel iPhone13 = MobileModel.createModel("Apple iPhone 13");
        MobileModel iPhone12 = MobileModel.createModel("Apple iPhone 12");
        MobileModel iPhone11 = MobileModel.createModel("Apple iPhone 11");
        MobileModel iPhoneX = MobileModel.createModel("iPhone X");
        MobileModel nokia3310 = MobileModel.createModel("Nokia 3310");

        if (phones.isEmpty()) {
            phones.add(new MobilePhone(samsungS9));
            phones.add(new MobilePhone(samsungS8));
            phones.add(new MobilePhone(samsungS8));
            phones.add(new MobilePhone(motorolaNexus6));
            phones.add(new MobilePhone(onePlus9));
            phones.add(new MobilePhone(iPhone13));
            phones.add(new MobilePhone(iPhone12));
            phones.add(new MobilePhone(iPhone11));
            phones.add(new MobilePhone(iPhoneX));
            phones.add(new MobilePhone(nokia3310));
        }
    }

    public List<MobilePhone> getPhones() {
        return phones;
    }
}
