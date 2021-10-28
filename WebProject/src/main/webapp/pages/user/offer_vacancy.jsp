<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.validator.ValidatorRegex" %>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 26.09.2021
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="prev_page" value="${PagePath.OFFER_VACANCY_PAGE}" scope="session"/>
<fmt:message key="offer_vacancy.company_name" var="offer_vacancy_company_name"/>
<fmt:message key="offer_vacancy.position" var="offer_vacancy_position"/>
<fmt:message key="offer_vacancy.salary" var="offer_vacancy_salary"/>
<fmt:message key="all_vacancies.description" var="all_vacancies_description"/>
<fmt:message key="offer_vacancy.offer_vacancy" var="offer_vacancy_offer_vacancy"/>
<fmt:message key="user_account.logo" var="user_account_logo"/>
<html>
<head>
    <title>Offering a vacancy</title>
    <link rel="stylesheet" href="<c:url value="/static/css/offer_vacancies.css"/> ">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container">
    <div class="row main-form">
        <form method="post" action="<c:url value="/Controller"/>" enctype="multipart/form-data">
            <input type="hidden" name="command" value="add_new_vacancy_command" />
            <div class="form-group">
                <label for="name" class="cols-sm-2 control-label">${offer_vacancy_company_name}</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="company" id="name" placeholder="Enter company name" pattern="${ValidatorRegex.COMPANY_NAME_REGEX}"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="position" class="cols-sm-2 control-label">${offer_vacancy_position}</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="position" id="position" placeholder="Enter position" pattern="${ValidatorRegex.POSITION_REGEX}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="salary" class="cols-sm-2 control-label">${offer_vacancy_salary}</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="salary" id="salary" placeholder="Enter salary" pattern="${ValidatorRegex.SALARY_REGEX}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="cols-sm-2 control-label">${all_vacancies_description}</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" name="description" id="description" placeholder="Enter description" pattern="${ValidatorRegex.DESCRIPTION_REGEX}"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="logo" class="cols-sm-2 control-label">
                    ${user_account_logo}
                </label>
                <label for="logo" class="cols-sm-2 control-label">
                    <img src="<c:url value="/static/img/logo/logo_upload.png"/>" alt="Upload logo">
                </label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                        <input type="file" id="logo" name="logo" class="logo_input">
                    </div>
                </div>
            </div>

            <div class="submit-button-group">
                <div class="form-group">
                    <button type="submit" id="button" class="btn btn-primary btn-lg btn-block login-button">${offer_vacancy_offer_vacancy}</button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
