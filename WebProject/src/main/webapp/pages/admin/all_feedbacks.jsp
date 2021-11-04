<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 16.09.2021
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="prev_page" value="${PagePath.ALL_FEEDBACKS_PAGE}" scope="session"/>
<fmt:message key="header.feedbacks" var="header_feedbacks"/>
<fmt:message key="signUp.login" var="sign_up_login"/>
<fmt:message key="user_account.date" var="user_account_date"/>
<html>
<head>
    <title>All feedbacks</title>
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
                        <h2>${header_feedbacks}</h2>
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
                                            <th scope="col">${sign_up_login}</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">${user_account_date}</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="feedback" items="${requestScope.feedbacks_list}">
                                            <tr class="inner-box">
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${feedback.employee.login}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${feedback.employee.email}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${feedback.date}
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
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
