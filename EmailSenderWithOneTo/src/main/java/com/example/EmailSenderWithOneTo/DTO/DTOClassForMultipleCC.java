package com.example.EmailSenderWithOneTo.DTO;

public class DTOClassForMultipleCC {
    private String[] toMail;
    private String[] toCc;
    private String subject;
    private String text;



    public String[] getToMail() {
        return toMail;
    }

    public void setToMail(String[] toMail) {
        this.toMail = toMail;
    }

    public String[] getToCc() {
        return toCc;
    }

    public void setToCc(String[] toCc) {
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


}
