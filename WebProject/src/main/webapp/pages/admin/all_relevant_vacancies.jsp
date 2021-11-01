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
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="prev_page" value="${PagePath.GO_TO_ALL_RELEVANT_VACANCIES_PAGE_COMMAND}" scope="session"/>
<fmt:message key="header.vacancies" var="header_vacancies"/>
<fmt:message key="all_vacancies.company" var="all_vacancies_company"/>
<fmt:message key="all_vacancies.description" var="all_vacancies_description"/>
<fmt:message key="all_vacancies.actions" var="all_vacancies_actions"/>
<html>
<head>
    <title>Vacancies</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value="/static/css/listsss.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="event-schedule-area-two bg-color pad100">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title text-center">
                    <div class="title-text">
                        <h2>${header_vacancies}</h2>
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
                                            <th class="text-center" scope="col">Logo</th>
                                            <th scope="col">${all_vacancies_company}</th>
                                            <th scope="col">${all_vacancies_description}</th>
                                            <th scope="col">${all_vacancies_actions}</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="vacancy" items="${requestScope.vacancies_list}">
                                            <tr class="inner-box">
                                                <td>
                                                    <div class="event-img">
                                                        <c:if test="${vacancy.logo == null}">
                                                            <img src="" alt="Logo" />
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
                                                        <h3>${vacancy.description}</h3>
                                                    </div>
                                                </td>
                                                <td>
                                                    <form action="<c:url value="/Controller"/>" method="post">
                                                        <input type="hidden" name="command" value="change_vacancy_status_command">
                                                        <input type="hidden" name="vacancy_id" value="${vacancy.id}">
                                                        <button type="submit" name="status" value="IRRELEVANT" class="btn btn-danger">
                                                            Reject
                                                        </button>
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
