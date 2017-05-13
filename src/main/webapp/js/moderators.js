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

function eliminateAplication(userEmail, subjectName){
    $.ajax({
        type:'post',
        url:'/deletePostulant',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        }
    })
}

function acceptAplication(userEmail, subjectName){
    $.ajax({
        type:'post',
        url:'/acceptPostulation',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        }
    })
}

function denyAplication(userEmail, subjectName){
    $.ajax({
        type:'post',
        url:'/denyPostulation',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        }
    })
}
