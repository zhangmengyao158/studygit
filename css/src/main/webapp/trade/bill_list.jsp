<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@include file="/common/common.jsp" %>
<html>
<head>
<meta name="decorator" content="navigation">
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>�û��˵�</title>
</head>
<BODY bgColor=#dee7ff leftMargin=0 background="" topMargin=0 marginheight="0" marginwidth="0">
<center>
	  <form action="trade.do" method="post">
	  <input type="hidden" name="method" value="deposit">
	  
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD width="82%" height=14 align=right vAlign=center noWrap>
            </TD>
            <TD width="18%" align=right vAlign=center noWrap>��</TD>
          </TR>
          <TR>
            <TD height=14 align=right vAlign=center noWrap>
            	<!-- ����������ѯ�� -->
            </TD>
            <TD height=14 align="left" vAlign=center noWrap>
            <% 
            /**
            * �����ﶨ�塰��ӡ�������ѯ���Ȱ�ť
            * <input type="image" name="find" value="find" src="images/cz.gif">
            * &nbsp;&nbsp;&nbsp;&nbsp; 
            * <a href="#" onClick="BeginOut('document.do?method=addInput','470')">
            * <img src="images/addpic.gif" border=0 align=absMiddle style="CURSOR: hand"></a>
            */ 
            %>
            
              ���ÿ��ţ�<input type="text" name="cardNo" value="${bill.card.cardNo }" readonly="true">
              
              �û�������<input type="text" value="${bill.card.owner.name }" readonly="true">
              
              �˵�������<input type="text" name="sn" value="${bill.sn }" readonly="true"> <br>
              
              �˵��ܶ<input type="text" name="amount" readonly="true" value="${bill.amount }">
              
              ���ڷ��<input type="text" name="amerce" readonly="true" value="${tradeForm.amerce }">
              
              Ӧ���ܶ<input type="text" name="" value="${tradeForm.shouldPay}" readonly="true">
              <input type="submit" name="submit2" value="����">
            </TD>
          </TR>
          <TR>
            <TD height=28 colspan="2" align=center vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <!-- ��������������ҳ������ -->
            *********************************** �˵���ϸ�б� ***************************************
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      </form>
      <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6">
          <!-- �б������ -->
	      <tr bgcolor="#EFF3F7" class="TableBody1">
		      <td width="25%" height="37" align="center"><b>����</b></td>
		      <td width="25%" height="37" align="center"><B>��ע[�̻�]</B></td>
		      <td width="25%" height="37" align="center"><b>����ʱ��</b></td>
		      <td width="25%" height="37" align="center"><b>�������</b></td>
          </tr>
          <!-- �б������� -->
          <c:if test="${!empty trades}">
          <c:forEach items="${trades }" var="trade">
	      <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
		      <td align="center" vAlign="center">${trade.type}</td>
	          <td align="center" vAlign="center">${trade.remark }</td>
	          <td align="center" vAlign="center">${trade.tradeTime }</td>
	          <td align="center" vAlign="center"><fmt:formatNumber type="currency" value="${trade.amount}" /> </td>
        </tr>
        </c:forEach>
		</c:if>
        <!-- ���б�����Ϊ�յ�ʱ��Ҫ��ʾ����ʾ��Ϣ -->
	    <c:if test="${empty trades}">
	    <tr>
	    	<td colspan="5" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	û���ҵ���Ӧ�ļ�¼
	    	</td>
	    </tr>
	    </c:if>
      </table>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD height=28 align=right vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <!-- ��������������ҳ������ -->
    		</TD>
          </TR>
        </TBODY>
      </TABLE>
</center>

</body>

</html>
