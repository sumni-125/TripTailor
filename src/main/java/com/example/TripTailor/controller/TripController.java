package com.example.TripTailor.controller;

import com.example.TripTailor.dto.request.TripForm;
import com.example.TripTailor.dto.response.CustomUserDetails;
import com.example.TripTailor.entity.Trip;
import com.example.TripTailor.entity.User;
import com.example.TripTailor.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trip")
public class TripController {

    @Autowired
    TripService tripService;

    @GetMapping
    public String list(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            Model model
    ) {
        List<Trip> trips = tripService.getMyTrips(userDetails.getUserCd());

        model.addAttribute("trips", trips);
        return "trip/list"; // templates/trip/list.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        TripForm form = new TripForm();
        form.setVisibility("PRIVATE"); // 기본값

        model.addAttribute("tripForm", form);
        return "trip/new"; // templates/trip/new.html
    }

    @PostMapping
    public String create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @ModelAttribute("tripForm") TripForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "trip/new";
        }

        // 날짜 검증 (start > end 인지 체크)
        if (form.getStartDate().isAfter(form.getEndDate())) {
            bindingResult.reject("dateRange", "시작일은 종료일보다 이후일 수 없습니다.");
            return "trip/new";
        }

        tripService.createTrip(userDetails.getUserCd(), form);

        return "redirect:/trip";
    }
}
