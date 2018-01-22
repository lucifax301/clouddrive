function studentCheckStatus(value) {
    switch (value) {
        case 0 :
        return "无需审核";
        case 1 :
        return "待审核";
        case 2 :
        return "审核通过";
        case 3 :
        return "审核拒绝";
    }
}

function studentCoachState(value) {
    switch (value) {
        case 0 :
            return "无需审核";
        case 1 :
            return "待审核";
        case 2 :
            return "审核通过";
        case 3 :
            return "审核拒绝";
    }
}

function formatSubject(value) {
    switch (value) {
        case 1 :
            return "科目一";
        case 2 :
            return "科目二";
        case 3 :
            return "科目三";
        case 4 :
            return "科目四";
    }
}



function transferItemStatus(value) {
    switch (value) {
        case 0 :
        return "正常";
        case 1 :
        return "退表";
        case 2 :
        return "非正常";
    }
}

function sexState(value){
	switch (value) {
        case '1' :
        return "男";
        case '2' :
        return "女";
        
    }
}

//学员状态
function studentStatus(applyexam,applystatus) {
     if (applyexam == 1) {
         if (applystatus == 1) {
             return "报名-已录绿表";
         } else if (applystatus == 2) {
             return "报名-已交资料";
         } else if (applystatus == 3) {
             return "报名-已受理";
         }
     } else if (applyexam == 2) {
        if (applystatus == 1) {
             return "科目一-排课";
         } else if (applystatus == 2) {
             return "科目一-已培训";
         } else if (applystatus == 3) {
             return "科目一-报考";
         } else if (applystatus == 4) {
             return "科目一-合格";
         }
     } else if (applyexam == 3) {
         if (applystatus == 1) {
             return "科目二-分配教练";
         } else if (applystatus == 2) {
             return "科目一-已培训";
         } else if (applystatus == 3) {
             return "科目二-报考";
         } else if (applystatus == 4) {
             return "科目二-合格";
         }
     } else if (applyexam == 4) {
        if (applystatus == 1) {
             return "科目三-报考";
         } else if (applystatus == 2) {
             return "科目三-合格";
         }
     } else if (applyexam == 5) {
         if (applystatus == 1) {
             return "科目三文明安全-报考";
         } else if (applystatus == 2) {
             return "科目三文明安全-合格";
         }
     } else if (applyexam == 6) {
         if (applystatus == 1) {
             return "毕业-毕业";
         }
     }
}



function paidvalue(str){
	 str = parseInt(str)
	 switch (str) {
      case 1: return  "玻璃险";
			case 2: return  "车损";
			case 5: return  "盗抢险";
			case 6: return  "第三者10万";
			case 7: return  "第三者30万";
			case 8: return  "第三者50万";
			case 9: return  "第三者100万";
			case 3: return  "乘客4座*10万";
			case 4: return  "乘客4座*20万";
			case 10: return  "司机2万";
			case 11: return  "司机5万";
			case 12: return  "司机10万";
			case 13: return  "司机20万";
  	}
}

var formatall = {
	indicators_type(value) {
    	switch (value) {
	        case 0 :
	            return "无需审核";
	        case 1 :
	            return "待审核";
	        case 2 :
	            return "审核通过";
	        case 3 :
	            return "审核拒绝";
	    }
	},
	
};




























