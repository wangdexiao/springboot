<#--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/22 0022
  Time: 22:02
  To change this template use File | Settings | File Templates.
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <style>
        *all{
            margin: 0;
            padding: 0;
        }
        div.inner,table{
            position:absolute;
        }
        body{
            height: 100%;
            width: 100%;
            background: url('imgs/loginbg.jpg') no-repeat;
            background-size: cover;
            position: absolute;
            overflow: hidden;
            background-size:cover;
        }
        .outer{
            height: 100%;
        }
        .inner{
            border: 5px solid rgba(255,255,255,1);
            border-radius: 20px;
            width: 370px;
            height: 340px;
            top: 40%;
            left: 76%;
            transform: translate(-50%,-50%);
            box-shadow:11px 11px 11px rgba(50,50,50,1);	///边框阴影
        }
        .table{
            top: 7%;
            left: 12%;
        //border: solid;
            width: 260px;
            height: 190px;
            border-spacing:7px;
        }
        .under{
            position: fixed;
            left: 0px;
            bottom: 30px;
            width: 100%;
            text-align:center;
            font-size: 15px;
        }
        .Time{
            top: 65%;
            left: 62%;
            position: fixed;
            width: 400px;
            text-align:right;
        }
        .TEXT{
            height:72%;
            width: 200px;
        }
        a {text-decoration: none}	//去超链接下划线

     </style>

</head>
<body>


    <div class="outer">
        <div class="inner">
            <table class="table">
                <caption> <h1>学习网站系统</h1> </caption>
                <tr height="30px">
                    <td><big><b>账&nbsp;&nbsp;号</b></big></td>
                    <td><input type="text" name="user" id="user" class="TEXT"/> </td>
                </tr>
                <tr height="30px">
                    <td><big><b>密&nbsp;&nbsp;码</b></big></td>
                    <td><input class="TEXT" type="password" name="passwd" id="passwd"/> </td>
                </tr>
                <tr height="30px">
                    <td colspan="2" align="right">
                        <input class="TEXT" type='button' onclick = 'login()' value="            登陆系统              "/>
                    </td>
                </tr>
              <#--  <tr height="20px"><td colspan="2" style="text-align:right;font-size:small"><a href = "">联系我们</a> | <a href = "">帮助中心</a> | <a href = "">其他</a></td></tr>-->
            </table>
        </div>
    </div>


    <script type="text/javascript" >


        function login() {
            var user =  document.getElementById("user").value;
            var passwd = document.getElementById("passwd").value;

            // user = $(" #user").val();
            // passwd = $(" #passwd ").val();

            $.post("./userlogin",{user:user,passwd:passwd},function (result) {

                console.log("登录结果：" + result.result);
                if(result.result){

                    window.open("home",'_self');
                }
            })
        }
    </script>
</body>
</html>
