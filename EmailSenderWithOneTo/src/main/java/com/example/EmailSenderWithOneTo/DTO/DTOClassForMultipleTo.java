package com.example.EmailSenderWithOneTo.DTO;

public class DTOClassForMultipleTo {
    private String[] toMail;
    private String toCc;
    private String subject;
    private String text;

    private String attachment;


    public DTOClassForMultipleTo(String[] toMail, String toCc, String subject, String text, String attachment) {
        this.toMail = toMail;
        this.toCc = toCc;
        this.subject = subject;
        this.text = text;
        this.attachment = attachment;
    }

    public String[] getToMail() {
        return toMail;
    }

    public void setToMail(String[] toMail) {
        this.toMail = toMail;
    }

    public String getToCc() {
        return toCc;
    }

    public void setToCc(String toCc) {
        this.toCc = toCc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
