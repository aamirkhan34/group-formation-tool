package com.group1.SDCapplication.courseadministration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.group1.SDCapplication.models.User;

public class EmailNotification implements UserNotification {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	private String email;
	private String message;
		
	public EmailNotification(String email, String message) {
		super();
		this.email = email;
		this.message = message;
	}

	@Override
	public Boolean notifyUser() {
		// TODO Auto-generated method stub
		try {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            simpleMessage.setTo(email);
            simpleMessage.setSubject("Password Reset email for SDC application");
            simpleMessage.setText(message);
            javaMailSender.send(simpleMessage);
            return true;
        }
        catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
	}
	
}
