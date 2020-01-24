var redrawLinkValue;
var elementWithDataSelectorValue;
var redrawFunctionValue;

const redrawPagination = (result) => {

    let doc = new DOMParser().parseFromString(result, "text/html");
    destroyPagination();
    let pageCount = doc.getElementById('pageTotalCount').innerText;
    let pageNumber = doc.getElementById('pageNumberValue').innerText;
    document.getElementById("totalPages").innerText = pageCount;
    document.getElementById("totalElements").innerText = doc.getElementById('totalElementsCount').innerText;

    if (Number(pageCount) === 0) +pageCount++;
    if (Number(pageNumber) === 0) +pageNumber++;

    drawPagination(pageCount, pageNumber, redrawLinkValue, elementWithDataSelectorValue, redrawFunctionValue);
};

const sendRedrawRequest = (url, paginationReset, redrawFunction) => {
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            redrawFunction(result);

            if (paginationReset) {
                redrawPagination(result);
            }
        },
        error: function (response) {
            console.log(response);
        }
    });
};

const drawPagination = (totalPageCount, pageNumber, redrawLink, elementWithDataSelector, redrawFunction) => {

    if (!redrawLinkValue) redrawLinkValue = redrawLink;
    if (!elementWithDataSelectorValue) elementWithDataSelectorValue = elementWithDataSelector;
    if (!redrawFunctionValue) redrawFunctionValue = redrawFunction;

    if($(elementWithDataSelector).text().trim() === "") {
        document.getElementById("pagination-div").style.opacity = "0";
    }
    else {
        document.getElementById("pagination-div").style.opacity = "1";
    }

    if (Number(totalPageCount) === 0) +totalPageCount++;
    if (Number(pageNumber) === 0) +pageNumber ++;

    $(function() {
        $('#pagination').twbsPagination({
            totalPages: Number(totalPageCount),
            visiblePages: 5,
            startPage: Number(pageNumber),
            last: '>>',
            first: '<<',
            next: ">",
            prev: "<",
            paginationClass: "light-theme",
            pageClass: "light-theme",
            // nextClass: "d-none",
            // prevClass: "d-none",
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                sendRedrawRequest(redrawLink + page, false, redrawFunction);
            }
        });
    })
};

const destroyPagination = () => {
    $('#pagination').twbsPagination('destroy');
};