package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.api.timetracko.requests.EmailRequest;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        message.setFrom("inga.grabska2000@gmail.com");
        mailSender.send(message);
    }
}
