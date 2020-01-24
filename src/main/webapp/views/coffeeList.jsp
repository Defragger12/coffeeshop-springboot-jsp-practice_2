<html>
<head>
    <title>Coffee List</title>
    <%@ include file="fragments/header.jsp" %>

    <script src="../js/coffeeList.js"></script>
    <script>
        $(document).ready(function () {
            drawPagination(
                ${sessionScope.coffee_pageCount},
                ${sessionScope.coffee_pageNumber},
                "/coffee/page?coffee_pageNumber=",
                "div#add-to-cart-actions",
                redrawCoffees)
            ;
        });
    </script>
</head>
<body>
<span id="pageNumberValue" class="d-none">${sessionScope.coffee_pageNumber}</span>
<span id="pageTotalCount" class="d-none">${sessionScope.coffee_pageCount}</span>
<span id="totalElementsCount" class="d-none">${sessionScope.coffee_totalElementsCount}</span>
<span id="sort-by-value" class="d-none">${sessionScope.coffee_sort}</span>
<span id="page-size-value" class="d-none">${sessionScope.coffee_pageSize}</span>

<section class="menu-area" id="coffee">
    <div class="container" style="min-height: 470px;">
        <div class="row d-flex justify-content-center">
            <div>
                <nav class="navbar navbar-expand-lg navbar-dark indigo">
                    <button class="btn btn-secondary mr-10 mt-2">
                        <a class="search-button fa fa-undo" href="<c:out value="/coffee/reset"/>">
                        </a>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <form id="search-by" action="<c:out value="/coffee/page" />" method="get"
                              class="form-inline ml-auto justify-content-center centered">
                            <div class="md-form my-0 flex">
                                <div>
                                    <label class="pb-2 text-black"><spring:message
                                            code="search.name"/></label>
                                    <input name="coffee_searchName" class="form-control mr-sm-2 mr-sl-2" type="text"
                                           placeholder=""
                                           aria-label="Search" value="${sessionScope.coffee_searchName}">
                                </div>
                                <div>

                                    <label class="pb-2 text-black"><spring:message
                                            code="search.description"/></label>
                                    <input name="coffee_searchDescription" class="form-control mr-sm-2 mr-sl-2"
                                           type="text"
                                           placeholder=""
                                           aria-label="Search" value="${sessionScope.coffee_searchDescription}">
                                </div>
                                <div>
                                    <label class="pb-2 text-black"><spring:message
                                            code="search.minPrice"/></label>
                                    <input name="coffee_minPrice" class="form-control mr-sm-2 mr-sl-2" type="number"
                                           placeholder=""
                                           aria-label="Search" value="${sessionScope.coffee_minPrice}">
                                </div>
                                <div>
                                    <label class="pb-2 text-black"><spring:message
                                            code="search.maxPrice"/></label>
                                    <input name="coffee_maxPrice" class="form-control mr-sm-2 mr-sl-2" type="number"
                                           placeholder=""
                                           aria-label="Search" value="${sessionScope.coffee_maxPrice}">
                                </div>
                                <div style="padding-top: 26px;">
                                    <button class="btn btn-secondary" type="submit">
                                        <li class="search-button fa fa-search" aria-hidden="true"></li>
                                    </button>
                                </div>
                            </div>
                        </form>

                    </div>
                </nav>
            </div>

            <div class="menu-content col-lg-10 pb-25">
                <div class="title text-center">
                    <h1 class="mb-10"><spring:message code="app.advertisement"/></h1>
                </div>
            </div>
            <div class="ml-15 flex pb-25 w-100 justify-content-between" id="util-div">
                <div>
                    <label for="sort-by" class="mb-0"><spring:message code="sort.by"/></label>
                    <select class="form-control-sm" id="sort-by" data-url="<c:url value="/coffee/page?coffee_sort="/>">
                        <option value="name,asc"><spring:message
                                code="sort.nameAsc"/></option>
                        <option value="name,desc"><spring:message
                                code="sort.nameDesc"/></option>
                        <option value="price,asc"><spring:message
                                code="sort.priceAsc"/></option>
                        <option value="price,desc"><spring:message
                                code="sort.priceDesc"/></option>
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
                            id="totalPages">${sessionScope.coffee_pageCount}</span>
                        <spring:message
                                code="app.pages"/>,
                        <span id="totalElements">${sessionScope.coffee_totalElementsCount}</span> <spring:message
                            code="app.coffees"/>)
                    </div>
                </div>

                <div class="mr-15">
                    <label for="page-size" class="mb-0 label-block"><spring:message code="coffee.shownOnPage"/></label>
                    <select class="form-control-sm float-right" id="page-size" data-url="<c:url value="/coffee/page?coffee_pageSize="/>">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="5">5</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="row" id="add-to-cart-actions">
            <c:forEach var="coffee" items="${coffeeList}">
                <form action="<c:url value='/order/add-to-cart'/>">
                    <div class="col-lg-12">
                        <div class="single-menu menu-height-locked flex">
                            <div>
                                <a class="text-dark" href="<c:out value="/coffee/${coffee.id}"/>">
                                    <img class="coffee-image" src="${coffee.imageUrl}" alt="${coffee.name}">
                                </a>
                            </div>
                            <div class="title-div col-lg-8">
                            <span class="coffee-title">
                                <a class="text-dark"
                                   href="<c:out value="/coffee/${coffee.id}"/>">${coffee.name}</a></span>
                                <p class="coffee-description">
                                    <a class="text-dark" href="<c:out value="/coffee/${coffee.id}"/>">
                                            ${coffee.description}
                                    </a>
                                </p>
                            </div>

                            <div class="col-lg-2">
                                <div class="price col-12">
                                    $${coffee.price}
                                </div>
                                <div class="number-input">
                                    <div class="col-12">
                                        <input class="form-control" min="1" type="number" name="quantityToAdd"
                                               value="1">
                                    </div>
                                </div>
                                <button type="submit" class="add-to-cart-button">
                                    <i class="pt-10 fa fa-5x fa-cart-arrow-down add-to-cart-image"></i>
                                </button>
                            </div>
                            <input type="hidden" name="coffeeId" value="${coffee.id}"/>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>
    </div>
</section>

<%@ include file="fragments/footer.jsp" %>
</body>
</html>
