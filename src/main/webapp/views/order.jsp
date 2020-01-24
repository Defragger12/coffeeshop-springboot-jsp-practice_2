<html>
<head>
    <title>Order Info</title>
    <%@ include file="fragments/header.jsp" %>
    <script type="application/javascript" src="../js/order.js"></script>
</head>
<body>
<div class="container">
    <div id="order-content" class="flex-lg-column your-order-div single-menu mt-10">

        <c:if test="${fn:length(sessionScope.coffeesToConfirm) == 0}">
            <div class="centered your-order-text text-italic"><spring:message
                    code="order.emptyCart"/></div>
        </c:if>
        <c:if test="${fn:length(sessionScope.coffeesToConfirm) > 0}">
            <div class="centered your-order-text text-italic"><spring:message
                    code="order.yourOrder"/></div>
            <div class="centered">
                <table id="orderData"
                       class="order-data-table dataTable centered table-responsive">
                    <thead>
                    <tr>
                        <th class="pb-30"></th>
                        <th><spring:message
                                code="order.coffeeName"/></th>
                        <th><spring:message
                                code="order.coffeePrice"/></th>
                        <th><spring:message
                                code="order.coffeeTotalQuantity"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="coffee" items="${sessionScope.coffeesToConfirm}">
                        <tr>
                            <td colspan="4">
                                <div class="order-delimeter"></div>
                            </td>
                        </tr>
                        <tr>

                            <td class="bg-white">

                                <a class="text-dark image-link" href="<c:out value="/coffee/${coffee.coffeeId}"/>">
                                    <img class="coffee-image-order coffee-image-in-menu" src="${coffee.coffeeImageUrl}"
                                         alt="${coffee.coffeeName}">
                                </a>
                            </td>
                            <td>${coffee.coffeeName}</td>
                            <td>
                                $${coffee.coffeePrice}</td>
                            <td id="actionButtons" data-url="<c:out value="/order/action"/>">
                            <span>
                                    ${coffee.totalQuantity}
                            </span>
                                <button data-action="addItem" data-coffeeId="${coffee.coffeeId}" type="button"
                                        class="btn btn-outline-success button-small-plus">+
                                </button>
                                <button data-action="removeItem" data-coffeeId="${coffee.coffeeId}" type="button"
                                        class="btn btn-outline-warning button-small-minus">-
                                </button>
                                <button data-action="removeCoffee" data-coffeeId="${coffee.coffeeId}" type="button"
                                        class="btn btn-outline-danger button-small-remove">x
                                </button>
                            </td>
                        </tr>

                    </c:forEach>
                    <tr>
                        <td colspan="4">
                            <div class="order-delimeter"></div>
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <div class="centered final-price-text"><spring:message
                    code="order.finalPrice"/>: <span
                    id="finalOrderPrice">$${sessionScope.finalPrice}</span></div>
            <div class="centered"><a id="confirm-order-link" href="<c:url value='/order/confirm'/>">
                <button type="button" class="mb-50 mt-25 btn btn-success btn-lg">
                    <spring:message
                            code="button.confirmOrder"/>
                </button>
            </a></div>
        </c:if>
    </div>
</div>
<%@ include file="fragments/footer.jsp" %>
</body>
</html>
