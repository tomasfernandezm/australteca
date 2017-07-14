

function onSignIn(googleUser) {
    var id_token = googleUser.getAuthResponse().id_token;
    var profile = googleUser.getBasicProfile();

    $.ajax({
        type:'post',
        url:'/googleOAuth',
        datatype:JSON,
        data:{
            id_token: id_token,
            name: profile.getName(),
            email: profile.getEmail()
        },
        success: function(jsonObject){
            login(jsonObject[0], jsonObject[1]);
        }
    });
}

function login(u, p){

    var form = document.createElement("form");
    var element1 = document.createElement("input");
    var element2 = document.createElement("input");

    form.method = "POST";
    form.action = "/j_security_check";

    element1.value=u;
    element1.name="j_username";
    form.appendChild(element1);

    element2.value=p;
    element2.name="j_password";
    form.appendChild(element2);

    document.body.appendChild(form);

    form.submit();
}

function signOut() {

    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function(){
        console.log('User signed out');
    });
}

window.onbeforeunload = function(){
    gapi.auth2.getAuthInstance().signOut();
};
