function addNewBookHandler(res, title) {
    $(".error").remove();
    $(".notification").remove();
    var successNotification = $("<div>");
    var errorTitleNotification = $("<div>");
    var errorAuthorNotification = $("<div>");
    var errorExistedBook = $("<div>");

    if (res.existedBook === true) {
        errorExistedBook.html("This book already in your list. Please add another one");
        errorExistedBook.addClass("error");
        $("#description").after(errorExistedBook);
                
    } else if (res.validBook === true) {
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