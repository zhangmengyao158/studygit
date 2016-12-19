<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="navigation">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>还款</title>
</head>
<body>
<form onSubmit="return Validator.Validate(this,2)" action="trade.do" method="post">
<input type="hidden" name="method" value="searchBills">
信用卡号：<input type="text" name="cardNo" dataType="Require" msg="您必须输入用户标识"> <br>
还款期号：<input type="text" name="sn">(格式：yyyy-MM，如：2007-05) <br>
<input type="submit" name="submit1" value="查询用户账单">
</form>
</body>
</html>