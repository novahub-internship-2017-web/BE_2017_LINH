var successMessage = $("<div>");
successMessage.addClass("notification");

$(document).ready(function () {
    $("#change-profile").click(function () {
        getUserInformation();
    });

    $("#change-pass").click(function () {
        $(".error").remove();
        $("form").trigger("reset");
    });

    $(".user-modify").submit(function (e) {
        e.preventDefault();
        updateProfile();
    });

    $("#password-modify").submit(function (e) {
        e.preventDefault();
        $(".error").remove();
        updatePassword();
    });



});

function getUserInformation() {
    $("#user-email").html($("#email").val());
    $("#first-name").val($("#firstName").val());
    $("#last-name").val($("#lastName").val());
}

function updateProfile() {
    var currentUser = {};

    currentUser["id"] = $("#userId").val();
    currentUser["firstName"] = $("#first-name").val();
    currentUser["lastName"] = $("#last-name").val();

    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: "/api/users/edit-profile",
        data: JSON.stringify(currentUser),
        dataType: 'json',
        timeout: 100000,
        success: function (res) {
            $("#profile-modify").modal("hide");
            console.log("Linh tran kljdfaldjal");
            successMessage.html("Edit profile successfully");
            successMessage.attr("id", "success-noti")
            $("#list-name").before(successMessage);
            setTimeout(function () {
                $("#success-noti").remove();
            }, 5000);
        }
    });
}

function updatePassword() {
    var currentUser = {};
    var oldPass = $("#current-password").val();
    var newPass = $("#new-password").val();
    var confirmPass = $("#confirm-password").val();
    var errorEmptyNewPassword = $("<div>").addClass("error");
    var errorEmptyConfirmPassword = $("<div>").addClass("error");
    var errorConfirmPassword = $("<div>").addClass("error");
    var errorWrongPassword = $("<div>").addClass("error").html("Wrong password");

    //Add user information in to json
    currentUser.id = $("#userId").val();
    currentUser.oldPassword = oldPass;
    currentUser.newPassword = newPass;


    if (newPass === "" && confirmPass === "") {
        errorEmptyNewPassword.html("New password must not be empty.");
        $("#new-password").after(errorEmptyNewPassword)
        errorEmptyConfirmPassword.html("Confirm password must not be empty");
        $("#confirm-password").after(errorEmptyConfirmPassword);
    } else if (newPass !== confirmPass) {
        errorConfirmPassword.html("New password and confirm password not match");
        $("#confirm-password").after(errorConfirmPassword);
    } else {
        $.ajax({
            type: "PUT",
            contentType: "application/json",
            url: "/api/users/change-password",
            data: JSON.stringify(currentUser),
            dataType: 'json',
            timeout: 100000,
            success: function (isSuccess) {
                if (!isSuccess) {
                    console.log("Wrong pass");
                    $("#current-password").after(errorWrongPassword);
                    $("form").trigger("reset");
                } else {
                    $("#password-modify").modal("hide");
                    successMessage.html("Edit password successfully");
                    successMessage.attr("id", "success-noti")
                    $("#list-name").before(successMessage);
                    setTimeout(function () {
                        $("#success-noti").remove();
                    }, 5000);
                }

            }
        });

    }

}