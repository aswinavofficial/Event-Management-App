package com.letslearn.eventify.service.impl;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.letslearn.eventify.controller.AuthController;
import com.letslearn.eventify.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	Logger log = LoggerFactory.getLogger(MailServiceImpl.class);


	@Autowired
	private JavaMailSender mailSender;

	@Override
    @Async
	public void sendSimpleMail(String from, String to, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		log.info("Sending mail to " + to);
		mailSender.send(message);
		

	}

	@Override
    @Async
	public void sendHTMLMail(String from, String to, String subject, String htmlContent) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		boolean html = true;
		
		log.info("Sending mail to " + to);

		try {
			helper.setSubject(subject);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setText(htmlContent, html);
		} catch (MessagingException e) {
			e.printStackTrace();
			log.error("Mail Exception ",e);
		}
		
		mailSender.send(message);
	}
	
	
	@Override
    @Async
	public void sendMailWithAttachment(String from, String to, String subject, String htmlContent, List<FileSystemResource> attachments) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		boolean html = true;
		
		log.info("Sending mail to " + to);

		try {
			helper.setSubject(subject);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setText(htmlContent, html);
		} catch (MessagingException e) {
			e.printStackTrace();
			log.error("Mail Exception ",e);
		}
				
		for(FileSystemResource attachment : attachments) {
		
			try {
				helper.addAttachment(attachment.getFilename(), attachment);
				
			} catch (MessagingException e) {
				e.printStackTrace();
				log.error("Mail Attachment Exception ",e);
			}
			
		}
		
		
		mailSender.send(message);
	}
	
	@Override
    @Async
	public void sendMailWithInlineImages(String from, String to, String subject, String htmlContent, List<FileSystemResource> attachments) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		boolean html = true;
		
		log.info("Sending mail to " + to);

		try {
			helper.setSubject(subject);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setText(htmlContent, html);
		} catch (MessagingException e) {
			e.printStackTrace();
			log.error("Mail Exception ",e);
		}
				
		for(FileSystemResource attachment : attachments) {
			
			try {
				helper.addInline(attachment.getFilename(), attachment);
				
			} catch (MessagingException e) {
				e.printStackTrace();
				log.error("Mail Attachment Exception ",e);
			}
			
		}
		
		
		mailSender.send(message);
	}

}
