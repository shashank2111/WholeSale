//package com.example.Trial.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class EmailConfig {
//	@Bean
//    public JavaMailSenderImpl mailSender() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//
//        javaMailSender.setProtocol("SMTP");
//        javaMailSender.setHost("127.0.0.1");
//        javaMailSender.setPort(25);
//
//        return javaMailSender;
//    }
//}