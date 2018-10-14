package com.assignment.reservation.util;

import com.assignment.reservation.domain.Reservation;

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
                    .start(LocalDateTime.of(date, time.plusMinutes(i * DateUtils.MINUTES_DIVIDER)))
                    .end(LocalDateTime.of(date, time.plusMinutes((i + 1) * DateUtils.MINUTES_DIVIDER)))
                    .build());
        }

        return reservationList;
    }
}
