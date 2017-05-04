<%--
  Created by IntelliJ IDEA.
  User: pleiyang@outlook.com
  Date: 2017/3/19
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${pd.SYSNAME}</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="static/js/bootstrap/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="static/js/jquery/jquery-3.2.0.min.js"></script>
    <script src="static/js/jquery/jquery.tips.js"></script>
    <script src="static/js/jquery/jquery.cookie.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="static/js/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="static/login/css/login.css" />
</head>
<body>
<div class="bg">
    <div id="login">
        <form class="form-signin">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" id="saveId" value="remember-me" onclick="savePaw();"> 记住密码
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit" id="to_login">登  陆</button>
        </form>
</div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        var login = $("#to_login");
        login.click(serverCheck);
    });
    //客户端校验
    function check(){
        //验证用户名
        if ($("#inputEmail").val() == "") {

            $("#inputEmail").tips({
                side : 2,
                msg : '用户名不得为空',
                bg : '#AE81FF',
                time : 3
            });
            $("#inputEmail").focus();
            return false;
        }else {
            $("#inputEmail").val(jQuery.trim($('#inputEmail').val()));
        }
        //验证密码
        if ($("#inputPassword").val() == "") {

            $("#inputPassword").tips({
                side : 2,
                msg : '密码不得为空',
                bg : '#AE81FF',
                time : 3
            });
            $("#inputPassword").focus();
            return false;
        }

        //弹出正在登录提示
        $("#login").tips({
            side : 1,
            msg : '正在登录 , 请稍后 ...',
            bg : '#68B500',
            time : 10
        });
        return true;
    }
    //服务器端校验
    function serverCheck(){
        if(check()){

            var inputEmail = jQuery.trim($("#inputEmail").val());
            var inputPassword = jQuery.trim($("#inputPassword").val());
            var code = "qq247151115pl"+inputEmail+",pl,"+inputPassword;
            $.ajax({
                type: "POST",
                url: 'login_login',
                data: {KEYDATA:code,tm:new Date().getTime()},
                dataType:'json',
                cache: false,
                success: function(data){
                
                    if("success" == data.result){
                        saveCookie();
                        window.location.href="main/index";
                    }else if("usererror" == data.result){
                        $("#inputEmail").tips({
                            side : 1,
                            msg : "用户名或密码有误",
                            bg : '#FF5080',
                            time : 15
                        });
                        $("#inputEmail").focus();
                    }else{
                        $("#inputEmail").tips({
                            side : 1,
                            msg : "缺少参数",
                            bg : '#FF5080',
                            time : 15
                        });
                        $("#inputEmail").focus();
                    }
                }

            });
        }
    }

    //如果记住密码未勾选则清空cookie中的帐号和密码信息，已勾选则不做任何操作
    function savePaw() {
        if (!$("#saveId").attr("checked")) {
            $.cookie('inputEmail', '', {
                expires : -1   //cookie的有效期单位为天，如果不设置则浏览器关闭就失效
                //path:'/'      cookie值保存的路径，默认与创建页路径一致。
            });
            $.cookie('inputPassword', '', {
                expires : -1
            });
            $("#inputEmail").val('');
            $("#inputPassword").val('');
        }
    }

    //将用户名和密码保存到cookie中，有效期为七天
    function saveCookie() {
        if ($("#saveId").attr("checked")) {
            $.cookie('inputEmail', $("#inputEmail").val(), {
                expires : 7
            });
            $.cookie('inputPassword', $("#inputPassword").val(), {
                expires : 7
            });
        }
    }

    //从cookie中获取帐号密码
    jQuery(function() {
        var inputEmail = $.cookie('inputEmail');
        var password = $.cookie('inputPassword');

        if (typeof(inputEmail) != "undefined"
            && typeof(password) != "undefined") {
            alert(inputEmail+"--"+password);
            $("#inputEmail").val(inputEmail);
            $("#inputPassword").val(password);
            $("#saveId").attr("checked", true);
            //$("#code").focus();
        }
    });
</script>
</html>
