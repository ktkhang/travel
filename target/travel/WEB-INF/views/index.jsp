<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Test page</title>
</head>
<body>
<h1>Sample page</h1>
<h2>${message}</h2>

<form action="/logout" method="post">
  <input value="Logout" type="submit">
</form>
</body>
</html>
