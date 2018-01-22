package cn.com.liliyun.common.util;

public enum SubjectEnum {

	SUBJECT1_ORDER("科目一预约",1),
	SUBJECT2_ORDER("科目二预约",2),
	SUBJECT3_ORDER("科目三预约",3),
	SUBJECT4_ORDER("科目四预约",4),

	SUBJECT1_TRAIN("科目一培训",1),
	SUBJECT2_TRAIN("科目二培训",2),
	SUBJECT3_TRAIN("科目三培训",3),
	SUBJECT4_TRAIN("科目四培训",4),

	SUBJECT1_EXAM("科目一考试",1),
	SUBJECT2_EXAM("科目二考试",2),
	SUBJECT3_EXAM("科目三考试",3),
	SUBJECT4_EXAM("科目四考试",4);

	private String name;

	private int status;

	private SubjectEnum(String name, int status) {
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
