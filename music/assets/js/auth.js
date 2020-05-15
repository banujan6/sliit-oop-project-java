let isLoggedIn = () => {

    try {
        let data = JSON.parse(atob(localStorage.getItem('_data')));
        return data.name && data.email && data.role && data.id;
    } catch(e) {

    }
};

let getUserInfo = () => {
    try {
        return JSON.parse(atob(localStorage.getItem('_data')));
    } catch (e) {
        
    }
};

let requireLogin = () => {
    if ( !isLoggedIn() ) {
        window.location = '/login.html';
    }
};

let requireLogout = () => {
    if ( isLoggedIn() ) {
        window.location = '/index.html';
    }
};

if ( isLoggedIn() ) {
    $('#link_login').hide();
    $('#link_register').hide();
} else {
    $('#link_logout').hide();
    $('#link_dashboard').hide();
}

$('#link_logout').click(function () {
    localStorage.clear();
    location.reload();
});