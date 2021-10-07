<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 16.09.2021
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<c:url value="../../static/css/list.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="event-schedule-area-two bg-color pad100">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title text-center">
                    <div class="title-text">
                        <h2>All users</h2>
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
                                            <th scope="col">Login</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Actions</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="user" items="${requestScope.users_list}">
                                            <tr class="inner-box">
                                                <td>
                                                    <div class="event-img">
                                                        <img src="" alt="User" />
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
                                                                BAN
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${user.userStatus == 'REJECTED'}">
                                                            <button type="submit" name="status" value="APPROVED" class="btn btn-success">
                                                                UNBAN
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${user.userStatus == 'IN_PROGRESS'}">
                                                            <button type="submit" name="status" value="APPROVED" class="btn btn-success">
                                                                Confirm
                                                            </button>
                                                            <button type="submit" name="status" value="REJECTED" class="btn btn-danger">
                                                                Reject
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
