$(document).ready(function () {
    $.get("/rest/books", function (res) {
        console.log(res);
    });
});

