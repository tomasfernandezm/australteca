/**
 * Created by tomi on 03/05/17.
 */

function getUserRating(){
    $.ajax({
        type:'get',
        url:'/userGetSubjectRating',
        dataType: 'JSON',
        data:{
            subjectName: document.getElementById('subjectName-h2').innerHTML,
        },

        success: function(jsonObject){
            switch (jsonObject){
                case 1:
                    document.getElementById('rating1').checked = true;
                    break;
                case 2:
                    document.getElementById('rating2').checked = true;
                    break;
                case 3:
                    document.getElementById('rating3').checked = true;
                    break;
                case 4:
                    document.getElementById('rating4').checked = true;
                    break;
                case 5:
                    document.getElementById('rating5').checked = true;
                    break;
                default:
                    break;
            }
        }
    })
}

$(document).ready(getUserRating());

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



