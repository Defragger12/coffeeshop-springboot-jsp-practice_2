<html>
<head>
    <%@ include file="fragments/header.jsp" %>
    <title>Title</title>
</head>
<body>
<div class="container thanks-for-order-div">
    <div class="row single-menu mt-10 justify-content-center flex">
        <div class="col-lg-12 centered text-italic text-success text-heading">
            <spring:message
                    code="order.thankYou"/> ${orderId}.
        </div>

        <div class="col-lg-12 centered">
            <form method="get" action="<c:url value='/coffee'/>">
                <button class="mt-25 btn btn-success btn-lg text-light" type="submit"><spring:message
                        code="button.proceedToOrder"/></button>
            </form>
        </div>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>
</body>
</html>
