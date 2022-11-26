package org.bogus.groove.mail.config;

public class test {

    MimeMessage message = emailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    //메일 제목 설정
        helper.setSubject(mailDto.getTitle());

    //수신자 설정
        helper.setTo(mailDto.getAddress());

    //참조자 설정
        helper.setCc(mailDto.getCcAddress());

    //템플릿에 전달할 데이터 설정
    HashMap<String, String> emailValues = new HashMap<>();
    	emailValues.put("name", "jimin");

    Context context = new Context();
        emailValues.forEach((key, value)->{
        context.setVariable(key, value);
    });

    //메일 내용 설정 : 템플릿 프로세스
    String html = templateEngine.process(mailDto.getTemplate(), context);
        helper.setText(html, true);

    //템플릿에 들어가는 이미지 cid로 삽입
        helper.addInline("image1", new ClassPathResource("static/images/image-1.jpeg"));

    //메일 보내기
        emailSender.send(message);
}
