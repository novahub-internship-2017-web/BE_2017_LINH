var table = $("table");

$(document).ready(function () {
    $.get("/rest/books", function (res) {
        displayBooks(res.result, "There are no books in the list! Let's create one.")
    });

    $(".search-form").submit(function (event) {
        event.preventDefault();
        table.addClass("hidden");
        $("tbody").remove();
        searchBook();
    });
});



   //Search book ajax request
    function searchBook() {
        var search = {};
        var searchResult;
        search["searchType"] = $("select[name=search-type]").val();
        search["searchValue"] = $("input[name=search-value]").val();
        console.log(search["searchType"]);
        console.log(search["searchValue"]);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/rest/search",
            data: JSON.stringify(search),
            dataType: 'json',
            timeout: 100000,
            success: function (res) {
                displayBooks(res.result, "No result can found!");
            }
        });

    }



    function displayBooks(books, notification) {
        if (books.length > 0) {
            displayBooksList(books);
            $(".notification").remove();
        } else {
            table.addClass("hidden");
            var errorNotification = $("<div>");
            errorNotification.addClass("notification");
            errorNotification.html(notification);
            $(".search-form").after(errorNotification);
        }
    }

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

            //Show table and add tbody
            table.removeClass("hidden");
            table.addClass("table table-striped");
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




