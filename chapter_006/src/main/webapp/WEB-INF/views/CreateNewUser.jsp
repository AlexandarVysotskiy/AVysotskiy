<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a new user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/UserCreateServlet" method="post">
    Name : <input type='text' name='name'/>
    Login : <input type='text' name='login'/>
    Email : <input type='text' name='email'/>
    password : <input type='text' name='password'/>
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