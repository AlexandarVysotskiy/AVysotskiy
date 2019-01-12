$(loadPlace());

function loadPlace() {
    $.ajax({
        type: "GET",
        url: "cinema",
        success: function (data) {
            $.each(data, function (key, value) {
                var akk = "";
                $.each(value, function (key, value) {
                    akk += '<td>' + value + '</td>';
                });
                $('#table tbody').append('<tr>' + akk + '</tr>');
            });
        }
    });
}

function getPlace() {
    var place = $('input[name=place]:checked');
    if (place.val() === undefined) {
        alert("Select the place, please!");
    } else {
        window.location.href = 'as?place=' + place.val();
    }
}