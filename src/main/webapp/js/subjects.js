function changeFavorite(subjectName, checkBoxId) {
    $.ajax({
        type:'post',
        url:'/userSubjectManagerAjax',
        dataType: 'JSON',
        data:{
            subjectName: subjectName,
            status: document.getElementById(checkBoxId).checked
        }
    })
}

function addSubject(subjectName){
    $.ajax({
        type:'post',
        url:'/userSubjectManagerAjax',
        dataType: 'JSON',
        data:{
            subjectName: subjectName
        },

        success: function(){
            var table = document.getElementById("dev-table");
            var row = table.insertRow();
            var cellName = row.insertCell(0);
            var cellFavorite = row.insertCell(1);
            var cellDelete = row.insertCell(2);
            var link = document.createElement('a');
            link.href = "/postSubject?subjectName= " + subjectName;
            link.text = subjectName;
            cellName.appendChild(link);
            var div = document.createElement('div');
            div.class = "checkbox_wrapper";
            cellFavorite.appendChild(div);
            var checkbox = document.createElement('input');
            checkbox.type = "checkbox";
            checkbox.value = subjectName;
            checkbox.class = "checkbox";
            checkbox.id = subjectName + "Checkbox";
            checkbox.setAttribute("onclick","changeFavorite(" + checkbox.value +","+checkbox.id +")");
            var button = document.createElement('button');
            button.type="submit";
            button.class="standardButton btn btn-default btn-xs";
            button.onsubmit = "removeSubject('" + subjectName +"')";
            var iButton = document.createElement('i');
            iButton.class = "glyphicon glyphicon-trash";
            button.appendChild(iButton);
            cellDelete.appendChild(button);
        }
    })
}

function removeSubject(subjectName){
    $.ajax({
        type:'post',
        url:'/subjectDelete',
        dataType: 'JSON',
        data:{
            subjectName: subjectName
        },

        success: function(){
            document.getElementById(subjectName + "Row").remove();

        }
    })
}