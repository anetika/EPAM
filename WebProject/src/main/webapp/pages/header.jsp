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
<%@ taglib prefix="mytag" uri="customtag" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<fmt:message key="header.signIn" var="header_signIn"/>
<fmt:message key="header.signUp" var="header_signUp"/>
<fmt:message key="header.offerVacancy" var="header_offerVacancy"/>
<fmt:message key="header.myProfile" var="header_myProfile"/>
<fmt:message key="header.pendingVacancies" var="header_pendingVacancies"/>
<fmt:message key="header.vacancies" var="header_vacancies"/>
<fmt:message key="header.users" var="header_users"/>
<fmt:message key="header.logOut" var="header_logOut"/>
<fmt:message key="header.findJob" var="header_findJob"/>
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
                        <a class="nav-link" href=" <c:url value="/Controller?command=go_to_sign_up_page_command"/>">${header_signUp}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_sign_in_page_command"/>">${header_signIn}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role == 'USER'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_find_job_page_command"/>">${header_findJob}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_offer_vacancy_page_command"/>">${header_offerVacancy}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_user_account_page_command"/> ">${header_myProfile}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value = "/Controller?command=go_to_all_irrelevant_vacancies_page_command"/>">${header_pendingVacancies}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value = "/Controller?command=go_to_all_relevant_vacancies_page_command"/>">${header_vacancies}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_all_users_page_command"/>">${header_users}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/Controller?command=go_to_admin_account_page_command"/> ">${header_myProfile}</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role != 'GUEST'}">
                    <li class="nav-item">
                        <a href="<c:url value="/Controller?command=log_out_command"/> " class="nav-link">${header_logOut}</a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <form action="<c:url value="/Controller"/> " method="post">
                        <mytag:localization />
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
