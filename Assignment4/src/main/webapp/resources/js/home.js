$(document).ready(function () {
    $(".register").submit(function (event) {
        event.preventDefault();
        registerNewUser();
    });
});

function registerNewUser() {
    var newUser = {};
    newUser["firstName"] = $("input[name=firstName]").val();
    newUser["lastName"] = $("input[name=lastName]").val();
    newUser["email"] = $("input[name=email]").val();
    newUser["password"] = $("input[name=password]").val();
    newUser["confirmPassword"] = $("input[name=confirmPassword]").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/users/register",
        data: JSON.stringify(newUser),
        dataType: 'json',
        timeout: 10000,
        success: function (res) {
            // registerHandler(res);
            console.log(res);
        }
    });
}

function registerHandler(res) {
    $(".error").remove();
    $(".notification").remove();
    var successNotification = $("<div>");
    var invalidEmailMessage = $("<div>");
    var confirmPasswordNotMatchMessage = $("<div>");
    var takenEmailMessage = $("<div>");

    if (res.validUser) {
        successNotification.html("Register new user success");
        successNotification.addClass("notification");
        $(".register").after(successNotification);
    } else {
        if (res.validEmail === false) {
            invalidEmailMessage.html("Invalid email.");
            invalidEmailMessage.addClass("error");
            $("input[name=email]").after(invalidEmailMessage);
        }

        if (res.confirmPasswordMatchPassword === false) {
            confirmPasswordNotMatchMessage.html("Confirm password must match password");
            confirmPasswordNotMatchMessage.addClass("error");
            $("input[name=confirmPassword]").after(confirmPasswordNotMatchMessage);
        }

        if (res.availableEmail === false) {
            takenEmailMessage.html("This email is not available. Try another one.");
            takenEmailMessage.addClass("error");
            $("input[name=email]").after(takenEmailMessage);
        }
    }
}