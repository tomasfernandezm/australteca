/**
 * Created by tomasforman on 2/6/17.
 */

$(document).ready(function(){
    $("#showandhide").click(function(){
        $("#show").toggle("show");
    });
});

// $(function () {
//     $('[data-toggle="popover"]').popover()
// })
//




$(document).ready(function(){
    $('[data-toggle="popover"]').popover({
        placement : 'top',
        html : true,
        title : 'Seguro deseas enviar?',
        content : '<button type="submit">Si</button>' +
        ''
    });
    $(document).on("click", ".popover .close" , function(){
        $(this).parents(".popover").popover('hide');
    });
});