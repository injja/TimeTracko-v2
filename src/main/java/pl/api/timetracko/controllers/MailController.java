package pl.api.timetracko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.api.timetracko.requests.EmailRequest;
import pl.api.timetracko.services.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PreAuthorize("@userService.isAdmin()")
    @PostMapping
    public String sendEmail(
            @RequestBody EmailRequest emailRequest) {
        mailService.sendSimpleEmail(emailRequest);
        return "Email wyslany";
    }
}
