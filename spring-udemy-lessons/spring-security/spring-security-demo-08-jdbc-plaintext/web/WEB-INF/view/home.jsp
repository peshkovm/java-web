<%--
  Created by IntelliJ IDEA.
  User: Денис
  Date: 03.08.2019
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>luv2code Company Home Page</title>
</head>
<body>
<h2>luv2code Company Home Page - Yoohoo!</h2>
<hr>

<p>
    Welcome to the luv2code company home page!
</p>

<hr>

<p>
    User: <security:authentication property="principal.username"></security:authentication>
    <br><br>
    Role(s): <security:authentication property="principal.authorities"></security:authentication>
</p>


<security:authorize access="hasRole('MANAGER')">
    <p>
        <a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a>
        (Only for Manager peeps)
    </p>
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
    <p>
        <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
        (Only for Admin peeps)
    </p>
</security:authorize>

<hr>

<form:form action="${pageContext.request.contextPath}/logout"
           method="post">

    <input type="submit" value="Logout">

</form:form>

</body>
</html>
