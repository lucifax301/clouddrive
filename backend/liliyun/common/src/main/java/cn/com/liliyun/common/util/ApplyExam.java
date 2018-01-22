package cn.com.liliyun.common.util;

public enum ApplyExam {

	SIGNUP_STUDENT_INFO("报名-已录绿表",1,1,111),
	SIGNUP_MATERIAL_STORE("报名-已交资料-门店交表",1,2,121),
	SIGNUP_MATERIAL_AREA("报名-已交资料-片区交表",1,2,122),
	SIGNUP_MATERIAL_SHCOOL("报名-已交资料-牌证交表",1,2,123),
	SIGNUP_MATERIAL_RETURN("报名-已交资料-退表",1,2,124),
	SIGNUP_ACCEPT_FLOWNUM("报名-已受理-录入流水号",1,3,131),
	SIGNUP_ACCEPT_ICCARD("报名-已受理-IC卡发新卡",1,3,132),
	SIGNUP_ACCEPT_FILE("报名-已受理-受理档案",1,3,133),
	SIGNUP_ACCEPT_FEE("报名-已受理-申请缴费",1,3,134),

    SUBJECT1_FEE_PAY("科目一-已缴费-缴费",2,1,211),
    SUBJECT1_CLASS_CLASS("科目一-排课-排课",2,2,221),
    SUBJECT1_TRAIN_THEORY("科目一-已培训-录入理论课培训成绩",2,3,231),
    SUBJECT1_TRAIN_CARD("科目一-已培训-科目二/三发卡",2,3,232),

    SUBJECT1_SIGN_REPLY("科目一-报考-报考批复",2,4,241),
    SUBJECT1_OK_RESULT("科目一-合格-录入成绩",2,5,251),
    SUBJECT1_OK_LEARNCARD("科目一-合格-录入学习证",2,5,252),
    SUBJECT1_OK_APPLY_STAMP("科目一-合格-申请盖章",2,5,253),
    SUBJECT1_OK_SCHOOL_STAMP("科目一-合格-驾校盖章",2,5,254),

	SUBJECT2_CAOCH_ASSIGN("科目二-分配教练-分配教练",3,1,311),
	SUBJECT2_COACH_FEE("科目二-分配教练-申请缴费",3,1,312),
	SUBJECT2_FEE_PAY("科目二-已缴费-缴费",3,2,321),
	SUBJECT2_SIGN_REPLY("科目二-报考-报考批复",3,3,331),
	SUBJECT2_OK_RESULT("科目二-合格-录入成绩",3,4,341),
	SUBJECT2_OK_FEE("科目二-合格-申请缴费",3,4,342),
	SUBJECT2_OK_COST("科目二-合格-申请缴工本费",3,4,343),

	SUBJECT3_FEE_PAY("科目三-已缴费-缴费",4,1,411),
	SUBJECT3_FEE_TRIBILL("科目三-已缴费-录入三联单",4,1,412),
	SUBJECT3_FEE_TRIBILL_STAMP("科目三-已缴费-三联单盖章",4,1,413),
	SUBJECT3_FEE_FILE("科目三-已缴费-路考档案",4,1,414),
	SUBJECT3_SIGN_REPLY("科目三-报考-报考批复",4,2,421),
	SUBJECT3_OK_RESULT("科目三-合格-录入成绩",4,2,422),
    SUBJECT3_OK_FEE("科目三-合格-申请缴费",4,2,423),

    SUBJECT4_FEE_PAY("科目三文明安全-已缴费-缴费",5,1,511),
	SUBJECT4_SIGN_REPLY("科目三文明安全-报考-报考批复",5,2,521),
	SUBJECT4_OK_RESULT("科目三文明安全-合格-成绩录入",5,3,531),

	GRADUATE_GRADUATE_LICENSE("毕业-毕业-驾驶证",6,1,611),
	GRADUATE_GRADUATE_FILE("毕业-毕业-学员档案",6,1,612),

	OTHER_TRANSFER_STORE("其他-转店",9,1,911),
	OTHER_CHANGE_COACH("其他-变更教练",9,1,912),
	OTHER_STOP_LEARN("其他-暂停学车",9,1,913),
	OTHER_MODIFY_MATERIAL("其他-变更资料",9,1,914),
	OTHER_SIGN_OUT("其他-退学",9,1,915),
	OTHER_SUBJECT2_TRAIN("其他-科目二已培训",9,1,916),
	OTHER_SUBJECT3_TRAIN("其他-科目三已培训",9,1,917),
	OTHER_LEARN_TEST("其他-已学科测试",9,1,918),
	OTHER_SUBJECT_TEST("其他-已科目三文明测试",9,1,919),
	OTHER_RETURN_CERTI("其他-归还证件",9,1,920),
	OTHER_REPAY_MATERIAL("其他-补交资料",9,1,921),
	OTHER_FINGER_PRINT("其他-中智指纹",9,1,922);

	//办证科目名称
	private String name;
	//进度
	private int applyexam;
	//状态
	private int applystatus;
	//办证
	private int subject;

    /**
     *
     * @param name 办证科目名称
     * @param applyexam 学车进度
     * @param applystatus 学车状态
     * @param subject 办证科目
     */
	private ApplyExam(String name, int applyexam, int applystatus, int subject) {
		this.name = name;
		this.applyexam = applyexam;
		this.applystatus = applystatus;
		this.subject = subject;
	}

	public int getApplystatus() {
		return applystatus;
	}

	public void setApplystatus(int applystatus) {
		this.applystatus = applystatus;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(int applyexam) {
		this.applyexam = applyexam;
	}

	public static String getNameBySubject(int subject) {
		for (ApplyExam ae : ApplyExam.values()) {
			if (ae.getSubject() == subject) {
				return ae.getName();
			}
		}
		return null;
	}

    public static String getName(int applyexam,int applystatus) {
        for (ApplyExam ae : ApplyExam.values()) {
            if (ae.getApplyexam() == applyexam
                    && ae.getApplystatus() == applystatus) {
                return ae.getName();
            }
        }
        return null;
    }
	
}
