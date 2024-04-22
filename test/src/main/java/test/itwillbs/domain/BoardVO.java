package test.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	
	private String id;
	private String pw;
	private String email;
	private String name;
	private String birthday;
	private String gender;
	private String phoneNumber;
	private Timestamp regdate;
	private Timestamp updatedate;

}
