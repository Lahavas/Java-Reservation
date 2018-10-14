package com.assignment.reservation.service;

import com.assignment.reservation.domain.Reservation;
import com.assignment.reservation.domain.ReservationRepository;
import com.assignment.reservation.util.FixtureFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void givenReservationList_whenFindReservationsByDate_thenFindCorrectReservations() {
        // given
        List<Reservation> oneReservations =
                FixtureFactory.generateReservationList(2, LocalDate.MIN, LocalTime.MIN);
        List<Reservation> otherReservations =
                FixtureFactory.generateReservationList(2, LocalDate.now(), LocalTime.MIN);
        otherReservations.addAll(oneReservations);

        // when
        when(reservationRepository.findAll()).thenReturn(otherReservations);

        // then
        assertEquals(oneReservations, reservationService.findReservationsByDate(LocalDate.MIN));
    }
}
