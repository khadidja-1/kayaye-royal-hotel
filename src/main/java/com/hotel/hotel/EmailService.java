package com.hotel.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // On ajoute l'ID de la réservation en paramètre pour créer le lien
    public void sendConfirmationEmail(String to, String clientName, String roomNumber, String checkIn, String checkOut, Long reservationId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Reservation Confirmation - Paradise Hotel");

        // On crée le lien vers la clé digitale
        String digitalKeyLink = "http://localhost:8081/digital-key.html?id=" + reservationId;

        message.setText("Dear " + clientName + ",\n\n" +
                "Your reservation at Paradise Hotel has been confirmed!\n\n" +
                "Room Number: " + roomNumber + "\n" +
                "Check-In Date: " + checkIn + "\n" +
                "Check-Out Date: " + checkOut + "\n\n" +
                "🔑 CLICK HERE TO GET YOUR DIGITAL ROOM KEY:\n" +
                digitalKeyLink + "\n\n" +
                "Thank you for choosing our hotel.\n" +
                "Best regards,\n" +
                "The Hotel Management Team");

        mailSender.send(message);
    }
}