const receivedData = location.href.split('?')[1];
$(document).ready(function () {
    let id = Number(receivedData); // data
    getPost(id);
    getMyLike(id);

    $("#button-addon2").click(function () {
        $.ajax({
            type: 'POST',
            url: `/api/posts/${receivedData}/comments`,
            contentType: 'application/json',
            data: JSON.stringify({
                "content": $("#commentInput").val()
            }),
            async: true,
            success: function (response) {
                alert(response.message);
                location.reload();
            },
            error(error, status, response) {
                alert(error.responseJSON.message);
            }
        });
    })

    $(document).on('click', '.updateComment', function () {
        const newCommentInput = `
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="수정 할 댓글을 입력하세요" id="updateValue">
            <button class="btn btn-outline-secondary" type="button" id="updateBtn">수정</button>
        </div>
    `;
        $(this).closest('.list-group-item').parent().append(newCommentInput);
    });

    $(document).on('click', '#updateBtn', function () {
        const listItem = $(this).parent().prev();
        const commentId = listItem.data('comment-id');
        const updatedComment = $(this).prev().val();

        $.ajax({
            type: 'PATCH',
            url: `/api/posts/${receivedData}/comments/${commentId}`,
            contentType: 'application/json',
            data: JSON.stringify({
                "content": updatedComment
            }),
            async: true,
            success: function (response) {
                alert(response.message);
                location.reload();
            },
            error(error, status, response) {
                alert(error.responseJSON.message);
            }
        });
    });

    $(document).on('click', '.deleteComment', function () {
        const listItem = $(this).closest('.list-group-item');
        const commentId = listItem.data('comment-id');

        $.ajax({
            type: 'DELETE',
            url: `/api/posts/${receivedData}/comments/${commentId}`,
            contentType: 'application/json',
            success: function (response) {
                alert(response.message);
                location.reload();
            },
            error(error, status, response) {
                alert(error.responseJSON.message);
            }
        });
    });
})


function getPost(id) {
    $.ajax({
        type: 'GET',
        url: `/api/posts/${id}`,
        contentType: 'application/json',
        success: function (response) {
            let title = response['title'];
            let image = response['image'];
            let contents = response['contents'];
            let deadline = response['deadline'];
            let job = response['job'];
            let career = response['career'];
            let skill = response['skill'];
            let empType = response['empType'];
            let education = response['education'];
            let company = response['company'];
            let likeCount = response['likes'];
            let commentList = response['comments'];

            let companyName = company['companyName'];
            let email = company['email'];
            let phone = company['phone'];
            let location = company['location'];
            let industry = company['industry'];


            $('title').text(title);
            $('#title').text(title);
            $('#image').attr("src", image);
            $('#contents').text(contents);
            $('#job').text(job);
            $('#career').text(career);
            $('#skill').text(skill);
            $('#deadline').text(deadline);
            $('#empType').text(empType);
            $('#education').text(education);
            $('#count').text(likeCount);

            $('#companyName').text(companyName);
            $('#email').text(email);
            $('#phone').text(phone);
            $('#industry').text(industry);
            $('#location').text(location);

            if ($('#owner').val() === company['loginId']) {
                let html = `<div>
                                    <button class="btn btn-primary" onclick="location.href='/posts/${response["id"]}/edit'">수정</button>
                                </div>
                                `
                $('#title').append(html);
            }

            if (commentList.length === 0) {
                $("#commentTitle").text("댓글이 없습니다.");
            } else {
                for (i = 0; i < commentList.length; i++) {
                    let comment = commentList[i].comment;
                    let name = commentList[i].nickname;
                    let userId = commentList[i].id;
                    $("#commentTitle").text("댓글 " + commentList.length + "개");
                    let tempHtml = make_commentHtml(comment, name, userId);
                    $('#commentList').append(tempHtml);
                }
            }
        },
        error(error, status, request) {
            console.log(error);
        }
    });
}

function getMyLike(id) {
    $.ajax({
        type: 'GET',
        url: `/api/getMyLike/${id}`,
        contentType: 'application/json',
        success: function (response) {
            if (response) {
                $("#checkbox").prop("checked", true);
            } else {
                $("#checkbox").prop("checked", false);
            }
        },
        error(error) {
            console.log(error);
        }
    });
}

function like(id) {
    $.ajax({
        type: 'POST',
        url: `/api/like/${id}`,
        contentType: 'application/json',
        success: function (response) {
            if (response.status === 201) {
                $("#checkbox").prop("checked", true);
            } else if (response.status === 200) {
                $("#checkbox").prop("checked", false);
            }
            alert(response.message);
            getPost(id);
        },
        error(error, status, response) {
            $("#checkbox").prop("checked", false);
            alert(error.responseJSON.message);
        }
    });
}


function make_commentHtml(comment, nickname, commentId) {
    return `<ul class="list-group">
                  <li class="list-group-item" data-comment-id="${commentId}">${comment}(작성자 : ${nickname})
                      <button type="button" class="btn btn-outline-primary updateComment">수정</button>
                      <button type="button" class="btn btn-outline-danger deleteComment">삭제</button>
                  </li>
            </ul>`;
}
