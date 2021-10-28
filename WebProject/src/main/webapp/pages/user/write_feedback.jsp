<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<%@ page import="edu.epam.webproject.controller.command.PagePath" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 17.10.2021
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="prev_page" value="${PagePath.GO_TO_FIND_JOB_PAGE_COMMAND}" scope="session"/>
<fmt:message key="write_feedback.letter" var="write_feedback_letter"/>
<fmt:message key="find_job.writeFeedback" var="find_job_writeFeedback"/>
<html>
<head>
    <title>Write feedback</title>
    <link rel="stylesheet" href="<c:url value="/static/css/offer_vacancies.css"/> ">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container">
    <div class="row main-form">
        <form method="post" action="<c:url value="/Controller"/>" enctype="multipart/form-data">
            <input type="hidden" name="command" value="add_new_feedback_command" />
            <input type="hidden" name="vacancy_id" value="${requestScope.vacancy_id}">
            <div class="form-group">
                <label for="letter" class="cols-sm-2 control-label">${write_feedback_letter}</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                        <textarea name="letter" id="letter" cols="50" rows="10" placeholder="Write a letter"></textarea>
                    </div>
                </div>
            </div>

            <div class="submit-button-group">
                <div class="form-group">
                    <button type="submit" id="button" class="btn btn-primary btn-lg btn-block login-button">${find_job_writeFeedback}</button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>
