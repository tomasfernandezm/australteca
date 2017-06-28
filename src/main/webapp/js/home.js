function show(divId) {
    $(divId).toggle();
}

function changeFavorite(publicationID, checkBoxId, divID) {
    $.ajax({
        type:'post',
        url:'/servlet/favoritePublication',
        dataType: 'JSON',
        data:{
            publicationID: publicationID,
            status: document.getElementById(checkBoxId).checked
        },
        success: function(){
            document.getElementById(divID).remove();
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