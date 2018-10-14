package com.assignment.reservation.util;

import java.time.LocalDateTime;

public interface Period {
    LocalDateTime getStart();

    LocalDateTime getEnd();
}
