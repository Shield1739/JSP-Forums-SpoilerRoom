<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="sectionModelList" scope="request" type="java.util.List<utp.ts.spoilerroom.models.SectionModel>"/>
<jsp:useBean id="dateUtils" scope="request" type="utp.ts.spoilerroom.utilities.DateUtils"/>
<html>
<head>
    <title>Forum</title>
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
                                Forum
                            </a>
                        </li>
                    </ol>
                </nav>
            </div>
            <p class="h2 pb2 m0-3">
                Forum
            </p>
        </div>
        <div class="container p-5">
            <c:forEach items="${sectionModelList}" var="sectionModel">
                <div class="card bg-dark mb-4">
                    <div class="card-header card-header-border background-color-black-z4">
                        <p class="h3">${sectionModel.sectionEntity.section.title}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item background-color-black-z3 py-1 px-3 color-gray">
                            <div class="row">
                                <div class="col-7">
                                    <span>Board</span>
                                </div>
                                <div class="col-1 text-center">
                                    <span>Threads</span>
                                </div>
                                <div class="col-1 text-center">
                                    <span>Posts</span>
                                </div>
                                <div class="col-3">
                                    <span>Last Post</span>
                                </div>
                            </div>
                        </li>
                        <c:forEach items="${sectionModel.boardModelList}" var="boardModel">
                            <c:set var="boardEntity" scope="page" value="${boardModel.boardEntity}" />
                            <li class="list-group-item background-color-black-z5">
                                <div class="row">
                                    <div class="col-7">
                                        <a class="h4 m-0" href="${pageContext.servletContext.contextPath}/Board?board=${boardEntity.board.id}">
                                            ${boardEntity.board.title}
                                        </a>
                                        <br>
                                        <span class="fs-6 color-gray">
                                            ${boardEntity.board.description}
                                        </span>
                                    </div>
                                    <div class="col-1 d-flex align-items-center text-white">
                                        <div class="d-block m-auto">
                                                ${boardEntity.board.threadCount}
                                        </div>
                                    </div>
                                    <div class="col-1 d-flex align-items-center text-white">
                                        <div class="d-block m-auto">
                                                ${boardEntity.board.postCount}
                                        </div>
                                    </div>
                                    <div class="col-3">
                                        <c:set var="lastPostEntity" scope="page" value="${boardModel.lastPostEntity}" />
                                        <c:if test="${lastPostEntity.post.id!=null}">
                                            <div class="row">
                                                <div class="col-3 my-auto">
                                                    <img class="post-img rounded" src="${pageContext.servletContext.contextPath}/images/profile/${lastPostEntity.ownerAccount.profileImageUrl}.jpg" alt="#">
                                                </div>
                                                <div class="col-9 ps-0">
                                                    <a class="d-block text-truncate fs-6" href="${pageContext.servletContext.contextPath}/Thread?thread=${lastPostEntity.thread.id}">
                                                        ${lastPostEntity.thread.title}
                                                    </a>
                                                    <div class="d-block text-white fs-7">
                                                        By
                                                        <a href="${pageContext.servletContext.contextPath}/Account?account=${lastPostEntity.ownerAccount.id}">
                                                                ${lastPostEntity.ownerAccount.username}
                                                        </a>
                                                    </div>
                                                    <div class="d-flex justify-content-between text-white fs-7">
                                                        <div class="d-inline-block">
                                                            <c:if test="${lastPostEntity.post.cheese>0}">
                                                                <span class="color-primary fw-bold">${lastPostEntity.post.cheese}</span>
                                                            </c:if>
                                                            <c:if test="${lastPostEntity.post.cheese==0}">
                                                                <span class="color-gray">${lastPostEntity.post.cheese}</span>
                                                            </c:if>
                                                            <c:if test="${lastPostEntity.post.cheese<0}">
                                                                <span class="text-danger">${lastPostEntity.post.cheese}</span>
                                                            </c:if>
                                                            Cheese
                                                        </div>
                                                        <div class="d-inline-block">
                                                                ${dateUtils.dateToLongFormat(lastPostEntity.post.creationDate)}
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
        </div>
    </div>
    <footer>
        <jsp:include page="../includes/footer.jsp" />
    </footer>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.bundle.js"></script>
</body>
</html>
