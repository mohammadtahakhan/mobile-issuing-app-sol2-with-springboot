package com.mobile;

import com.mobile.entity.BookedBy;
import com.mobile.entity.MobilePhone;
import com.mobile.service.MobilePhoneBookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MobileBookingTesting {

        private MobilePhoneBookingService booking;
        private BookedBy user1;
        private BookedBy user2;

        @BeforeEach
        public void setUp() {
            booking = new MobilePhoneBookingService(null);
            user1 = new BookedBy("User 1");
            user2 = new BookedBy("User 2");
        }

        @Test
        public void testBookPhone() {
            String bookedPhoneId = booking.bookPhone("Samsung Galaxy S8", user1);
            MobilePhone phone = booking.getAvailablePhoneById(UUID.fromString(bookedPhoneId));
            Assertions.assertNotNull(phone);
            Assertions.assertFalse(phone.isAvailable());
            Assertions.assertEquals(user1, phone.getBookedBy());
        }
}
