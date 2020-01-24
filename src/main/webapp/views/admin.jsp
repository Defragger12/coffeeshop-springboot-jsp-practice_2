<html>
<head>
    <title>Admin</title>
    <%@ include file="fragments/header.jsp" %>
    <script src="../js/admin.js"></script>
    <script>
        $(document).ready(function () {
            drawPagination(
                ${sessionScope.order_pageCount},
                ${sessionScope.order_pageNumber},
                "/order/page?order_pageNumber=",
                "div#savedOrders",
                redrawOrders
            );
        });
    </script>
</head>

<body>

<span id="pageNumberValue" class="d-none">${sessionScope.order_pageNumber}</span>
<span id="pageTotalCount" class="d-none">${sessionScope.order_pageCount}</span>
<span id="totalElementsCount" class="d-none">${sessionScope.order_totalElementsCount}</span>
<span id="sort-by-value" class="d-none">${sessionScope.order_sort}</span>
<span id="page-size-value" class="d-none">${sessionScope.order_pageSize}</span>

<div class="container" style="min-height: 417px;">
    <div class="row d-flex justify-content-center">
        <div class="dropdown pt-50">
        </div>
        <div>
            <nav class="navbar navbar-expand-lg navbar-dark indigo">

                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <form id="search-by" action="<c:out value="/order/page" />" method="get"
                          class="form-inline ml-auto">
                        <div class="md-form my-0 flex">
                            <div>
                                <label class="pb-2 text-black"><spring:message code="search.id" /></label>
                                <input name="order_searchId" class="form-control mr-sm-2 mr-sl-2" type="text"
                                       placeholder=""
                                       aria-label="Search" value="${sessionScope.order_searchId}">
                            </div>
                            <div>
                                <label class="pb-2 text-black"><spring:message code="search.searchDateFrom" /></label>
                                <input name="order_searchDateFrom" class="form-control mr-sm-2 mr-sl-2" type="date"
                                       placeholder=""
                                       aria-label="Search" value="${sessionScope.order_searchCreatedDate}">
                            </div>
                            <div>
                                <label class="pb-2 text-black"><spring:message code="search.searchDateTo" /></label>
                                <input name="order_searchDateTo" class="form-control mr-sm-2 mr-sl-2" type="date"
                                       placeholder=""
                                       aria-label="Search" value="${sessionScope.order_searchCreatedDate}">
                            </div>
                            <div style="padding-top: 26px;">
                                <button class="btn btn-secondary" type="submit">
                                    <li class="search-button fa fa-search"title="<spring:message code="title.search"/>" aria-hidden="true"></li>
                                </button>
                            </div>
                        </div>
                    </form>

                </div>
            </nav>
        </div>
    </div>
    <div class="centered your-order-text text-italic"><spring:message code="order.orders" /></div>
    <div id="util-div" class="flex pb-15 justify-content-between ">
        <div>
            <label for="sort-by" class="mb-0"><spring:message code="sort.by"/></label>
            <select class="form-control-sm" id="sort-by" data-url="<c:url value="/order/page?order_sort="/>">
                <option value="id,asc"><spring:message
                        code="sort.idAsc"/></option>
                <option value="id,desc"><spring:message
                        code="sort.idDesc"/></option>
                <option value="createdDate,asc"><spring:message
                        code="sort.createdDateAsc"/></option>
                <option value="createdDate,desc"><spring:message
                        code="sort.createdDateDesc"/></option>
                <option value="isCompleted,asc"><spring:message
                        code="sort.statusAsc"/></option>
                <option value="isCompleted,desc"><spring:message
                        code="sort.statusDesc"/></option>
            </select>
        </div>

        <div class="pr-5 mr-2">
            <div id="pagination-div" class="flex justify-content-center">
                <div>
                    <ul id="pagination" class="pagination"></ul>
                </div>
            </div>
            <div class="centered">
                (<spring:message code="app.total"/> <span
                    id="totalPages">${sessionScope.order_pageCount}</span>
                <spring:message
                        code="app.pages"/>,
                <span id="totalElements">${sessionScope.order_totalElementsCount}</span> <spring:message
                    code="app.orders"/>)
            </div>
        </div>

        <div>
            <label for="page-size" class="mb-0 label-block"><spring:message code="order.shownOnPage"/></label>
            <select class="form-control-sm float-right" id="page-size" data-url="<c:url value="/order/page?order_pageSize="/>">
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="20">20</option>
            </select>
        </div>
    </div>
    <div class="container" id="savedOrders">
        <div class="row flex-lg-column your-order-div">
            <c:if test="${fn:length(requestScope.orderList) == 0}">
                <div class="centered your-order-text text-italic"><spring:message code="order.noOrders" /></div>
            </c:if>
            <c:if test="${fn:length(requestScope.orderList) > 0}">
                <div class="centered">
                    <c:forEach var="order" items="${orderList}">
                        <div class="single-menu">
                            <table class="table table-bordered dataTable centered admin-table">
                                <thead>
                                <tr>
                                    <th><spring:message code="order.Id" /></th>
                                    <th><spring:message code="order.Price" /></th>
                                    <th><spring:message code="order.createdDate" /></th>
                                    <th><spring:message code="order.customerInfo" /></th>
                                    <th><spring:message code="order.status" /></th>
                                </tr>
                                </thead>
                                <tbody id="orders">
                                <tr>
                                    <td>${order.id}</td>
                                    <td>$${order.price}</td>
                                    <td>${order.createdDate}</td>
                                    <td>${order.customerName}, ${order.customerAddress}, ${order.customerPhone} </td>
                                    <td>
                                        <c:if test="${!order.isCompleted}">
                                            <button data-url="<c:url value="/order/${order.id}/delivered" />"
                                                    class="btn btn-success" type="button">
                                                <spring:message code="button.markAsCompleted" />
                                            </button>
                                        </c:if>
                                        <c:if test="${order.isCompleted}">
                                            <button class="btn btn-info" type="button" disabled><spring:message
                                                    code="button.completed"/></button>
                                        </c:if>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <table class="table table-bordered dataTable centered admin-table">
                                <thead>
                                <tr>
                                    <th><spring:message code="order.coffeeId" /></th>
                                    <th><spring:message code="order.coffeeName" /></th>
                                    <th><spring:message code="order.coffeePrice" /></th>
                                    <th><spring:message code="order.coffeeTotalQuantity" /></th>
                                </tr>
                                <c:forEach var="orderItem" items="${order.items}">
                                <tr>
                                    <td>${orderItem.coffee.id}</td>
                                    <td>${orderItem.coffee.name}</td>
                                    <td>$${orderItem.coffee.price}</td>
                                    <td>${orderItem.quantity}</td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="fragments/footer.jsp" %>
</body>

</html>
