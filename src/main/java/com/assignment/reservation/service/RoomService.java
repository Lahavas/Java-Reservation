package com.assignment.reservation.service;

import com.assignment.reservation.domain.Room;
import com.assignment.reservation.domain.RoomRepository;
import com.assignment.reservation.dto.ReservationDTO;
import com.assignment.reservation.exception.RoomEntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Room에 관한 business logic을 담당하는 service layer입니다
 *
 * @author 정재호
 */
@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Reservation 정보를 바탕으로 Room을 reserve하는 method입니다.
     *
     * @author 정재호
     * @param dto Reservation 정보를 담고 있습니다.
     * @return 예약이 완료된 Room을 return합니다.
     */
    @Transactional
    public Room reserve(ReservationDTO dto) {
        return roomRepository.save(
                findRoomByName(dto.getRoom().getName())
                        .appendReservations(dto.toEntityList()));
    }

    /**
     * Database에 저장된 모든 Room 정보를 가져오는 method입니다.
     *
     * @author 정재호
     * @return Database에 저장된 모든 Room 정보를 return합니다.
     */
    @Transactional(readOnly = true)
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    Room findRoomByName(String name) {
        return roomRepository.findByName(name)
                .orElseThrow(RoomEntityNotFoundException::new);
    }
}
