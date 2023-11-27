package com.example.spring_jobs.email.service;

import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.user.entity.User;
import com.example.spring_jobs.user.repository.UserRepository;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;

@Slf4j(topic = "Email 인증")
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    @Value("${AdminMail.id}")
    private String sender;

    private final UserRepository userRepository;
    public static final String ePw = createKey();

    private MimeMessage createMessage(String to) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            message.addRecipients(RecipientType.TO, to);//보내는 대상
            message.setSubject("SpringJobs 이메일 인증");//제목

            String msgg = "";
            msgg += "<div style='margin:20px;'>";
            msgg += "<h1> Springjobs 입니다. </h1>";
            msgg += "<br>";
            msgg += "<p>아래 코드를 복사해 입력해주세요<p>";
            msgg += "<br>";
            msgg += "<p>감사합니다.<p>";
            msgg += "<br>";
            msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
            msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
            msgg += "<div style='font-size:130%'>";
            msgg += "CODE : <strong>";
            msgg += ePw + "</strong><div><br/> ";
            msgg += "</div>";
            message.setText(msgg, "utf-8", "html");//내용
            message.setFrom(new InternetAddress(sender, "SpringJobs"));
        } catch (MessagingException e) {
            log.error("MessagingException");
            throw new CustomException(StatusEnum.FAIL_CREATE_MESSAGE);
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException");
        }

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        // TODO Auto-generated method stub

        Optional<User> checkEmail = userRepository.findByEmail(to);
        if (checkEmail.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_EMAIL);
        }

        MimeMessage message = createMessage(to);
        try {//예외처리
            emailSender.send(message);
        } catch (MailException es) {
            throw new CustomException(StatusEnum.FAIL_SEND_EMAIL);
        }
        return ePw;
    }
}
