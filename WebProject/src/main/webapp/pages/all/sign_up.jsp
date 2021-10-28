<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.validator.ValidatorRegex" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 01.09.2021
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="prev_page" value="${PagePath.SIGN_UP_PAGE}" scope="session"/>
<fmt:message key="signUp.login" var="signUp_login"/>
<fmt:message key="signUp.password" var="signUp_password"/>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="<c:url value="../../static/css/forms.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="data">
    <div class="inner-data">
        <form action="<c:url value="/Controller"/> " method="post" class="main_form">
            <input type="hidden" name="command" value="sign_up_command">
            <div class="form-input">
                <label for="login">${signUp_login}:</label>
                <input type="login" placeholder="login" name="login" id="login" pattern="${ValidatorRegex.LOGIN_REGEX}" required/>
            </div>
            <div class="form-input">
                <label for="email">Email:</label>
                <input type="email" placeholder="email" name="email" id="email" pattern="${ValidatorRegex.EMAIL_REGEX}" required/>
            </div>
            <div class="form-input">
                <label for="password">${signUp_password}:</label>
                <input type="password" placeholder="password" name="password" id="password" pattern="${ValidatorRegex.PASSWORD_REGEX}">
            </div>
            <div class="form-input">
                <input type="submit" value="Enter" id="submit-button">
            </div>
        </form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
