package util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	// Email: tungnd21896@gmail.com
	// Password: kptq owqw xkbn yhkv
	static final String from = "tungnd21896@gmail.com";
	static final String password = "dyyllaxbtdermupg";

	public static boolean sendEmail(String to, String tieuDe, String noiDung) {
		// Propretie: khái báo các thuộc tính
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
		props.put("mail.smtp.port", "587");// TLS 587, SSL 465
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		// create Authenticator: tạo trình xác thực
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		};

		// Sesion object
		Session session = Session.getInstance(props, auth);
		// Tạo một tin nhắn
		MimeMessage msg = new MimeMessage(session);

		try {
			// Kiểu nội dung
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

			// Người gửi
			msg.setFrom(from);

			// Người nhận
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

			// Tiêu đề email
			msg.setSubject(tieuDe);

			// Quy đinh ngày gửi
			msg.setSentDate(new Date());

			// Quy định email nhận phản hồi
			// msg.setReplyTo(InternetAddress.parse(from, false))

			// Nội dung
			msg.setContent(noiDung, "text/HTML; charset=UTF-8");

			// Gửi email
			Transport.send(msg);
			System.out.println("Gửi email thành công");
			return true;
		} catch (Exception e) {
			System.out.println("Gặp lỗi trong quá trình gửi email");
			e.printStackTrace();
			return false;
		}
	}

//	public static void main(String[] args) {
//		for (int i = 0; i < 10; i++) {
//			Email.sendEmail("thoai12309@gmail.com", System.currentTimeMillis() + "", "Alo Alo");
//		}
//	}
}
