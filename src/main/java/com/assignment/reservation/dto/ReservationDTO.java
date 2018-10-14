package com.assignment.reservation.dto;

import com.assignment.reservation.domain.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.next;

/**
 * Reservation에 관한 데이터 전송을 위한 data transfer object class입니다.
 *
 * @author 정재호
 * @see Reservation
 */
@Getter
@NoArgsConstructor
public class ReservationDTO {

    @NotEmpty
    private String owner;

    @Valid
    private RoomDTO room;

    @Valid
    private PeriodDTO period;

    @FutureOrPresent
    private LocalDate until;

    @Builder
    public ReservationDTO(@NotEmpty String owner,
                          @Valid RoomDTO room,
                          @Valid PeriodDTO period,
                          @FutureOrPresent LocalDate until) {
        this.owner = owner;
        this.room = room;
        this.period = period;
        this.until = until;
    }

    /**
     * RervationDTO를 통해 Reservation's list를 return하는 method입니다.
     * until field가 null일 때에는 전달받은 Reservation 정보를 그대로 return합니다.
     * until field가 null이 아닐 때에는 until 전까지 주단위로 Reservation 정보를 Reservation's list에 추가하여 return합니다.
     *
     * @author 정재호
     * @return Reservation 정보를 통해 return된 Reservation's list입니다.
     */
    public List<Reservation> toEntityList() {
        List<Reservation> reservationList = new ArrayList<>();

        if (until == null) {
            return getNotRepeatedReservation(reservationList);
        }

        return getRepeatedReservation(reservationList);
    }

    private List<Reservation> getNotRepeatedReservation(List<Reservation> reservationList) {
        reservationList.add(Reservation.builder()
                .owner(owner)
                .start(period.getStart())
                .end(period.getEnd())
                .build());
        return reservationList;
    }

    private List<Reservation> getRepeatedReservation(List<Reservation> reservationList) {
        LocalDateTime start = period.getStart();
        LocalDateTime end = period.getEnd();

        do {
            reservationList.add(Reservation.builder()
                    .owner(owner)
                    .start(start)
                    .end(end)
                    .build());

            start = start.with(next(start.getDayOfWeek()));
            end = end.with(next(end.getDayOfWeek()));
        } while (!end.toLocalDate().isAfter(until));

        return reservationList;
    }
}
