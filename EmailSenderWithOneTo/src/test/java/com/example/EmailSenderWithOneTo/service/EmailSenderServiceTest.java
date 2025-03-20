package com.example.EmailSenderWithOneTo.service;

import com.example.EmailSenderWithOneTo.DTO.DTOClassForCC;
import com.example.EmailSenderWithOneTo.DTO.DTOClassForMultipleTo;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSenderServiceTest {


    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailSenderService emailService;

    @Test
    void sendEmail() throws MessagingException {
       MimeMessage mimeMessage = new MimeMessage(Session.getInstance(new Properties()));
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        emailService.sendEmail("test@example.com", "Test Body", "Test Subject");


        verify(javaMailSender, times(1)).send(mimeMessage);

        assertNotNull(mimeMessage);
        assertEquals("test@example.com",mimeMessage.getAllRecipients()[0].toString());
        assertTrue(mimeMessage.getSubject().contains("Test Subject"));

    }

    @Test
    void checktoMailNull() throws MessagingException {

        DTOClassForCC dtoClassForCC = new DTOClassForCC(null,"test@example.com","Hai","Thank You");

        assertThrows(NullPointerException.class,()->{
            emailService.sendMailWithCc(dtoClassForCC.getToMail(),dtoClassForCC.getToCc(), dtoClassForCC.getSubject(),dtoClassForCC.getText());
        });




    }
    @Test
    void checktoCCNull() throws MessagingException {

        DTOClassForCC dtoClassForCC = new DTOClassForCC("test@example.com",null,"Hai","Thank You");

        assertThrows(NullPointerException.class,()->{
            emailService.sendMailWithCc(dtoClassForCC.getToMail(),dtoClassForCC.getToCc(), dtoClassForCC.getSubject(),dtoClassForCC.getText());
        });




    }

    @Test
    void checktoCCBlank() throws MessagingException {

        DTOClassForCC dtoClassForCC = new DTOClassForCC("test@example.com","","Hai","Thank You");

        assertThrows(NullPointerException.class,()->{
            emailService.sendMailWithCc(dtoClassForCC.getToMail(),dtoClassForCC.getToCc(), dtoClassForCC.getSubject(),dtoClassForCC.getText());
        });




    }

    @Test
    void checktoMailBlank() throws MessagingException {

        DTOClassForCC dtoClassForCC = new DTOClassForCC("","test@example.com","Hai","Thank You");

        assertThrows(NullPointerException.class,()->{
            emailService.sendMailWithCc(dtoClassForCC.getToMail(),dtoClassForCC.getToCc(), dtoClassForCC.getSubject(),dtoClassForCC.getText());
        });




    }

    @Test
    void checkInValidtoMailFormat() throws MessagingException {

        DTOClassForCC dtoClassForCC = new DTOClassForCC("@test@example.com","test@example.com","Hai","Thank You");

        assertThrows(IllegalArgumentException.class,()->{
            emailService.sendMailWithCc(dtoClassForCC.getToMail(),dtoClassForCC.getToCc(), dtoClassForCC.getSubject(),dtoClassForCC.getText());
        });




    }

    @Test
    void checkFileExistsMultipleTo(){
        String[] toId = {"balakannantvl@gmail.com"};
        String toCC = "super@gmail.com";
        String subject = "Hai";
        String text = "Hello";
        String attchment = "E:\\Fin Apis\\EmailSenderWithOneTo\\EmailSenderWithOneTo\\duplihjgkvcates.xlsx";

        DTOClassForMultipleTo dtoClassForMultipleTo = new DTOClassForMultipleTo(toId,toCC,subject,text,attchment);

        assertThrows(FileNotFoundException.class,()->{
            emailService.sendMailWithMultipleTo(dtoClassForMultipleTo.getToMail(),dtoClassForMultipleTo.getToCc(),dtoClassForMultipleTo.getSubject(),dtoClassForMultipleTo.getText(),dtoClassForMultipleTo.getAttachment());
        });

    }








}