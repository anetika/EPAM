<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.validator.ValidatorRegex" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 05.10.2021
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="prev_page" value="${PagePath.CHANGE_PASSWORD_PAGE}" scope="session"/>
<fmt:message key="signUp.password" var="signUp_password"/>
<html>
<head>
    <title>Change password</title>
    <link rel="stylesheet" href="<c:url value="/static/css/forms.css"/>">
    <script src="<c:url value="/static/js/change_password.js"/> "></script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="data">
    <div class="inner-data">
        <form action="<c:url value="/Controller"/>" method="post" class="main-form">
            <input type="hidden" name="command" value="change_user_password_command">
            <input type="hidden" name="email" value="${requestScope.email}" />
            <div class="form-input">
                <label for="password">${signUp_password}:</label>
                <input type="password" placeholder="Password" name="password" id="password" pattern="${ValidatorRegex.PASSWORD_REGEX}" onchange="checkSamePasswords()"/>
            </div>
            <div class="form-input">
                <label for="repeated_password">${signUp_password}:</label>
                <input type="password" placeholder="Repeat password" name="repeated_password" id="repeated_password" pattern="${ValidatorRegex.PASSWORD_REGEX}" onchange="checkSamePasswords()"/>
            </div>
            <div class="form-input">
                <input type="submit" value="Enter" id="submit-button" disabled>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
