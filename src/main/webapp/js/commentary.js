/**
 * Created by tomi on 11/06/17.
 */

function createComment(userEmail, commentary, date, commentaryID, subjectName){
    var div1 = document.createElement("div");
    div1.className = "row";
    var div2 = document.createElement("div");
    div2.id ="newCommentary" + commentaryID;
    div2.appendChild(createImageDiv(userEmail));
    div2.appendChild(createTextDiv(userEmail, date, commentaryID, subjectName, commentary));
    div1.appendChild(div2);
    return div1;
}

function createImageDiv(userEmail){
    var div1 = document.createElement("div");
    div1.className="col-lg-2 col-md-2 col-sm-2 col-lg-offset-1  hidden-xs";
    var div2 = document.createElement("div");
    div2.className="thumbnail";
    var img = document.createElement("img");
    img.src = "/servlet/userPostPhoto?userEmail="+userEmail;
    img.setAttribute("onerror", "if (this.src != '/images/avatar.jpg') this.src = '/images/avatar.jpg';");
    img.className="img-responsive user-photo";
    div2.appendChild(img);
    div1.appendChild(div2);
    return div1;
}

function createTextDiv(userEmail, date, commentaryID, subjectName, commentary){
    var div1 = document.createElement("div");
    div1.className = "col-lg-8 col-md-10 col-sm-10 col-xs-12";
    var div2 = document.createElement("div");
    div2.className = "panel panel-default";
    var div3 = document.createElement("div");
    div3.className = "panel-body";
    div3.innerText = commentary;
    div2.appendChild(makeHeader(userEmail,date, commentaryID, subjectName));
    div2.appendChild(div3);
    div1.appendChild(div2);
    return div1;
}

function makeHeader(userEmail, date, commentaryID, subjectName){
    var div1 = document.createElement("div");
    div1.className = "panel-heading";
    var strong = document.createElement("strong");
    strong.innerText = userEmail;
    var span = document.createElement("span");
    span.className = "text-muted";
    var abbr = document.createElement("abbr");
    abbr.className = "timeago";
    abbr.title = date;
    span.appendChild(abbr);
    div1.appendChild(strong);
    div1.appendChild(span);
    div1.appendChild(makeButton(commentaryID, subjectName, "newComment"+commentaryID));
    return div1;
}

function makeButton(commentaryID, subjectName, articleID){
    var button = document.createElement("button");
    button.type = "submit";
    button.className = "btn pull-right remove-comment";
    button.setAttribute("onclick", "removeComment('" + commentaryID + "','" + subjectName + "','" + articleID+ "')");
    var i = document.createElement("i");
    i.className = "glyphicon glyphicon-trash";
    button.appendChild(i);
    return button;
}