<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="accountEntity" scope="request" type="utp.ts.spoilerroom.models.entities.AccountEntity"/>
<jsp:useBean id="postEntityList" scope="request" type="java.util.List<utp.ts.spoilerroom.models.entities.PostEntity>"/>
<html>
<head>
    <title>Account</title>
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
        <div class="container p-0">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb fs-7 background-color-black-z1">
                    <li class="breadcrumb-item active" aria-current="page">
                        <a href="${pageContext.servletContext.contextPath}/Forum">
                            Account
                        </a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">
                        <a href="${pageContext.servletContext.contextPath}/Account?account=${accountEntity.account.id}">
                            ${accountEntity.account.username}
                        </a>
                    </li>
                </ol>
            </nav>
        </div>
        <p class="h2 pb2 m0-3">
            ${accountEntity.account.username}
        </p>
        <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="container text-center background-color-black-z4">
                        <img class="rounded" src="${pageContext.servletContext.contextPath}/images/profile/${accountEntity.account.profileImageUrl}.jpg" style="width: 300px; height: 300px" alt="#">
                    </div>
                </div>
                <div class="col-8">
                    <div class="container py-3 background-color-black-z4">
                        <p class="h-3">About:</p>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item d-flex justify-content-between text-white background-color-black-z5">
                                <div class="fw-bold">
                                    Type:
                                </div>
                                <div class="text-end">
                                    ${accountEntity.account.accountTypeName}
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between text-white background-color-black-z5">
                                <div class="fw-bold">
                                    Member since:
                                </div>
                                <div class="text-end">
                                    ${accountEntity.account.creationDate}
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between text-white background-color-black-z5">
                                <div class="fw-bold">
                                    Last Active:
                                </div>
                                <div class="text-end">
                                    ${accountEntity.account.lastActiveDate}
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between text-white background-color-black-z5">
                                <div class="d-block fw-bold">
                                    Title:
                                </div>
                                <div class="text-end">
                                    ${accountEntity.account.title}
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between text-white background-color-black-z5">
                                <div class="fw-bold">
                                    Location:
                                </div>
                                <div class="text-end">
                                    ${accountEntity.account.location}
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between text-white background-color-black-z5">
                                <div class="fw-bold">
                                    Thread Count:
                                </div>
                                <div class="text-end">
                                    ${accountEntity.account.threadCount}
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between text-white background-color-black-z5">
                                <div class="fw-bold">
                                    Post Count:
                                </div>
                                <div class="text-end">
                                    ${accountEntity.account.postCount}
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="container">
                        <div class="card border-0 bg-transparent">
                            <div class="card-header card-header-border bg-transparent">
                                <p class="h4">Latest Activity</p>
                            </div>
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${postEntityList}" var="postEntity">
                                    <li class="list-group-item background-color-black-z4 text-white">
                                        <div class="d-block">
                                            <c:if test="${postEntity.post.parentId==null}">
                                                ${accountEntity.account.username} posted a thread
                                            </c:if>
                                            <c:if test="${postEntity.post.parentId!=null}">
                                                ${accountEntity.account.username} replied to a thread
                                            </c:if>
                                        </div>
                                        <div class="d-block">
                                            <a href="${pageContext.servletContext.contextPath}/Thread?thread=${postEntity.thread.id}">
                                                    ${postEntity.thread.title}
                                            </a>
                                        </div>
                                        <div class="d-block">
                                            ${postEntity.post.creationDate}
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
    <jsp:include page="../includes/footer.jsp" />
</footer>
</body>
</html>
