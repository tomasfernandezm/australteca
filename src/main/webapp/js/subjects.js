(function(){
    'use strict';
    var $ = jQuery;
    $.fn.extend({
        filterTable: function(){
            return this.each(function(){
                $(this).on('keyup', function(e){
                    $('.filterTable_no_results').remove();
                    var $this = $(this),
                        search = $this.val().toLowerCase(),
                        target = $this.attr('data-filters'),
                        $target = $(target),
                        $rows = $target.find('tbody tr');

                    if(search == '') {
                        $rows.show();
                    } else {
                        $rows.each(function(){
                            var $this = $(this);
                            $this.text().toLowerCase().indexOf(search) === -1 ? $this.hide() : $this.show();
                        })
                        if($target.find('tbody tr:visible').size() === 0) {
                            var col_count = $target.find('tr').first().find('td').size();
                            var no_results = $('<tr class="filterTable_no_results"><td colspan="'+col_count+'">No results found</td></tr>')
                            $target.find('tbody').append(no_results);
                        }
                    }
                });
            });
        }
    });
    $('[data-action="filter"]').filterTable();
})(jQuery);

$(function(){
    // attach table filter plugin to inputs
    $('[data-action="filter"]').filterTable();

    $('.container').on('click', '.panel-heading span.filter', function(e){
        var $this = $(this),
            $panel = $this.parents('.panel');

        $panel.find('.panel-body').slideToggle();
        if($this.css('display') != 'none') {
            $panel.find('.panel-body input').focus();
        }
    });
    $('[data-toggle="tooltip"]').tooltip();
})



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
            checkbox.onclick = "changeFavorite(" + checkbox.value +","+checkbox.id +")";
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