package com.assignment.reservation.controller;

import com.assignment.reservation.domain.Room;
import com.assignment.reservation.dto.ReservationDTO;
import com.assignment.reservation.service.RoomService;
import com.assignment.reservation.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
public class RoomRestController {

    private RoomService roomService;

    @Autowired
    public RoomRestController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/reserve")
    public RestResponse<Room> reserve(@Valid @RequestBody ReservationDTO dto) {
        return RestResponse.success(roomService.reserve(dto));
    }
}
