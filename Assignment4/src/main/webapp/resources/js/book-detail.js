$(document).ready(function () {
    $("#modify-book").click(function () {
        $("#modalLabel").html("MODIFY BOOK");
        getBookInformation();
    });

    $(".addbook-form").submit(function (event) {
        event.preventDefault();
        modifyBookInfo();
    });

    $("#comment-box").keypress(function (e) {
        if(e.which == 13 && !e.altKey) {
            postNewComment();
            e.preventDefault();
        }
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

function postNewComment() {
    var newComment = {};
    newComment["content"] = $("#comment-box").val();
    newComment["bookId"] = $("#book-id").val();
  $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/post-comment",
        data: JSON.stringify(newComment),
        dataType: 'json',
        timeout: 100000,
        success: function (res) {
            addCommentLine(res);
        }
    });
}

function addCommentLine(res) {
    var comment = $("<div>");
    var commentOwner = $("<div>");
    var commentContent = $("<div>");

    commentOwner.html("Linh tran");
    commentContent.html(res.content);

    comment.append(commentOwner);
    comment.append(commentContent);
    comment.addClass("comment");

    $(".comment").last().after(comment);
}