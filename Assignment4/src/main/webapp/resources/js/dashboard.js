var table = $("table");

$(document).ready(function () {

    getBookList(0);

    $("#max-books").change(function () {
        getBookList(0);
    });

    $(".pagination").on("click", "a", function () {
        var page = $(this).text();
        console.log(page);
        getBookList(page - 1);
    });

    $(".dropdown-toggle").click(function (e) {
        e.preventDefault();
        $(".dropdown-menu").toggle();
        $(".dropdown-menu").mouseleave(function () {
            $(this).hide();
        });
    });

    $(".search-form").submit(function (event) {
        event.preventDefault();
        table.addClass("hidden");
        $("tbody").remove();
        searchBook();
    });

    $(".addbook-form").submit(function (event) {
        event.preventDefault();
        addNewBook();
        $(".text-input").val("");
    });

    $(".clickable").click(function () {
        var sortType;
        if ($(this).is("#th-title")) {
            sortType = "title";
            console.log("Th title clicked")
        } else if ($(this).is("#th-author")) {
            sortType = "author";
            console.log("Th author clicked")
        } else {
            sortType = "owner";
            console.log("Th created clicked")
        }
        sortBooksList(sortType);
    });

    $("body").on("click", "input.enabled-checkbox", function () {
        blockAndUnblockBook($(this));
        console.log($(this).attr("id") + " Checkbox clicked");
    });

});

    function getBookList(page) {
        table.addClass("hidden");
        var maxBooks = $("#max-books").val();
        var uri = "/api/books/list/" + maxBooks + "/" + page;
        console.log(maxBooks);
        console.log(page);
        $("tbody").remove();
        $.get(uri, function (res) {
            displayBooks(res.result, "There are no books in the list! Let's create one.")
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
            url: "/api/books/search",
            data: JSON.stringify(search),
            dataType: 'json',
            timeout: 100000,
            success: function (res) {
                displayBooks(res.result, "No result can found!");
            }
        });

    }

    // Sort book list
   function sortBooksList(sortType) {
       $("tbody").remove();
       $.get("/api/books/" + sortType, function (res) {
           displayBooks(res.result, "There are no books in the list! Let's create one.")
       });
   }

   // Block and unblock book
    function blockAndUnblockBook(checkbox) {
        var data = {};
        data.id = checkbox.attr("id");
        data.enabled = checkbox.is(":checked");
        $.ajax({
            type: "POST",
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
            href : "/book/detail/" + book.id
        }).append($("<i>").addClass("glyphicon glyphicon-folder-open"));
        var detail = $("<td>").append(aTag);

        //Create td for enable or disable book
        var enabled = $("<td>");
        var checkBox = $("<input>").addClass("enabled-checkbox");
        checkBox.prop("type", "checkbox");
        checkBox.prop("id", book.id);

        if (book.enabled) {
            checkBox.prop("checked", true);
        }


        if (parseInt(book.user.id) !== parseInt($("#userId").val())) { //If logged user is not book's owner
            if (parseInt($("#roleId").val()) === 1) { //Check if user is admin
                checkBox.prop("disabled", false);
            } else {
                checkBox.prop("disabled", true);
            }
        } else {
            checkBox.prop("disabled", false);
        }
        enabled.append(checkBox);


        tr.append(index);
        tr.append(title);
        tr.append(author);
        tr.append(createdBy);
        tr.append(detail);
        tr.append(enabled);

        return tr;
    }





