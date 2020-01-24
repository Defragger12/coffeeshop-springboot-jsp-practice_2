<html>
<head>
    <title>Coffee List</title>
    <%@ include file="fragments/header.jsp" %>
    <script type="application/javascript" src="../js/coffeeInfo.js"></script>
</head>
<body>

<div class="container">
    <div class="row" id="add-to-cart-actions">
        <form action="<c:url value='/order/add-to-cart'/>">
            <div class="col-lg-12 pt-25">
                <div class="single-menu flex coffee-single-item">
                    <div class="col-lg-4 centered pl-30">
                        <img class="coffee-single-image" src="${coffee.imageUrl}" alt="${coffee.name}">
                    </div>
                    <div class="col-lg-6">
                        <div class="coffee-single-title">
                            ${coffee.name}</div>
                        <p class="coffee-single-description">

                            ${coffee.description}
                        </p>
                    </div>
                    <div class="col-lg-2">
                        <div class="price col-12">
                            $${coffee.price}
                        </div>
                        <div class="number-input">
                            <form action="#">
                                <div class="col-12">
                                    <input class="form-control" min="1" type="number" name="quantityToAdd" value="1">
                                </div>
                            </form>
                        </div>
                        <button type="submit" class="add-to-cart-button">
                            <i class="pt-10 fa fa-5x fa-cart-arrow-down add-to-cart-image" title="<spring:message code="title.addToCart"/>"></i>
                        </button>
                    </div>
                    <input type="hidden" name="coffeeId" value="${coffee.id}"/>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>
</body>
</html>