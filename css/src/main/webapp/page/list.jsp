<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<base href="/mybatis-usersystem/">
<meta  charset=UTF-8">
<title>用户列表</title>
<link type="text/css" rel="stylesheet" href="easyui/themes/icon.css">
<link type="text/css" rel="stylesheet" href="easyui/themes/default/easyui.css">
<link type="text/css" rel="stylesheet" href="css/list.css">
</head>
<body>
<h2 style="text-align:center">用户列表</h2>
     <table id="userList"></table>
      <div id="modifyDiv">
        <form id="modifyForm" method="post" enctype="multipart/form-data">
           <p><input id="uid" name="id" readonly="readonly"/></p>
           <p><input id="uname" name="name"/></p>
           <p><input id="ubirthday" name="birthday" /></p>
           <p><input id="ugender" name="gender" /></p>
           <p><input id="ucareer" name="career" /></p>
           <p><input id="uaddress" name="address" /></p> 
           <p><input id="umobile" name="mobile" /></p>
           <p><input type="file" name="picPath" onchange="chgPic(this)"/><br>
               <img src="image/not_pic.jpg" id="pic">     
           </p>
            <p><a class="closeBtn" href="javascript:void(0)">关闭</a>&nbsp;&nbsp;
               <a class="updateBtn" href="javascript:void(0)">修改</a></p>         
        </form>
     </div>
     
     <div id="detailDiv">
        <form id="detailForm" method="post" enctype="multipart/form-data">
           <p><input id="did" name="id" readonly="readonly"/></p>
           <p><input id="dname" name="name" readonly="readonly"/></p>
           <p><input id="dbirthday" name="birthday" readonly="readonly"/></p>
           <p><input id="dgender" name="gender" readonly="readonly"/></p>
           <p><input id="dcareer" name="career" readonly="readonly"/></p>
           <p><input id="daddress" name="address" readonly="readonly"/></p> 
           <p><input id="dmobile" name="mobile" readonly="readonly"/></p>
           <p><input name="picPath" readonly="readonly"/><br><br><br>
               <img src="image/not_pic.jpg" id="dpic" ><br>     
           </p>
            <p><a class="closeDetailBtn" href="javascript:void(0)">关闭</a>&nbsp;&nbsp;
         </form>
     </div>
     
   	<script type="text/javascript" src="easyui/jquery.min.js"></script>
	<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
 	<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js""></script>
 	<script type="text/javascript" src="js/list.js"></script>
</body>
</html>