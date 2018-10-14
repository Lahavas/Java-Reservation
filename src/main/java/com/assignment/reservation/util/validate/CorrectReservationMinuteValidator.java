package com.assignment.reservation.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

/**
 * 입력된 Minute가 올바른지 판단하기 위한 ConstraintValidator입니다.
 *
 * @author 정재호
 */
public class CorrectReservationMinuteValidator implements ConstraintValidator<CorrectReservationMinute, LocalDateTime> {

    private int[] values;

    @Override
    public void initialize(CorrectReservationMinute constraintAnnotation) {
        values = constraintAnnotation.values();
    }

    /**
     * 지정된 Reservation의 올바른 Minute 정보와 입력된 값이 일치하는지 확인하는 method입니다.
     *
     * @author 정재호
     * @return 지정된 Reservation의 올바른 Minute 정보와 입력된 값이 일치할 때 true를 return합니다.
     */
    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        for (int correctMinute : values) {
            if (value.getMinute() == correctMinute) {
                return true;
            }
        }

        return false;
    }
}
