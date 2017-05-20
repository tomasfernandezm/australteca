/**
 * Created by tomasforman on 9/5/17.
 */
$(document).ready(function () {
    $("select").change(function () {
        var mode = $('#mode').val(),
            $postulant = $('#postulant-table'),
            $approved = $('#approved-table');

        if (mode === "postulant") {
            showAplicants();
            $postulant.show();
            $approved.hide();


        } else {
            showModerators();
            $approved.show();
            $postulant.hide();
        }


    }).change();
});

function showModerators(){

}

function showAplicants(){

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
