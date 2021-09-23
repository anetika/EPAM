<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 30.08.2021
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="header.signIn" var="header_signIn"/>
<fmt:message key="header.signUp" var="header_signUp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="connection.jsp"/>
<h1>${header_signIn}</h1>
<button type="button" class="btn btn-danger">Danger</button>
<h1>${header_signUp}</h1>
</body>
</html>
