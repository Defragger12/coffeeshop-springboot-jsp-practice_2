<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="localeCode" value="${pageContext.response.locale}"/>

<sec:authentication var="user" property="principal"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <base href="${pageContext.request.contextPath}/"/>

    <link href="webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/all.min.css">
    <link rel="stylesheet" href="../css/pagination.css">
    <link rel="stylesheet" href="../css/toast.css">

    <script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
    <script src="webjars/popper.js/1.14.1/umd/popper.min.js"></script>
    <script src="webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="webjars/datatables/1.9.4/media/js/jquery.dataTables.min.js"></script>

    <script type="application/javascript" src="../js/pagination.js"></script>
    <script type="application/javascript" src="../js/util.js"></script>
    <script src="../js/umd.js"></script>


    <script type="text/javascript" src="../js/jquery.twbsPagination.min.js"></script>
    <section class="header-area"></section>
    <header id="header">
        <div class="container">
            <sec:authorize access="isAuthenticated()">
                <span class="welcome-message"><spring:message code="app.welcomeMessage"/> ${user.name}</span>
                <span class="logout-message"><a class="fa fa-sign-out-alt fa-2x" title="<spring:message code="title.logout"/>" href="<c:url value="/logout"/>">
                </a></span>
            </sec:authorize>
            <div class="row align-items-center justify-content-between d-flex">
                <sec:authorize access="hasRole('ROLE_USER')">
                    <a href="${pageContext.request.contextPath}/order">
                        <i class="fa fa-shopping-cart fa-7x my-cart-image" title="<spring:message code="title.goToCart"/>"></i>
                    </a>
                </sec:authorize>

                <a
                        <sec:authorize access="hasRole('ROLE_USER')">
                            href="${pageContext.request.contextPath}/coffee"
                            title="<spring:message code="title.goToStartUser"/>"
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            href="${pageContext.request.contextPath}/admin"
                            title="<spring:message code="title.goToStartAdmin"/>"
                        </sec:authorize>
                >
                    <img class="logo-image" src="../img/shop/logo.png">
                </a>
            </div>
            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="cart-info">
                    <div class="text-right">
                    <span
                        id="totalQuantity"><%= session.getAttribute("totalQuantity") == null ? 0 : session.getAttribute("totalQuantity")%></span>
                    <span class="fa fa-coffee" title="<spring:message code="title.totalQuantity"/>"></span>
                    </div>
                    <div>
                    <span id="finalPrice">$<%= session.getAttribute("finalPrice") == null ? 0 : session.getAttribute("finalPrice")  %></span>
                    <span class="fa fa-money-check-alt" title="<spring:message code="title.totalPrice"/>"></span>
                    </div>
                </div>
            </sec:authorize>
            <div class="language-change-dropdown dropleft">
                <a class="fa btn text-white dropdown-toggle page-size-button" href="#" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${pageContext.response.locale}
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item"
                       href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a>
                    <a class="dropdown-item"
                       href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru">Русский</a>
                </div>
            </div>
        </div>
        <span class="d-none" id="locale">${localeCode}</span>
    </header>
</head>