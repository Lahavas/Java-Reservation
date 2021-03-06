package com.assignment.reservation.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateUtils {

    /**
     * Reservation의 분 간격입니다.
     */
    public static final int MINUTES_DIVIDER = 30;

    /**
     * period 인자를 MINUTES_DIVIDER 기준으로 나누는 method입니다.
     *
     * @author 정재호
     * @param period 나눠지기 전의 시간 정보. Period interface의 implementation
     * @return MINUTES_DIVIDER를 기준으로 나눠진 LocalDateTime's list
     * @see Period
     */
    public static List<LocalDateTime> divideDateTime(Period period) {
        List<LocalDateTime> result = new ArrayList<>();

        LocalDateTime temp = period.getStart();
        while (!temp.isAfter(period.getEnd())) {
            result.add(temp);
            temp = temp.plusMinutes(MINUTES_DIVIDER);
        }

        return result;
    }
}
