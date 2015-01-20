package com.softserve.edu.service.routes.implementation;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.routes.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaceServiceImp implements PlaceService{

    @Autowired
    ProfileService profileService;

    @Autowired
    PlaceRepository placeRepository;

    public List<Place> getAllPlaces(Pageable pageable) {
        return placeRepository.findAll(pageable).getContent();
    }

    public List<Place> getAllNotUserPlaces() {
        User user = getCurrentUser();
        return placeRepository.findByOwnerNot(user);
    }

    public List<Place> getPlacesNearToUser() {
        ArrayList<Place> result = new ArrayList<>();
        User user = profileService.getCurrentUser();
        for (Place place : placeRepository.findAll()) {
            if (user.getCity() == place.getCity()) {
                result.add(place);
            }
        }
        return result;
    }

    public List<Place> getUserPlaces() {
        return new ArrayList<>(getCurrentUser().getPlaces());
    }

    public List<PlaceDto> placeToPlaceDto(List<Place> places) {
        List<PlaceDto> result = new ArrayList<>();
        PlaceDto buffDto;
        for (Place place : places) {
            buffDto = new PlaceDto(place);
            buffDto.setLink(createLink(place.getId()));
            result.add(buffDto);
        }
        return result;
    }

    private final String LINK_BEGIN = "\\place?placeId=";
    private final StringBuilder sb = new StringBuilder();
    private final int EMPTY = 0;
    private String createLink(int id) {
        sb.setLength(EMPTY);
        sb.append(LINK_BEGIN);
        sb.append(id);
        return sb.toString();
    }

    private User getCurrentUser() {
        String login = SecurityContextHolder.getContext().getAuthentication()
                .getName();
        return profileService.getUserByLogin(login);
    }
}
