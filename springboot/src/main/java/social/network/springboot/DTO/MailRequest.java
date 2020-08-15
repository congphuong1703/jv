package social.network.springboot.DTO;

import lombok.Data;


@Data
public class MailRequest {

	public MailRequest() {
	}

	public MailRequest(String to, String subject, String content) {
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public MailRequest(String from, String to, String cc, String bcc, String subject, String content, String type) {
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.content = content;
		this.type = type;
	}

	private String from;

	private String to;

	private String cc;

	private String bcc;

	private String subject;

	private String content;

	private String type;
}
