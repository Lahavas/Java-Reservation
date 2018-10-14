package com.assignment.reservation.util.validate;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

/**
 * 입력된 Period의 순서가 올바른지 판단하기 위한 ConstraintValidator입니다.
 * 순서는 start가 end와 같거나 빨라야합니다.
 *
 * @author 정재호
 */
public class ValidPeriodOrderValidator implements ConstraintValidator<ValidPeriodOrder, Object> {

   private String startField;
   private String endField;

   public void initialize(ValidPeriodOrder constraint) {
      this.startField = constraint.start();
      this.endField = constraint.end();
   }

   /**
    * 입력된 Period의 순서가 올바른지 판단하기 위한 method입니다.
    *
    * @author 정재호
    * @return start가 end와 같거나 빠를때 true를 return합니다.
    */
   public boolean isValid(Object object, ConstraintValidatorContext context) {
      LocalDateTime start = (LocalDateTime) new BeanWrapperImpl(object)
              .getPropertyValue(startField);
      LocalDateTime end = (LocalDateTime) new BeanWrapperImpl(object)
              .getPropertyValue(endField);

      if (start == null || end == null) {
         return false;
      }

      return start.isEqual(end) || start.isBefore(end);
   }
}
