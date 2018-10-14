package com.assignment.reservation.domain;

import com.assignment.reservation.util.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ReservationTest {

    private static final int TINY_DAY_OF_MONTH = 1;
    private static final int TINY_HOUR = 1;

    private String owner;
    private LocalDateTime start;
    private LocalDateTime end;

    private Reservation reservationWithOwner;
    private Reservation reservationWithStartAndEnd;

    @Before
    public void initVariables() throws Exception {
        int year = 2017;
        int month = 10;

        int startDayOfMonth = 16;
        int startHour = 14;
        int startMinute = 30;

        int endDayOfMonth = startDayOfMonth + (TINY_DAY_OF_MONTH * 2);
        int endHour = startHour + (TINY_HOUR * 2);
        int endMinute = 10;

        owner = "Real Owner";
        start = LocalDateTime.of(year, month, startDayOfMonth, startHour, startMinute);
        end = LocalDateTime.of(year, month, endDayOfMonth, endHour, endMinute);

        reservationWithOwner = Reservation.builder().owner(owner).build();
        reservationWithStartAndEnd = Reservation.builder().start(start).end(end).build();
    }

    @Test
    public void whenCreateReservationWithOwner_thenEqualsToOwner() {
        // then
        assertEquals(owner, reservationWithOwner.getOwner());
    }

    @Test
    public void whenCreateReservationWithOwner_thenNotEqualsToOther() {
        // given
        String other = "Fake Owner";

        // then
        assertNotEquals(other, reservationWithOwner.getOwner());
    }

    @Test
    public void whenCreateReservationWithStartAndEnd_thenEqualsToStartAndEnd() {
        // then
        assertEquals(start, reservationWithStartAndEnd.getStart());
        assertEquals(end, reservationWithStartAndEnd.getEnd());
    }

    @Test
    public void whenCreateReservationWithStartAndEnd_thenNotEqualsToOthers() {
        // then
        assertNotEquals(start.plusHours(TINY_HOUR), reservationWithStartAndEnd.getStart());
        assertNotEquals(end.plusHours(TINY_HOUR), reservationWithStartAndEnd.getEnd());
    }

    @Test
    public void givenLocalDateTimeBetweenStartAndEnd_whenIsBetweenStartAndEnd_thenReturnTrue() {
        // given
        LocalDateTime between = start.plusHours(TINY_HOUR);

        // then
        assertTrue(reservationWithStartAndEnd.isOverlapped(between));
    }

    @Test
    public void givenLocalDateTimeEqualsToStartAndDurationEqualsToMinuteDivider_whenIsBetweenStartAndEnd_thenReturnTrue() {
        // given
        Reservation reservationWithDurationEqualsToMinuteDivider =
                Reservation.builder()
                        .start(start)
                        .end(start.plusMinutes(DateUtils.MINUTES_DIVIDER))
                        .build();

        // then
        assertTrue(reservationWithDurationEqualsToMinuteDivider.isOverlapped(start));
    }

    @Test
    public void givenLocalDateTimeBeforeStart_whenIsBetweenStartAndEnd_thenReturnFalse() {
        // given
        LocalDateTime beforeStart = start.minusHours(TINY_HOUR);

        // then
        assertFalse(reservationWithStartAndEnd.isOverlapped(beforeStart));
    }

    @Test
    public void givenLocalDateTimeAfterEnd_whenIsBetweenStartAndEnd_thenReturnFalse() {
        // given
        LocalDateTime afterEnd = end.plusHours(TINY_HOUR);

        // then
        assertFalse(reservationWithStartAndEnd.isOverlapped(afterEnd));
    }

    @Test
    public void givenSameDateToStart_whenIsSameDate_thenReturnTrue() {
        // given
        LocalDate sameToStart = start.toLocalDate();

        // then
        assertTrue(reservationWithStartAndEnd.isSameDate(sameToStart));
    }

    @Test
    public void givenSameDateToEnd_whenIsSameDate_thenReturnTrue() {
        // given
        LocalDate sameToEnd = end.toLocalDate();

        // then
        assertTrue(reservationWithStartAndEnd.isSameDate(sameToEnd));
    }

    @Test
    public void givenOtherDate_whenIsSameDate_thenReturnFalse() {
        // given
        LocalDate otherDate = start.toLocalDate().minusDays(1);

        // then
        assertFalse(reservationWithStartAndEnd.isSameDate(otherDate));
    }
}
