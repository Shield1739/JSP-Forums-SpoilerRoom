<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="boardEntity" scope="request" type="utp.ts.spoilerroom.models.entities.BoardEntity"/>
<html>
<head>
    <title>Forum</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap_custom.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/quill.snow.css">
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
            Post New Thread In: ${boardEntity.board.title}
        </p>
    </div>
    <form id="postNewThreadForm" action="PostThread" method="post">
        <div class="container-sm">
            <div class="rich-text-editor-container m-auto">
                <div class="container pb-3 px-0">
                    <label for="threadTitle" class="fs-4">Title: </label>
                    <input type="text" class="form-control" id="threadTitle" name="threadTitle" placeholder="Enter title">
                </div>
                <div class="pb-1 fs-4">Content: </div>
                <div class="mb-2 background-color-black-z5">
                    <div id="quillEditor" class="quill-editor-text-area"></div>
                </div>
                <div class="pb-2 text-end">
                    <input type="hidden" name="boardId" value="${boardEntity.board.id}">
                    <textarea id="threadContent" name='threadContent' class="d-none" type="hidden"></textarea>
                    <button id="postThreadSubmitButton" type="submit" class="btn btn-primary fw-bold">
                        Post New Thread
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
<footer>
    <jsp:include page="../includes/footer.jsp" />
</footer>
<script src="${pageContext.servletContext.contextPath}/js/quill.js"></script>
<script>
    var toolbarOptions = [
        [{ 'size': ['small', false, 'large', 'huge'] }],
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],

        [{ 'list': 'ordered'}, { 'list': 'bullet' }],

        [{ 'color': [] }],
        [{ 'align': [] }],

        ['clean']
    ];

    var quill = new Quill('#quillEditor', {
        modules: {
            toolbar: toolbarOptions
        },
        placeholder: 'Write something quick!',
        theme: 'snow'
    });

    var threadContent = document.getElementById('threadContent')

    quill.on('text-change', function() {
        var delta = quill.getContents();
        threadContent.innerHTML = JSON.stringify(delta);
    });
</script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.bundle.js"></script>
</body>
</html>
