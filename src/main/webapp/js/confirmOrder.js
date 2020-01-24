$(document).ready(function () {

    $("input[name='customer_name']").blur(function() {
            validateNotEmptyInput($(this))
        }
    );

    $("input[name='customer_phone']").blur(function() {
            validatePhoneInput($(this))
        }
    );

    $("input[name='customer_address']").blur(function() {
            validateNotEmptyInput($(this))
        }
    );

    $("form#user-info-form").submit(function (event) {

        let submittedForm = $(this);
        let formDataArray = submittedForm.serializeArray();

        let isValid = true;

        formDataArray.forEach(item => {

            switch (item.name) {
                case "customer_name":
                        isValid = validateNotEmptyInput($("input[name='customer_name']")) && isValid;
                    break;
                case "customer_phone":
                        isValid = validateNotEmptyInput($("input[name='customer_phone']")) && isValid;
                    break;
                case "customer_address":
                        isValid = validateNotEmptyInput($("input[name='customer_address']")) && isValid;
                    break;
            }
        });

        console.log(isValid);

        if (!isValid) {
            event.preventDefault();
        }
    });
});

const validateNotEmptyInput = (element) => {
    if (element.val() === "") {
        element.addClass("is-invalid");
        element.removeClass("is-valid");
        return false;
    } else {
        element.removeClass("is-invalid");
        element.addClass("is-valid");
        return true;
    }
};