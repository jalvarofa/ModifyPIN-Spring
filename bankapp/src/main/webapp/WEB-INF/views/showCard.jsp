<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <h3>Card</h3>
    <p>Numero de tarjeta: ${model.cardNumber} </p>
    <p>PIN: ${model.pin}</p>
    <p>Fecha de Caducidad: ${model.expirationDate}</p>
    <p>Limite Diario: ${model.buyLimitDiary}</p>

    <a href="<c:url value="modifyPIN.htm"/>">MODIFICAR PIN</a>
</html>