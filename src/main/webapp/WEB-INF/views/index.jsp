<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container" style="width: 60%;padding: 5%">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"> ID</th>
            <th scope="col"> Name</th>
            <th scope="col"> Text</th>
            <th scope="col"> Address</th>
        </tr>
        </thead>
        <c:forEach var="accident" items="${accidents}">
            <tr>
                <th scope="row"> ${accident.value.id} </th>
                <td> ${accident.value.name} </td>
                <td> ${accident.value.text} </td>
                <td> ${accident.value.address} </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a class="btn btn-primary" href="<c:url value='/create'/>" style="float: right" role="button">Add accident</a>
</div>
</body>
</html>