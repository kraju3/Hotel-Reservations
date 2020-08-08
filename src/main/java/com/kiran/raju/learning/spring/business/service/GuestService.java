package com.kiran.raju.learning.spring.business.service;

import com.kiran.raju.learning.spring.business.domain.Guests;
import com.kiran.raju.learning.spring.data.entity.Guest;
import com.kiran.raju.learning.spring.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guests> getAllGuests(){
        Iterable<Guest> guests = this.guestRepository.findAll();

        List<Guests> guestList = new ArrayList<>();

        guests.forEach(guest->{
            Guests guest_ = new Guests();
            guest_.setAddress(guest.getGuestAddress());
            guest_.setCountry(guest.getGuestCountry());
            guest_.setEmailAddress(guest.getEmailAddress());
            guest_.setFirstName(guest.getFirstName());
            guest_.setLastName(guest.getLastName());
            guest_.setGuestID(guest.getGuestID());
            guest_.setPhoneNumber(guest.getGuestPhoneNumber());
            guest_.setState(guest.getGuestState());
            guestList.add(guest_);
        });

        guestList.sort(new Comparator<Guests>() {
            @Override
            public int compare(Guests t0, Guests t1) {
                if(t0.getLastName().equals(t1.getLastName())){
                   return t0.getFirstName().compareTo(t1.getFirstName());
                }

                return t0.getLastName().compareTo(t1.getLastName());
            }
        });

        return guestList;

    }
}
