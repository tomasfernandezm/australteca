/**
 * Created by tomi on 03/05/17.
 */

function changeRating(subjectName, button_value) {
    $.ajax({
        type:'post',
        url:'/subjectChangeRatingAjax',
        dataType: 'JSON',
        data:{
                rating: button_value,
                subject: subjectName
        },

        success: function(jsonObject){
            document.getElementById("subject_score_h2").innerHTML = JSON.parse(jsonObject);
        }
    })
}

function addModeratorPostulation(userEmail, subjectName){
    $.ajax({
        type:'post',
        url: '/addPostulant',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        }
    })
}

function addComment(subjectName, email){
    $.ajax({
        type:'post',
        url: '/subjectAddCommentary',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail,
            commentary: document.getElementById('textarea').value
        },

        success: function () {
            // hacer el append
        }
    })
}

function removeComment(commentaryID, subjectName, rowID){
    $.ajax({
        type:'post',
        url:'/deleteCommentary',
        dataType: 'JSON',
        data:{
            commentaryID: commentaryID,
            subjectName: subjectName
        },
        success: function(){
            document.getElementById(rowID).remove();
        }
    })
}



