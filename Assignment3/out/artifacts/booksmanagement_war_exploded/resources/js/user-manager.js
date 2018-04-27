$(document).ready(function () {

    $('input[type="checkbox"]').on('click', function(){

        var data = {};
        data.id = $(this).attr('id');
        data.value = $(this).is(':checked') ? 1 : 0;

        console.log(data);

        $.ajax({
            type: "POST",
            url: "/admin/enabled",
            data: data
        }).done(function(data) {
            console.log(data);
        });
    });

});

