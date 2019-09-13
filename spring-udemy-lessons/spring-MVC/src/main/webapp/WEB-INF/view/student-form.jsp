<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="for" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
    <title>Student Registration Form</title>
</head>

<body>
<form:form action="processForm" modelAttribute="student">

    First name: <form:input path="firstName"></form:input>

    <br><br>

    Last name: <form:input path="lastName"></form:input>

    <br><br>

    Country:

    <form:select path="country">
        <form:options items="${student.countryOptions}"></form:options>
    </form:select>

    <br><br>

    Java <for:radiobutton path="favoriteLanguage" value="Java"></for:radiobutton>
    C# <for:radiobutton path="favoriteLanguage" value="C#"></for:radiobutton>
    PHP <for:radiobutton path="favoriteLanguage" value="PHP"></for:radiobutton>
    Ruby <for:radiobutton path="favoriteLanguage" value="Ruby"></for:radiobutton>

    <br><br>

    Operating Systems:

    Linux <form:checkbox path="operatingSystems" value="Linux"></form:checkbox>
    Mac OS <form:checkbox path="operatingSystems" value="Mac OS"></form:checkbox>
    MS Windows <form:checkbox path="operatingSystems" value="MS Windows"></form:checkbox>

    <br><br>

    <input type="submit" value="Submit"/>
</form:form>
</body>

</html>