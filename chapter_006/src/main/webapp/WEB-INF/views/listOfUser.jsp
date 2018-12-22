<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<p>
    <table>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>User login: ${user.login}
            <td>
                <form action="${pageContext.servletContext.contextPath}/UserServlet" method="post">
<p>
    <button name="login" type="hidden" value="${user.login}">Delete</button>
</p>
</form>
</td>
<td>
    <form action="${pageContext.servletContext.contextPath}/UserUpdateServlet" method="get">
        <p>
            <button name="login" type="hidden" value="${user.login}">Update</button>
        </p>
    </form>
</td>

</tr>
</c:forEach>
</table>
<c:if test="${users == null}">
    <p>User list is empty</p>
</c:if>
<a href="${pageContext.servletContext.contextPath}/UserCreateServlet">Create a new user</a>
</body>
</html>