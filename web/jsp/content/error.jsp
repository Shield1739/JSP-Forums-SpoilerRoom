<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 404</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap_custom.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/general.css">
</head>
<body>
    <header>
        <jsp:include page="../includes/header.jsp" />
    </header>
    <div class="container-xxl p-4 background-color-black-z1">
        <h1>404 Page not Found</h1>
        <br>
        <a href="${pageContext.servletContext.contextPath}/Home">
            Back to Homepage
        </a>
    </div>
    <footer>
        <jsp:include page="../includes/footer.jsp" />
    </footer>
</body>
</html>
