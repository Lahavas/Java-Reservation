package com.assignment.reservation.service;

import com.assignment.reservation.domain.Reservation;
import com.assignment.reservation.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reservation에 관한 business logic을 담당하는 service layer입니다.
 *
 * @author 정재호
 */
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * 데이터베이스에 저장되어 있는 Reservation 중, date 인자와 같은 Reservation's list를 찾기 위한 method입니다.
     *
     * @author 정재호
     * @param date 비교를 위한 parameter입니다.
     * @return 데이터베이스에 저장되어 있는 Reservation 중, date 인자와 같은 Reservation's list를 return합니다.
     */
    @Transactional(readOnly = true)
    public List<Reservation> findReservationsByDate(LocalDate date) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> reservation.isSameDate(date))
                .collect(Collectors.toList());
    }
}
