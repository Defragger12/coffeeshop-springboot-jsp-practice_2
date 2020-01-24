<html>
<head>
    <title>Login</title>
    <%@ include file="fragments/header.jsp" %>
</head>
<body>
<div class="container login-div">
    <h3 class="mb-20"><spring:message
            code="login.pleaseLogIn"/></h3>
    <c:if test="${param.error != null}">
        <div style="color: red"><spring:message
                code="error.invalidCredentials"/></div>
    </c:if>
    <form action="<c:url value="/login"/>" method="post">
        <div class="form-group">
            <label for="username"><spring:message
                    code="login.userName"/>:</label> <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="pwd"><spring:message
                    code="login.password"/>:</label> <input type="password"
                                                      class="form-control" id="pwd" name="password">
        </div>

        <button type="submit" class="btn btn-success"><spring:message
                code="button.submit"/></button>
    </form>
</div>
<%@ include file="fragments/footer.jsp" %>
</body>
</html>