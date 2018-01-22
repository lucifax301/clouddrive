/*网站常规配置*/
var config = {
    basePath : "http://localhost:8080/httpaccess/",                     //API根地址
    imagePath : "http://obqfnhv9x.bkt.clouddn.com/",                    //七牛云外链
    loginUrl : "index.html",                                            //登录页地址
    ajaxtimeout : 8000                                         
};

$.AJAX = function(json){
    $.ajax({
        type : json.type||"POST",
        url : json.url,
        xhrFields : {
            withCredentials: true
        },
        async : json.async,
        crossDomain : true,
        data : json.data ||"",
        datatype : "json",
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            json.success(data);
        },
        error : function(XMLHttpRequest) {
            _error(XMLHttpRequest,json);
        }
    });
}

// _error函数
function _error(XMLHttpRequest,json){
    if(json.code){
        json.error(JSON.parse(XMLHttpRequest.responseText));
    }else{
        switch(XMLHttpRequest.status){
            case 401:
                Layer.alert({width:300,height:150,type:"error",title:"用户鉴权失败,请重新登录"});
                break;
            case 404:
                Layer.alert({width:300,height:150,type:"error",title:"接口地址不存在"});
                break;
            case 500:
            case 502:
                Layer.alert({width:300,height:150,type:"error",title:"服务器内部错误"});
            break;
            default:
                Layer.alert({width:300,height:150,type:"error",title:"未知错误"});
        }
    }
}
