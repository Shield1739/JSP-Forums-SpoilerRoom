<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if  test="${loggedAccountModel!=null}">
    <jsp:useBean id="loggedAccountModel" scope="session" type="utp.ts.spoilerroom.models.AccountModel"/>
</c:if>
<html>
<head>
    <title></title>
</head>
<body id="header">
    <c:if test="${loggedAccountModel.isAdmin()}">
        <div class="container d-flex flex-wrap">
            <ul class="nav me-auto">
                <li class="nav-item">
                    <a href="#" class="nav-link color-primary px-2">
                        <i class="bi bi-gear"></i> Admin Panel
                    </a>
                </li>
            </ul>
        </div>
    </c:if>
    <nav class="navbar navbar-expand-md navbar-dark pt-3 pb-0">
        <div class="container-xxl">
            <a class="navbar-brand p-0 me-2" href="${pageContext.servletContext.contextPath}/Home">
                <p class="h1">
                    Spoiler Room
                </p>
            </a>
            <c:if test="${loggedAccountModel.accountEntity==null}">
                <div>
                    <a class="btn btn-outline-primary d-lg-inline-block fw-bold me-1"
                       href="${pageContext.servletContext.contextPath}/Login">
                        <i class="bi bi-box-arrow-in-right"></i>
                        Login
                    </a>
                    <a class="btn btn-primary d-lg-inline-block fw-bold"
                       href="${pageContext.servletContext.contextPath}/SignUp">
                        <i class="bi bi-person"></i>
                        Sign Up
                    </a>
                </div>
            </c:if>
            <c:if test="${loggedAccountModel.accountEntity!=null}">
                <div>
                    <ul class="navbar-nav flex-row flex-wrap">
                        <li class="nav-item col-md-auto">
                            <a class="nav-link" href="#">
                                <i class="bi bi-bell"></i>
                                <span>Notificaciones</span>
                            </a>
                        </li>
                        <li class="nav-item col-md-auto">
                            <a class="nav-link" href="#">
                                <i class="bi bi-envelope"></i>
                                <span>Mensajes</span>
                            </a>
                        </li>
                        <li id="profileDropdown" class="nav-item col-md-auto">
                            <ul class="navbar-nav p-0">
                                <li class="nav-item dropdown">
                                    <a id="profileDropdownLink" class="nav-link dropdown-toggle"
                                       href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        <img class="nav-profile-img me-2 rounded-circle"
                                             src="${pageContext.servletContext.contextPath}/images/profile/${loggedAccountModel.accountEntity.account.profileImageUrl}.jpg" alt="profile">
                                        <span>${loggedAccountModel.accountEntity.account.username}</span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="profileDropdownLink">
                                        <li><a class="dropdown-item" href="#">Mi Perfil</a></li>
                                        <li><a class="dropdown-item" href="#">Mis Posts</a></li>
                                        <li><a class="dropdown-item" href="#">Configuraciones</a></li>
                                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">Log Out</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </nav>
    <nav class="navbar navbar-expand-lg py-3">
        <div class="container-xxl background-color-black-z3 py-3">
            <ul class="navbar-nav fs-5 fw-bold me-auto">
                <li class="nav-item">
                    <a class="nav-link color-primary active" aria-current="page"
                       href="${pageContext.servletContext.contextPath}/Home">
                        Home
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link color-primary"
                       href="${pageContext.servletContext.contextPath}/Forum">
                        Forums
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link color-primary"
                       href="${pageContext.servletContext.contextPath}/Team">
                        Team
                    </a>
                </li>
            </ul>
            <form class="d-flex m-0" action="Search" method="get">
                <input id="searchInput" name="searchInput" class="form-control background-color-black-z5 border-0 me-2" type="search" placeholder="Buscar..." aria-label="Search">
                <button class="btn btn-primary fw-bold" type="submit">Buscar</button>
            </form>
        </div>
    </nav>
</body>
</html>
