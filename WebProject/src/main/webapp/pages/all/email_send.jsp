<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 24.10.2021
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="prev_page" value="${PagePath.MESSAGE_SEND_PAGE}" scope="session"/>
<html>
<head>
    <title>Message</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="data">
    <div class="inner-data">
        <c:if test="${!sessionScope.email_confirm}">
            Message with activate account link was sent to your email
            <c:remove var="email_confirm" scope="session"/>
        </c:if>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
