var table = $("table");

$(document).ready(function () {

    getBookList();

    $(".search-form").submit(function (event) {
        event.preventDefault();
        table.addClass("hidden");
        $("tbody").remove();
        searchBook();
    });

    $(".addbook-form").submit(function (event) {
        event.preventDefault();
        addNewBook();
    });
});

    function getBookList() {
        table.addClass("hidden");
        $("tbody").remove();
        $.get("/api/books", function (res) {
            displayBooks(res.result, "There are no books in the list! Let's create one.")
        });
    }


    function addNewBook() {
        var newBook = {};
        newBook["title"] = $("input[name=title]").val();
        newBook["author"] = $("input[name=author]").val();
        newBook["description"] = $("texarea[name=description]").val();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/add-book",
            data: JSON.stringify(newBook),
            dataType: 'json',
            timeout: 100000,
            success: function (res) {
                addNewBookHandler(res, newBook["title"]);
                var lastTr = $("tr").last();
                var index = lastTr.find("td:first-child").html();
                lastTr.after(createOneRowBook(res.book, Number(index) + 1));
                console.log(res);
            }
        });

    }

    function addNewBookHandler(res, title) {
        $(".error").remove();
        $(".notification").remove();
        var successNotification = $("<div>");
        var errorTitleNotification = $("<div>");
        var errorAuthorNotification = $("<div>");
        if (res.validBook === true) {
            successNotification.html("Adding book with title \"" + title + "\" success");
            successNotification.addClass("notification");
            table.after(successNotification);
            $("#addBookModal").modal("hide");
        } else {
            if (res.validTitle === false) {
                errorTitleNotification.html("Title must not be empty!");
                errorTitleNotification.addClass("error");
                $("#title").after(errorTitleNotification);
            }

            if(res.validAuthor === false) {
                errorAuthorNotification.html("Author must not be empty!");
                errorAuthorNotification.addClass("error");
                $("#author").after(errorAuthorNotification);
            }
        }
    }
   //Search book ajax request
    function searchBook() {
        var search = {};
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
            tbody.append(createOneRowBook(book, i));
            //Show table and add tbody
            table.removeClass("hidden");
            table.addClass("table table-striped");
            table.append(tbody);
        }
    }

    function createOneRowBook(book, index) {
        var tr = $("<tr>");

        //Create td column
        var index = $("<td>").append(index);
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

        return tr;
    }





