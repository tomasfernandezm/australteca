function show(divId) {
    $(divId).toggle();
}

function changeFavorite(publicationID, checkBoxId, divID) {
    $.ajax({
        type:'post',
        url:'/favoritePublication',
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