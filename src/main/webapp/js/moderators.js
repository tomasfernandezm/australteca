/**
 * Created by tomasforman on 9/5/17.
 */
$(document).ready(function () {
    $("select").change(function () {
        var mode = $('#mode').val(),
            $postulant = $('#postulant-table'),
            $approved = $('#approved-table');

        if (mode === "postulant") {
            $postulant.show();
            $approved.hide();


        } else {
            $approved.show();
            $postulant.hide();
        }


    }).change();
});

function addModeratorToTable(userEmail, subjectName){
    var table = document.getElementById("approved-table");
    var x = document.getElementById("approved-table").rows.length;
    var row = table.insertRow();
    row.id = 'accepted' + x;
    var cellEmail = row.insertCell(0);
    var cellSubject = row.insertCell(1);
    var cellButton2 = row.insertCell(2);
    cellEmail.innerHTML = userEmail;
    cellSubject.innerHTML = subjectName;
    cellButton2.innerHTML = '<button type="submit" class="btn btn-danger" onclick="eliminateAplication(' +'\'' + userEmail + '\''+',\'' + subjectName + '\''+',\'accepted' + x +'\')">Eliminar</button>';

}

function eliminateAplication(userEmail, subjectName, rowID){
    $.ajax({
        type:'post',
        url:'/deletePostulant',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        },
        success: function(){
            document.getElementById(rowID).remove();
        }
    })
}

function acceptAplication(userEmail, subjectName, rowID){
    $.ajax({
        type:'post',
        url:'/acceptPostulation',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        },
        success: function(){
            document.getElementById(rowID).remove();
            addModeratorToTable(userEmail, subjectName);
        }
    })
}

function denyAplication(userEmail, subjectName, rowID){
    $.ajax({
        type:'post',
        url:'/denyPostulation',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        },
        success: function(){
            document.getElementById(rowID).remove();
        }
    })
}
