<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="boardEntity" scope="request" type="utp.ts.spoilerroom.models.entities.BoardEntity"/>
<jsp:useBean id="threadModelList" scope="request" type="java.util.List<utp.ts.spoilerroom.models.ThreadModel>"/>
<jsp:useBean id="dateUtils" scope="request" type="utp.ts.spoilerroom.utilities.DateUtils"/>
<c:if  test="${loggedAccountModel!=null}">
    <jsp:useBean id="loggedAccountModel" scope="session" type="utp.ts.spoilerroom.models.AccountModel"/>
</c:if>
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
                        <li class="breadcrumb-item active" aria-current="page">
                            <a href="${pageContext.servletContext.contextPath}/Forum">
                                ${boardEntity.section.title}
                            </a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            <a href="${pageContext.servletContext.contextPath}/Board?board=${boardEntity.board.id}">
                                ${boardEntity.board.title}
                            </a>
                        </li>
                    </ol>
                </nav>
            </div>
            <p class="h2 pb2 m0-3">
                ${boardEntity.board.title}
            </p>
            <c:if test="${loggedAccountModel!=null}">
                <div class="text-end">
                    <form id="postThreadForm" action="PostThread" method="get">
                        <input type="hidden" name="boardId" value="${boardEntity.board.id}">
                        <button id="postThreadSubmitButton" type="submit" class="btn btn-primary fw-bold">
                            Post New Thread
                        </button>
                    </form>
                </div>
            </c:if>
        </div>
        <div class="container p-5">
            <c:if test="${boardPages > 1}">
                <nav aria-label="Thread pagination">
                    <ul class="pagination pagination-sm justify-content-end">
                        <c:if test="${currentPage==1}">
                            <li class="page-item disabled">
                                <a class="page-link" href="${pageContext.servletContext.contextPath}/Board?board=${boardEntity.board.id}&page=${currentPage - 1}">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${currentPage!=1}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.servletContext.contextPath}/Board?board=${boardEntity.board.id}&page=${currentPage - 1}">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${boardPages}" varStatus="i">
                            <c:if test="${i.index == currentPage}">
                                <li class="page-item active">
                                    <a class="page-link" href="${pageContext.servletContext.contextPath}/Board?board=${boardEntity.board.id}&page=${i.index}">
                                            ${i.index}
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${i.index != currentPage}">
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.servletContext.contextPath}/Board?board=${boardEntity.board.id}&page=${i.index}">
                                            ${i.index}
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${currentPage==boardPages}">
                            <li class="page-item disabled">
                                <a class="page-link" href="${pageContext.servletContext.contextPath}/Board?board=${boardEntity.board.id}&page=${currentPage + 1}">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${currentPage!=boardPages}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.servletContext.contextPath}/Board?board=${boardEntity.board.id}&page=${currentPage + 1}">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
            <div class="card bg-transparent mb-4">
                <div class="card-header card-header-border background-color-black-z4">
                    <div class="row text-center">
                        <div class="col-7">
                            <span>Thread</span>
                        </div>
                        <div class="col-1">
                            <span>Replies</span>
                        </div>
                        <div class="col-1">
                            <span>Views</span>
                        </div>
                        <div class="col-3">
                            <span>Last Post</span>
                        </div>
                    </div>
                </div>
                <ul class="list-group list-group-flush">
                    <c:forEach items="${threadModelList}" var="threadModel">
                        <c:set var="threadEntity" scope="page" value="${threadModel.threadEntity}" />
                        <li class="list-group-item background-color-black-z5">
                            <div class="row">
                                <div class="col-7">
                                    <div class="d-flex flex-row">
                                        <div class="me-1">
                                            <img class="post-img rounded" src="${pageContext.servletContext.contextPath}/images/profile/${threadEntity.ownerAccount.profileImageUrl}.jpg" alt="#">
                                        </div>
                                        <div class="px-2 text-start">
                                            <a class="d-block text-truncate fs-6" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}">
                                                ${threadEntity.thread.title}
                                            </a>
                                            <div class="d-block text-white fs-7">
                                                By
                                                <a href="${pageContext.servletContext.contextPath}/Account?account=${threadEntity.ownerAccount.id}">
                                                        ${threadEntity.ownerAccount.username}
                                                </a>
                                                - ${dateUtils.dateToLongFormat(threadEntity.thread.creationDate)} |
                                                <c:if test="${threadEntity.thread.cheese>0}">
                                                    <span class="color-primary fw-bold">${threadEntity.thread.cheese}</span>
                                                </c:if>
                                                <c:if test="${threadEntity.thread.cheese==0}">
                                                    <span class="color-gray fw-bold">${threadEntity.thread.cheese}</span>
                                                </c:if>
                                                <c:if test="${threadEntity.thread.cheese<0}">
                                                    <span class="text-danger fw-bold">${threadEntity.thread.cheese}</span>
                                                </c:if>
                                                 Cheese
                                                <c:if test="${threadModel.mediaEntity != null}">
                                                    <span class="ms-2 badge background-color-secondary text-dark">${threadModel.mediaEntity.media.title}</span>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-1 d-flex align-items-center text-white">
                                    <div class="d-block m-auto">
                                            ${threadEntity.thread.postCount}
                                    </div>
                                </div>
                                <div class="col-1 d-flex align-items-center text-white">
                                    <div class="d-block m-auto">
                                            ${threadEntity.thread.viewCount}
                                    </div>
                                </div>
                                <div class="col-3">
                                    <div class="d-flex flex-row justify-content-center">
                                        <c:set var="lastPostEntity" scope="page" value="${threadModel.lastPostEntity}" />
                                        <div class="me-1">
                                            <img class="post-img rounded" src="${pageContext.servletContext.contextPath}/images/profile/${lastPostEntity.ownerAccount.profileImageUrl}.jpg" alt="#">
                                        </div>
                                        <div class="d-flex flex-column ps-2">
                                            <div class="d-block text-white fs-7">
                                                By
                                                <a href="${pageContext.servletContext.contextPath}/Account?account=${lastPostEntity.ownerAccount.id}">
                                                    ${lastPostEntity.ownerAccount.username}
                                                </a>
                                            </div>
                                            <div class="d-block text-white fs-7">
                                                ${dateUtils.dateToLongFormat(lastPostEntity.post.creationDate)}
                                            </div>
                                            <div class="d-block text-white fs-7">
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
    <footer>
        <jsp:include page="../includes/footer.jsp" />
    </footer>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.bundle.js"></script>
</body>
</html>
