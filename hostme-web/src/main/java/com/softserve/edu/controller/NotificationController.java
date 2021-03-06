package com.softserve.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.edu.dto.NotificationDto;
import com.softserve.edu.model.User;
import com.softserve.edu.service.NotificationService;
import com.softserve.edu.service.ProfileService;

/**
 * @author Oleksandr Bandurka
 */
@Controller
public class NotificationController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public String notificationsCreationShow() {
        return "notifications";
    }

    @RequestMapping(value = "/all-notifications", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<NotificationDto> getUpdatedInterestingGroups() {
        User interestedUser = profileService
                .getUserByLogin(SecurityContextHolder.getContext()
                        .getAuthentication().getName());
        List<NotificationDto> notifications = notificationService
                .findAllNotificationsByUser(interestedUser);
        return notifications;
    }

    @RequestMapping(value = "/delete-notification", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<NotificationDto> deleteNotification(
            @RequestParam(value = "id") Long notifyId) {
        User userDeleter = profileService.getUserByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName());
        notificationService.removeNotifyRelationship(userDeleter.getUserId(),
                notifyId);
        return getUpdatedInterestingGroups();
    }

}
