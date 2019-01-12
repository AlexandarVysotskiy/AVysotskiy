function loadRowAndColumn() {
    $.ajax({
        type: "GET",
        url: "as",
        success: function (data) {
            $('#success').append('<h3>' + data + '</h3>');
        }
    });
}

function getAccount() {
    var fullName = $('#fullName');
    var phone = $('#phone');
    var resp = {
        "fullName": fullName.val(),
        "phone": phone.val(),
    };
    $.ajax({
        method: "POST",
        url: "as",
        data: JSON.stringify(resp),
        dataType: "json",
        success: function (data) {
            alert(data);
            window.location.href = 'index.html';
        }
    });
}