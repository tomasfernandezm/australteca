/**
 * Created by tomi on 03/05/17.
 */

function changeRating(subjectName) {
    $.ajax({
        type:'post',
        url:'/subjectChangeRatingAjax',
        dataType: 'JSON',
        data:{
                rating: $('input[name=rating]').val(),
                subject: subjectName
            },

        success: function(jsonObject){
            var response = JSON.parse(jsonObject);
            document.getElementById("subject_score").innerHTML = response;
        }
    })
}
