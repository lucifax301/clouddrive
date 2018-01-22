/**********************************************后台配置**************************************************************************/

//window.IP="http://192.168.1.94:9731";    //"192.168.1.94:9731",——94服务器   // http://192.168.1.123:28686 陈斌




var storedata,areadata,classdata,businessdata,roledata,btndata,subjectdata;
$(window).ready(function(){
    //获取菜单

    BJUI.ajax('doajax', {
        url: config.openBasePath + "privilege/getUserMenu",
        type : 'GET',
        datatype : "JSON",
        async : false,
        crossDomain: true,
        okCallback: function(json, options) {
            window.m_main_json = json.result;
            window.m_main_menu_json = new Array();
            function pushmenujson(json1){
                $.each(json1, function(i,m) {
                    m.id = "menu-" + m.id;
                    if(m.children){
                        pushmenujson(m.children)
                    }else{
                        window.m_main_menu_json.push(m);
                    }
                })
            }
            pushmenujson(window.m_main_json);
            m_menu_add();
        }
    });

    //输出顶部一级菜单
    function m_menu_add(){
        var m_menu_html = '';
        //console.log(window.m_main_json)
        $.each(window.m_main_json, function(i,m) {
            if(i==0){
                m_menu_html = m_menu_html + '<li class="active"><a href="javascript:" data-name="'+m.name+'" data-toggle="sidenav" data-id-key="targetid"><i class="mr5 fa fa-'+m.icon+'"></i>'+m.name+'</a></li>';
            }else{
                m_menu_html = m_menu_html + '<li><a href="javascript:" data-name="'+m.name+'" data-toggle="sidenav" data-id-key="targetid"><i class="mr5 fa fa-'+m.icon+'"></i>'+m.name+'</a></li>';
            }

        });

        $('#bjui-hnav-navbar').html(m_menu_html);
        setTimeout(function(){
            $('#bjui-hnav-navbar').find('li').eq(0).find('a').trigger('click');
        },0)
    }

    //全局菜单搜索
    $('#c_seach_menu').keyup(function(){
        var text = $(this).val();
        var html = '';
        var coord ={
            x:$(this).offset().left,
            y:$(this).offset().top+30
        }
        $.each(window.m_main_menu_json, function(i,m) {
            if(m.name.indexOf(text) >= 0){
                html = html + '<a href="' + m.url +'" data-toggle="navtab"><li>'+ m.name + '</li></a>'
            }
        })
        if(html==''){
            html = '<a href="javascript:" ><li>没有找到该菜单</li></a>'
        }
        $('#menu-seach-box').css({top:coord.y,left:coord}).show().html(html);
    })
    $(document).click(function(){
        $('#menu-seach-box').hide();
    })
    //选项卡
    $(document).on("click","[data-tabs]",function(){
        var tabid = $(this).attr('data-tabs');
        var box = $(this).parent().parent('.c-tabs-box');
        $(this).parent('.c-tabs-header').children('li').removeClass('active');
        $(this).addClass('active');
        box.children('.c-tabs-body').removeClass('active');
        box.find('.c-tabs-body#'+tabid).addClass('active');
    })
    //seachform 搜索重置按钮
    $(document).on("click","form button[type='reset']",function(){
        $(this).parents('form').trigger(BJUI.eventType.initUI)//重新渲染BJUI
        $(this).parents('form').find('select').selectpicker('val','');
    })

    //clz.getdictionary({name:'mendian',refresb:false})
    $(document).on("click","[clz-click]",function(){
        var way = $(this).attr('clz-click'),
            thisdom = $(this);
        if($(this).attr('clz-options')){
            var options = clz.JSON.parse($(this).attr('clz-options'))
        }
        switch(way){
            case 'adddom':
                var html = '';
                if(options.current == 'dialog'){
                    var dom = $.CurrentDialog.find('#'+options.id);
                }else if(options.current == 'navtab'){
                    var dom = $.CurrentNavtab.find('#'+options.id);
                }

                html = '<div >' + dom.html() + '<label class="row-label red" clz-click="removedom">删除</label></div>';
                thisdom.parents('.adddom-btn').before(html);


                break;
            case 'removedom':
                thisdom.parent().remove();
                break;
        }

    })
    //下拉框选择赋值关联input
    $(document).on("change","[clz-change]",function(){
        var way = $(this).attr('clz-change'),
            thisdom = $(this);
        if($(this).attr('clz-options')){
            var options = clz.JSON.parse($(this).attr('clz-options'))
        }
        switch(way){
            case 'select':
                var inputname = options.inputname,
                    father = options.father,
                    current = options.current,
                    fatherdom;
                if(current == 'dialog'){
                    fatherdom = $.CurrentDialog.find('#'+father);
                }else if(current == 'navtab'){
                    fatherdom = $.CurrentNavtab.find('#'+father);
                }else{
                    return
                }
                var text = thisdom.find('option:selected').text();
                console.log(text);
                fatherdom.find('input[name="'+inputname+'"]').val(text);
                break;
        }
    })
    //获取待办事项
    function getworkspace(){
        BJUI.ajax('doajax',{
            url: config.openBasePath + 'flow/list?status=0',
            type : 'GET',
            okCallback: function(json, options) {
                var num = json.result.list.length || '';
                $('.headuser li .nav-icon-num').html(num)
            }
        })
        setTimeout(function(){
            getworkspace()
        },60000)
    }
    getworkspace()
    BJUI.ajax('doajax', {
        url: config.openBasePath + "store/list",
        type : 'GET',
        okCallback: function(json, options) {
            storedata = json.result.list;
        }
    });

    BJUI.ajax('doajax', {
        url: config.openBasePath + "area/list",
        type : 'GET',
        okCallback: function(json, options) {
            areadata = json.result.list;
        }
    });

    BJUI.ajax('doajax', {
        url: config.openBasePath + "class/list?pageNo=-1",
        type : 'GET',
        okCallback: function(json, options) {
            classdata = json.result.list;
        }
    });

    BJUI.ajax('doajax', {
        url: config.openBasePath + "business/list",
        type : 'GET',
        okCallback: function(json, options) {
            businessdata = json.result.list;
        }
    });

    BJUI.ajax('doajax', {
        url: config.openBasePath + "privilege/listRole?isPage=0",
        type : 'GET',
        okCallback: function(json, options) {
            roledata = json.result.list;
        }
    });

    BJUI.ajax('doajax', {
        url: config.openBasePath + "financeSubject/listAll",
        type : 'GET',
        okCallback: function(json, options) {
            subjectdata = json.result.list;
        }
    });


    function formatAreaCell(value){
        for(var i=0 ;i< areadata.length ;i++){
            if(value == areadata[i].id){
                return areadata[i].name;
            }else{
                continue;
            }
        }
    };

    function formatClassCell(value){
        for(var i=0 ;i< classdata.length ;i++){
            if(value == classdata[i].id){
                return classdata[i].name;
            }else{
                continue;
            }
        }
    };

    function formatStoreCell(value){
        for(var i=0 ;i< storedata.length ;i++){
            if(value == storedata[i].id){
                return storedata[i].name;
            }else{
                continue;
            }
        }
    };

    function formatRoleCell(value){
        for(var i=0 ;i< roledata.length ;i++){
            if(value == roledata[i].id){
                return roledata[i].rolename;
            }else{
                continue;
            }
        }
    };

    function formatSubjectCell(value){
        for(var i=0 ;i< subjectdata.length ;i++){
            if(value == subjectdata[i].id){
                return subjectdata[i].subject;
            }else{
                continue;
            }
        }
    };

    clz.privilege_Init.getdata();//获取初始化权限值
})
window.clz = {
    JSON : {
        parse: function(json){
            json = json.replace(/'/g,'"');
            return JSON.parse(json);
        },
    },
    //获取当前选中的页面选项卡参数
    getNavtab_more : function(){
        var jsonstr = '';
        $('.tabsPageHeaderContent .navtab-tab li').each(function(){
            if($(this).hasClass('active')){
                jsonstr = $(this).find('a').data();
            }
        })
        if(Object.prototype.toString.call(jsonstr.more) === "[object String]"){
            jsonstr.more = jsonstr.more.replace(/'/g,'"');
            if (jsonstr.more != "") {
                jsonstr.more = JSON.parse(jsonstr.more)
            }
        }
        if(Object.prototype.toString.call(jsonstr.more) === "[object String]"){
            if (jsonstr.more != "") {
                jsonstr.more = JSON.parse(jsonstr.more)
            }
        }
        return jsonstr.more;
    },//获取当前选中的页面选项卡权限
    getNavtab_privilege : function(){
        var jsonstr = '';
        $('.tabsPageHeaderContent .navtab-tab li').each(function(){
            if($(this).hasClass('active')){
                jsonstr = $(this).find('a').data();
            }
        })
        return jsonstr.privilege;
    },

    CurrentDom_init:function(json){
        var thisdom,
            data = new Array();
        if(json.current == 'Navtab'){
            thisdom = $.CurrentNavtab;
        }else if(json.current == 'Dialog'){
            thisdom = $.CurrentDialog;
        }
        if (json.url) {
            thisdom.find("#"+json.form_name).attr('action',json.url);
        }
        if(json.data){
            var data = json.data;
        }else if(json.table_name){
            var data = $.CurrentNavtab.find("#"+json.table_name).data('selectedDatas');
            if(data){
                data = data[0];
            }
        }
        if(json.before){//输出值前的处理
            var newdata = json.before(data);
            if(newdata){
                data = newdata;
            }
        }
        if(json.timetype){
            $.each(json.timetype, function(i,m) {
                if(data[m.name]){
                    data[m.name] = clz.filter.time({date:data[m.name],type:m.type})
                }
            });
        }
        if(json.returnFloat){
            var floatarray = json.returnFloat.split(',');
            $.each(floatarray, function(i,m) {
                if(data[m]){
                    data[m] = clz.filter.returnFloat(data[m])
                }
            });
        }
        if (data !=null && data != undefined) {
            thisdom.find("#"+json.form_name).fill(data);
            //select 多选初始化赋值
            thisdom.find('select').each(function(){
                var name = $(this).attr('name');
                $(this).attr('data-val',data[name]);
                if($(this).attr('multiple')){
                    var this_select = $(this);
                    var select_data = data[name];
                    if(select_data){
                        select_data = select_data.split(',');
                        $.each(select_data, function(i,m) {
                            this_select.find('option').each(function(){
                                if($(this).val() == m){
                                    $(this).attr('selected','selected')
                                }
                            })
                        })
                    }
                }

            })
        }
        $.each(thisdom.find('select'),function(i,m){
            $(this).selectpicker('refresh')
        })
        if(json.callback){//执行完毕回调
            json.callback(data);
        }
    },
    //处理弹出层的初始化信息
    CurrentDialog_init : function(json){
        if (json.url) {
            $.CurrentDialog.find("#"+json.form_name).attr('action',json.url);
        }
        if(json.data && !json.table_name){
            var data = json.data;
        }else if(json.table_name){
            var data = $.CurrentNavtab.find("#"+json.table_name).data('selectedDatas');
            if(data){
                data = data[0];
            }
        }
        if(json.before){//输出值前的处理
            json.before(data);
        }
        if(json.timetype){
            $.each(json.timetype, function(i,m) {
                if(data[m.name]){
                    data[m.name] = clz.filter.time({date:data[m.name],type:m.type})
                }
            });
        }
        if (data !=null && data != undefined && !window.table_add) {
            $.CurrentDialog.find("#"+json.form_name).fill(data);
            //select 多选初始化赋值
            $.CurrentDialog.find('select').each(function(){
                var name = $(this).attr('name');
                $(this).attr('data-val',data[name]);
                if($(this).attr('multiple')){
                    var this_select = $(this);
                    var select_data = data[name];
                    if(select_data){
                        select_data = select_data.split(',');
                        $.each(select_data, function(i,m) {
                            this_select.find('option').each(function(){
                                if($(this).val() == m){
                                    $(this).attr('selected','selected')
                                }
                            })
                        })
                    }
                }

            })
        }
        if(json.callback){//执行完毕回调
            json.callback(data);
        }
    },
    //处理标签页的初始化信息
    CurrentNavtab_init : function(json){
        if(json.url){
            $.CurrentNavtab.find("#"+json.form_name).attr('action',json.url);
        }
        if (json.data) {
            var data = json.data;
        }
        if(json.before){//输出值前的处理
            json.before(data);
        }
        if(json.timetype){
            $.each(json.timetype, function(i,m) {
                if(data[m.name]){
                    data[m.name] = clz.filter.time({date:data[m.name],type:m.type})
                }
            });
        }
        if (data !=null && data != undefined && !window.table_add) {
            $.CurrentNavtab.find("#"+json.form_name).fill(data);
            //select 多选初始化赋值
            $.CurrentNavtab.find('select').each(function(){
                var name = $(this).attr('name');
                $(this).attr('data-val',data[name]);
                if($(this).attr('multiple')){
                    var this_select = $(this);
                    var select_data = data[name];
                    if(select_data){
                        select_data = select_data.split(',');
                        $.each(select_data, function(i,m) {
                            this_select.find('option').each(function(){
                                if($(this).val() == m){
                                    $(this).attr('selected','selected')
                                }
                            })
                        })
                    }
                }

            })
        }
        window.table_add = false;
        if(json.callback){//执行完毕回调
            json.callback(data);
        }
    },
    //循环输出单项选择
    output_checkbox :function(json){
        var data = json.data,
            chedata = json.chedata?json.chedata.split(','):null,
            domid = json.domid,
            name = json.name,
            place = json.place,
            dataurl = json.dataurl;
        if(dataurl && !data){
            BJUI.ajax('doajax', {
                url: window.IP + dataurl,
                type:'get',
                async:false,
                okCallback: function(json) {
                    data = json
                }
            })
        }
        function check(m){
            var check = '';
            if(m.checked == true){
                check = 'checked'
            }
            return check
        }
        function disabled(m){
            var disabled = '';
            if(m.disabled == true){
                disabled = 'disabled'
            }
            return disabled
        }
        var html = '<div class="c-output-checkbox">';
        $.each(data, function(i,m) {
            html += '<li><input name="' + name + '" data-toggle="icheck" value="' + m.id + '" ' + check(m)+' '+ disabled(m) + ' data-label="' + m.name + '" type="checkbox"></li>';
        });
        html+='</div>';
        if(place == 'CurrentNavtab'){
            $.CurrentNavtab.find('#'+domid).html(html);
            $.CurrentNavtab.find('#'+domid).trigger(BJUI.eventType.initUI)//重新渲染BJUI
        }else if(place == 'CurrentDialog'){
            $.CurrentDialog.find('#'+domid).html(html);
            $.CurrentDialog.find('#'+domid).trigger(BJUI.eventType.initUI)//重新渲染BJUI
        }
    },
    //时间格式处理
    filter : {
        time: function(json){
            if (json.date == "") {
                return "";
            }
            var time = new Date(json.date),
                type = json.type || 'yyyy-MM-dd hh:mm:ss';
            var date = {
                year : time.getFullYear(),
                month : addzero(time.getMonth()+1),
                day : addzero(time.getDate()),
                hour : addzero(time.getHours()),
                minute : addzero(time.getMinutes()),
                second : addzero(time.getSeconds()),
            }
            var str = type;
            str = str.replace(/yyyy/, date.year);
            str = str.replace(/MM/, date.month);
            str = str.replace(/dd/, date.day);
            str = str.replace(/hh/, date.hour);
            str = str.replace(/mm/, date.minute);
            str = str.replace(/ss/, date.second);
            function addzero(text){
                text = text.toString()
                if(text.length<2){
                    text =  '0'+text
                }
                return text
            }
            return str
        },
        theoryState: function (state) {
            var result;
            switch (state) {
                case 0: result = '已取消';break;
                case 1: result = '未安排学员';break;
                case 2: result = '待审核';break;
                case 3: result = '审核通过';break;
                case 4: result = '已开课';break;
                case 5: result = '已完成';break;
                case 9: result = '审核拒绝';break;
                default:result = '异常'
            }
            return result;
        },
        transferState: function (state) {
            var result;
            switch (state) {
                case 1: result = '待审核';break;
                case 2: result = '审核通过';break;
                case 9: result = '审核拒绝';break;
                default:result = '异常'
            }
            return result;
        },
        returnFloat: function (value) {
            var value=Math.round(parseFloat(value)*100)/100;
            var xsd=value.toString().split(".");
            if(xsd.length==1){
                value=value.toString()+".00";
                return value;
            }
            if(xsd.length>1){
                if(xsd[1].length<2){
                    value=value.toString()+"0";
                }
                return value;
            }
        },
    	bigmoney:function(num){
		      var strOutput = "";  
		      var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';  
		      num += "00";  
		      var intPos = num.indexOf('.');  
		      if (intPos >= 0)  
		        num = num.substring(0, intPos) + num.substr(intPos + 1, 2);  
		      strUnit = strUnit.substr(strUnit.length - num.length);  
		      for (var i=0; i < num.length; i++)  
		        strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);  
		        return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");  
    	}
    },
    //弹层选择的获取参数
    getWindowJson: function(){
        var text = window.clz.findgrid;
        window.clz.findgrid = '';
        return text;
    },
    setWindowJson: function(data){
        window.clz.findgrid = data;
    },
    //获取整个form表单数据
    parseForm : function(obj){
        var serializeObj={};
        var array=obj.serializeArray();
        var str=obj.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        return serializeObj;
    },
    //输出adddom
    pushaddom : function(json){
        var options = json;
        var html = '';
        if(options.current == 'dialog'){
            var dom = $.CurrentDialog.find('#'+options.domid);
        }else if(options.current == 'navtab'){
            var dom = $.CurrentNavtab.find('#'+options.domid);
        }
        var init_html = dom.html();
        if(options.data){
            $.each(options.data[0].data,function(i){
                if(options.edit && options.edit=='none'){
                    var thehtml = '<div >' + init_html + '</div>';
                }else{
                    var thehtml = '<div >' + init_html + '<label class="row-label red" clz-click="removedom">删除</label></div>';
                }

                $.each(options.data, function(j,m) {
                    thehtml = thehtml.replace('"'+m.name+'"','"'+m.name+'" value="'+m.data[i]+'" ');
                    thehtml = thehtml.replace("'"+m.name+"'","'"+m.name+"' value='"+m.data[i]+"' ");
                });
                html += thehtml;
            })

        }
        if(!options.edit || options.edit!='none'){
            html += '<div class="adddom-btn"><label class="row-label"></label><div class="row-input "><div class="c-button-blue" clz-click="adddom" clz-options="{\'id\':\'' + json.domid + '\',\'current\':\''+ options.current +'\'}"><i class="fa fa-plus"></i>' + json.btn_name + '</div></div></div>';
        }
        dom.after(html);
    },
    //获取数据字典
    dictionaryAPI :window.config.dictionaryAPI,
    getdictionary : function(json){
        var name = json.name,
            refresh = json.refresh || false,
            belong = json.belong,
        	belongkeys = json.belongkeys,
        	filter = json.filter?(typeof(json.filter)!='string'?json.filter:JSON.parse(json.filter.replaceAll("'","\""))):null,
        	thisAPI = window.config.dictionaryAPI[name];
        //判断是否存在config
		if(!thisAPI){
        	console.log('数据字典中不存在 '+name)
        }
		var thisdata = null || thisAPI.data;
		//判断超时
		if(thisAPI.url && thisAPI.date && (new Date()-thisAPI.date)>30*60*1000){
			refresh = true
		}
        if(thisdata && refresh==false){
            if(typeof thisdata == 'function'){
                return deepClone(thisdata())
            }else{
                if(belong || filter){
                    return deepClone(iffilter({data:thisdata,belong:belong,belongkeys:belongkeys,filter:filter}))
                }else{
                    return deepClone(thisdata)
                }
            }
        }else{
            thisdata = getdata(thisAPI);
            window.config.dictionaryAPI[name].data = thisdata
            window.config.dictionaryAPI[name].date = new Date();
            if(belong || filter){
                return deepClone(iffilter({data:thisdata,belong:belong,belongkeys:belongkeys,filter:filter}))
            }else{
                return deepClone(thisdata)
            }
        }
        function iffilter(json){
            var data = json.data,
                belong = json.belong,
                belongkeys = json.belongkeys || 'belong',
                filter = json.filter,
                newjson = []
            if(filter){
            	$.each(data,function(i,m){
            		var isok = true
            		for(var i in filter) {
					    if(m[i] != filter[i]){
					    	isok = false
					    }
					}
            		if(isok){
	                	newjson.push(m)
            		}
	            })
            }else{
            	$.each(data,function(i,m){
	                if(m[belongkeys] == belong){
	                    newjson.push(m)
	                }
	            })
            }
            console.log(newjson)
            return newjson
        }
        function getdata(obj){
            var thedata = '';
            BJUI.ajax('doajax', {
		        type:"get",
                url:obj.url,
                async:false,
		        okCallback: function(json, options) {
		            thedata = json.result.list;
		        }
		    });
            return thedata
        }
    },
    privilege_Init : {//权限管理
        privilege:[],
        Current_privilege : function(){
            var jsonstr = {};
            if($.CurrentDialog){
                jsonstr = {
                    privilege:$.CurrentDialog.find('#thispage').attr('privilege'),
                    dom:$.CurrentDialog
                }
            }else if($.CurrentNavtab){
                jsonstr = {
                    privilege:$.CurrentNavtab.find('#thispage').attr('privilege'),
                    dom:$.CurrentNavtab
                }
            }
            return jsonstr;
        },
        getdata : function(){
            BJUI.ajax('doajax', {
                url: config.openBasePath + "privilege/getUserBtn",
                type : 'GET',
                async:false,
                okCallback: function(json, options) {
                    btndata = json.result;
                }
            });
            //console.log(btndata)
            this.privilege = btndata;
        },
        start : function(){
            var privilege = this.privilege;
            var pageprivilege = clz.privilege_Init.Current_privilege();
            if(!privilege){return}
            pageprivilege.dom.find('[data-privilege]').each(function(){
                var data = $(this).data('privilege');
                var thisprivilege = false;
                $.each(privilege, function(i,m) {
                    //console.log(m.privilege,data)
                    if(m.privilege == data){
                        thisprivilege = true;
                        return true
                    }
                });

                if(thisprivilege == false && data.indexOf('undefined')<0){
                   	//$(this).remove()
                }else{
                	$(this).css('visibility','inherit')
                }
            })
        }
        //		function getNavprivilege(){
//			var jsonstr = '';
//			$('.tabsPageHeaderContent .navtab-tab li').each(function(){
//				if($(this).hasClass('active')){
//					jsonstr = $(this).find('a').data('privilege');
//					return false
//				}
//			})
//			return jsonstr;
//		}
//		var nav_privilege = getNavprivilege();
    },
    //检查表格选中
    check_table_checked: function(dom){
        var $selectTrs = dom.find('tr.datagrid-selected-tr')
        return $selectTrs.length==0?false:true
    },
    fittable : function(json){
        var data = json.data,
            dom = json.dom,
            cols = json.cols,
            thead = json.thead,
            html = '';
        function table_head_init(){
            html += '<thead><tr>';
            for(var j = 0; j < thead.length; j++) {
                html += '<th align="center">'+thead[j].label +'</th>'

            }
            html +=  '</tr></thead>';
            console.log(html)
            dom.html(html);
        }
        function table_init(){
            table_head_init()
            html += '<tbody>';
            for(var i = 0; i < data.length; i++) {
                html += '<tr class="j-number">';
                for(var j = 0; j < thead.length; j++) {
                    var text = data[i][thead[j].name];
                    if(thead[j].render){
                        text = thead[j].render(text,data)
                    }
                    html += '<td align="'+thead[j].align+'">'+text +'</td>'
                }
                html +=  '</tr>';
            }
            html += '</tbody>';
            dom.html(html);
        }
        if(json.data){
            table_init()
        }
        dom.tablesMergeCell({
            cols: cols
        });
        if(json.keybg){
            var sear=new RegExp(json.keybg);
            $.each(dom.find('td'),function(i,m){
                if(sear.test($(this).html())){
                    $(this).parent('tr').css('background','#f3f4f5')
                }
            })
        }
    },
    //表格数据转换的封装，能够调用数据字典的数据
    configRender(json){
        var text = json.val,
            name = json.name,
            value = json.val,
            data = json.data,
            dataname=json.dataname||'name'
        refresh = json.refresh || false,
            keys = json.key || 'val'
        $.each(clz.getdictionary({name:name,refresh:refresh}),function(i,m){
            if(m[keys] == value){
                text = m[dataname]
            }
        })
        //console.log("aa "+text)
        return text
    },
    //通过id获取datagrid中的所有数据
    getDialogGridData(gridId) {
        var data = [];
        $.each($.CurrentDialog.find("#" + gridId).find('tbody tr'),function(i,m) {
            var trjson = {};
            trjson['index'] = i;
            $.each($(this).find('input[type="text"]'),function(i,m) {
                var name = $(this).attr('name'),
                    val  = $(this).val();
                trjson[name] = val;
            });
            $.each($(this).find('select'),function(i,m) {
                var name = $(this).attr('name'),
                    val  = $(this).val();
                trjson[name] = val;
            });
            data.push(trjson);
        });
        return data;
    },
    //显示并设置必填
    showNavRequired : function (id) {
        $.CurrentNavtab.find("#" + id).css("display","inline");
        $.CurrentNavtab.find("#" + id).find("label").attr("data-rule","required");
        $.CurrentNavtab.find("#" + id).find("div").addClass("required");
    },
    //隐藏并取消必填
    hideNavRequired : function (id) {
        $.CurrentNavtab.find("#" + id).css("display","none");
        $.CurrentNavtab.find("#" + id).find("label").removeAttr("data-rule");
        $.CurrentNavtab.find("#" + id).find("div").removeClass("required");
    },
    //显示并设置必填
    showRequired : function (id) {
        $.CurrentDialog.find("#" + id).css("display","inline");
        $.CurrentDialog.find("#" + id).find("label").attr("data-rule","required");
        $.CurrentDialog.find("#" + id).find("div").addClass("required");
    },
    //隐藏并取消必填
    hideRequired : function (id) {
        $.CurrentDialog.find("#" + id).css("display","none");
        $.CurrentDialog.find("#" + id).find("label").removeAttr("data-rule");
        $.CurrentDialog.find("#" + id).find("div").removeClass("required");
    },
    //用于获取学员证件号
    getStudentIdcard : function(obj,subject){
    if ($.trim(obj.val()) == "") {
        return;
    }
    var idcard = obj.parent().next("td").find("input");
    BJUI.ajax('doajax', {
        url: config.openBasePath + 'student/list',
        data: {
            name: obj.val(),
        },
        type: 'GET',
        loadingmask: true,
        okCallback: function (json, options) {
            var students = json.result.list;
            if (students.length == 0) {
                BJUI.alertmsg('info', '不存在对应的学员信息！');
                return false;
            } else {
                if (students.length > 1) {
                    var data = {};
                    data.students = students;
                    data.idcardObj = idcard;
                    data.subject = subject;
                    clz.setWindowJson(data);
                    BJUI.dialog({
                        id: 'dialog-multi-student',
                        url: 'html/common/student/multi-student-list.html',
                        title: '重名学员列表',
                        width: 400,
                        height: 300,
                        mask: true,
                        maxable: false,
                        resizable: false
                    });
                } else {
                    if (students[0].status != 0) {
                        var msg = clz.configRender({name:'studentstatus',val:students[0].status});
                        BJUI.alertmsg('info', '该学员状态不正常<br>当前学员状态[' + msg + ']!');
                        return false;
                    }
                    if (subject != undefined && students[0].nextsubject != subject) {
                        var msg = clz.configRender({name:'subject',val:students[0].nextsubject});
                        BJUI.alertmsg('info', '该学员不满足当前办证条件！<br>下一办证科目[' + msg + ']!');
                        return false;
                    }
                    idcard.val(students[0].idcard);
                    idcard.change();
                }
            }
        }
    });
}
}

function deepClone(obj){
    var result,oClass=isClass(obj);
        //确定result的类型
    if(oClass==="Object"){
        result={};
    }else if(oClass==="Array"){
        result=[];
    }else{
        return obj;
    }
    for(key in obj){
        var copy=obj[key];
        if(isClass(copy)=="Object"){
            result[key]=arguments.callee(copy);//递归调用
        }else if(isClass(copy)=="Array"){
            result[key]=arguments.callee(copy);
        }else{
            result[key]=obj[key];
        }
    }
    return result;
}

//返回传递给他的任意对象的类
function isClass(o){
    if(o===null) return "Null";
    if(o===undefined) return "Undefined";
    return Object.prototype.toString.call(o).slice(8,-1);
}

//console.log(222222,clz.getdictionary({name:'storelist',refresh:true}))

function formatStoreCell(value){
    for(var i=0 ;i< storedata.length ;i++){
        if(value == storedata[i].id){
            return storedata[i].name;
        }else{
            continue;
        }
    }
};

function formatAreaCell(value){
    for(var i=0 ;i< areadata.length ;i++){
        if(value == areadata[i].id){
            return areadata[i].name;
        }else{
            continue;
        }
    }
};

function formatClassCell(value){
    for(var i=0 ;i< classdata.length ;i++){
        if(value == classdata[i].id){
            return classdata[i].name;
        }else{
            continue;
        }
    }
};
function formatRoleCell(value){
    for(var i=0 ;i< roledata.length ;i++){
        if(value == roledata[i].id){
            return roledata[i].rolename;
        }else{
            continue;
        }
    }
};
function formatSubjectCell(value){
    for(var i=0 ;i< subjectdata.length ;i++){
        if(value == subjectdata[i].id){
            return subjectdata[i].subject;
        }else{
            continue;
        }
    }
};


//判断是否选择列表数据，未选中提示信息，选中返回以选中的数据。
function checkSelectData(tableobj) {
    var selectData = tableobj.data('selectedDatas');
    if (selectData == undefined || selectData == '') {
        BJUI.alertmsg('info', '请选择一行数据！');
        return null;
    } else {
        return selectData;
    }
}

//获得本月的停止日期
function getMonthEndDate(nowYear,nowMonth){
    var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowYear,nowMonth));
    return monthEndDate;
}

