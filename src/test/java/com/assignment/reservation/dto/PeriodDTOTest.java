package com.assignment.reservation.dto;

import com.assignment.reservation.util.validate.ValidationTest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class PeriodDTOTest extends ValidationTest {

    private int invalidMinute;

    private LocalDateTime validStart;
    private LocalDateTime validEnd;
    private LocalDateTime past;

    @Before
    public void initVariables() throws Exception {
        invalidMinute = 10;

        validStart = LocalDateTime.now().plusHours(1).withMinute(0);
        validEnd = LocalDateTime.now().plusHours(1).withMinute(30);
        past = LocalDateTime.now().minusHours(1).withMinute(0);
    }

    @Test
    public void whenStartAndEndIsValid_thenValid() {
        // given
        PeriodDTO dtoWithValidStartAndEnd = PeriodDTO.builder()
                .start(validStart)
                .end(validEnd)
                .build();

        // then
        assertConstraintViolations(dtoWithValidStartAndEnd, 0);
    }

    @Test
    public void whenStartIsNull_thenInvalid() {
        // given
        PeriodDTO dtoWithNullStart = PeriodDTO.builder()
                .start(null)
                .end(validEnd)
                .build();

        // then
        assertConstraintViolations(dtoWithNullStart, 2);
    }

    @Test
    public void whenStartIsPast_thenInvalid() {
        // given
        PeriodDTO dtoWithStartPast = PeriodDTO.builder()
                .start(past)
                .end(validEnd)
                .build();

        // then
        assertConstraintViolations(dtoWithStartPast, 1);
    }

    @Test
    public void whenStartIsInvalidMinute_thenInvalid() {
        // given
        PeriodDTO dtoWithStartPast = PeriodDTO.builder()
                .start(validStart.withMinute(invalidMinute))
                .end(validEnd)
                .build();

        // then
        assertConstraintViolations(dtoWithStartPast, 1);
    }

    @Test
    public void whenEndIsNull_thenInvalid() {
        // given
        PeriodDTO dtoWithNullEnd = PeriodDTO.builder()
                .start(validStart)
                .end(null)
                .build();

        // then
        assertConstraintViolations(dtoWithNullEnd, 2);
    }

    @Test
    public void whenEndIsPast_thenInvalid() {
        // given
        PeriodDTO dtoWithEndPast = PeriodDTO.builder()
                .start(validStart)
                .end(past)
                .build();

        // then
        assertConstraintViolations(dtoWithEndPast, 2);
    }

    @Test
    public void whenEndIsInvalidMinute_thenInvalid() {
        // given
        PeriodDTO dtoWithStartPast = PeriodDTO.builder()
                .start(validStart)
                .end(validEnd.withMinute(invalidMinute))
                .build();

        // then
        assertConstraintViolations(dtoWithStartPast, 1);
    }

    @Test
    public void whenStartIsAfterEnd_thenInvalid() {
        // given
        PeriodDTO dtoWithStartAfterEnd = PeriodDTO.builder()
                .start(validEnd.plusHours(1))
                .end(validEnd)
                .build();

        // then
        assertConstraintViolations(dtoWithStartAfterEnd, 1);
    }
}
