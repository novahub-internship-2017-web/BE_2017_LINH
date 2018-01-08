window.onload = init;
var form = document.getElementById("form");
var lecturerDiv = document.getElementById("lecturer");
var officerDiv = document.getElementById("officer");
var submitBtn = document.getElementById("submit-btn");

function init() {
    form.removeChild(lecturerDiv);
    form.removeChild(officerDiv);
}

document.addEventListener('DOMContentLoaded',function() {
    document.querySelector('select[name="employee-type"]').onchange=changeEventHandler;
},false);


function changeEventHandler(event) {
    if (event.target.value === "lecturer") {
        if (form.contains(officerDiv)) {
            form.removeChild(officerDiv);
        }
        form.insertBefore(lecturerDiv, submitBtn);

    } else if (event.target.value === "officer") {
        if (form.contains(lecturerDiv)) {
            form.removeChild(lecturerDiv);
        }
        form.insertBefore(officerDiv, submitBtn);
    }
}




