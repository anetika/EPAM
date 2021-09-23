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
    <link rel="stylesheet" href="<c:url value="../../static/css/list.css"/>">
</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
<div class="event-schedule-area-two bg-color pad100">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title text-center">
                    <div class="title-text">
                        <h2>All users</h2>
            </div>
            <!-- /.col end-->
        </div>
        <!-- row end-->
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
                                <tr class="inner-box">
                                    <td>
                                        <div class="event-img">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" />
                                        </div>
                                    </td>
                                    <td>
                                        <div class="event-wrap">
                                            <h3><a href="#"><%--login--%></a></h3>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="event-wrap">
                                            <h3><a href="#"><%--email--%></a></h3>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="primary-btn">
                                            <a class="btn btn-primary" href="#">Button</a>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="inner-box">
                                    <td>
                                        <div class="event-img">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="" />
                                        </div>
                                    </td>
                                    <td>
                                        <div class="event-wrap">
                                            <h3><a href="#"><%--login--%></a></h3>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="event-wrap">
                                            <h3><a href="#"><%--email--%></a></h3>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="r-no">
                                            <span>Room D3</span>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="primary-btn">
                                            <a class="btn btn-primary" href="#">Button</a>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="inner-box border-bottom-0">
                                    <td>
                                        <div class="event-img">
                                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt="" />
                                        </div>
                                    </td>
                                    <td>
                                        <div class="event-wrap">
                                            <h3><a href="#"><%--login--%></a></h3>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="event-wrap">
                                            <h3><a href="#"><%--email--%></a></h3>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="primary-btn">
                                            <a class="btn btn-primary" href="#">Button</a>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
            </div>
            <!-- /col end-->
        </div>
        <!-- /row end-->
    </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
