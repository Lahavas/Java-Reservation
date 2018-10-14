package com.assignment.reservation.domain;

import com.assignment.reservation.exception.ReservationAlrealyExistException;
import com.assignment.reservation.util.DateUtils;
import com.assignment.reservation.util.FixtureFactory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RoomTest {

    private String roomName;

    private Room roomWithName;

    private LocalDate date;
    private LocalTime midnight;
    private int someReservationsCount;

    private List<Reservation> existedReservationList;
    private Room roomWithReservationList;

    @Before
    public void initVariables() throws Exception {
        roomName = "Real Room";

        roomWithName = Room.builder().name(roomName).build();

        date = LocalDate.now();
        midnight = LocalTime.MIDNIGHT;
        someReservationsCount = 3;

        existedReservationList = FixtureFactory.generateReservationList(someReservationsCount, date, midnight);
        roomWithReservationList = Room.builder().reservations(existedReservationList).build();
    }

    @Test
    public void whenCreateRoomWithName_thenEqualsToName() {
        // then
        assertEquals(roomName, roomWithName.getName());
    }

    @Test
    public void whenCreateRoomWithName_thenNotEqualsToOther() {
        // given
        String otherRoomName = "Other Room";

        // then
        assertNotEquals(otherRoomName, roomWithName.getName());
    }

    @Test
    public void whenCreateRoomWithOneReservation_thenEqualsToOne() {
        // given
        Reservation reservation = Reservation.builder().build();
        Room roomWithOneReservation = Room.builder().reservations(Collections.singletonList(reservation)).build();

        // then
        assertEquals(1, roomWithOneReservation.getReservations().size());
        assertTrue(roomWithOneReservation.getReservations().contains(reservation));
    }

    @Test
    public void whenCreateRoomWithSomeReservation_thenEqualsToSome() {
        // given
        List<Reservation> reservationList = FixtureFactory.generateReservationList(someReservationsCount);
        Room roomWithSomeReservation = Room.builder().reservations(reservationList).build();

        // then
        assertEquals(someReservationsCount, roomWithSomeReservation.getReservations().size());
        assertTrue(roomWithSomeReservation.getReservations().containsAll(reservationList));
    }

    @Test
    public void givenOtherReservationsNotAlreadyReserved_whenAppendOtherReservations_thenReturnRoomAppended() {
        // given
        List<Reservation> notExistedReservationList =
                FixtureFactory.generateReservationList(someReservationsCount,
                        date,
                        midnight.plusMinutes(DateUtils.MINUTES_DIVIDER * someReservationsCount));

        // when
        roomWithReservationList.appendReservations(notExistedReservationList);

        // then
        assertTrue(roomWithReservationList.getReservations().containsAll(existedReservationList));
        assertTrue(roomWithReservationList.getReservations().containsAll(notExistedReservationList));
    }

    @Test(expected = ReservationAlrealyExistException.class)
    public void givenOtherReservationsAlreadyReserved_whenAppendOtherReservations_throwsReservationAlreadyExistException() {
        // given
        List<Reservation> alreadyExistedReservationList =
                FixtureFactory.generateReservationList(someReservationsCount,
                        date,
                        midnight.plusMinutes(DateUtils.MINUTES_DIVIDER * (someReservationsCount - 2)));

        // when
        roomWithReservationList.appendReservations(alreadyExistedReservationList);
    }
}
