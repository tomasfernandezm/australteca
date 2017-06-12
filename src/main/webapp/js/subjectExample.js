/**
 * Created by tomi on 03/05/17.
 */

function getUserRating(){
    $.ajax({
        type:'get',
        url:'/userGetSubjectRating',
        dataType: 'JSON',
        data:{
            subjectName: document.getElementById('subjectName-h2').innerHTML
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

        success: function(professorJSON){
            var div = document.getElementById('tab2default');
            var profDiv = document.createElement('div');
            profDiv.id = professorJSON[1];
            div.appendChild(profDiv);
            var profChildDiv = document.createElement('div');
            profChildDiv.className = "col col-md-4 ProfessorBoxContent";
            profDiv.appendChild(profChildDiv);

            var h3 = document.createElement('h3');
            h3.innerHTML = professorJSON[0];
            var pEmail = document.createElement('p');
            pEmail.innerHTML = professorJSON[1];
            var pInformation = document.createElement('p');
            pInformation.innerHTML = professorJSON[2];
            profChildDiv.appendChild(h3);
            profChildDiv.appendChild(pEmail);
            profChildDiv.appendChild(pInformation);
            var button = document.getElementById(professorJSON[1]+'button');
            button.innerHTML = 'Eliminar';
            button.className = "btn btn-danger";
            button.setAttribute("onclick","removeProfessorFromSubject("+professorID+", '"+subjectName+"')" );
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
            button.innerHTML = 'Agregar';
            button.className = "btn btn-success";
            button.setAttribute("onclick","addProfessorToSubject("+professorID+", '"+subjectName+"')" );
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
        },
        success: function(){
            var button = document.getElementById('addModeratorButton');
            button.innerHTML = "Has aplicado";
            button.disabled = true;
        }
    });
}

function addComment(subjectName, userEmail){
    var commentaryText = document.getElementById('commentTextarea').value;
    $.ajax({
        type:'post',
        url: '/subjectAddCommentary',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail,
            commentaryText: commentaryText
        },

        success: function (jsonObject) {
           var div = document.getElementById("comments_container");
           div.appendChild(createComment(userEmail, jsonObject[0],commentaryText, jsonObject[1], jsonObject[2],subjectName));
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
