package com.mobile.service;

import com.mobile.entity.BookedBy;
import com.mobile.entity.MobileModel;
import com.mobile.entity.MobilePhone;
import com.mobile.repository.MobilePhoneInventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@ExtendWith(MockitoExtension.class)
class MobilePhoneBookingServiceTest {
    @InjectMocks
    private MobilePhoneBookingService mobilePhoneBookingService;

    @Mock
    private MobilePhoneInventory inventory;

    private static BookedBy bookedBy;
    private static UUID uuid;
    private static MobilePhone mobilePhone;

    @BeforeEach
    void setUp() {
        this.bookedBy = BookedBy.builder().name("Taha").build();
        this.uuid = UUID.randomUUID();
        this.mobilePhone = MobilePhone.builder()
                .id(uuid)
                .model(MobileModel.builder().name("Samsung Galaxy S9").build())
                .bookedBy(bookedBy)
                .bookedAt(LocalDate.now())
                .isAvailable(Boolean.TRUE)
                .build();
    }


    @Test
    void bookPhone() {
        //Book a Phone
        Mockito.when(inventory.getPhones()).thenReturn(Arrays.asList(mobilePhone));
        String result = mobilePhoneBookingService.bookPhone("SamsungGalaxyS9", bookedBy);
        Assertions.assertEquals(uuid.toString(),result);
    }

    @Test
    void returnPhone() {
        //Book a Phone
        Mockito.when(inventory.getPhones()).thenReturn(Arrays.asList(mobilePhone));
        String bookedPhoneId = mobilePhoneBookingService.bookPhone("SamsungGalaxyS9", bookedBy);
        Assertions.assertEquals(uuid.toString(),bookedPhoneId);

        //Return the above booked phone
        String  returnedPhoneId = mobilePhoneBookingService.returnPhone("SamsungGalaxyS9");
        Assertions.assertEquals(bookedPhoneId,returnedPhoneId);

    }

    @Test
    void getAvailablePhoneByModel() {
        //Get a phone by model
        Mockito.when(inventory.getPhones()).thenReturn(Arrays.asList(mobilePhone));
        MobilePhone mobilePhone = mobilePhoneBookingService.getAvailablePhoneByModel("SamsungGalaxyS9");
        Assertions.assertEquals("SamsungGalaxyS9",mobilePhone.getModel().getName().replaceAll(" ",""));
    }

    @Test
    void getBookedPhoneByModel() {
        //Book a Phone
        Mockito.when(inventory.getPhones()).thenReturn(Arrays.asList(mobilePhone));
        String bookedPhoneId = mobilePhoneBookingService.bookPhone("SamsungGalaxyS9", bookedBy);
        Assertions.assertEquals(uuid.toString(),bookedPhoneId);

        //Get a phone by model
        MobilePhone mobilePhone = mobilePhoneBookingService.getBookedPhoneByModel("SamsungGalaxyS9");
        Assertions.assertEquals("SamsungGalaxyS9",mobilePhone.getModel().getName().replaceAll(" ",""));
    }

    @Test
    void getAllBookedPhones() {

        //Get a phone by model
        Mockito.when(inventory.getPhones()).thenReturn(Arrays.asList(mobilePhone));
        MobilePhone mobilePhone = mobilePhoneBookingService.getAvailablePhoneByModel("SamsungGalaxyS9");
        Assertions.assertEquals("SamsungGalaxyS9",mobilePhone.getModel().getName().replaceAll(" ",""));
    }

    @Test
    void getAllAvailablePhones() {

        //Test get all available phones
        Mockito.when(inventory.getPhones()).thenReturn(Arrays.asList(mobilePhone));
        List<MobilePhone> allAvailablePhones = mobilePhoneBookingService.getAllAvailablePhones();
        Assertions.assertTrue(allAvailablePhones.size()==1);
        Assertions.assertEquals("SamsungGalaxyS9",allAvailablePhones.get(0).getModel().getName().replaceAll(" ",""));
    }

    @Test
    void getAllPhones() {

        //Test get all phones
        Mockito.when(inventory.getPhones()).thenReturn(Arrays.asList(mobilePhone));
        List<MobilePhone> allAvailablePhones = mobilePhoneBookingService.getAllPhones();
        Assertions.assertTrue(allAvailablePhones.size()==1);
        Assertions.assertEquals("SamsungGalaxyS9",allAvailablePhones.get(0).getModel().getName().replaceAll(" ",""));
    }


}