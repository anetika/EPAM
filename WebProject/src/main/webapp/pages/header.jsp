<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 06.09.2021
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Header</title>
</head>
<body>
<jsp:include page="connection.jsp"/>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Logo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <c:if test="${sessionScope.role == 'GUEST'}">
                    <li class="nav-item">
                        <a class="nav-link" href=" <c:url value="/Controller?command=go_to_sign_up_page_command"/>">Sign Up</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_sign_in_page_command"/>">Sign In</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role == 'USER'}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Find job</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_offer_vacancy_page_command"/> ">Offer vacancy</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_user_account_page_command"/> ">My profile</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value = "/Controller?command=go_to_all_irrelevant_vacancies_page_command"/>">Pending vacancies</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value = "/Controller?command=go_to_all_relevant_vacancies_page_command"/>">Vacancies</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_all_users_page_command"/> ">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_admin_account_page_command"/> ">My profile</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role != 'GUEST'}">
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=log_out_command"/> " class="nav-link">Log Out</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
