package test.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	
	private int bno;
	private String writer;
	private String content;
	private String title;
	private Timestamp regdate;
	private Timestamp updatedate;

}
