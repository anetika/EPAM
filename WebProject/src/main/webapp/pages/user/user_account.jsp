<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 09.09.2021
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="prev_page" value="${PagePath.GO_TO_USER_ACCOUNT_PAGE_COMMAND}" scope="session"/>
<fmt:message key="header.vacancies" var="header_vacancies"/>
<fmt:message key="all_vacancies.company" var="all_vacancies_company"/>
<fmt:message key="all_vacancies.description" var="all_vacancies_description"/>
<fmt:message key="all_vacancies.actions" var="all_vacancies_actions"/>
<fmt:message key="offer_vacancy.position" var="offer_vacancy_position"/>
<fmt:message key="offer_vacancy.salary" var="offer_vacancy_salary"/>
<fmt:message key="all_users.icon" var="all_users_icon"/>
<fmt:message key="user_account.date" var="user_account_date"/>
<fmt:message key="signUp.login" var="signUp_login"/>
<fmt:message key="user_account.logo" var="user_account_logo"/>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" href="<c:url value="/static/css/account.css"/>">
    <script src="<c:url value="/static/js/account.js"/> "></script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
    <div class="container">
        <div class="main-body">
            <div class="row gutters-sm">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex flex-column align-items-center text-center">
                                <c:if test="${user.icon == null}">
                                    <label for="content" class="d-flex flex-column align-items-center text-center">
                                        <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="User icon" class="rounded-circle" width="150">
                                    </label>
                                </c:if>
                                <c:if test="${user.icon != null}">
                                    <label for="content" class="d-flex flex-column align-items-center text-center">
                                        <img src="<c:url value="${user.icon}"/> " alt="User icon" id="user_icon">
                                    </label>
                                </c:if>
                                <div class="mt-3">
                                    <form action="<c:url value="/Controller"/>" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="command" value="upload_user_icon_command">
                                        <input type="file" id="content" name="content" onchange="uploadButtonController()">
                                        <input type="submit" class="btn btn-primary" value="Upload photo" id="upload_button" disabled/>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card mt-3">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-github mr-2 icon-inline"><path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path></svg>Github</h6>
                                <span class="text-secondary">${user.login.toLowerCase()}</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-twitter mr-2 icon-inline text-info"><path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"></path></svg>Twitter</h6>
                                <span class="text-secondary">@${user.login.toLowerCase()}</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-instagram mr-2 icon-inline text-danger"><rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>Instagram</h6>
                                <span class="text-secondary">${user.login.toLowerCase()}</span>
                            </li>
                            <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-facebook mr-2 icon-inline text-primary"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path></svg>Facebook</h6>
                                <span class="text-secondary">${user.login.toLowerCase()}</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">${signUp_login}</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.login}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Email</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.email}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <!--Vacancies list-->
                        <div class="col-md-6">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th class="text-center" scope="col">${user_account_logo}</th>
                                            <th scope="col">${all_vacancies_company}</th>
                                            <th scope="col">${offer_vacancy_position}</th>
                                            <th scope="col">${offer_vacancy_salary}</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="vacancy" items="${requestScope.vacancies_list}">
                                            <tr class="inner-box">
                                                <td>
                                                    <div class="event-img">
                                                        <c:if test="${vacancy.logo == null}">
                                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="Default logo" class="logo"/>
                                                        </c:if>
                                                        <c:if test="${vacancy.logo != null}">
                                                            <img src="<c:url value="${vacancy.logo}"/>" alt="Vacancy logo" class="logo">
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${vacancy.company}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${vacancy.position}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${vacancy.salary}$</h3>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!--Feedbacks list-->
                        <div class="col-md-6">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th class="text-center" scope="col">${signUp_login}</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">${user_account_date}</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="feedback" items="${requestScope.feedbacks_list}">
                                            <tr class="inner-box">
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${feedback.vacancy.company}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${feedback.vacancy.position}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${feedback.date}</h3>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
