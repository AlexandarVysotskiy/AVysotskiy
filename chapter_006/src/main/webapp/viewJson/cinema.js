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
    var resp = {"place": place.val()};
    if (place.val() === undefined) {
        alert("Select the place, please!");
    } else {
        $.ajax({
            type: "POST",
            url: "cinema",
            data: JSON.stringify(resp),
            dataType: "json",
            success: window.location.href = 'as?place=' + place.val()
        });
    }
}