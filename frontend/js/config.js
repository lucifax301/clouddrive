/**********************************************后台配置**************************************************************************/

window.config={
    loginUrl:'#',
    homeUrl:'index.html',
    hostUrl:'http://'+window.location.host,
    closeBasePath:window.IP+'/httpaccess/v1/',                   //close api
    openBasePath:window.IP+'/httpaccess/',                  		//open api
    msgTime:60,
    qiniuDomainName:'http://obqfnhv9x.bkt.clouddn.com/'
};
window.config.userinfo = JSON.parse(sessionStorage.getItem("liliyun_userinfo"))

window.config.dictionaryAPI = {
	userinfo:{
		data:JSON.parse(sessionStorage.getItem("liliyun_userinfo"))
	},
    //门店
    storelist:{
        url:config.openBasePath + "store/list?pageNo=-1"
    },
    //所属门店
    storelist_filter:{
        url:config.openBasePath + "store/list?pageNo=-1&areaid=" + (window.config.userinfo.areaid||"") + "&id=" + (window.config.userinfo.storeid||"") + "&status=0"
    },
    //所有片区
    areaall:{
        url:config.openBasePath + "area/list?pageNo=-1"
    },
    //已启用片区
    arealist:{
        url:config.openBasePath + "area/list?status=0&pageNo=-1"
    },
    //所属片区
    arealist_filter:{
        url:config.openBasePath + "area/list?status=0&pageNo=-1&id=" + (window.config.userinfo.areaid||"") + "&status=0"
    },
    //流程
    businesslist:{
        url:config.openBasePath + "business/list?pageNo=-1"
    },
    //考场
    examarealist:{
        url:config.openBasePath + "examarea/list?pageNo=-1"
    },
    //班别
    classlist : {
        url:config.openBasePath + "class/list?pageNo=-1&status=0"
    },
    teachtypelist:{
		url:config.openBasePath + "coachSetting/listAvailTeachType?pageNo=-1"
	},
    //一级营销渠道
    channellist:{
        url:config.openBasePath + "saleschannel/list?parentid=0&status=0&pageNo=-1"
    },
    //班型
    classtypelist:{
		url:config.openBasePath + "coachSetting/listAllClassType?status=0&pageNo=-1"
	},
	//带教车型
	cartypelist:{
		url:config.openBasePath + "coachSetting/listCarType"
	},
    //部门
    deptlist:{
        url:config.openBasePath + "dept/list?pageNo=-1"
    },
    //带教类型
    teachjoblist:{
    	url:config.openBasePath + "coachSetting/listAllJob?status=0"
    },
    //费用类型
    financeSubject:{
    	url:config.openBasePath + "financeSubject/listAll"
    },
    oldyear:{
        data:function(){
            var myDate = new Date(),
                json = [];
            var year = myDate.getFullYear();
            for(var i = year;i>=1990;i--){
                json.push({
                    val:i
                })
            }
            return json
        }
    },
    //demo中测试联动筛选的数据
    ceshi:{
        data:[
            {name:'科目一培训', val:'0', belong:1},
            {name:'科目一考试', val:'1', belong:2},
        ]
    },
    //科目
    subjecttype:{
        data:[
            {name:'科目一', val:'1'},
            {name:'科目二', val:'2'},
            {name:'科目三', val:'3'},
            {name:'科目四', val:'4'}
        ]
    },
    //学员状态
    studentstatus:{
        data:[
            {name:'正常', val:'0'},
            {name:'欠费', val:'1'},
            {name:'暂停', val:'2'},
            {name:'退学', val:'3'},
            {name:'延期', val:'4'},
            {name:'毕业', val:'5'},
            {name:'注销', val:'6'}
        ]
    },
    //快考试成绩
    examresult:{
        data:[
            {name:'合格', val:'0'},
            {name:'不合格', val:'1'},
            {name:'未到', val:'2'},
            {name:'未考', val:'3'},
            {name:'取消', val:'4'}
        ]
    },
    //学车进度
    applyexam:{
        data:[
            {name:'报名', val:1},
            {name:'科目一',val:2},
            {name:'科目二', val:3},
            {name:'科目三', val:4},
            {name:'科目三安全文明',val:5},
            {name:'毕业', val:6},
            {name:'其他', val:9}
        ]
    },
    //办证状态
    subject:{
        data:[
            {name:'录报名表', val:111},
            {name:'门店交表', val:121},
            {name:'片区交表', val:122},
            {name:'牌证收表', val:123},
            {name:'退表', val:124},
            {name:'录入流水号', val:131},
            {name:'IC卡发新卡', val:132},
            {name:'受理档案', val:133},
            {name:'排课', val:211},
            {name:'录入理论课培训成绩', val:221},
            {name:'科目一报考批复', val:231},
            {name:'科目一录入成绩', val:241},
            {name:'录入学习证', val:242},
            {name:'申请盖章', val:243},
            {name:'驾校盖章', val:244},
            {name:'分配教练', val:311},
            {name:'科目二培训', val:321},
            {name:'科目二报考批复', val:331},
            {name:'科目二成绩录入', val:341},
            {name:'录入三联单', val:342},
            {name:'三联单盖章', val:343},
            {name:'路考档案', val:344},
            {name:'科目三培训', val:411},
            {name:'科目三报考批复', val:421},
            {name:'科目三录入成绩', val:431},
            {name:'科目三安全文明报考批复', val:511},
            {name:'科目三安全文明成绩录入', val:521},
            {name:'学员档案', val:611},
            {name:'驾驶证', val:612},
        ]
    },
    
    //准驾类型
    applytype : {
        data : [
            {name:'C1', val:'C1'},
            {name:'C2', val:'C2'}
        ]
    },
    //信息来源
    infosource : {
        data : [
            {name : '别人介绍',val:0},
            {name : '广告媒体',val:1},
            {name : '视频媒体',val:2},
            {name : '广播电台',val:3},
            {name : '网络搜索',val:4},
            {name : '报刊杂志',val:5},
            {name : '市场活动',val:6},
            {name : '其他',val:7}
        ]
    }
};

