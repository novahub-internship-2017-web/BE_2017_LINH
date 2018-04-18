var table = $("table");

$(document).ready(function () {

    $("#isMyList").val(false);
    getBookList($("#sortType").val(), 0);

    $("#max-books").change(function () {
        var sortType = $("#sortType").val();
        getBookList(sortType, 0);

    });

    $(".pagination").on("click", "a", function () {
        var page = $(this).text();
        var sortType = $("#sortType").val();

        getBookList(sortType, page - 1);

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
        getBookList($("#sortType").val(), 0);
    });

    $(".addbook-form").submit(function (event) {
        event.preventDefault();
        addNewBook();
        $(".text-input").val("");
    });

    //Sort book by click header of table
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
        $("#sortType").val(sortType);
        getBookList(sortType, 0);
    });

    $("#my-list").click(function () {
        $("#isMyList").val(true);
        getBookList($("#sortType").val(), 0);
    });



    $("body").on("click", "input.enabled-checkbox", function () {
        blockAndUnblockBook($(this));
        console.log($(this).attr("id") + " Checkbox clicked");
    });

});

    function getBookList(sortType, page) {
        table.addClass("hidden");
        var maxBooks = $("#max-books").val();
        var searchType = $("select[name=search-type]").val();
        var searchValue = $("input[name=search-value]").val();
        var uri = "/api/books/list/?type=" + sortType +
                  "&max-books=" + maxBooks +
                   "&page=" + page +
                    "&search-type=" + searchType +
                    "&search-value=" + searchValue +
                     "&my-list=" + $("#isMyList").val();
        $("tbody").remove();
        $.get(uri, function (res) {
            $("#total-books").html(res.amountOfBooks);
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
   //  function searchBook() {
   //      var search = {};
   //      search["searchType"] = $("select[name=search-type]").val();
   //      search["searchValue"] = $("input[name=search-value]").val();
   //      console.log(search["searchType"]);
   //      console.log(search["searchValue"]);
   //
   //      $.ajax({
   //          type: "POST",
   //          contentType: "application/json",
   //          url: "/api/books/search",
   //          data: JSON.stringify(search),
   //          dataType: 'json',
   //          timeout: 100000,
   //          success: function (res) {
   //              displayBooks(res.result, "No result can found!");
   //          }
   //      });
   //
   //  }

   //  // Sort book list
   // function sortBooksList(sortType, page) {
   //     $("tbody").remove();
   //     var url = "/api/books/sort?type=" +
   //         sortType +
   //         "&page=" + page +
   //         "&max-books=" + $("#max-books").val();
   //     $.get(url, function (res) {
   //         displayBooks(res.result, "There are no books in the list! Let's create one.")
   //     });
   // }

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

        tr.append(book.id);
        tr.append(title);
        tr.append(author);
        tr.append(createdBy);
        tr.append(detail);

        //Create td for enable or disable book
        if ($("#userId").val() == 1) {
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





