package com.choish.mjq.service;

import com.choish.mjq.domain.emails.Emails;
import com.choish.mjq.dto.emails.EmailsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;
    Environment env;

    public Emails sendEmail(EmailsSaveRequestDto dto) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        String email = dto.getEmail();
        String name = email.split("@")[0];
        String token = Base64Utils.encodeToString((email +":mjuquest").getBytes());
        final String serviceDomain = "http://ec2-52-79-227-73.ap-northeast-2.compute.amazonaws.com:8080/";
        final String localDomain = "http://localhost:8080/";
        mail.setTo(email);
        mail.setFrom("no-reply@mjquest.com");
        mail.setSubject("명지퀘스트 계정 등록 확인 메일입니다.");
        mail.setText("안녕하세요." + name + "님!\n\n등록을 완료하시려면 아래의 링크를 클릭해주세요.\n\n"+ serviceDomain +"users/register/"+token);
        javaMailSender.send(mail);
        return dto.toEntity();
    }
}