var ApplySubjectEnum = {
    SIGNUP_STUDENT_INFO : 111,
    SIGNUP_MATERIAL_STORE : 121,
    SIGNUP_MATERIAL_AREA : 122,
    SIGNUP_MATERIAL_SHCOOL : 123,
    SIGNUP_MATERIAL_RETURN : 124,
    SIGNUP_ACCEPT_FLOWNUM : 131,
    SIGNUP_ACCEPT_ICCARD : 132,
    SIGNUP_ACCEPT_FILE : 133,

    SUBJECT1_CLASS_CLASS : 211,
    SUBJECT1_TRAIN_THEORY : 221,
    SUBJECT1_SIGN_REPLY : 231,
    SUBJECT1_OK_RESULT : 241,
    SUBJECT1_OK_LEARNCARD : 242,
    SUBJECT1_OK_APPLY_STAMP : 243,
    SUBJECT1_OK_SCHOOL_STAMP : 244,

    SUBJECT2_CAOCH_ASSIGN : 311,
    SUBJECT2_TRAIN : 321,
    SUBJECT2_SIGN_REPLY : 331,
    SUBJECT2_OK_RESULT : 341,
    SUBJECT2_OK_TRIBILL_PRINT : 342,
    SUBJECT2_OK_TRIBILL_STAMP : 343,
    SUBJECT2_OK_ROAD_FILE : 344,

    SUBJECT3_TRAIN : 411,
    SUBJECT3_SIGN_REPLY : 421,
    SUBJECT3_OK_RESULT : 431,

    SUBJECT4_SIGN_REPLY : 511,
    SUBJECT4_OK_RESULT : 521,

    GRADUATE_GRADUATE_FILE : 611,
    GRADUATE_GRADUATE_LICENSE : 612

};

