<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="streamingMediaList" scope="request" type="java.util.List<utp.ts.spoilerroom.models.entities.MediaEntity>"/>
<jsp:useBean id="mostActiveThreadsList" scope="request" type="java.util.List<utp.ts.spoilerroom.models.ThreadModel>"/>
<jsp:useBean id="latestThreadsList" scope="request" type="java.util.List<utp.ts.spoilerroom.models.ThreadModel>"/>
<jsp:useBean id="announcementThreadsList" scope="request" type="java.util.List<utp.ts.spoilerroom.models.ThreadModel>"/>
<jsp:useBean id="topAccount" scope="request" type="utp.ts.spoilerroom.models.beans.Account"/>
<jsp:useBean id="dateUtils" scope="request" type="utp.ts.spoilerroom.utilities.DateUtils"/>
<html>
<head>
    <title>Home</title>
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
                            <a href="${pageContext.servletContext.contextPath}/Home">
                                Home
                            </a>
                        </li>
                    </ol>
                </nav>
            </div>
            <p class="h2 pb-2 m-0">
                Home
            </p>
        </div>
        <div class="row">
            <div class="col-9">
                <div class="container p-0 mb-5">
                    <div class="card border-0 bg-transparent">
                        <div class="card-header card-header-border bg-transparent">
                            <p class="h3">Streaming Now!</p>
                        </div>
                        <div class="card-body px-0">
                            <div id="streamingNowCarousel" class="carousel slide"
                                 data-bs-ride="carousel" data-bs-interval="false">
                                <div class="carousel-inner">
                                    <c:set var="firstLoop" scope="page" value="${0}"/>
                                    <c:forEach begin="0" end="${streamingMediaListSlides}" varStatus="i">
                                        <c:if test="${firstLoop != 0}">
                                            <div class="carousel-item">
                                        </c:if>
                                        <c:if test="${firstLoop == 0}">
                                            <div class="carousel-item active">
                                            <c:set var="firstLoop" scope="page" value="${1}"/>
                                        </c:if>
                                            <ul class="poster-slider mx-auto my-0 p-0">
                                            <c:forEach begin="0" end="4" varStatus="j">
                                                <c:set var="posterIndex" scope="page" value="${(i.index * 5) + j.index}" />
                                                <c:if test="${posterIndex >= streamingMediaList.size()}">
                                                    <c:set var="posterIndex" scope="page" value="${posterIndex - streamingMediaList.size()}" />
                                                </c:if>
                                                <c:set var="media" scope="page" value="${streamingMediaList.get(posterIndex).media}"/>
                                                <li class="poster-slider-item">
                                                    <div class="card background-color-black-z4 border-0">
                                                        <img class="card-img-top poster-img" src="${pageContext.servletContext.contextPath}/images/posters/${media.posterImageUrl}.jpg" alt="poster">
                                                        <div class="card-body p-2 text-start">
                                                            <p class="card-title text-truncate poster-title fs-6 mt-0">
                                                                    ${media.title}
                                                            </p>
                                                            <div>
                                                                <p class="card-subtitle w-50 fs-7 text-muted d-lg-inline-block m-0">${media.mediaTypeName}</p>
                                                                <p class="card-subtitle w-50 fs-7 text-muted d-lg-inline-block m-0 text-end">${media.threadCount} Threads</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                            </ul>
                                        </div>
                                        <%--TEMP FIX FOR TAG STRUCTURE--%>
                                        <c:if test="${firstLoop == 0}">
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#streamingNowCarousel" data-bs-slide="prev">
                                    <span class="carousel-control-icon" aria-hidden="true"><i class="bi bi-arrow-left-square"></i></span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#streamingNowCarousel" data-bs-slide="next">
                                    <span class="carousel-control-icon" aria-hidden="true"><i class="bi bi-arrow-right-square"></i></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex justify-content-between">
                    <div class="d-flex">
                        <div class="card border-0 bg-transparent" style="width: 600px">
                            <div class="card-header card-header-border">
                                <p class="h5">Announcements</p>
                            </div>
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${announcementThreadsList}" var="threadModel">
                                    <c:set var="threadEntity" scope="page" value="${threadModel.threadEntity}" />
                                    <li class="list-group-item background-color-black-z4">
                                        <div class="row">
                                            <div class="col-3 my-auto">
                                                <img class="post-img rounded" src="${pageContext.servletContext.contextPath}/images/profile/${threadEntity.ownerAccount.profileImageUrl}.jpg" alt="#">
                                            </div>
                                            <div class="col-9 ps-0">
                                                <a class="d-block text-truncate fs-6" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}">
                                                        ${threadEntity.thread.title}
                                                </a>
                                                <div class="d-block text-white fs-7">
                                                        ${dateUtils.dateToLongFormat(threadEntity.thread.creationDate)}
                                                    By
                                                    <a href="${pageContext.servletContext.contextPath}/Account?account=${threadEntity.ownerAccount.id}">
                                                            ${threadEntity.ownerAccount.username}
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="d-flex flex-column">
                        <div class="d-flex justify-content-center mb-5">
                            <div class="card border-0 background-color-black-z4" style="width: 325px">
                                <div class="card-header card-header-border background-color-black-z1">
                                    <p class="h5">Spoiler of the Month</p>
                                </div>
                                <div class="card-body text-center">
                                    "Eren se muere" -Sebas
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-between">
                            <div class="d-inline-block">
                                <div class="card border-0 background-color-black-z4" style="width: 140px; height: 175px">
                                    <div class="card-header card-header-border background-color-black-z1">
                                        <p class="h6">Stats</p>
                                    </div>
                                    <div class="card-body text-start">
                                        Members: ${memberCount}
                                        <br>
                                        Threads: ${threadCount}
                                        <br>
                                        Posts: ${postCount}
                                    </div>
                                </div>
                            </div>
                            <div class="d-inline-block">
                                <div class="card border-0 background-color-black-z4" style="width: 175px; height: 175px">
                                    <div class="card-header card-header-border background-color-black-z1">
                                        <p class="h6">Month's Cheeser</p>
                                    </div>
                                    <div class="card-body text-start">
                                        <div class="d-block text-center">
                                            <div class="d-block">
                                                <img class="post-img" src="${pageContext.servletContext.contextPath}/images/profile/${topAccount.profileImageUrl}.jpg">
                                            </div>
                                            <div class="d-block color-primary fw-bold">
                                                <a href="${pageContext.servletContext.contextPath}/Account?account=${topAccount.id}">
                                                    ${topAccount.username}
                                                </a>
                                            </div>
                                            <div class="d-block">
                                                ${topAccount.title}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-3">
                <div class="container mb-3 p-0">
                    <div class="card border-0 bg-transparent">
                        <div class="card-header card-header-border bg-transparent">
                            <p class="h5">Most Active Threads</p>
                        </div>
                        <ul class="list-group list-group-flush">
                            <c:forEach items="${mostActiveThreadsList}" var="threadModel">
                                <c:set var="threadEntity" scope="page" value="${threadModel.threadEntity}"/>
                                <li class="list-group-item background-color-black-z4">
                                    <div class="row">
                                        <div class="col-3 my-auto">
                                            <img class="post-img rounded" src="${pageContext.servletContext.contextPath}/images/profile/${threadEntity.ownerAccount.profileImageUrl}.jpg" alt="#">
                                        </div>
                                        <div class="col-9 ps-0">
                                            <a class="d-block text-truncate fs-6" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}">
                                                ${threadEntity.thread.title}
                                            </a>
                                            <div class="d-block text-white fs-7">
                                                ${dateUtils.dateToLongFormat(threadEntity.thread.creationDate)} By
                                                <a href="${pageContext.servletContext.contextPath}/Account?account=${threadEntity.ownerAccount.id}">
                                                        ${threadEntity.ownerAccount.username}
                                                </a>
                                            </div>
                                            <div class="d-flex justify-content-between text-white fs-7">
                                                <div class="d-inline-block">
                                                    in
                                                    <a href="${pageContext.servletContext.contextPath}/Board?board=${threadEntity.board.id}">
                                                            ${threadEntity.board.title}
                                                    </a>
                                                </div>
                                                <div class="d-inline-block">
                                                    ${threadEntity.thread.postCount} Replies
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="container mb-3 p-0">
                    <div class="card border-0 bg-transparent">
                        <div class="card-header card-header-border bg-transparent">
                            <p class="h5">Latest Threads</p>
                        </div>
                        <ul class="list-group list-group-flush">
                            <c:forEach items="${latestThreadsList}" var="threadModel">
                                <c:set var="threadEntity" scope="page" value="${threadModel.threadEntity}" />
                                <li class="list-group-item background-color-black-z4">
                                    <div class="row">
                                        <div class="col-3 my-auto">
                                            <img class="post-img rounded" src="${pageContext.servletContext.contextPath}/images/profile/${threadEntity.ownerAccount.profileImageUrl}.jpg" alt="#">
                                        </div>
                                        <div class="col-9 ps-0">
                                            <a class="d-block text-truncate fs-6" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}">
                                                ${threadEntity.thread.title}
                                            </a>
                                            <div class="d-block text-white fs-7">
                                                ${dateUtils.dateToLongFormat(threadEntity.thread.creationDate)}
                                                By
                                                <a href="${pageContext.servletContext.contextPath}/Account?account=${threadEntity.ownerAccount.id}">
                                                        ${threadEntity.ownerAccount.username}
                                                </a>
                                            </div>
                                            <div class="d-flex justify-content-between text-white fs-7">
                                                <div class="d-inline-block">
                                                    in
                                                    <a href="${pageContext.servletContext.contextPath}/Board?board=${threadEntity.board.id}">
                                                            ${threadEntity.board.title}
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
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
