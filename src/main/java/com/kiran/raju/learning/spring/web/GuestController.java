package com.kiran.raju.learning.spring.web;


import com.kiran.raju.learning.spring.business.domain.Guests;
import com.kiran.raju.learning.spring.business.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public String getGuests(Model model){
        List<Guests> guestList = guestService.getAllGuests();

        model.addAttribute("guestList",guestList);
        return "guests";
    }
}
