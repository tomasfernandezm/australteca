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

function elminateAplication(subjectName, userEmail){
    $.ajax({
        type:'post',
        url:'/mandaloAalgúnLado',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        }
    })
}

function acceptAplication(subjectName, userEmail){
    $.ajax({
        type:'post',
        url:'/mandaloAalgúnLado',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        }
    })
}

function denyAplication(subjectName, userEmail){
    $.ajax({
        type:'post',
        url:'/mandaloAalgúnLado',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            userEmail: userEmail
        }
    })
}
