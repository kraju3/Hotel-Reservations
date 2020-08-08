package com.kiran.raju.learning.spring.business.service;

import com.kiran.raju.learning.spring.business.domain.RoomReservation;
import com.kiran.raju.learning.spring.data.entity.Guest;
import com.kiran.raju.learning.spring.data.entity.Reservation;
import com.kiran.raju.learning.spring.data.entity.Room;
import com.kiran.raju.learning.spring.data.repository.GuestRepository;
import com.kiran.raju.learning.spring.data.repository.ReservationRepository;
import com.kiran.raju.learning.spring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date){

        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap = new HashMap<>();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.putIfAbsent(room.getRoomId(),roomReservation);
        });

        Iterable<Reservation> reservations = reservationRepository.findReservationByReservationDate(new Date(date.getTime()));

        reservations.forEach(reservation->{
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomID());
            roomReservation.setReservationDate(reservation.getReservationDate());
            roomReservation.setReservationID(reservation.getReservationID());
            Guest guest = guestRepository.findById(reservation.getGuestID()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestID(guest.getGuestID());
        });

        List<RoomReservation> roomReservations_ = new ArrayList<>();

        for (Long id: roomReservationMap.keySet()){

            roomReservations_.add(roomReservationMap.get(id));
        }

        roomReservations_.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation t0, RoomReservation t1) {
                if(t0.getRoomName()==t1.getRoomName()){
                    return t0.getRoomNumber().compareTo(t1.getRoomNumber());
                }
                return t0.getRoomName().compareTo(t1.getRoomName());
            }
        });

        return roomReservations_;

    }

}
