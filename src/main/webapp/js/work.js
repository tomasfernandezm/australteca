/**
 * Created by tomasforman on 2/6/17.
 */

var simplemde;

$('#myPill').click(function(e) {
    e.preventDefault();
    $(this).tab('show');
});

$("ul.nav-pills > li > a").on("shown.bs.tab", function(e) {
    var id = $(e.target).attr("href").substr(1);
    window.location.hash = id;
});

var hash = window.location.hash;
$('#myPill').find('a[href="' + hash + '"]').tab('show');


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

    simplemde = loadSimpleMDE("descriptionTextarea");
});

$(document).on("click", "#modalButton", function () {
    var pubId = $(this).data('id');
    document.getElementById('publicationModalID').value = pubId;
});

function addPublication(){

    var publicationName = document.getElementById('nameInput').value;
    var publicationDescription = simplemde.value();

    var selectedRole = document.getElementById('publicationRole');
    var roleText = selectedRole.options[selectedRole.selectedIndex].text;

    $.ajax({
        type:'post',
        url: '/servlet/addPublication',
        dataType: 'JSON',
        data:{
            publicationName: publicationName,
            publicationDescription: publicationDescription,
            publicationRole: roleText
        },
        success: function () {
            document.getElementById('successBox').style.display='block';
        }
    });
}

function changeFavorite(publicationID, checkBoxId) {
    $.ajax({
        type:'post',
        url:'/servlet/favoritePublication',
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
        url: '/servlet/removePublication',
        dataType: 'JSON',
        data:{
            publicationID: publicationID
        },
        success: function(){
            document.getElementById(containerID).remove();
        }
    });
}

function sendPublicationPetition(emailTopic, emailDescription, publicationID){
    $.ajax({
        type:'post',
        url: '/servlet/publicationPetition',
        dataType: 'JSON',
        data:{
            emailTopic: emailTopic,
            emailDescription: emailDescription,
            publicationID: publicationID
        },
        success: function(){

        }
    });
}

function createPublicationDiv(name, description, id, author){

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
    secondDiv.appendChild(thirdDiv);

    var sixthDiv = document.createElement("div");
    sixthDiv.className = "pull-right";
    var button = document.createElement("button");
    button.type="button";
    button.id="deleteButton";
    button.className="btn btn-default btnremovework pull-right";
    button.setAttribute("onclick", "removePublication('" + id + "','" + name + id + "')");
    var i = document.createElement("i");
    i.className = "glyphicon glyphicon-trash";
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
    // descripcionHeader.innerText = "Descripcion: ";
    var e = document.createElement("div");
    e.innerHTML = simplemde.options.previewRender(description);
    descripcionHeader.appendChild(e);
    thirdTextDiv.appendChild(descripcionHeader);
    secondTextDiv.appendChild(thirdTextDiv);

    var fourthTextDiv = document.createElement("div");
    fourthTextDiv.className = "panel-body showMore";
    fourthTextDiv.id="show" + name;
    fourthTextDiv.setAttribute("hidden","");

    var button2 = document.createElement("button");
    button2.type = "button";
    button2.id = "showhide"+ id + 5;
    button2.className="btn btn-default pull-right";
    buttonItag = document.createElement("i");
    buttonItag.innerText="Mostrar mas";
    button2.appendChild(buttonItag);
    button2.setAttribute("onclick", "show('#show" + name + "','showhide" +id + 5 +"')");

    secondTextDiv.appendChild(fourthTextDiv);
    secondTextDiv.appendChild(button2);
    firstTextDiv.appendChild(secondTextDiv);

    secondDiv.appendChild(firstTextDiv);
    firstDiv.appendChild(secondDiv);

    return firstDiv;
}

function loadSimpleMDE(textareaID){

    return new SimpleMDE({
        autofocus: false,
        autosave: {
            enabled: false
        },
        element: document.getElementById(textareaID),
        forceSync: true,
        hideIcons: ["guide", "heading"],
        insertTexts: {
            horizontalRule: ["", "\n\n-----\n\n"],
            image: ["![](http://", ")"],
            link: ["[", "](http://)"],
            table: ["", "\n\n| Column 1 | Column 2 | Column 3 |\n| -------- | -------- | -------- |\n| Text     | Text      | Text     |\n\n"]
        },
        placeholder: "Dale una descripcion ...",
        shortcuts: {
            drawTable: "Cmd-Alt-T"
        },
        showIcons: ["code", "table"],
        spellChecker: false,
        status: false,
        status: ["autosave", "lines", "words", "cursor"], // Optional usage
        status: ["autosave", "lines", "words", "cursor", {
            className: "keystrokes",
            defaultValue: function(el) {
                this.keystrokes = 0;
                el.innerHTML = "0 Keystrokes";
            },
            onUpdate: function(el) {
                el.innerHTML = ++this.keystrokes + " Keystrokes";
            }
        }], // Another optional usage, with a custom status bar item that counts keystrokes
        styleSelectedText: false,
        tabSize: 4,
    });
}