//获得某月的天数
function getMonthDays(nowYear,myMonth){
    var monthStartDate = new Date(nowYear, myMonth, 1);
    var monthEndDate = new Date(nowYear, myMonth + 1, 1);
    var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);
    return days;
}

function renderselect(dom,data,flag){
    if(data.length>0){
        var html = '';
        if(flag){
        	html+="<option value=\"\">请选择</option>";
        }
        for(var i=0 ;i< data.length ;i++){
            html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
        }
    }else{
        html="<option value=\"\">全部</option>";
    }
    $(dom).html(html);

    dom.selectpicker('refresh')
};

function rendercoachselect(dom,data){
    if(data.length>0){
        var html = '';
        html+="<option value=\"\">请选择</option>";
        for(var i=0 ;i< data.length ;i++){
            html += "<option value='"+data[i].coachid+"'>"+data[i].name+"</option>";
        }
    }else{
        html="<option value=\"\">全部</option>";
    }
    $(dom).html(html);

    dom.selectpicker('refresh')
};


function isCardNo(card) {
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    return reg.test(card) === true;
}

//判断数组中是否有重复数据
function hasReapeat(arrayData) {
    var flag = false;
    var s = arrayData.join(",") + ",";
    for (var i = 0; i < arrayData.length; i++) {
        if (s.replace(arrayData[i] + ",", "").indexOf(arrayData[i] + ",") > -1) {
            flag = true;
            break;
        }
    }
    return flag;
}

