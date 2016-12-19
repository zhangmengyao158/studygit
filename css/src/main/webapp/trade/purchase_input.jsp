<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="navigation">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>消费</title>
</head>
<body>
<center>
<form onSubmit="return Validator.Validate(this,2)" action="trade.do" method="post">
<input type="hidden" name="method" value="purchase">
信用卡号：<input type="text" name="cardNo" dataType="Require" msg="您必须输入用户标识"> <br>
消费金额：<input type="text" name="amount" dataType="Currency" msg="您输入的消费金额格式不对！">元 <br>
所属商户：<input type="text" name="remark" > <br>
<input type="submit" name="submit1" value="模拟消费提交">
</form>
</center>
</body>
</html>