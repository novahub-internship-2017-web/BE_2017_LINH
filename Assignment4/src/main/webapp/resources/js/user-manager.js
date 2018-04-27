$(document).ready(function () {
    $('input[type="checkbox"]').on('click', function(){

        var data = {};
        data.id = $(this).attr('id');
        data.enabled = $(this).is(':checked');

        console.log(data);

        $.ajax({
            type: "PUT",
            url: "/admin/enabled",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: 'json',
            timeout: 100000,
            sucess: function (res) {
                console.log(res);
            }
        });
    });


});
