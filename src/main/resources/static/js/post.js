$(document).ready(function () {
    const receivedData = location.href.split('?')[1];
    let id = Number(receivedData); // data
    getPost(id);
    getMyLike(id);
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

            if(commentList.length === 0){
                $("#commentTitle").text("댓글이 없습니다.");
            }else{
                for (i = 0; i < commentList.length; i++) {
                    let comment = commentList[i];
                    $("#commentTitle").text("댓글 " + commentList.length + "개");
                    let tempHtml = make_commentHtml(comment);
                    $('#commentList').append(tempHtml);
                }
            }
        },
        error(error, status, request) {
            console.log(error);
        }
    });
}

function getMyLike(id){
    $.ajax({
        type: 'GET',
        url: `/api/getMyLike/${id}`,
        contentType: 'application/json',
        success: function (response) {
            if(response){
                $("#checkbox").prop("checked", true);
            }else{
                $("#checkbox").prop("checked", false);
            }
        },
        error(error) {
            console.log(error);
        }
    });
}

function like(id){
    $.ajax({
        type: 'POST',
        url: `/api/like/${id}`,
        contentType: 'application/json',
        success: function (response) {
            if(response.status === 201){
                $("#checkbox").prop("checked", true);
            }else if(response.status === 200){
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


function make_commentHtml(comment) {
    return `<ul class="list-group">
                  <li class="list-group-item">${comment}</li>
                </ul>`;
}