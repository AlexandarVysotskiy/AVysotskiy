<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="viewJson/cityCountry.js"></script>
    <title>Create a new user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/UserCreateServlet" method="post">
<form>
    Name : <input type='text' name='name'/>
    Login : <input type='text' name='login'/>
    Email : <input type='text' name='email'/>
    City : <input type='text' name='city' id="city"/>
    Country : <input type='text' name='country' id="country"/>
    password : <input type='password' name='password'/>
    <select name="role">
        <option value="user">User</option>
        <option value="admin">Admin</option>
    </select>
    <input type='submit'>
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