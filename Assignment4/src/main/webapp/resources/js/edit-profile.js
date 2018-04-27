$(document).ready(function () {
    $("#change-profile").click(function () {
        getUserInformation();
    });

    $(".user-modify").submit(function (e) {
        e.preventDefault();
        updateProfile();
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
            console.log("modify book success" + res);
        }
    });

}