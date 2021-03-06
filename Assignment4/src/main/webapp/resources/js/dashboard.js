var table = $("table");
var page = 1;
var bookId;
var isAsc = true; //this variable for sorting order


$(document).ready(function () {

    $("#isMyList").val(false);
    $("#list-name").text("List of books");

    //Get book list after page loaded
    getBookList($("#sortType").val(), 0, "There are no books in the list. Please create one.");

    $("#max-books").change(function () {
        var sortType = $("#sortType").val();
        getBookList(sortType, 0, "There are no books in the list. Please create one.");

    });

    //Event when click page button
    $(".pagination").on("click", "a", function () {
        var maxPage = ($(".page-number").last().find("a").text());
        var minPage = 1;
        if ($(this).parent().hasClass("page-number")) {
            page = parseInt($(this).text());
        } else if ($(this).parent().hasClass("next")) {
            if (page < maxPage) {
                page = page + 1;
            }
        } else if ($(this).parent().hasClass("previous")) {
            if (page > minPage) {
                page = page - 1;
            }
        } else if ($(this).parent().hasClass("last")) {
            page = parseInt($(".page-number").last().find("a").text());
        } else {
            page = parseInt($(".page-number").first().find("a").text());
        }

        var sortType = $("#sortType").val();

        changeColorPageNumber(page);
        getBookList(sortType, page - 1, "The are no book in this page");

    });

    //Event submit searchform
    $(".search-form").submit(function (event) {
        event.preventDefault();
        table.addClass("hidden");
        $("tbody").remove();
        getBookList($("#sortType").val(), 0, "There are no books in the result");
    });

    //Even add book form
    $(".addbook-form").submit(function (event) {
        event.preventDefault();
        addNewBook();
        $(".text-input").val("");
    });

    //Sort book by click header of table
    $(".clickable").click(function () {
        var sortType;
        var icon = $(this).find("i");
        console.log("is asc is" + isAsc);
        $("i").removeClass("glyphicon-sort-by-alphabet-alt");
        $("i").removeClass("glyphicon-sort-by-alphabet");

        if ($(this).is("#th-title")) {
            sortType = "title";
            isAsc = !isAsc;
        } else if ($(this).is("#th-author")) {
            sortType = "author";
            isAsc = !isAsc;
        } else {
            sortType = "owner";
            isAsc = !isAsc;
        }

        if (isAsc) {
           icon.removeClass("glyphicon-sort-by-alphabet-alt");
           icon.addClass("glyphicon-sort-by-alphabet");
        } else {
            icon.removeClass("glyphicon-sort-by-alphabet");
            icon.addClass("glyphicon-sort-by-alphabet-alt");
        }

        $("#sortType").val(sortType);
        getBookList(sortType, 0, "There are no book in the list");
    });

    //Move to my list of books
    $("#my-list").click(function () {
        $("#isMyList").val(true);
        $("#list-name").text("My list of book");
        getBookList($("#sortType").val(), 0, "There are no book this list. Please create one");
    });

    //Enabled and disable books
    $("body").on("click", "input.enabled-checkbox", function () {
        blockAndUnblockBook($(this));
        console.log($(this).attr("id") + " Checkbox clicked");
    });

    $("body").on("click", ".delete-btn", function () {
        bookId =  $(this).closest("tr").find("td").first().text();
        $(".confirm-delete").modal("show");
    });

    $(".btn-block").click(function () {
        $(".confirm-delete").modal("hide");
        deleteBook(bookId);
    });

});

    function getBookList(sortType, page, message) {
        table.addClass("hidden");
        var maxBooks = $("#max-books").val();
        var searchValue = $("input[name=search-value]").val();
        var uri = "/api/books?type=" + sortType +
                  "&max-books=" + maxBooks +
                   "&page=" + page +
                    "&search-value=" + searchValue +
                     "&my-list=" + $("#isMyList").val() +
                     "&is-asc=" + isAsc;
        $("tbody").remove();
        $.get(uri, function (res) {

            $("#total-books").html(res.amountOfBooks);
            var numberOfPages = Math.floor(parseInt(res.amountOfBooks)/(parseInt(maxBooks) + 1)) + 1;
            console.log(res.amountOfBooks);
            console.log(maxBooks + 1);
            console.log(numberOfPages);
            var maxCurrentPages = parseInt($(".pagination .page-number").last().find("a").text());
            var i;
            if (maxCurrentPages < numberOfPages) {
                for (i = maxCurrentPages + 1; i <= numberOfPages; i++) {
                    var a = $("<a>").prop("href", "#");
                    a.html(i);
                    var li = $("<li>").append(a);
                    li.addClass("page-number");
                    $(".page-number").last().after(li);
                }

             } else if (maxCurrentPages > numberOfPages) {
                for (i = maxCurrentPages; i > numberOfPages; i--) {
                    $(".pagination .page-number").last().remove();
                }
            }

            displayBooks(res.result, message)
        });
    }


    function addNewBook() {
        var newBook = {};
        newBook["title"] = $("input[name=title]").val();
        newBook["author"] = $("input[name=author]").val();
        newBook["description"] = $("textarea[name=description]").val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/books",
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


   // Block and unblock book
    function blockAndUnblockBook(checkbox) {
        var data = {};
        data.id = checkbox.attr("id");
        data.enabled = checkbox.is(":checked");
        $.ajax({
            type: "PUT",
            url: "/api/book/block",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: 'json',
            timeout: 100000,
            success: function (res) {
                console.log("Book is enabled/disabled");
            }

     });
    }

    // Delete book
    function deleteBook(bookId) {
        var data = {};
        data.id = parseInt(bookId);
        $.ajax({
            type: "DELETE",
            url: "/api/books",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: 'json',
            timeout: 100000,
            success: function (res) {
                console.log("Book is deleted");
                $(".pagination").find('a:contains("' + page + '")').click();
            }

        });
    }


    function displayBooks(books, notification) {
        $(".notification").remove();
        if (books.length > 0) {
            displayBooksList(books);
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
            tbody.append(createOneRowBook(book));
            //Show table and add tbody
            table.removeClass("hidden");
            table.addClass("table table-striped");
            table.append(tbody);
        }
    }

    function createOneRowBook(book) {
        var tr = $("<tr>");

        //Create td column
        var id = $("<td>").append(book.id);
        var title = $("<td>").append(book.title);
        var author = $("<td>").append(book.author);
        var createdBy = $("<td>").append(book.user.email);



         //Create td for detail button
        var detailAtag = $("<a/>", {
            href : "/book/detail/" + book.id
        });
        detailAtag.addClass("btn btn-primary detail-btn");
        detailAtag.text("Detail");

        //Create td for delete button
        var deleteAtag = $("<a>");
        deleteAtag.addClass("btn btn-danger delete-btn");
        deleteAtag.text("Delete");

        var action = $("<td>").append(detailAtag);
        if ($("#roleId").val() == 1 || $("#userId").val() == book.user.id) {
            action.append(deleteAtag);
        }

        tr.append(id);
        tr.append(title);
        tr.append(author);
        tr.append(createdBy);
        tr.append(action);

        //Create td for enable or disable book
        if ($("#roleId").val() == 1) {
            var enabled = $("<td>");
            var checkBox = $("<input>").addClass("enabled-checkbox");
            checkBox.prop("type", "checkbox");
            checkBox.prop("id", book.id);
            enabled.append(checkBox);
            if (book.enabled) {
                checkBox.prop("checked", true);
            }
            tr.append(enabled);
        }

        return tr;
    }

    function changeColorPageNumber(page) {
        $(".page-number").find("a").css("background", "white");
        $(".pagination").find('a:contains("' + page + '")').css("background", "#ededee");
    }





