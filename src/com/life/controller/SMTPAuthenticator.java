package com.life.controller;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
 

public class SMTPAuthenticator extends Authenticator {
 
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "rlead9247@gmail.com";
        String password = "Wlstn1030!";
        return new PasswordAuthentication(username, password);
    }
}