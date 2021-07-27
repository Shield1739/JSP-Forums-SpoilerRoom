<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>The Team</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap_custom.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/general.css">
</head>
<body>
    <header>
        <jsp:include page="../includes/header.jsp" />
    </header>
    <div class="container-xxl background-color-black-z1 p-4">
        <div class="mb-2">
            <ol class="breadcrumb fs-7 background-color-black-z1">
                <li class="breadcrumb-item active" aria-current="page">
                    <a href="${pageContext.servletContext.contextPath}/Team">
                        The Team
                    </a>
                </li>
            </ol>
        </div>
        <p class="h2 pb-2 m-0">
            Meet The Team
        </p>
        <div class="d-flex my-3 flex-row justify-content-between">
            <div class="d-flex">
                <div class="d-inline-block team-desc p-4 background-color-black-z5">
                    <p class="h3 color-primary fw-bold">Luis Villalaz</p>
                    <span>Le duele la espalda</span>
                </div>
                <div class="d-inline-block">
                    <img class="team-img" src="${pageContext.servletContext.contextPath}/images/team/luis.jpg">
                </div>
            </div>
            <div class="d-flex">
                <div class="d-inline-block team-desc p-4 background-color-black-z5">
                    <p class="h3 color-primary fw-bold">Sebastian Samaniego</p>
                    <span>Se ha "vacunado" 6 veces en las ultimas 8 semanas</span>
                </div>
                <div class="d-inline-block">
                    <img class="team-img" src="${pageContext.servletContext.contextPath}/images/team/sebas.jpg">
                </div>
            </div>
            <div class="d-flex">
                <div class="d-inline-block team-desc p-4 background-color-black-z5">
                    <p class="h3 color-primary fw-bold">Anthony Wen</p>
                    <span>Estoy esperando el codigo aun wen</span>
                </div>
                <div class="d-inline-block">
                    <img class="team-img" src="${pageContext.servletContext.contextPath}/images/team/wen.jpg">
                </div>
            </div>
        </div>
        <div class="d-flex my-3 flex-row justify-content-evenly">
            <div class="d-flex">
                <div class="d-inline-block team-desc p-4 background-color-black-z5">
                    <p class="h3 color-primary fw-bold">Guillermo Nuñez</p>
                    <span>No atendio a 1 clase de OAC</span>
                </div>
                <div class="d-inline-block">
                    <img class="team-img" src="${pageContext.servletContext.contextPath}/images/team/guillermo.jpg">
                </div>
            </div>
            <div class="d-flex">
                <div class="d-inline-block team-desc p-4 background-color-black-z5">
                    <p class="h3 color-primary fw-bold">Sheyfer Bilbao</p>
                    <span>No ve anime</span>
                </div>
                <div class="d-inline-block">
                    <img class="team-img" src="${pageContext.servletContext.contextPath}/images/team/sheyfer.jpg">
                </div>
            </div>
        </div>
    </div>
    <footer>
        <jsp:include page="../includes/footer.jsp" />
    </footer>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.bundle.js"></script>
</body>
</html>
