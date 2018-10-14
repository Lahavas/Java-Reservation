package com.assignment.reservation.dto;

import com.assignment.reservation.domain.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * Room에 관한 데이터 전송을 위한 data transfer object class입니다.
 *
 * @author 정재호
 * @see Room
 */
@Getter
@NoArgsConstructor
public class RoomDTO {

    @NotEmpty
    private String name;

    @Builder
    public RoomDTO(@NotEmpty String name) {
        this.name = name;
    }

    /**
     * RoomDTO와 일치하는 정보를 가진 Room instance를 return하기 위한 method입니다.
     *
     * @author 정재호
     * @return Room type의 instance를 return합니다.
     * @see Room
     */
    public Room toEntity() {
        return Room.builder()
                .name(name)
                .build();
    }
}
