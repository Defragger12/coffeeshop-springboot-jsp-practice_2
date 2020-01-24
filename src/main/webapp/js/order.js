$(document).ready(function () {

    $("td#actionButtons > button").click(function() {

        let action = this.dataset.action;
        let elementWithQuantity =  $(this).closest("td").find("span");
        let currentQuantityValue = elementWithQuantity.text().trim();
        let newQuantityValue;

        let message = "This coffee will be removed from your order. Continue?";
        if (getLocale() === "ru") {
            message = "Это кофе будет убрано из заказа. Продолжить?"
        }

        switch (action) {
            case "addItem":
                newQuantityValue = +currentQuantityValue + 1;
                break;
            case "removeItem":
                if (currentQuantityValue === "1" && !confirm(message)) {
                    return;
                }
                newQuantityValue = +currentQuantityValue - 1;
                break;
            case "removeCoffee":
                if (!confirm(message)) {
                    return;
                }
                newQuantityValue = 0;
                break;
        }

        if (newQuantityValue === 0) {
            $(this).closest("tr").remove();
        } else {
            elementWithQuantity.text(newQuantityValue);
        }

        $.ajax({
            url: this.closest("td").dataset.url,
            data: {
                action: this.dataset.action,
                coffeeId: this.dataset.coffeeid
            },
            type: "GET",
            success: function(result) {

                let cartInfo = "Your cart is empty";
                if (getLocale() === "ru") {
                    cartInfo = "Ваша корзина пуста"
                }

                if (result.totalQuantity === "0") {
                    $('#order-content').html("<div class=\"centered your-order-text text-italic\">" + cartInfo + "</div>");
                    document.getElementById("finalPrice").textContent = "$" + result.finalPrice;
                    document.getElementById("totalQuantity").textContent = result.totalQuantity;
                    return;
                }

                document.getElementById("finalPrice").textContent = "$" + result.finalPrice;
                document.getElementById("finalOrderPrice").textContent = "$" + result.finalPrice;
                document.getElementById("totalQuantity").textContent = result.totalQuantity;
            },
            error: function(response) {
                console.log(response)
            }
        });
    })
});