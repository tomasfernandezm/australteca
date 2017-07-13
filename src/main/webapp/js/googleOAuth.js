/**
 * Created by tomi on 28/06/17.
 */

function onSignIn(googleUser) {
    var id_token = googleUser.getAuthResponse().id_token;
    var profile = googleUser.getBasicProfile();

    $.ajax({
        type:'post',
        url:"/googleOAuth",
        datatype:JSON,
        data:{
            id_token: id_token,
            name: profile.getName(),
            email: profile.getEmail()
        },
        success: function(){

        }
    })
}

function signOut() {

    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function(){
        console.log('User signed out');
    });
}
