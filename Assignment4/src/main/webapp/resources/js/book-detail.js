$(document).ready(function () {
    $("#modify-book").click(function () {
        getBookInformation();
    });

    $(".addbook-form").submit(function (event) {
        event.preventDefault();
        modifyBookInfo();
    });
});

function getBookInformation() {
    $.ajax({
        contentType: "application/json",
        url: "/api/get-book/" + $("#book-id").val(),
        dataType: 'json',
        timeout: 100000,
        success: function (res) {
            addCurrentBookInforToForm(res);
        }
    });
}

function addCurrentBookInforToForm(res) {
    $("#book-id").val(res.id);
    $("#title").val(res.title);
    $("#author").val(res.author);
    $("#description").val(res.description);
}

function modifyBookInfo() {
    var currentBook = {};
    currentBook["id"] = $("#book-id").val();
    currentBook["title"] = $("input[name=title]").val();
    currentBook["author"] = $("input[name=author]").val();
    currentBook["description"] = $("textarea[name=description]").val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/modify-book",
        data: JSON.stringify(currentBook),
        dataType: 'json',
        timeout: 100000,
        success: function (res) {
            $("#addBookModal").modal("hide");
            console.log("modify book success" + res);
        }
    });

}