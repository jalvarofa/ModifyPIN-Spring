<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head><title><fmt:message key="title2"/></title>
  <style>
			.error {
				color: #ff0000;
			}
			.errorblock{
				color: #000;
				background-color: #ffEEEE;
				border: 3px solid #ff0000;
				padding: 8px;
				margin: 16px;
			}
		</style>
  </head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <h3>Card</h3>

    <form:form method="POST" commandName="card">
    	<table>
    		<tr>
    			<td>PIN VIEJO:</td>
    			<td>${card.pin}</td>
    		</tr>
    		<tr>
    			<td>Nuevo PIN: </td>
    			<td><form:input path="newPin" maxlength="4" size="5"/></td>
    			<td><form:errors path="newPin" cssClass="error"/></td>
    		</tr>
    		<tr>
    			<td><input type="submit" value="Modificar"/></td>
    		</tr>
    	</table>
    </form:form>


    <a href="<c:url value="showCard.htm"/>">Principal</a>
</html>