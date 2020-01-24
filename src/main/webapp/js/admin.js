$(document).ready(function () {

    let ordersBody = $('div#savedOrders');

    if(ordersBody.text().trim() === "No orders present") {
        document.getElementById("util-div").style.opacity = "0";
    }
    else {
        document.getElementById("util-div").style.opacity = "1";
    }

    applySetOrderDeliveredFunctionality();

    let pageSize = document.getElementById("page-size-value").textContent;
    let sortBy = document.getElementById("sort-by-value").textContent;

    $("select#page-size").val(pageSize).change(function(event) {
        event.preventDefault();

        sendRedrawRequest(this.dataset.url + this.value, true, redrawOrders);
    });

    $("select#sort-by").val(sortBy).change(function(event) {
        event.preventDefault();

        sendRedrawRequest(this.dataset.url + this.value, true, redrawOrders);
    });

    $("form#search-by").submit(function(event) {
        event.preventDefault();

        let submittedForm = $(this);
        let formData = submittedForm.serialize();

        $.ajax({
            url: submittedForm.attr('action'),
            data: submittedForm.serialize(),
            type: submittedForm.attr('method'),
            success: function(result) {
                redrawOrders(result);
                redrawPagination(result);
            },
            error: function(response) {
                console.log(response)
            }
        });
    });
});

const redrawOrders = (result) => {
    let ordersBody = $(result).find('div#savedOrders');

    if(ordersBody.text().trim() === "No orders present") {
        document.getElementById("util-div").style.opacity = "0";
    }
    else {
        document.getElementById("util-div").style.opacity = "1";
    }

    $('div#savedOrders').replaceWith(ordersBody);
    applySetOrderDeliveredFunctionality();
};
