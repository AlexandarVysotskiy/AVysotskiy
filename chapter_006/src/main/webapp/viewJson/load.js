$(loadUsers());

function loadUsers() {
    $.ajax({
        type: "GET",
        url: "json",
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

function createUser() {
    var name = $('#name');
    var surname = $('#surname');
    var desc = $('#description');
    var radio = $('input[name=sex]:checked');
    var arr = [name, surname, description];
    var result = validate(arr, radio);
    if (result) {
        var resp = {
            "name": name.val(), "surname": surname.val(),
            "sex": radio.val(), "description": desc.val()
        };
        $.ajax({
            type: "POST",
            url: "json",
            data: JSON.stringify(resp),
            dataType: "json",
            success: function (data) {
                alert("User has been successfully added");
                var akk = "";
                $.each(data, function (key, value) {
                    akk += '<td>' + value + '</td>';
                });
                var tableBody = $('#table tbody');
                if (tableBody.html() === "") {
                    tableBody.append('<tr>' + akk + '</tr>');
                } else {
                    $('#table tbody tr:last').after('<tr>' + akk + '</tr>');
                }
            }
        });
        $('#name, #surname, #description').val("");
        $('input[name=sex]').prop('checked', false);
    }
    return result;
}

function validate() {
    var result = false;

    function check(value) {
        if (value.val() === "") {
            alert(value.attr('placeholder'));
            return false;
        } else {
            return true;
        }
    }

    if (check($('#name')) && check($('#surname'))
        && check($('#description'))) {
        result = true;
    }
    return result;
}