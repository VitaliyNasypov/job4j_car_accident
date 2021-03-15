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
    <a class="btn btn-outline-info" href="<c:url value='/logout'/>" style="float: right">User name: ${user.username} |
        Logout</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"> ID</th>
            <th scope="col"> Name</th>
            <th scope="col"> Text</th>
            <th scope="col"> Address</th>
            <th scope="col"> Accident Type</th>
            <th scope="col"> Rules</th>
        </tr>
        </thead>
        <c:forEach var="accident" items="${accidents}">
            <tr>
                <th scope="row">${accident.id}
                    <a href="<c:url value='/update?id=${accident.id}'/>">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-pencil-square" viewBox="0 0 16 16">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                            <path fill-rule="evenodd"
                                  d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                        </svg>
                    </a>
                </th>
                <td> ${accident.name} </td>
                <td> ${accident.text} </td>
                <td> ${accident.address} </td>
                <td> ${accident.type.name} </td>
                <td>
                    <c:forEach var="rule" items="${accident.rules}" varStatus="loop">
                        <c:choose>
                            <c:when test="${loop.last}">
                                ${rule.name}
                            </c:when>
                            <c:otherwise>
                                ${rule.name},
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a class="btn btn-primary" href="<c:url value='/create'/>" style="float: right" role="button">Add accident</a>
</div>
</body>
</html>