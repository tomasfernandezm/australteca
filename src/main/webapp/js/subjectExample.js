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
    });
}

$(document).ready(getUserRating());

function removeNote(noteID, subjectName){
    $.ajax({
        type: 'post',
        url:'/noteDelete',
        dataType: 'JSON',
        data:{
            noteID: noteID,
            subjectName: subjectName
        },

        success: function(){
            $(this).closest('tr').remove();
        }
    });
}

function addProfessorToSubject(professorID, subjectName){
    $.ajax({
        type:'post',
        url:'/addProfessorToSubject',
        dataType: 'JSON',
        data:{
            professorID: professorID,
            subjectName: subjectName
        },

        success: function(jsonObject){
            document.getElementById('idtab2default').append('<div class="row" id="${professorWrapper.professor.email}"> <div class="boxContent"> <div class="col col-md-4"> <h3>jsonObject[0]</h3> <p>jsonObject[1]</p> <p>jsonObject[2]</p> </div> </div> </div>');

            var button = document.getElementById(jsonObject[1]+'button');
            button.onclick = removeProfessorFromSubject(professorID, subjectName);
            button.innerText = 'Eliminar';
            button.class = 'btn btn-danger';
        }
    });
}

function removeProfessorFromSubject(professorID, subjectName){
    $.ajax({
        type:'post',
        url:'/removeProfessorFromSubject',
        dataType: 'JSON',
        data:{
            professorID: professorID,
            subjectName: subjectName
        },

        success: function(jsonObject){
            document.getElementById(jsonObject).remove();
            var button = document.getElementById(jsonObject+'button');
            button.onclick = addProfessorToSubject(professorID, subjectName);
            button.innerText = 'Agregar';
            button.class = 'btn btn-success';
        }
    });
}

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
    });
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
    });
}

function addComment(subjectName, userEmail){
    $.ajax({
        type:'post',
        url: '/subjectAddCommentary',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail,
            commentaryText: document.getElementById('textarea').value
        },

        success: function () {
            var article = document.createElement("ARTICLE");
            var div = document.createElement("DIV");


        }
    });
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
    });
}
