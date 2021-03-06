package com.softserve.edu.controller;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.RoutePagingDto;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.routes.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@EnableSpringDataWebSupport
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    @Autowired
    PlaceService placeService;

    @RequestMapping (value = "/addBookedPlace", method = RequestMethod.POST)
    @ResponseBody
    public boolean addBookingPlace(@RequestBody String placeId) {
        userService.setBookedPlace(Integer.valueOf(placeId.replace("=", "")));
        return true;
    }

    @RequestMapping(value = "/getUserPlaces", method = RequestMethod.POST)
    @ResponseBody
    public List<PlaceDto> getUserPlaces(Pageable pageable) {
        return placeService.placeToPlaceDto(userService.getUserPlaces(pageable));
    }

    @RequestMapping(value = "/getUserBookedPlaces", method = RequestMethod.POST)
    @ResponseBody
    public List<PlaceDto> getBookedPlaces(Pageable pageable) {
        return placeService.placeToPlaceDto(userService.getUserBookedPlaces(pageable));
    }
}
