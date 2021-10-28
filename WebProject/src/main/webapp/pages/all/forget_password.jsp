<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.validator.ValidatorRegex"%>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 18.10.2021
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="prev_page" value="${PagePath.FORGET_PASSWORD_PAGE}" scope="session"/>
<html>
<head>
    <title>Forget password</title>
    <link rel="stylesheet" href="<c:url value="/static/css/forms.css"/>">
</head>
<body>
    <jsp:include page="../header.jsp"/>
    <c:if test="${requestScope.incorrect_data}">
        <script>
            alert("User with such email doesn't exist");
        </script>
    </c:if>
        <div class="data">
            <div class="inner-data">
                <c:if test="${requestScope.email == null}">
                    <form action="<c:url value="/Controller"/>" method="post" class="main_form">
                        <input type="hidden" name="command" value="forget_password_command">
                        <div class="form-input">
                            <label for="email">Email:</label>
                            <input type="email" placeholder="email" name="email" id="email" pattern="${ValidatorRegex.EMAIL_REGEX}">
                        </div>
                        <div class="form-input">
                            <input type="submit" value="Enter" id="submit-button">
                        </div>
                    </form>
                </c:if>
                <c:if test="${requestScope.email != null}">
                    <div class="mail-send">
                        Message with change password link was sent to email: ${requestScope.email}.
                    </div>
                </c:if>
            </div>
        </div>
    <jsp:include page="../footer.jsp"/>
</body>
</html>
