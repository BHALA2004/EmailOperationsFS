package com.example.EmailSenderWithOneTo.DTO;

public class DTOClassForOne {
    private String fromMailId;
    private String toMailId;
    private String text;
    private String subject;

    public DTOClassForOne(String fromMailId, String toMailId, String text, String subject) {
        this.fromMailId = fromMailId;
        this.toMailId = toMailId;
        this.text = text;
        this.subject = subject;
    }

    public String getFromMailId() {
        return fromMailId;
    }

    public void setFromMailId(String fromMailId) {
        this.fromMailId = fromMailId;
    }

    public String getToMailId() {
        return toMailId;
    }

    public void setToMailId(String toMailId) {
        this.toMailId = toMailId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
