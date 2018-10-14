package com.assignment.reservation.dto;

import com.assignment.reservation.domain.Reservation;
import com.assignment.reservation.util.validate.ValidationTest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.Assert.assertEquals;

public class ReservationDTOTest extends ValidationTest {

    private String validOwner;
    private LocalDateTime now;

    @Before
    public void setUp() throws Exception {
        validOwner = "Valid Owner";
        now = LocalDateTime.now();
    }

    @Test
    public void whenOwnerIsNotNull_thenValid() {
        // given
        ReservationDTO dtoWithValidOwner = ReservationDTO.builder()
                .owner(validOwner)
                .build();

        // then
        assertConstraintViolations(dtoWithValidOwner, 0);
    }

    @Test
    public void whenOwnerIsNull_thenInvalid() {
        // given
        ReservationDTO dtoWithNullOwner = ReservationDTO.builder()
                .owner(null)
                .build();

        // then
        assertConstraintViolations(dtoWithNullOwner, 1);
    }

    @Test
    public void whenOwnerIsEmpty_thenInvalid() {
        // given
        ReservationDTO dtoWithNullOwner = ReservationDTO.builder()
                .owner("")
                .build();

        // then
        assertConstraintViolations(dtoWithNullOwner, 1);
    }

    @Test
    public void whenUntilIsNow_thenValid() {
        // given
        ReservationDTO dtoWithPastUntil = ReservationDTO.builder()
                .owner(validOwner)
                .until(LocalDate.now())
                .build();

        // then
        assertConstraintViolations(dtoWithPastUntil, 0);
    }

    @Test
    public void whenUntilIsPast_thenInvalid() {
        // given
        ReservationDTO dtoWithPastUntil = ReservationDTO.builder()
                .owner(validOwner)
                .until(LocalDate.now().minusDays(1))
                .build();

        // then
        assertConstraintViolations(dtoWithPastUntil, 1);
    }

    @Test
    public void givenUntilIsNull_whenCreateReservationListUsingToEntity_thenEqualsToReservation() {
        // given
        LocalDateTime end = now.plusHours(3);

        ReservationDTO dtoWithNullUntil = ReservationDTO.builder()
                .owner(validOwner)
                .period(PeriodDTO.builder()
                        .start(now)
                        .end(end)
                        .build())
                .until(null)
                .build();

        // when
        List<Reservation> reservationList = dtoWithNullUntil.toEntityList();

        // then
        for (Reservation reservation : reservationList) {
            assertEquals(validOwner, reservation.getOwner());
            assertEquals(now, reservation.getStart());
            assertEquals(end, reservation.getEnd());
        }
    }

    @Test
    public void givenUntilIsNextDayOfWeek_whenCreateReservationListUsingToEntity_thenDivideCorrectReservations() {
        // given
        LocalDateTime end = now.plusHours(3);

        ReservationDTO dtoWithNextDayOfWeekUntil = ReservationDTO.builder()
                .owner(validOwner)
                .period(PeriodDTO.builder()
                        .start(now)
                        .end(end)
                        .build())
                .until(end.with(next(end.getDayOfWeek())).toLocalDate())
                .build();

        // when
        List<Reservation> reservationList = dtoWithNextDayOfWeekUntil.toEntityList();

        // then
        for (int i = 0; i < reservationList.size(); i++) {
            assertEquals(validOwner, reservationList.get(i).getOwner());
            assertEquals(now.plusDays(7 * i), reservationList.get(i).getStart());
            assertEquals(end.plusDays(7 * i), reservationList.get(i).getEnd());
        }
    }
}
