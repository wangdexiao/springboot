<#--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/21 0021
  Time: 17:46
  To change this template use File | Settings | File Templates.
-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>

    <h1 style="color: red">${name}</h1>

    <img src="imgs/oldpc.jpg" width="400" height="300">
    <br/>

    <button onclick="qiaogou()">抢购</button>

    <script type="text/javascript">
        var params = {
            userId:1,
            productId:1,
            quantity:3
        };
        function qiaogou() {
            for(var i = 0; i <= 10;i++){
                $.post("./purchase",params,function (result) {
                    console.log("抢购成功:" + result.success)
                })
            }
        }


    </script>
</body>
</html>
