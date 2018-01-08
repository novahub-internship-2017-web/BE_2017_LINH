$(document).ready(function () {

    $(".modify-btn").on("click", function () {
        $("form").css("display", "block");
        var type = $(this).closest("tr").find(".type").text();
        var parentNode = $(this).closest("tr");
        $("#username").attr("value", parentNode.find(".username").text());
        $("#type").attr("value", parentNode.find(".type").text());
        $("#full-name").attr("value", parentNode.find(".full-name").text());
        $("#birth-year").attr("value", parentNode.find(".birth-year").text());
        $("#home-town").attr("value", parentNode.find(".home-town").text());
        $("#salary-factor").attr("value", parentNode.find(".salary-factor").text());

        if (type === "Lecturer") {
            $("#lecturer").css("display", "block");
            $("#officer").css("display", "none");
            $("#faculty").val(parentNode.find(".faculty").text());
            $("#degree").val(parentNode.find(".degree").text());
            $("#quantity").attr("value", parentNode.find(".quantity").text());

        } else if (type === "Officer") {
            $("#lecturer").css("display", "none");
            $("#officer").css("display", "block");
            $("#department").val(parentNode.find(".department").text());
            $("#position").val(parentNode.find(".position").text());
            $("#working-days").attr("value", parentNode.find(".working-days").text());
        }
    });

    $(".delete-btn").on("click", function (e) {
        if (!confirm("Are you sure? This will delete this employee FOREVER")) {
            e.preventDefault();
        }
    });


});

$("#search-box").keypress(function () {
    var searchType = $("#search-type").val();
    var searchValue = $("#search-box").val();

    switch (searchType) {
        case "Name":
            searchByName(searchValue);
            break;
        case "Type":
            searchByType(searchValue);
            break;
        case "Birth year":
            searchByBirthYear(searchValue);
            break;
    }
});
function searchByName(searchValue) {
    var nameList = $(".full-name");
    var name1 = nameList[0].html();
    console.log(name1);
}

function searchByType() {
    alert("Search by type");
}

function searchByBirthYear() {
    alert("Search by birth year") ;
}

