<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 30.08.2021
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="prev_page" value="${PagePath.ABOUT_PAGE}" scope="session"/>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .data {
            background: url(../../static/img/background.jpg) no-repeat fixed center center;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
    </style>
</head>
<body>
<c:if test="${sessionScope.rejected_message}">
    <script>
        alert("Your account was rejected. Please, create new or wait until admin approve it");
    </script>
    <c:remove var="rejected_message" scope="session"/>
</c:if>
<c:if test="${sessionScope.incorrect_role_message}">
    <script>
        alert("You don't have rights to visit this page. Please, visit only pages that were developed for you");
    </script>
    <c:remove var="incorrect_role_message" scope="session"/>
</c:if>
<jsp:include page="../header.jsp"/>
<div class="data">

</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
