package com.example.EmailSenderWithOneTo.controller;


import com.example.EmailSenderWithOneTo.DTO.DTOClassForCC;
import com.example.EmailSenderWithOneTo.DTO.DTOClassForMultipleCC;
import com.example.EmailSenderWithOneTo.DTO.DTOClassForMultipleTo;
import com.example.EmailSenderWithOneTo.DTO.DTOClassForOne;
import com.example.EmailSenderWithOneTo.service.EmailSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/sendtoone")
    public ResponseEntity<String> sendMailtoOne(@RequestBody DTOClassForOne dtoClassForOne) throws MessagingException {
        emailSenderService.sendEmail(dtoClassForOne.getToMailId(),dtoClassForOne.getText(), dtoClassForOne.getSubject());
        return new ResponseEntity<>("Email Send Successfully", HttpStatus.OK);
    }

    @GetMapping("/sendwithCc")
    public ResponseEntity<String> sendMailWithCc(@RequestBody DTOClassForCC dtoClassForCC) throws MessagingException {
        emailSenderService.sendMailWithCc(dtoClassForCC.getToMail(), dtoClassForCC.getToCc(), dtoClassForCC.getSubject(), dtoClassForCC.getText());
        return new ResponseEntity<>("Email Send Successfully", HttpStatus.OK);
    }

    @GetMapping("/sendwithMultipleTo")
    public ResponseEntity<String> sendMailWithMultipleTo(@RequestBody DTOClassForMultipleTo dtoClassForCC) throws MessagingException {
        emailSenderService.sendMailWithMultipleTo(dtoClassForCC.getToMail(), dtoClassForCC.getToCc(), dtoClassForCC.getSubject(), dtoClassForCC.getText(),dtoClassForCC.getAttachment());
        return new ResponseEntity<>("Email Send Successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> sendMailWithMultipleCC(
            @RequestParam("dto") String dtoJson,  // JSON as a string
            @RequestPart("file") MultipartFile file) throws MessagingException {

        try {
            // Convert JSON string into DTO object
            ObjectMapper objectMapper = new ObjectMapper();
            DTOClassForMultipleCC dtoClassForCC = objectMapper.readValue(dtoJson, DTOClassForMultipleCC.class);

            // Call email service with parsed data
            emailSenderService.sendMailWithMultipleCC(
                    dtoClassForCC.getToMail(),
                    dtoClassForCC.getToCc(),
                    dtoClassForCC.getSubject(),
                    dtoClassForCC.getText(),
                    file);

            return new ResponseEntity<>("Email Sent Successfully", HttpStatus.OK);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Invalid DTO format: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
