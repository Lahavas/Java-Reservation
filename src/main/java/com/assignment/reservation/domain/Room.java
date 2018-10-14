package com.assignment.reservation.domain;

import com.assignment.reservation.exception.ReservationAlrealyExistException;
import com.assignment.reservation.util.DateUtils;
import com.assignment.reservation.util.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * domain layer에 해당하는 entity class입니다.
 * Room에 필요한 name과 Reservation의 list에 대한 정보를 지닙니다.
 * Room class와 Reservation class는 1:N 관계입니다.
 *
 * @author 정재호
 * @see Reservation
 */
@Entity
@NoArgsConstructor
@Getter
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(targetEntity = Reservation.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private List<Reservation> reservations;

    @Builder
    public Room(String name, List<Reservation> reservations) {
        this.name = name;
        this.reservations = reservations;
    }

    /**
     * 만약 reservationAppended가 기존의 reservations와 중복되지 않는다면,
     * 기존의 reservation에 reservationAppended를 추가하는 method입니다
     *
     * @author 정재호
     * @param reservationAppended 기존의 reservations에 추가되는 Reservation's list입니다.
     * @return 변경된 Room instance를 return합니다.
     * @throws ReservationAlrealyExistException 만약 기존의 reservation과 중복되면 throw합니다.
     */
    public Room appendReservations(List<Reservation> reservationAppended) {
        for (Reservation reservation : reservationAppended) {
            if (isAlreadyReserved(reservation)) {
                throw new ReservationAlrealyExistException();
            }
        }

        reservations.addAll(reservationAppended);
        return this;
    }

    private boolean isAlreadyReserved(Period period) {
        List<LocalDateTime> dividedTimeList = DateUtils.divideDateTime(period);

        for (Reservation reservation : reservations) {
            if (dividedTimeList.stream()
                    .anyMatch(reservation::isOverlapped)) {
                return true;
            }
        }

        return false;
    }
}
