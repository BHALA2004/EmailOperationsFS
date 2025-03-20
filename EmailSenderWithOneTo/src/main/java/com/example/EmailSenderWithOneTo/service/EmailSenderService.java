package com.example.EmailSenderWithOneTo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;


    private final Logger logger = LogManager.getLogger(EmailSenderService.class);
    public void sendEmail(String toEmailAddress,String body,String subject) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();



        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);


        mimeMessageHelper.setFrom("balasubramanian.kannan@finsurge.tech");
        mimeMessageHelper.setTo(toEmailAddress);
        mimeMessageHelper.setText(body,true);
        mimeMessageHelper.setSubject(subject + " " + UUID.randomUUID().toString().substring(0, 5));


        FileSystemResource fileSystemResource = new FileSystemResource(new File("E:\\ExcelOperations\\studentsNew.xlsx"));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        javaMailSender.send(mimeMessage);
        logger.info("Email Send Successfully");
    }


    public void sendMailWithCc(String toMail,String toCc,String subject,String text) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("balasubramanian.kannan@finsurge.tech");
        mimeMessageHelper.setTo(toMail);
        mimeMessageHelper.setCc(toCc);
        mimeMessageHelper.setSubject(subject + " " + UUID.randomUUID().toString().substring(0, 5));
        mimeMessageHelper.setText(text,true);

        FileSystemResource fileSystemResource = new FileSystemResource(new File("E:\\ExcelOperations\\studentsNew.xlsx"));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        javaMailSender.send(mimeMessage);
        logger.info("Message Send Successfully");

    }


    public void sendMailWithMultipleTo(String[] toMail,String toCc,String subject,String text,String attachment) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("balasubramanian.kannan@finsurge.tech");
        mimeMessageHelper.setTo(toMail);
        mimeMessageHelper.setCc(toCc);
        mimeMessageHelper.setSubject(subject + " " + UUID.randomUUID().toString().substring(0, 5));
        mimeMessageHelper.setText(text,true);

        FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        javaMailSender.send(mimeMessage);
        logger.info("Message Send Successfully");

    }

    public void sendMailWithMultipleCC(String[] toMail, String[] toCc, String subject, String text, MultipartFile file) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom("balasubramanian.kannan@finsurge.tech");
        mimeMessageHelper.setTo(toMail);
        mimeMessageHelper.setCc(toCc);
        mimeMessageHelper.setSubject(subject + " " + UUID.randomUUID().toString().substring(0, 5));
        mimeMessageHelper.setText(text,true);

        mimeMessageHelper.addAttachment(file.getOriginalFilename(),file);
        javaMailSender.send(mimeMessage);
        logger.info("Message Send Successfully");

    }

}
