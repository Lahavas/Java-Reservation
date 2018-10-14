package com.assignment.reservation.util;

import com.assignment.reservation.domain.Reservation;
import com.assignment.reservation.domain.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FixtureFactory {

    public static List<Reservation> generateReservationList(int count) {
        return generateReservationList(count, LocalDate.MIN, LocalTime.MIN);
    }

    public static List<Reservation> generateReservationList(int count, LocalDate date, LocalTime time) {
        List<Reservation> reservationList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            reservationList.add(Reservation.builder()
                    .owner(String.format("Owner %d", i))
                    .room(Room.builder().name(String.format("Room %d", i)).build())
                    .start(LocalDateTime.of(date, time.plusMinutes(i * DateUtils.MINUTES_DIVIDER)))
                    .end(LocalDateTime.of(date, time.plusMinutes((i + 1) * DateUtils.MINUTES_DIVIDER)))
                    .build());
        }

        return reservationList;
    }

    public static List<Room> generateRoomList(int count) {
        List<Room> roomList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            roomList.add(Room.builder()
                    .name(String.format("Room %d", i))
                    .build());
        }

        return roomList;
    }
}
