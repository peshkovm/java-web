<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>Customer Registration Form</title>

    <style>
        .error {
            color: red
        }
    </style>
</head>

<body>

<i>Fill out the form. Asterisk (*) means required</i>

<br><br>

<form:form action="processForm" modelAttribute="customer">

    First name: <form:input path="firstName"></form:input>

    <br><br>

    Last name (*): <form:input path="lastName"></form:input>
    <form:errors path="lastName" cssClass="error"></form:errors>

    <br><br>

    Free passes: <form:input path="freePasses"></form:input>
    <form:errors path="freePasses" cssClass="error"></form:errors>

    <br><br>

    Postal Code: <form:input path="postalCode"></form:input>
    <form:errors path="postalCode" cssClass="error"></form:errors>

    <br><br>

    Course Code: <form:input path="courseCode"></form:input>
    <form:errors path="courseCode" cssClass="error"></form:errors>

    <br><br>

    <input type="submit" value="Submit"/>

</form:form>

</body>

</html>
