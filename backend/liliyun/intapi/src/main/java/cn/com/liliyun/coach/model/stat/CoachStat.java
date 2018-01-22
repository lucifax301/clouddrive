package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;

public class CoachStat implements Serializable{

	private int coachid;
	
	private String coachname;
	
	private int step1;
	
	private int step2;
	
	private int step3;
	
	private int step4;
	
	private int sum;
	
	

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getCoachid() {
		return coachid;
	}

	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public int getStep1() {
		return step1;
	}

	public void setStep1(int step1) {
		this.step1 = step1;
	}

	public int getStep2() {
		return step2;
	}

	public void setStep2(int step2) {
		this.step2 = step2;
	}

	public int getStep3() {
		return step3;
	}

	public void setStep3(int step3) {
		this.step3 = step3;
	}

	public int getStep4() {
		return step4;
	}

	public void setStep4(int step4) {
		this.step4 = step4;
	}
	
	
}
