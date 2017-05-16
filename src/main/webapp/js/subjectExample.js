/**
 * Created by tomi on 03/05/17.
 */

function changeRating(subjectName, button_value) {
    $.ajax({
        type:'post',
        url:'/subjectChangeRatingAjax',
        dataType: 'JSON',
        data:{
                rating: button_value,
                subject: subjectName
            },

        success: function(jsonObject){
            document.getElementById("subject_score").innerHTML = JSON.parse(jsonObject);
        }
    })
}


