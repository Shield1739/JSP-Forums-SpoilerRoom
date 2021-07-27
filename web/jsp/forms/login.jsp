<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
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
        <div class="mb-2">
            <div class="container p-0">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb fs-7 background-color-black-z1">
                        <li class="breadcrumb-item active" aria-current="page">
                            <a href="${pageContext.servletContext.contextPath}/Login">
                                Login
                            </a>
                        </li>
                    </ol>
                </nav>
            </div>
            <p class="h2 pb2 m-0">
                Login
            </p>
        </div>
        <div class="container">
            <form id="loginForm" action="Login" method="post">
<%--                <p class="h3 mb-3 fw-normal">Sign in to continue</p>--%>
                <div class="row justify-content-center">
                    <div class="col-3 mb-3">
                        <label class="form-label" for="loginEmail">Email address</label>
                        <input id="loginEmail" name="loginEmail" class="form-control">
                        <div id="emailHelp" class="form-text">We won't not refrain from selling your info</div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-3 mb-3">
                        <label class="form-label" for="loginPassword">Password</label>
                        <input id="loginPassword" name="loginPassword" class="form-control" type="password">
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-3 mb-3">
                        <button class="btn btn-success float-end" type="submit">Login</button>
                        <div class="form-text float-end px-2 text-danger">${loginErrorMessage}</div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <footer>
        <jsp:include page="../includes/footer.jsp" />
    </footer>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.bundle.js"></script>
</body>
</html>
