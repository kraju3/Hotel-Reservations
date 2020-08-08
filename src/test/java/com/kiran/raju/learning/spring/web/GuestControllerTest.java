package com.kiran.raju.learning.spring.web;

import com.kiran.raju.learning.spring.business.domain.Guests;
import com.kiran.raju.learning.spring.business.service.GuestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GuestController.class)
public class GuestControllerTest {
    @MockBean
    private GuestService guestService;


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getReservations() throws Exception {
        List<Guests> allGuests = new ArrayList<>();

        Guests guest = new Guests();
        guest.setPhoneNumber("708-717-7432");
        guest.setLastName("Raju");
        guest.setFirstName("Kiran");
        guest.setEmailAddress("gmail@gmail.com");

        allGuests.add(guest);


        given(guestService.getAllGuests()).willReturn(allGuests);

        this.mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Raju, Kiran")));
    }

}
