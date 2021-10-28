<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.validator.ValidatorRegex"%>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 01.09.2021
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="prev_page" value="${PagePath.SIGN_IN_PAGE}" scope="session"/>
<fmt:message key="signUp.password" var="signUp_password"/>
<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" href="<c:url value="/static/css/forms.css"/>">
    <script src="<c:url value="/static/js/form.js"/> "></script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="data">
    <div class="inner-data">
        <form action="<c:url value="/Controller"/>" method="post" class="main_form">
            <input type="hidden" name="command" value="sign_in_command">
            <div class="form-input">
                <label for="email">Email:</label>
                <input type="email" placeholder="email" name="email" id="email" pattern="${ValidatorRegex.EMAIL_REGEX}" required>
            </div>
            <div class="form-input">
                <label for="password">${signUp_password}:</label>
                <input type="password" placeholder="password" name="password" id="password" pattern="${ValidatorRegex.PASSWORD_REGEX}" required>
            </div>
            <div class="form-input">
                <a href="<c:url value="/Controller?command=go_to_forget_password_page_command"/>">Forgot password?</a>
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
