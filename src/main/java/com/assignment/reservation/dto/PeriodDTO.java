package com.assignment.reservation.dto;

import com.assignment.reservation.util.Period;
import com.assignment.reservation.util.validate.CorrectReservationMinute;
import com.assignment.reservation.util.validate.ValidPeriodOrder;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

/**
 * Period에 관한 데이터 전송을 위한 data transfer object class입니다.
 * start와 end는 각각 Past or Future이며, 올바른 Reservation Minute<strong>(0, 30분)</strong>를 만족해야합니다.
 *
 * @author 정재호
 * @see Period
 */
@ValidPeriodOrder(start = "start", end = "end")
@NoArgsConstructor
public class PeriodDTO implements Period {

    @FutureOrPresent
    @CorrectReservationMinute(values = {0, 30})
    private LocalDateTime start;

    @FutureOrPresent
    @CorrectReservationMinute(values = {0, 30})
    private LocalDateTime end;

    @Builder
    public PeriodDTO(@FutureOrPresent LocalDateTime start,
                     @FutureOrPresent LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDateTime getStart() {
        return start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }
}
