//const host = 'http://' + window.location.host;

// 화면 시작하자마자
$(document).ready(function () {
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
                console.log(res);
                $('#nickname').text(res['nickname']);
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