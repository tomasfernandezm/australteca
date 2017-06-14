/**
 * Created by tomi on 11/06/17.
 */

function createComment(userEmail, firstName, commentary, date, commentaryID, subjectName){
    var article = document.createElement("article");
    article.className = "row";
    article.id ="newCommentary" + commentaryID;
    article.appendChild(createImageDiv(userEmail, firstName));
    article.appendChild(createTextDiv(userEmail, date, commentaryID, subjectName, commentary));
    return article;
}

function createImageDiv(userEmail, firstName){

    var div = document.createElement("div");
    div.className="col-lg-2 col-md-2 col-sm-2 hidden-xs";


    var figure = document.createElement("figure");
    figure.className = "thumbnail";
    var img = document.createElement("img");
    img.src = "/userPostPhoto?userEmail="+userEmail;
    img.setAttribute("onerror", "if (this.src != 'images/avatar.jpg') this.src = 'images/avatar.jpg';");
    img.className="img-responsive avatar img-circle";
    img.alt = "avatar"; /* check correct attribute */
    var figcaption = document.createElement("figcaption");
    figcaption.className="text-center";
    figcaption.innerText= firstName;
    figure.appendChild(img);
    figure.appendChild(figcaption);
    div.appendChild(figure);

    return div;
}

function createTextDiv(userEmail, date, commentaryID, subjectName, commentary){

    var firstDiv = document.createElement("div");
    firstDiv.className = "col-md-9 col-sm-9 col-xs-9";
    var secondDiv = document.createElement("div");
    secondDiv.className = "panel panel-default arrow left";
    var thirdDiv = document.createElement("div");
    thirdDiv.className = "panel-body";

    thirdDiv.appendChild(makeHeader(userEmail, date));
    thirdDiv.appendChild(makeButton(commentaryID, subjectName, "newCommentary"+commentaryID));

    var textDiv = document.createElement("div");
    textDiv.className = "comment-post";
    var p = document.createElement("p");
    p.innerText = commentary;
    textDiv.appendChild(p);

    thirdDiv.appendChild(textDiv);
    secondDiv.appendChild(thirdDiv);
    firstDiv.appendChild(secondDiv);

    return firstDiv;
}

function makeButton(commentaryID, subjectName, articleID){
    var button = document.createElement("button");
    button.type = "submit";
    button.className = "btn pull-right remove";
    button.setAttribute("onclick", "removeComment('" + commentaryID + "','" + subjectName + "','" + articleID+ "')");
    var i = document.createElement("i");
    i.className = "glyphicon glyphicon-remove";
    button.appendChild(i);
    return button;
}

function makeHeader(userEmail, date){
    var header = document.createElement("header");
    header.className = "text-left";
    var subDiv = document.createElement("div");
    subDiv.className = "comment-user";
    var i = document.createElement("i");
    i.className ="glyphicon glyphicon-user";
    var abbr = document.createElement("abbr");
    subDiv.appendChild(i);
    subDiv.innerText = userEmail;
    abbr.className = "timeago";
    abbr.title = date;
    header.appendChild(subDiv);
    header.appendChild(abbr);
    return header;
}