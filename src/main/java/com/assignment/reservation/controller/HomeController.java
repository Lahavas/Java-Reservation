package com.assignment.reservation.controller;

import com.assignment.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String index(Model model,
                        @RequestParam(name = "date")
                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                Optional<LocalDate> maybeDate) {
        maybeDate.ifPresent(localDate ->
                model.addAttribute("reservations", reservationService.findReservationsByDate(localDate))
        );
        return "/index";
    }
}
