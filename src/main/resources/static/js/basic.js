//const host = 'http://' + window.location.host;

// 화면 시작하자마자
$(document).ready(function () {
    click_total();
    getPosts();
    authorizationCheck();
})


function authorizationCheck() {
    const auth = getToken();

    if (auth !== undefined && auth !== '') {
        // 회원
        $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
            jqXHR.setRequestHeader('Authorization', auth);
        });
        //로그인
        $('#sign-up-btn').hide();
        $('#sign-in-btn').hide();
        $('#logout-btn').show();
        $('#mypage').show();


        //로그인한 회원 정보
        $.ajax({
            type: 'GET',
            url: `/api/users`,
            contentType: 'application/json',
        })
            .done(function (res, status, xhr) {
                $('#nickname').text(res['nickname']);
                let role = res['role'];
                if (role == "COMPANY") {
                    $('#post-btn').show();
                    $('#my_tap').show();
                    getMyPosts();
                } else {
                    $('#post-btn').hide();
                    $('#my_tap').hide();
                }


            })
            .fail(function (jqXHR, textStatus) {
                logout();
            });
    } else {
        // 비회원
        $('#sign-up-btn').show();
        $('#sign-in-btn').show();
        $('#logout-btn').hide();
        $('#mypage').hide();
        $('#my_tap').hide();
        $('#post-btn').hide();
    }
}

function logout() {
    $.ajax({
        type: 'GET',
        url: `/api/users/logout`,
        contentType: 'application/json',
        success: function (response) {
            Cookies.remove('Authorization', {path: '/'});
            window.location.reload();
        },
        error(error, status, request) {
            console.log(error['responseJSON']['message']);
        }
    })


}

function getToken() {
    let auth = Cookies.get('Authorization');

    if (auth === undefined) {
        return '';
    }

    return auth;
}

function make_html(post) {
    let id = post['id'];
    let title = post['title'];
    let image = post['image'];
    let deadline = post['deadline']
    let company = post['company'];
    let companyName = company['companyName']

    let career = post[`career`];
    let skill = post[`skill`];
    let empType = post[`empType`];
    let education = post[`education`];

    let tempHtml = `\<div class="col" onclick="location.href='/post-page?'+${id}">
                <div class="card border-info h-100">
                    <img src="${image}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${title}</h5>
                        <p class="card-text">${companyName}
                        <br>직군 : ${career}<br>${empType} / ${education} <br>스킬 : ${skill}
                        </p>
                    </div>
                    <div class="card-footer">                   
                        <small class="text-body-secondary">마감일 : ${deadline}</small>
                    </div>
                </div>
            </div></a>`;
    return tempHtml;
}

function getPosts() {
    $.ajax({
        type: 'GET',
        url: '/api/posts',
        contentType: 'application/json',
        success: function (response) {
            console.log(response);

            for (i = 0; i < response.length; i++) {
                let post = response[i];
                let tempHtml = make_html(post);
                $('#total_cards').append(tempHtml);
            }
        },
        error(error, status, request) {
            console.log(error);
        }
    });
}

function getMyPosts() {
    $.ajax({
        type: 'GET',
        url: '/api/myposts',
        contentType: 'application/json',
        success: function (response) {
            for (i = 0; i < response.length; i++) {
                let post = response[i];
                let tempHtml = make_html(post);
                $('#my_cards').append(tempHtml);
            }
        },
        error(error, status, request) {
            console.log(error);
        }
    });
}

function click_total() {
    $('#total_tab').addClass('active');
    $('#my_tap').removeClass('active');
    $('#total_body').show();
    $('#mypost_body').hide();
}

function click_mytab() {
    $('#total_tab').removeClass('active');
    $('#my_tap').addClass('active');
    $('#total_body').hide();
    $('#mypost_body').show();
}