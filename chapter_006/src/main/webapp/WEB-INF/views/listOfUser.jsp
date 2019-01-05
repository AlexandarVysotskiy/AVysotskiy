<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    <title>List of users</title>
</head>
<body>
<div class="container">
    <h2>User table</h2>
    <p>Description of user:</p>
    <table class="table" id="table">
        <tr>
            <th>Login</th>
            <th>Role</th>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.login}</td>
            <c:if test="${role != 'guest'}">
                <c:if test="${role == 'admin' || user.login == login}">
                    <td>${user.role}</td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/controller" method="post">
                            <button class="btn btn-primary" name="id" type="hidden" value="${user.id}">Delete</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${role == 'admin' || user.login == login}">
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/UserUpdateServlet" method="get">
                            <button class="btn btn-primary" name="id" type="hidden" value="${user.id}">Update</button>
                        </form>
                    </td>
                </c:if>
            </c:if>
            </c:forEach>
        </tr>
    </table>
</div>
<div class="center">
    <c:if test="${users == null}">
        <p>User list is empty</p>
    </c:if>
    <c:if test="${role != 'guest'}">
        <form action="${pageContext.servletContext.contextPath}/UserCreateServlet">
            <button class="btn btn-primary" name="create">Create a new user</button>
        </form>
        <form action="${pageContext.servletContext.contextPath}/controller" method="post">
            <p>
                <button class="btn btn-primary" name="exist" type="hidden" value="${user.id}">Exist</button>
            </p>
        </form>
    </c:if>
    <c:if test="${role == 'guest'}">
        <form action="${pageContext.servletContext.contextPath}/UserCreateServlet">
            <button class="btn btn-primary" name="create">Create a new user</button>
        </form>
        <form action="${pageContext.servletContext.contextPath}/controller" method="post">
            <p>
                <button class="btn btn-primary" name="exist" type="hidden" value="${user.id}">Sign in</button>
            </p>
        </form>
    </c:if>
</div>
</body>
</html>
