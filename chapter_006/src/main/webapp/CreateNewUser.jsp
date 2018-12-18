<%@ page import="crud.User" %>
<%@ page import="crud.UserError" %>
<%@ page import="crud.Validate" %>
<%@ page import="crud.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: Sanek
  Date: 17.12.2018
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a new user</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/UserCreateServlet" method="post">
    Name : <input type='text' name='name'/>
    Login : <input type='text' name='login'/>
    Email : <input type='text' name='email'/>
    <input type='submit'>
</form>
<%try {%>
<%for (User login : ValidateService.getInstance().findAll()) {%>
<tr>
    <td>User: <%login.getLogin();%>has add</td>
</tr>
<% }%>
<%} catch (UserError u) {%>
User list is empty
<% u.printStackTrace();%>
<%}%>
<a href="<%=request.getContextPath()%>/index.jsp">Show list of user</a>
</body>
</html>
