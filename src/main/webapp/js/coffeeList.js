$(document).ready(function () {

    let pageSize = document.getElementById("page-size-value").textContent;
    let sortBy = document.getElementById("sort-by-value").textContent;

    let coffeeDiv = $('div#add-to-cart-actions');

    if(coffeeDiv.text().trim() === "") {
        document.getElementById("util-div").style.opacity = "0";
    }
    else {
        document.getElementById("util-div").style.opacity = "1";
    }

    $("select#page-size").val(pageSize).change(function(event) {
        event.preventDefault();

        sendRedrawRequest(this.dataset.url + this.value, true, redrawCoffees);
    });

    $("select#sort-by").val(sortBy).change(function(event) {
        event.preventDefault();

        sendRedrawRequest(this.dataset.url + this.value, true, redrawCoffees);
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
                redrawCoffees(result);
                redrawPagination(result);
            },
            error: function(response) {

            }
        });
    });

    addAddToCartFunctionality();
});

const redrawCoffees = (result) => {
    let coffeeDiv = $(result).find('div#add-to-cart-actions');

    if(coffeeDiv.text().trim() === "") {
        document.getElementById("util-div").style.opacity = "0";
    }
    else {
        document.getElementById("util-div").style.opacity = "1";
    }

    $('div#add-to-cart-actions').replaceWith(coffeeDiv);

    addAddToCartFunctionality();
};