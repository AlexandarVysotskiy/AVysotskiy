<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update of user</title>
</head>
<body>
<table>
    <tr>
        <td>Parameter's user: <c:out value="${loginUpdate}" />
            <form action="${pageContext.servletContext.contextPath}/UserUpdateServlet" method="post">
                Name : <input type="text" value=" ${nameUpdate} " name="name"/>
                Login : <input type="text" value=" ${loginUpdate}" name="login"/>
                Email : <input type="text" value=" ${emailUpdate}" name="email"/>
                <input type="hidden" value="${id}" name="id"/>
                <input type="submit">
            </form>
        </td>
    </tr>
    <a href="${pageContext.servletContext.contextPath}>/">Return to list of user</a>
</body>
</html>