/**
 * Created by tomasforman on 2/6/17.
 */

$(document).ready(function(){
    $("#showandhide").click(function(){
        $("#show").toggle("show");
    });
});

$(document).ready(function(){
    $('[data-toggle="popover"]').popover({
        placement : 'left',
        html : true,
        title : 'Seguro deseas enviar?',
        content : '<button id="addPublicationButton" type="submit" class="btn btn-primary" onclick="addPublication()">Aceptar</button>'
    });
    $(document).on("click", ".popover .close" , function(){
        $(this).parents(".popover").popover('hide');
    });
});

function addPublication(){

    var selectedRole = document.getElementById('publicationRole');
    var roleText = selectedRole.options[selectedRole.selectedIndex].text;

    $.ajax({
        type:'post',
        url: '/addPublication',
        dataType: 'JSON',
        data:{
            publicationName: document.getElementById('nameInput').value,
            publicationDescription: document.getElementById('descriptionTextarea').value,
            publicationRequirements: document.getElementById('requisitesTextarea').value,
            publicationTasks: document.getElementById('tasksTextarea').value,
            publicationRole: roleText
        },
        success: function () {

        }
    });
}

function changeFavorite(publicationID, checkBoxId) {
    $.ajax({
        type:'post',
        url:'/favoritePublication',
        dataType: 'JSON',
        data:{
            publicationID: publicationID,
            status: document.getElementById(checkBoxId).checked
        }
    })
}

function removePublication(publicationID, containerID){
    $.ajax({
        type:'post',
        url: '/removePublication',
        dataType: 'JSON',
        data:{
            publicationID: publicationID
        },
        success: function(){
            document.getElementById(containerID ).remove();
        }
    });
}

function sendPublicationPetition(publicationID){
    $.ajax({
        type:'post',
        url: '/publicationPetition',
        dataType: 'JSON',
        data:{
            publicationID: publicationID
        },
        success: function(){

        }
    });
}

