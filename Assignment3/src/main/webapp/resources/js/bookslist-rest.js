var table = $("table");

$(document).ready(function () {
    $.get("/rest/books", function (res) {
        table.removeClass("hidden");
        displayBooksList(res.result);
        table.addClass("table table-striped");
    });
});

function displayBooksList(books) {
    for (var i = 1; i <= books.length; i++) {
        var book = books[i-1];

        //Create tbody element
        var tbody = $("<tbody>");
        var tr = $("<tr>");

        //Create td column
        var index = $("<td>").append(i);
        var title = $("<td>").append(book.title);
        var author = $("<td>").append(book.author);
        var createdBy = $("<td>").append(book.user.email);

        //Create td for button detail
        var aTag = $("<a/>", {
            href : "/book/detail?id=" + book.id
        }).append($("<i>").addClass("glyphicon glyphicon-folder-open"));
        var detail = $("<td>").append(aTag);

        tr.append(index);
        tr.append(title);
        tr.append(author);
        tr.append(createdBy);
        tr.append(detail);
        tbody.append(tr);
        table.append(tbody);
        console.log(i);
        console.log(book);
        console.log(book.title);
        console.log(book.author);
        console.log(book.user.email);
        console.log(new Date(book.createdAt));
        console.log(new Date(book.updatedAt));
    }
}



