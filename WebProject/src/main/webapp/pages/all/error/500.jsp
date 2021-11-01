<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 16.09.2021
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="error500.error_header" var="error_header"/>
<fmt:message key="error500.error_message" var="error_message"/>
<c:set var="prev_page" value="${PagePath.ERROR_404_PAGE}" scope="session"/>
<html>
<head>
    <title>500</title>
    <link rel="stylesheet" href="<c:url value="/static/css/error.css"/>">
</head>
<body>
<jsp:include page="../../header.jsp"/>
<div class="error-header">
    ${error_header}
</div>
<div class="error-message">
    ${error_message}
</div>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
