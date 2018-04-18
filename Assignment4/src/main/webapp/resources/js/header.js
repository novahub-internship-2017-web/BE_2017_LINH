$(document).ready(function () {
    $(".dropdown-toggle").click(function (e) {
        e.preventDefault();
        $(".dropdown-menu").toggle();
        $(".dropdown-menu").mouseleave(function () {
            $(this).hide();
        });
    });
});