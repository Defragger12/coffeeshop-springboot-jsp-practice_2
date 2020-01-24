<html>
<head>
    <%@ include file="fragments/header.jsp" %>
    <script src="../js/confirmOrder.js"></script>
    <title>Confirm Order</title>
</head>
<body>

<form class="form-inline ml-auto" id="user-info-form" action="<c:url value="/order/confirm"/>" method="post">
    <div class="container single-menu mt-10 confirm-order">
        <div class="mt-25 mb-50">
            <div class="centered text-info text-heading mb-4 font-weight-bold">
                <spring:message
                        code="order.enterContactInfo"/>:
            </div>
            <div class="centered mt-3 md-form my-0 flex justify-content-center">
                <div class="pr-2">
                    <input autocomplete="off" class="form-control" type="text" name="customer_name"
                           placeholder="<spring:message
                                code="placeholder.yourName"/>" value="${customer_name}">
                    <div class="invalid-feedback">
                        <spring:message
                                code="error.emptyName"/>
                    </div>
                </div>
                <div class="pr-2">
                    <input autocomplete="off" class="form-control" type="text" name="customer_phone" value="${customer_phone}"
                           placeholder="<spring:message
                                code="placeholder.phone"/>">
                    <div class="invalid-feedback">
                        <spring:message
                                code="error.invalidPhoneNumber"/>
                    </div>
                </div>
                <div>
                    <input autocomplete="off" class="form-control" type="text" name="customer_address"
                           placeholder="<spring:message
                                code="placeholder.address"/>" value="${customer_address}">
                    <div class="invalid-feedback">
                        <spring:message
                                code="error.emptyAddress"/>
                    </div>
                </div>
            </div>
            <c:if test="${fn:length(requestScope.errors) > 0}">
                <div class="centered font-weight-bold text-danger mt-20 fa fa-2x w-100 centered">
                    <div>
                    <c:forEach var="error" items="${errors}">
                        <div>${error}</div>
                    </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="flex-lg-column your-order-div mt-15">
            <div class="centered your-order-text"><spring:message
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
                            <td>$${coffee.coffeePrice}</td>
                            <td id="actionButtons" data-url="<c:out value="/order/action"/>">
                            <span>
                                    ${coffee.totalQuantity}
                            </span>
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
            <div class="centered">
                <button id="finish-order-button" class="mb-50 mt-25 btn btn-success btn-lg"><spring:message
                        code="button.finishOrder"/></button>
            </div>
        </div>
    </div>
    <a class="d-none" id="form-submit" href="<c:url value='/order/confirm'/>"></a>

</form>

<%@ include file="fragments/footer.jsp" %>

</body>
</html>
