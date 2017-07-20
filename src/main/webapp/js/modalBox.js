var modal;

// When the user clicks the button, open the modal
    function modalBox(btnId){
        btnId.style.display = "block";
        modal = btnId;
    }

    function modalBox(btnId, title){
        btnId.style.display = "block";
        btnId.title = title;
        modal = btnId;
    }

// When the user clicks on <span> (x), close the modal
    function closeModal(btnId){
    modal.style.display = "none";
    }


// When the user clicks anywhere outside of the modal, close it

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };

