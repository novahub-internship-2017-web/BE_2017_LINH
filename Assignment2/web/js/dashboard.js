$(document).ready(function () {


    //Create a dynamic form
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("class", "col col-sm-6");
    form.setAttribute("action", "ModifyInformation");

    //Create input elements
    var input = document.createElement("input");
    input.setAttribute("type", "text");


    var submit = document.createElement("input");
    submit.setAttribute("type", "submit");
    submit.setAttribute("value", "Confirm");

    var cancel = document.createElement("input");
    cancel.setAttribute("type", "button");
    cancel.setAttribute("value", "Cancel");
    cancel.setAttribute("class", "cancel-btn");

    //Append form
    form.append(input);
    form.append(submit);
    form.append(cancel);


    $(".modify-btn").click(function () {
        var parentNode = $(this).closest(".row");
        parentNode.find(".value").hide();
        var name = $(this).attr("name");

        var value = parentNode.find(".value").text();
        var fieldName = parentNode.find(".field-name").text();
        if (fieldName === "Birth year") {
            input.setAttribute("type", "number");
            input.setAttribute("min", "1960");
            input.setAttribute("max", "1999");
        } else {
            input.setAttribute("maxlength", "20");
        }


        input.setAttribute("value", value);
        input.setAttribute("name", name);
        parentNode.find("div").first().after(form);

        $(".modify-btn").attr("disabled", true);


        parentNode.find(".cancel-btn").on("click", function () {
            parentNode.find(".value").show();
            $(this).closest("form").remove();
            $(".modify-btn").attr("disabled", false);
        });
    });
});