/**
 * Created by tomi on 03/05/17.
 */

$('#rating5').click(function () {
    $.ajax({
        type:'post',
        url:'/rateSubject',
        dataType: 'JSON',
        data: {
            rating: JSON.stringify($('input[name="rating]').val())
        },
        success: function(){
            $.getJSON("GET", "/rateSubject", function (data) {
                document.getElementById("subject_score").innerHTML=
                    this.responseText;
            })
        }
    });
});