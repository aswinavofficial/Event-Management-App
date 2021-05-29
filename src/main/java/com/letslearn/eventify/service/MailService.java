package com.letslearn.eventify.service;

import java.io.File;
import java.util.List;

import org.springframework.core.io.FileSystemResource;

public interface MailService {

	public void sendSimpleMail(String from, String to, String subject, String body);

	public void sendHTMLMail(String from, String to, String subject, String htmlContent);

	public void sendMailWithAttachment(String from, String to, String subject, String htmlContent,
			List<FileSystemResource> attachments);

	public void sendMailWithInlineImages(String from, String to, String subject, String htmlContent,
			List<FileSystemResource> attachments);

}
