function cityCountry() {
    var city = $('#city');
    var country = $('#country');
    var resp = {
        "city": city.val(),
        "country": country.val()
    };
    $.ajax({
        contentType: "application/json",
        type: "POST",
        url: "UserCreateServlet",
        data: JSON.stringify(resp),
        dataType: "json",
    });
}