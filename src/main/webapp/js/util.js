const addAddToCartFunctionality = () => {
    $("div#add-to-cart-actions form").submit(function(event) {
        event.preventDefault();

        let submittedForm = $(this);
        let formData = submittedForm.serializeArray();
        let isInputWrong = false;
        formData.forEach(object => {
            if (!isInputWrong && object.name === "quantityToAdd" && object.value === "0") {
                isInputWrong = true;
            }
        });

        if (isInputWrong) {
            return;
        }

        $.ajax({
            url: submittedForm.attr('action'),
            data: submittedForm.serialize(),
            type: submittedForm.attr('method'),
            success: function(result) {

                let infoMessage = "Coffee was successfully added to cart";
                if (getLocale() === "ru") {
                    infoMessage = "Кофе было успешно добавлено"
                }

                new Toast(infoMessage, "success", 2000);
                let resultJson = JSON.parse(result);
                document.getElementById("finalPrice").textContent = "$" + resultJson.finalPrice;
                document.getElementById("totalQuantity").textContent = resultJson.totalQuantity;
            },
            error: function(response) {
               new Toast(response.responseText, "error", 2000);
            }
        });
    });
};

const applySetOrderDeliveredFunctionality = () => {
    $("div#savedOrders button").click(function () {

        event.preventDefault();

        let element = $(this);
        let buttonTitle = "Completed";
        if (getLocale() === "ru") {
            buttonTitle = "Выполнен"
        }

            $.ajax({
            url: this.dataset.url,
            type: "GET",
            success: function (result) {
                $(element).replaceWith("<button class=\"btn btn-info\" type=\"button\" disabled>" + buttonTitle + "</button>");
            },
            error: function (response) {
                $(this).addClass("d-none");
            }
        });
    });
};

const getLocale = () => {
    return document.getElementById("locale").textContent;
};