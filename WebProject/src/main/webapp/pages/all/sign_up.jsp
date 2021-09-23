<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 01.09.2021
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="<c:url value="../../static/css/form.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="data">
    <div class="inner-data">
        <form action="<c:url value="/Controller"/> ">
            <input type="hidden" name="command" value="sign_up_command">
            <div class="form-input">
                <label for="login">Login:</label>
                <input type="login" placeholder="login" name="login" id="login">
            </div>
            <div class="form-input">
                <label for="email">Email:</label>
                <input type="email" placeholder="email" name="email" id="email">
            </div>
            <div class="form-input">
                <label for="password">Password:</label>
                <input type="password" placeholder="password" name="password" id="password">
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
