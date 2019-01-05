<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = false;
            function check(value) {
                if (value.val() === "") {
                    alert(value.attr("placeholder"));
                    return false;
                } else {
                    return true;
                }
            }
            if (check($("#login")) && check($("#password"))) {
                result = true;
            }
            return result;
        }
    </script>
    <style>
        body {
            background: #333; /* Цвет фона */
            color: #ef8f1a; /* Цвет текста */
        }

        .center {
            width: 400px; /* Ширина элемента в пикселах */
            padding: 10px; /* Поля вокруг текста */
            margin: auto; /* Выравниваем по центру */
        }
    </style>
    <title>Auth filter</title>
</head>
<body>
<div class="center">
    <c:if test="${error != ''}">
        <div style="background-color: red">
            <c:out value="${error}"/>
        </div>
    </c:if>
    <form onsubmit="return validate()" action="${pageContext.servletContext.contextPath}/authFilter" method="post">

        </br>Login: <textarea id="login" class="form-control" rows="1" name="login"
                              placeholder="Form login is empty"></textarea>
        </br>Password: <textarea id="password" class="form-control" rows="1" name="password"
                                 placeholder="Form password is empty"></textarea>
        </br>
        <button type="submit" class="btn btn-primary">Submit</button>

    </form>

    <form action="${pageContext.servletContext.contextPath}/authFilter" method="post">
        </br>
        <button name="enterHowGuest" class="btn btn-primary" value="${user.id}" onsubmit="return validate()">Enter how
            guest
        </button>
    </form>
</div>
</body>
</html>
