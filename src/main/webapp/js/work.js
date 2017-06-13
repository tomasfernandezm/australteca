/**
 * Created by tomasforman on 2/6/17.
 */


function show(divId, buttonId) {
    $(divId).toggle();
        var button = document.getElementById(buttonId);
        button.innerHTML = "Mostrar menos";
}

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

    var publicationName = document.getElementById('nameInput').value;
    var publicationDescription = document.getElementById('descriptionTextarea').value;
    var publicationRequirements = document.getElementById('requisitesTextarea').value;
    var publicationTasks = document.getElementById('tasksTextarea').value;

    var selectedRole = document.getElementById('publicationRole');
    var roleText = selectedRole.options[selectedRole.selectedIndex].text;

    $.ajax({
        type:'post',
        url: '/addPublication',
        dataType: 'JSON',
        data:{
            publicationName: publicationName,
            publicationDescription: publicationDescription,
            publicationRequirements: publicationRequirements,
            publicationTasks: publicationTasks,
            publicationRole: roleText
        },
        success: function () {
            var div;
            if(roleText === 'Trabajo') div = document.getElementById("tab1default");
            else div = document.getElementById("tab2default");

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
            document.getElementById(containerID).remove();
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

function createPublicationDiv(name, description, requierements, tasks, id){
    var firstDiv = document.createElement("div");
    firstDiv.id = name + id;
    firstDiv.className = "bs-calltoaction bs-calltoaction-work";
    var secondDiv = document.createElement("div");
    secondDiv.className = "row";
    var thirdDiv = document.createElement("div");
    thirdDiv.className = "panel-heading";
    var fourthDiv = document.createElement("div");
    fourthDiv.className = "row";

    var fifthDiv = document.createElement("div");
    fifthDiv.className = "pull-left";
    var h1 = document.createElement("h1");
    h1.className ="cta-title";
    h1.innerText = name;
    fifthDiv.appendChild(h1);
    fourthDiv.appendChild(fifthDiv);

    var sixthDiv = document.createElement("div");
    sixthDiv.className = "pull-right";
    var button = document.createElement("button");
    button.type="button";
    button.id="deleteButton";
    button.className="btn btn-default btn removework pull-right";
    button.setAttribute("click", "removePublication('" + id + "'" + name + id + "')");
    var i = document.createElement("i");
    i.className = "glyphicon glyphicon-remove";
    button.appendChild(i);
    sixthDiv.appendChild(button);
    fourthDiv.appendChild(sixthDiv);

    thirdDiv.appendChild(fourthDiv);

    var firstTextDiv = document.createElement("div");
    firstTextDiv.className = "panel-body";
    var secondTextDiv = document.createElement("div");
    secondTextDiv.className = "col col-md-12 discussionBox";

    var thirdTextDiv = document.createElement("div");
    thirdTextDiv.className = "panel-heading";
    var descripcionHeader = document.createElement("p");
    descripcionHeader.innerText = "Descripcion: " + description;
    thirdTextDiv.appendChild(descripcionHeader);
    secondTextDiv.appendChild(thirdTextDiv);

    var fourthTextDiv = document.createElement("div");
    fourthTextDiv.className = "panel-body showMore";
    fourthTextDiv.id="show" + name + id;
    var tareasPrincipalesHeader = document.createElement("h4");
    tareasPrincipalesHeader.innerText = "Sus tareas principales seran:";
    var tareasPrincipalesP = document.createElement("p");
    tareasPrincipalesP.innerText = tasks;
    var requisitosHeader = document.createElement("h4");
    requisitosHeader.innerText ="Seran requisitos excluyentes";
    var requisitosP = document.createElement("p");
    requisitosP.innerText = requierements;
    var button1 = document.createElement("button");
    button1.className = "btn btn-primary pull-right";
    button1.setAttribute("click", "modalBox(document.getElementById('sendRequest'))");
    button1.innerText="Enviar peticion";
    fourthTextDiv.appendChild(tareasPrincipalesHeader);
    fourthTextDiv.appendChild(tareasPrincipalesP);
    fourthTextDiv.appendChild(requisitosHeader);
    fourthTextDiv.appendChild(requisitosP);
    fourthTextDiv.appendChild(button1);

    var button2 = document.createElement("button");
    button2.type = "button";
    button2.id = "showhide";
    button2.className="btn btn-default pull-right";
    button2.setAttribute("click", "show(#show" + name + id);

    secondTextDiv.appendChild(fourthTextDiv);
    secondTextDiv.appendChild(button2);
    firstTextDiv.appendChild(secondTextDiv);

    secondDiv.appendChild(firstTextDiv);
    firstDiv.appendChild(secondDiv);

    return firstDiv;
}

