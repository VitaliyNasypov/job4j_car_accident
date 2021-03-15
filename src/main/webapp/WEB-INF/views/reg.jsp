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
    <div class="shadow p-3 mb-5 bg-body rounded"><h1 class="display-6" style="text-align: center">Sign-Up</h1></div>
    <c:if test="${not empty errorMessage}">
        <div style="color:red; font-weight: bold; margin: 30px 0px;">
                ${errorMessage}
        </div>
    </c:if>
    <form class="row g-3" name='login' action="<c:url value='/reg'/>" method='POST'>
        <div class="col-12">
            <label for="username" class="form-label">UserName:</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="col-12">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="col-12">
            <a class="btn btn-outline-primary" href="<c:url value='/login'/>" role="button" style="float: right">Sign in</a>
            <button type="submit" class="btn btn-primary">Sign up</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>

</div>
</body>
</html>