/************** 表格合并处理 ***************/
;(function($) {
    $.fn.tablesMergeCell = function(options) {
        var defaultsettings= {
            automatic:true,
            cols: null,        // 用数组表示列的索引,从0开始,然后根据指定列来处理(合并)相同内容单元格
            rows: null
        };
        var opts = $.extend(defaultsettings, options);
        return this.each(function() {
            var cols = opts.cols,
                rows = opts.rows;
            if(rows==null){
                for ( var i = cols.length - 1; cols[i] != undefined; i--) {
                    tablesMergeCell($(this), cols[i]);
                }
            }else{
                for ( var i = cols.length - 1,k=opts.rows.length-1; cols[i] != undefined; i--,k--) {
                    tablesMergeCell($(this), cols[i],k);
                }
            }
            dispose($(this));
        });

        // 如果对javascript的closure和scope概念比较清楚, 这是个插件内部使用的private方法
        function tablesMergeCell($table, colIndex,rowIndex) {
            $table.data('col-content', '');     // 存放单元格内容
            $table.data('col-rowspan', 1);      // 存放计算的rowspan值 默认为1
            $table.data('col-td', $());         // 存放发现的第一个与前一行比较结果不同td(jQuery封装过的), 默认一个"空"的jquery对象
            $table.data('trNum', $('tbody tr', $table).length);     // 要处理表格的总行数, 用于最后一行做特殊处理时进行判断之用
            // 我们对每一行数据进行"扫面"处理 关键是定位col-td, 和其对应的rowspan
            $('tbody tr', $table).each(function(index) {
                var $tr = $(this);
                // td:eq中的colIndex即列索引
                var $td = $('td:eq(' + colIndex + ')', $tr);
                var currentContent = $td.html();
                if(opts.automatic){     // 内容
                    // 第一次时走此分支
                    if ($table.data('col-content') == '') {
                        $table.data('col-content', currentContent);
                        $table.data('col-td', $td);
                    } else {
                        // 上一行与当前行内容相同
                        if ($table.data('col-content') == currentContent) {
                            addRowspan();   // 上一行与当前行内容相同则col-rowspan累加, 保存新值
                        }else{
                            newRowspan();   // 上一行与当前行内容不同
                        }
                    }
                }else{      // 指定
                    if(opts.rows.length>0){
                        if(opts.rows[0].length==undefined){
                            for(var i=0; i<opts.rows.length; i++){
                                customRowspan(opts.rows[i],opts.rows.length);
                            }
                        }else{
                            for(var i=0; i<opts.rows[rowIndex].length; i++){
                                customRowspan(opts.rows[rowIndex][i],opts.rows[rowIndex].length);
                            }
                        }
                    }
                }
                function customRowspan(val,len){
                    if(index==val){
                        if ($table.data('col-content') == '') {
                            if(currentContent==''){
                                currentContent = true;
                            }
                            $table.data('col-content', currentContent);
                            $td.attr('rowspan', len);
                        }else{
                            $td.hide();
                        }
                    }
                }
                function addRowspan(){
                    var rowspan = $table.data('col-rowspan') + 1;
                    $table.data('col-rowspan', rowspan);
                    // 值得注意的是 如果用了$td.remove()就会对其他列的处理造成影响
                    $td.hide();
                    // 最后一行的情况比较特殊一点
                    // 比如最后2行 td中的内容是一样的, 那么到最后一行就应该把此时的col-td里保存的td设置rowspan
                    if (++index == $table.data('trNum')) {
                        $table.data('col-td').attr('rowspan', $table.data('col-rowspan'));
                    }
                }
                function newRowspan(){
                    // col-rowspan默认为1, 如果统计出的col-rowspan没有变化, 不处理
                    if ($table.data('col-rowspan') != 1) {
                        $table.data('col-td').attr('rowspan', $table.data('col-rowspan'));
                    }
                    // 保存第一次出现不同内容的td, 和其内容, 重置col-rowspan
                    $table.data('col-td', $td);
                    $table.data('col-content', $td.html());
                    $table.data('col-rowspan', 1);
                }
            });
        }
        // 同样是个private函数 清理内存之用
        function dispose($table) {
            $table.removeData('col-content');
            $table.removeData('col-td');
            $table.removeData('trNum');
            $table.removeData('col-rowspan');
        }
    };

})(jQuery);