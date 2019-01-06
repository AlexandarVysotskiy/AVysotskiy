<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function cityCountry() {
            var city = $('#city');
            var country = $('#country');
            var name = $('#name');
            var login = $('#login');
            var email = $('#email');
            var password = $('#password');
            var role = $('#role');
            var resp = {
                "city": city.val(),
                "country": country.val(),
                "name": name.val(),
                "login": login.val(),
                "email": email.val(),
                "password": password.val(),
                "role": role.val(),
            };
            $.ajax({
                contentType: "application/json",
                type: "POST",
                url: "UserCreateServlet",
                data: JSON.stringify(resp),
                dataType: "json",
            });
        }
    </script>
    <title>Create a new user</title>
</head>
<body>
<%--<form action="${pageContext.servletContext.contextPath}/UserCreateServlet" method="post">--%>
<form>
    Name : <input type='text' name='name' id="name"/>
    Login : <input type='text' name='login' id="login"/>
    Email : <input type='text' name='email' id="email"/>
    password : <input type='password' name='password' id="password"/>
    City : <input type='text' name='city' id="city"/>
    Country : <input type='text' name='country' id="country"/>
    <select name="role" id="role">
        <option value="user">User</option>
        <option value="admin">Admin</option>
    </select>
    <input type='submit' onclick="cityCountry()">
</form>
<c:forEach items="${users}" var="user">
    <tr>
        <td><p>User: ${user.login} has add</p></td>
    </tr>
</c:forEach>
<c:if test="${users == null}">
    <p>User list is empty</p>
</c:if>
<a href="${pageContext.servletContext.contextPath}/">Show list of user</a>
</body>
</html>