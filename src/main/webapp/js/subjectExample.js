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
            commentary: document.getElementById('textarea').value
        },

        success: function () {
            var article = document.createElement("ARTICLE");
            var div = document.createElement("DIV");


        }
    });
    /*
     var strVar = "<article id=\"commentary${loop.count}\" class=\"row\"> <div class=\"col-lg-2 col-md-2 col-sm-2 hidden-xs\">";
     strVar += "        <figure class=\"thumbnail\">";
     strVar += "";
     strVar += "        <img src=\"\/userPostPhoto?<%=Constants.USER_EMAIL_PARAM%>=${commentary.author.email}\" onerror=\"if (this.src != 'images\/avatar.jpg') this.src = 'images\/avatar.jpg';\"class=\"img-responsive avatar img-circle\" alt=\"avatar\">";
     strVar += "";
     strVar += "        <figcaption class=\"text-center\"><c:out value=\"${commentary.author.firstName}\"\/><\/figcaption>";
     strVar += "        <\/figure>";
     strVar += "        <\/div>";
     strVar += "        <div class=\"col-md-9 col-sm-9 col-xs-9\">";
     strVar += "        <div class=\"panel panel-default arrow left\">";
     strVar += "        <div class=\"panel-body\">";
     strVar += "        <header class=\"text-left\"><\/header>";
     strVar += "        <div class=\"comment-user\"><i class=\"glyphicon glyphicon-user\"><\/i><c:out value=\"${commentary.author.email}\"\/><\/div>";
     strVar += "        <abbr class=\"timeago\" title=\"<c:out value=\"${commentary.getFormatDate2()}\"\/>\"><\/abbr>";
     strVar += "    <\/header>";
     strVar += "    <c:set var=\"remoteUser\" value=\"<%=request.getRemoteUser()%>\"\/>";
     strVar += "        <c:if test=\"${commentary.author.email == remoteUser}\">";
     strVar += "";
     strVar += "        <button type=\"submit\" class=\"btn pull-right remove\" onclick=\"removeComment('${commentary.id}','${commentary.subject.subjectName}','commentary${loop.count}')\"><i class=\"glyphicon glyphicon-remove\"><\/i><\/button>";
     strVar += "        <\/c:if>";
     strVar += "<div class=\"comment-post\">";
     strVar += "        <p>";
     strVar += "        <c:out value=\"${commentary.commentary}\"\/>";
     strVar += "        <\/p>";
     strVar += "        <\/div>";
     strVar += "        <\/div>";
     strVar += "        <\/div>";
     strVar += "        <\/div>";
     strVar += "        <\/article>";
     */

/*<article id="commentary${loop.count}" class="row">
        <div class="col-lg-2 col-md-2 col-sm-2 hidden-xs">
        <figure class="thumbnail">

        <img src="/userPostPhoto?<%=Constants.USER_EMAIL_PARAM%>=${commentary.author.email}" onerror="if (this.src != 'images/avatar.jpg') this.src = 'images/avatar.jpg';"class="img-responsive avatar img-circle" alt="avatar">

        <figcaption class="text-center"><c:out value="${commentary.author.firstName}"/></figcaption>
        </figure>
        </div>
        <div class="col-md-9 col-sm-9 col-xs-9">
        <div class="panel panel-default arrow left">
        <div class="panel-body">
        <header class="text-left"></header>
        <div class="comment-user"><i class="glyphicon glyphicon-user"></i><c:out value="${commentary.author.email}"/></div>
        <abbr class="timeago" title="<c:out value="${commentary.getFormatDate2()}"/>"></abbr>
    </header>
    <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
        <c:if test="${commentary.author.email == remoteUser}">

        <button type="submit" class="btn pull-right remove" onclick="removeComment('${commentary.id}','${commentary.subject.subjectName}','commentary${loop.count}')"><i class="glyphicon glyphicon-remove"></i></button>
        </c:if>
<div class="comment-post">
        <p>
        <c:out value="${commentary.commentary}"/>
        </p>
        </div>
        </div>
        </div>
        </div>
        </article>*/
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
