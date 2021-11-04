<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 16.09.2021
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="prev_page" value="${PagePath.GO_TO_ALL_USERS_PAGE_COMMAND}" scope="session"/>
<fmt:message key="all_users.allUsers" var="all_users_allUsers"/>
<fmt:message key="signUp.login" var="signUp_login"/>
<fmt:message key="all_vacancies.actions" var="all_vacancies_actions"/>
<fmt:message key="all_users.ban" var="all_users_ban"/>
<fmt:message key="all_users.unban" var="all_users_unban"/>
<fmt:message key="all_users.confirm" var="all_users_confirm"/>
<fmt:message key="all_users.reject" var="all_users_reject"/>
<html>
<head>
    <title>All users</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value="/static/css/lists.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="event-schedule-area-two bg-color pad100">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title text-center">
                    <div class="title-text">
                        <h2>${all_users_allUsers}</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade active show" id="home" role="tabpanel">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th class="text-center" scope="col">Icon</th>
                                            <th scope="col">${signUp_login}</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">${all_vacancies_actions}</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="user" items="${requestScope.users_list}">
                                            <tr class="inner-box">
                                                <td>
                                                    <div class="event-img">
                                                        <c:if test="${user.icon == null}">
                                                            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="User icon" class="rounded-circle" width="150">
                                                        </c:if>
                                                        <c:if test="${user.icon != null}">
                                                                <img src="<c:url value="${user.icon}"/> " alt="User icon" id="user_icon">
                                                        </c:if>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${user.login}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${user.email}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <form action="<c:url value="/Controller"/>" method="post">
                                                        <input type="hidden" name="command" value="change_user_status_command">
                                                        <input type="hidden" name="email" value="${user.email}">
                                                        <c:if test="${user.userStatus == 'APPROVED'}">
                                                            <button type="submit" name="status" value="REJECTED" class="btn btn-danger">
                                                                ${all_users_ban}
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${user.userStatus == 'REJECTED'}">
                                                            <button type="submit" name="status" value="APPROVED" class="btn btn-success">
                                                                ${all_users_unban}
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${user.userStatus == 'IN_PROGRESS'}">
                                                            <button type="submit" name="status" value="APPROVED" class="btn btn-success">
                                                                ${all_users_confirm}
                                                            </button>
                                                            <button type="submit" name="status" value="REJECTED" class="btn btn-danger">
                                                                ${all_users_reject}
                                                            </button>
                                                        </c:if>
                                                    </form>
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
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
