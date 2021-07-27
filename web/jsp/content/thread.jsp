<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="threadModel" scope="request" type="utp.ts.spoilerroom.models.ThreadModel"/>
<jsp:useBean id="postEntityList" scope="request" type="java.util.List<utp.ts.spoilerroom.models.entities.PostEntity>"/>
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
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/quill.snow.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/general.css">
</head>
<body>
    <header>
        <jsp:include page="../includes/header.jsp" />
    </header>
    <div class="container-xxl background-color-black-z1 p-4">
        <c:set var="threadEntity" scope="page" value="${threadModel.threadEntity}" />
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
                                ${threadEntity.section.title}
                            </a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            <a href="${pageContext.servletContext.contextPath}/Board?board=${threadEntity.board.id}">
                                ${threadEntity.board.title}
                            </a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">
                            <a href="${pageContext.servletContext.contextPath}/Thread?thread=$${threadEntity.thread.id}">
                                ${threadEntity.thread.title}
                            </a>
                        </li>
                    </ol>
                </nav>
            </div>
            <p class="h2 px-2 mb-3">
                ${threadEntity.thread.title}
            </p>
        </div>
        <div class="container">
            <c:set var="count" value="1" scope="page"/>
            <c:set var="rowCount" value="${rowNumber}" scope="page"/>
            <script> var postContents = {}; </script>
            <c:if test="${threadPages > 1}">
                <nav aria-label="Thread pagination">
                    <ul class="pagination pagination-sm justify-content-end">
                        <c:if test="${currentPage==1}">
                            <li class="page-item disabled">
                                <a class="page-link" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}&page=${currentPage - 1}">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${currentPage!=1}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}&page=${currentPage - 1}">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${threadPages}" varStatus="i">
                            <c:if test="${i.index == currentPage}">
                                <li class="page-item active">
                                    <a class="page-link" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}&page=${i.index}">
                                            ${i.index}
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${i.index != currentPage}">
                                <li class="page-item">
                                    <a class="page-link" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}&page=${i.index}">
                                            ${i.index}
                                    </a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <c:if test="${currentPage==threadPages}">
                            <li class="page-item disabled">
                                <a class="page-link" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}&page=${currentPage + 1}">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${currentPage!=threadPages}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.servletContext.contextPath}/Thread?thread=${threadEntity.thread.id}&page=${currentPage + 1}">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
            <c:forEach items="${postEntityList}" var="postEntity">
                <div class="card bg-transparent mb-4">
                    <div class="card-header background-color-black-z3">
                        <div class="row fs-7">
                            <div class="col-2 text-start">
                                <span>${dateUtils.dateToLongFormat(postEntity.post.creationDate)}</span>
                            </div>
                            <div class="col-10 text-end">
                                <span>#${rowCount}</span>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-2 text-center background-color-black-z4">
                                <div class="d-block mt-2 mb-3">
                                    <a class="text-white" href="${pageContext.servletContext.contextPath}/Account?account=${postEntity.ownerAccount.id}">${postEntity.ownerAccount.username}</a>
                                    <br>
                                    <span class="fs-7">${postEntity.ownerAccount.title}</span>
                                </div>
                                <div class="d-block mb-3">
                                    <img class="post-img-big rounded" src="${pageContext.servletContext.contextPath}/images/profile/${postEntity.ownerAccount.profileImageUrl}.jpg" alt="#">
                                </div>
                                <div class="d-block mb-3 fs-7 text-start">
                                    <span>Join Date: ${dateUtils.dateToShortFormat(postEntity.ownerAccount.creationDate)}</span>
                                    <br>
                                    <span>Posts: ${postEntity.ownerAccount.postCount}</span>
                                </div>
                            </div>
                            <div class="col-10 background-color-black-z5">
                                <div class="d-block p-3">
                                    <div id="quillPostEditor${count-1}" class="quill-post-editor-text-area ql-editor-read-only">
                                    </div>
                                    <script>
                                        postContents[${count}-1] = '${postEntity.post.content}'
                                    </script>
                                </div>
                                <div class="d-block mb-2">
                                    <c:if test="${postEntity.lastEditorAccount.id != null}">
                                        <div class="text-end text-muted">
                                            Last Edited By: ${postEntity.lastEditorAccount.username}
                                            at ${dateUtils.dateToLongFormat(postEntity.post.lastEditDate)}
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-2 py-3 text-center background-color-black-z4">
                                <c:if test="${dateUtils.isOnline(postEntity.ownerAccount.lastActiveDate)}">
                                    <span class="color-secondary fs-7">${postEntity.ownerAccount.username} is online now</span>
                                </c:if>
                                <c:if test="${!dateUtils.isOnline(postEntity.ownerAccount.lastActiveDate)}">
                                    <span class="fs-7">${postEntity.ownerAccount.username} is offline</span>
                                </c:if>
                            </div>
                            <div class="col-10 text-end background-color-black-z5">
                                <div id="postEditButtonsDiv${count-1}" class="d-block mb-2 visually-hidden">
                                    <a id="postCancelEditButton${count-1}" onclick="editCancel(${count-1})" class="btn btn-outline-warning fw-bold"><i class="bi bi-x-square"></i> Cancel Edit</a>
                                    <form id="postSaveEditForm${count-1}" action="PostEdit" method="post" class="d-inline-block">
                                        <input type="hidden" name="threadId" value="${threadEntity.thread.id}">
                                        <input type="hidden" name="postId" value="${postEntity.post.id}">
                                        <textarea id="postEditContent${count-1}" name='postEditContent' class="d-none" type="hidden"></textarea>
                                        <button id="postSaveEditButton${count-1}" type="submit" onclick="editSavePost(${count-1})" class="btn btn-outline-primary fw-bold">
                                            <i class="bi bi-save"></i> Save Edit
                                        </button>
                                    </form>
                                </div>
                                <div class="d-block mb-2">
                                    <c:if test="${loggedAccountModel!=null}">
                                        <c:set var="accountVote" value="${postEntity.accountVote.voteType}" scope="page"/>
                                        <c:if test="${loggedAccountModel.isAdmin()}">
                                            <a id="postDeleteButton${count-1}" class="btn btn-outline-primary fw-bold mx-1"
                                               data-bs-toggle="modal" href="#deletePostModal"
                                               data-bs-postId="${postEntity.post.id}" data-bs-postParentId="${postEntity.post.parentId}">
                                                <i class="bi bi-trash"></i> Delete
                                            </a>
                                        </c:if>
                                        <c:if test="${loggedAccountModel.accountEntity.account.id == postEntity.ownerAccount.id || loggedAccountModel.isModerator()}">
                                            <a id="postEditButton${count-1}" onclick="editPost(${postEntity.post.id}, ${loggedAccountModel.accountEntity.account.id})" class="btn btn-outline-primary fw-bold me-3">
                                                <i class="bi bi-pencil-square"></i> Edit
                                            </a>
                                        </c:if>
                                        <form class="d-inline-block" action="Vote" method="post">
                                            <input type="hidden" name="threadId" value="${threadEntity.thread.id}">
                                            <input type="hidden" name="postId" value="${postEntity.post.id}">
                                            <input type="hidden" name="accountId" value="${loggedAccountModel.accountEntity.account.id}">
                                            <c:if test="${accountVote!=2}">
                                                <input type="hidden" name="voteType" value="${2}">
                                                <button id="postDownButton${count-1}" class="btn btn-outline-danger fw-bold mx-1">
                                                    <i class="bi bi-hand-thumbs-down"></i>
                                                </button>
                                            </c:if>
                                            <c:if test="${accountVote==2}">
                                                <input type="hidden" name="voteType" value="${0}">
                                                <button id="postDownButton${count-1}" class="btn btn-outline-danger fw-bold mx-1 active">
                                                    <i class="bi bi-hand-thumbs-down"></i>
                                                </button>
                                            </c:if>
                                        </form>
                                    </c:if>
                                    <div class="d-inline-block">
                                        <c:if test="${postEntity.post.cheese>0}">
                                            <span class="color-primary fw-bold">${postEntity.post.cheese}</span>
                                        </c:if>
                                        <c:if test="${postEntity.post.cheese==0}">
                                            <span class="color-gray">${postEntity.post.cheese}</span>
                                        </c:if>
                                        <c:if test="${postEntity.post.cheese<0}">
                                            <span class="text-danger">${postEntity.post.cheese}</span>
                                        </c:if>
                                        Cheese
                                    </div>
                                    <c:if test="${loggedAccountModel!=null}">
                                        <form class="d-inline-block" action="Vote" method="post">
                                            <input type="hidden" name="threadId" value="${threadEntity.thread.id}">
                                            <input type="hidden" name="postId" value="${postEntity.post.id}">
                                            <input type="hidden" name="accountId" value="${loggedAccountModel.accountEntity.account.id}">
                                            <c:if test="${accountVote!=1}">
                                                <input type="hidden" name="voteType" value="${1}">
                                                <button id="postUpButton${count-1}" class="btn btn-outline-success fw-bold mx-1">
                                                    <i class="bi bi-hand-thumbs-up"></i>
                                                </button>
                                            </c:if>
                                            <c:if test="${accountVote==1}">
                                                <input type="hidden" name="voteType" value="${0}">
                                                <button id="postUpButton${count-1}" class="btn btn-outline-success fw-bold mx-1 active">
                                                    <i class="bi bi-hand-thumbs-up"></i>
                                                </button>
                                            </c:if>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:set var="rowCount" value="${rowCount + 1}" scope="page"/>
            </c:forEach>
            <c:if test="${loggedAccountModel.isAdmin()}">
                <div class="modal fade" id="deletePostModal" tabindex="-1" aria-labelledby="deletePostModal" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content background-color-black-z3">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deletePostModalLabel">Delete Post</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Are you sure you want to delete this post?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                    <i class="bi bi-x-circle"></i> Cancel
                                </button>
                                <form id="deleteForm" action="DeletePost" method="post">
                                    <input id="deleteThreadIdInput" type="hidden" name="deleteThreadId" value="${threadEntity.thread.id}">
                                    <input id="deleteBoardIdInput" type="hidden" name="boardId" value="${threadEntity.board.id}">
                                    <input id="deletePostIdInput" type="hidden" name="deletePostId">
                                    <input id="deletePostParentIdInput" type="hidden" name="deletePostParentIdInput">
                                    <button type="submit" class="btn btn-primary">
                                        <i class="bi bi-trash"></i> Delete
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${loggedAccountModel!=null}">
                <div class="container-sm">
                    <div class="rich-text-editor-container m-auto">
                        <div class="pb-2 fs-6">
                            Write your message here:
                        </div>
                        <div class="mb-2 background-color-black-z5">
                            <div id="quillThreadEditor" class="quill-editor-text-area"></div>
                        </div>
                        <div class="pb-2 text-end">
                            <form id="postReplyForm" action="PostReply" method="post">
                                <input type="hidden" name="threadId" value="${threadEntity.thread.id}">
                                <input type="hidden" name="lastPostId" value="${threadModel.lastPostEntity.post.id}">
                                <textarea id="postReplyContent" name='postReplyContent' class="d-none" type="hidden"></textarea>
                                <button id="postReplySubmitButton" type="submit" class="btn btn-primary fw-bold">
                                    Post Reply
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
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

        var postEditors={};
        for (let i = 0; i < ${postEntityList.size()}; i++) {
            var id = '#quillPostEditor' + i
            postEditors[i] = new Quill(id, {
                modules: {
                    toolbar: toolbarOptions
                },
                theme: 'snow'
            });
            postEditors[i].setContents(JSON.parse(postContents[i]));
            postEditors[i].enable(false);
        }

        var postEditorToolbars = document.querySelectorAll(".ql-toolbar");
        for (let i = 0; i < postEditorToolbars.length; i++) {
            postEditorToolbars[i].classList.add("visually-hidden");
        }

        var deletePostModal = document.getElementById('deletePostModal')
        deletePostModal.addEventListener('show.bs.modal', function (event) {
            var button = event.relatedTarget

            var postId = button.getAttribute('data-bs-postId')
            var postParentId = button.getAttribute('data-bs-postParentId')

            var postIdInput = document.getElementById('deletePostIdInput')
            var postParentIdInput = document.getElementById('deletePostParentIdInput')

            postIdInput.value = postId
            postParentIdInput.value = postParentId
        })

        var tempContent;
        var editingState = 0;

        if (${loggedAccountModel!=null})
        {
            var quill = new Quill('#quillThreadEditor', {
                modules: {
                    toolbar: toolbarOptions
                },
                placeholder: 'Waiting for your precious comment...',
                theme: 'snow'
            });

            var postReplyContent = document.getElementById('postReplyContent')

            quill.on('text-change', function() {
                var delta = quill.getContents();
                postReplyContent.innerHTML = JSON.stringify(delta);

            });
        }

        function deletePost(clickedId) {
            console.log('DO POST DELETE FUNC' + clickedId);
        }

        function editPost(clickedId) {
            if (editingState === 1) {
                return
            }
            tempContent = postEditors[clickedId].getContents();
            postEditorToolbars[clickedId].classList.remove("visually-hidden");
            var postEditor = document.getElementById("quillPostEditor" + clickedId);
            postEditor.classList.remove("ql-editor-read-only");
            var postEditorButtonsDiv = document.getElementById("postEditButtonsDiv" + clickedId);
            postEditorButtonsDiv.classList.remove("visually-hidden");
            postEditors[clickedId].enable(true);
            editingState = 1;
        }

        function editSavePost(clickedId) {
            if (editingState === 0) {
                return
            }
            var preciousContent = document.getElementById('postEditContent' + clickedId);
            preciousContent.innerHTML = JSON.stringify(postEditors[clickedId].getContents());

            postEditorToolbars[clickedId].className += " visually-hidden";
            var postEditor = document.getElementById("quillPostEditor" + clickedId);
            postEditor.classList.add("ql-editor-read-only");
            var postEditorButtonsDiv = document.getElementById("postEditButtonsDiv" + clickedId);
            postEditorButtonsDiv.classList.add("visually-hidden");
            postEditors[clickedId].enable(false);
            editingState = 0;
        }

        function editCancel(clickedId) {
            if (editingState === 0) {
                return
            }
            postEditors[clickedId].setContents(tempContent);

            postEditorToolbars[clickedId].className += " visually-hidden";
            var postEditor = document.getElementById("quillPostEditor" + clickedId)
            postEditor.classList.add("ql-editor-read-only");
            var postEditorButtonsDiv = document.getElementById("postEditButtonsDiv" + clickedId)
            postEditorButtonsDiv.classList.add("visually-hidden");
            postEditors[clickedId].enable(false);
            editingState = 0;
        }

        function downVotePost(postId, accountId) {
            console.log(clickedId);
        }

        function upVotePost(postId, accountId) {
            document.location.href="/";
        }
    </script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.bundle.js"></script>
</body>
</html>
