<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<title>�û�������Ϣ</title>
</head>
<body>
<center>
<form action="user.do" method="post">
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
	<TBODY>
		<TR>
			<!-- ��������ӡ��༭����ı��� -->
			<td align="center" class="tdEditTitle">�û�������Ϣ</TD>
		</TR>
		<TR>
			<td>
			<!-- ��������ʼ -->

<input type="hidden" name="method" value="add">
<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td class="tdEditLabel" >�û�����</td>			
		<td class="tdEditContent"><input type="text" name="name"></td>
		<td class="tdEditLabel" >���֤��</td>			
		<td class="tdEditContent"><input type="text" name="idCardNo"></td>
	</tr>
	<tr>
		<td class="tdEditLabel" >�û�����</td>			
		<td class="tdEditContent"><input type="text" name="password"></td>
		<td class="tdEditLabel" >����ȼ�</td>			
		<td class="tdEditContent">
		<select name="applyLevel">
			<option value="GOLDEN">��</option>
			<option value="SILVER">����</option>
			<option value="EXCELLENT">���ʿ�</option>
			<option value="GENERAL">��ͨ��</option>
		</select>
		</td>
	</tr>
	<tr>
		<td class="tdEditLabel" >�Ա�</td>			
		<td class="tdEditContent"><input type="text" name="sex"></td>
		<td class="tdEditLabel" >������</td>			
		<td class="tdEditContent"><input type="text" name="salary">
		</td>
	</tr>
</table>

			<!-- ����������� -->
			</td>
		</TR>
	</TBODY>
</TABLE>

<TABLE>
		<TR align="center">
			<TD colspan="3" bgcolor="#EFF3F7">
			<input type="submit" name="saveButton"
				class="MyButton" value="�����û�������Ϣ"> 
			<input type="button" class="MyButton"
				value="�رմ���" onclick="window.close()">
			</TD>
		</TR>
</TABLE>
</form>
</center>
</body>
</html>