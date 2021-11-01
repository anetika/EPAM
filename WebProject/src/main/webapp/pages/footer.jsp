<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 06.09.2021
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Footer</title>
    <link rel="stylesheet" href="<c:url value="/static/css/footerss.css"/> ">
</head>
<body>
    <footer>
        <div class="copyright-info">
            © WorkSpace Inc., 2021
        </div>
        <div class="social-networks">
            <div class="social-network-item">
                <a href="">
                    <img src="<c:url value="/static/img/icons8-instagram-50.png"/>" alt="Instagram">
                </a>
            </div>
            <div class="social-network-item">
                <a href="">
                    <img src="<c:url value="/static/img/icons8-вконтакте-50.png"/>" alt="VK">
                </a>
            </div>
            <div class="social-network-item">
                <a href="">
                    <img src="<c:url value="/static/img/icons8-телеграмма-app-50.png"/>" alt="Telegram">
                </a>
            </div>
        </div>
    </footer>
</body>
</html>
