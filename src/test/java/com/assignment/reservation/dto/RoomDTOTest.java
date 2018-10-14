package com.assignment.reservation.dto;

import com.assignment.reservation.domain.Room;
import com.assignment.reservation.util.validate.ValidationTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoomDTOTest extends ValidationTest {

    private RoomDTO dtoWithValidName;

    @Before
    public void initVariables() throws Exception {
        String validName = "Room Owner";

        dtoWithValidName = RoomDTO.builder().name(validName).build();
    }

    @Test
    public void whenNameIsNotNull_thenValid() {
        // then
        assertConstraintViolations(dtoWithValidName, 0);
    }

    @Test
    public void whenNameIsNull_thenInvalid() {
        // given
        RoomDTO dtoWithNullName = RoomDTO.builder().name(null).build();

        // then
        assertConstraintViolations(dtoWithNullName, 1);
    }

    @Test
    public void whenNameIsEmpty_thenInvalid() {
        // given
        RoomDTO dtoWithNullName = RoomDTO.builder().name("").build();

        // then
        assertConstraintViolations(dtoWithNullName, 1);
    }

    @Test
    public void givenRoomDTO_whenCreateRoomUsingToEntity_thenEqualsToName() {
        // when
        Room room = dtoWithValidName.toEntity();

        // then
        assertEquals(room.getName(), dtoWithValidName.getName());
    }
}
