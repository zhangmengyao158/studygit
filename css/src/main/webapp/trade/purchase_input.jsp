<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="navigation">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>����</title>
</head>
<body>
<center>
<form onSubmit="return Validator.Validate(this,2)" action="trade.do" method="post">
<input type="hidden" name="method" value="purchase">
���ÿ��ţ�<input type="text" name="cardNo" dataType="Require" msg="�����������û���ʶ"> <br>
���ѽ�<input type="text" name="amount" dataType="Currency" msg="����������ѽ���ʽ���ԣ�">Ԫ <br>
�����̻���<input type="text" name="remark" > <br>
<input type="submit" name="submit1" value="ģ�������ύ">
</form>
</center>
</body>
</html>