package com.assignment.reservation.domain;

import com.assignment.reservation.util.DateUtils;
import com.assignment.reservation.util.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * domain layer에 해당하는 entity class입니다.
 * Reservation에 필요한 owner와 Period에 대한 정보를 가지고 있습니다.
 *
 * @author 정재호
 * @see Period
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation implements Period {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @Column(name = "end", nullable = false)
    private LocalDateTime end;

    @Builder
    public Reservation(String owner, LocalDateTime start, LocalDateTime end) {
        this.owner = owner;
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

    /**
     * 만약 time 인자와 start & end field가 중복되는지 확인하는 method입니다.
     *
     * @author 정재호
     * @param time start & end field의 사이 여부를 확인하기 위한 LocalDateTime type의 argument
     * @return time이 start & end field의 사이에 있거나, 완전히 겹치는 경우 true를 return
     */
    public boolean isOverlapped(LocalDateTime time) {
        return (time.isAfter(start) && time.isBefore(end))
                || time.isEqual(start) && start.plusMinutes(DateUtils.MINUTES_DIVIDER).isEqual(end);
    }
}
