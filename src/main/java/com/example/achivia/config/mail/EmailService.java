package com.example.achivia.config.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Matches: Your OTP Code for Registration");

            String emailContent = "<html>" +
                    "<body style='font-family: Arial, sans-serif; background-color: #f9f9fb; padding: 20px;'>" +
                    "<h2 style='color: #2c3e50; text-align: center;'>Welcome to Matches!</h2>" +
                    "<p style='font-size: 16px; color: #34495e;'>Thank you for joining <b>Matches</b>, a skill-based platform where people exchange and learn skills from each other. " +
                    "With Matches, you can connect, collaborate, and grow by sharing what you know and discovering new abilities. We're thrilled to have you on board!</p>" +
                    "<p style='font-size: 16px; color: #34495e;'>To complete your registration, please use the One-Time Password (OTP) below:</p>" +
                    "<h3 style='font-size: 32px; color: #e67e22; font-weight: bold; text-align: center;'>"
                    + otp + "</h3>" +
                    "<p style='font-size: 16px; color: #34495e;'>This OTP is valid for 5 minutes and is required to verify your account. " +
                    "If you did not request this, please ignore this email or contact support.</p>" +
                    "<br>" +
                    "<p style='font-size: 16px; color: #34495e;'>Best regards,</p>" +
                    "<p style='font-size: 16px; color: #34495e;'>The Matches Team</p>" +
                    "<footer style='font-size: 12px; color: #95a5a6; text-align: center; margin-top: 20px;'>" +
                    "Matches | Share Skills, Learn Skills, Grow Together</footer>" +
                    "</body>" +
                    "</html>";

            helper.setText(emailContent, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }



}


