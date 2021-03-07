<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adding Accident</title>
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
<div style="width: 60%;padding: 5%">
    <form class="row g-3" action="<c:url value='/save'/>" method='POST'>
        <div class="col-md-4">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <br>
        <div class="col-md-4">
            <label for="text" class="form-label">Text</label>
            <input type="text" class="form-control" id="text" name="text" required>
        </div>
        <div class="col-md-4">
            <label for="address" class="form-label">Address</label>
            <input type="text" class="form-control" id="address" name="address" required>
        </div>
        <div class="col-md-4">
            <label for="type.id" class="form-label">Address</label>
            <select class="form-select" aria-label="Default select example" id="type.id" name="type.id" required>
                <option selected disabled value=''>Select Accident Type</option>
                <c:forEach var="type" items="${accidentTypes}">
                    <option value="${type.id}">${type.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-12">
            <button class="btn btn-primary" type="submit">Add accident</button>
        </div>
    </form>
</div>
</body>
</html>