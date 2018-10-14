package com.assignment.reservation.service;

import com.assignment.reservation.domain.Room;
import com.assignment.reservation.domain.RoomRepository;
import com.assignment.reservation.exception.RoomEntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    public void givenRoom_whenFindingRoomByName_thenReturnRoom() {
        // given
        Room room = Room.builder().build();

        // when
        when(roomRepository.findByName(anyString())).thenReturn(Optional.of(room));

        // then
        assertEquals(room, roomService.findRoomByName(anyString()));
    }

    @Test(expected = RoomEntityNotFoundException.class)
    public void givenNull_whenFindingRoomByName_throwsRoomEntityNotFoundException() {
        // when
        when(roomRepository.findByName(anyString())).thenReturn(Optional.empty());

        // then
        roomService.findRoomByName(anyString());
    }
}
