$(document).ready(function() {
    let navbarBtn = $("#nav-button"),
        sidebar = $("#sidebar"),
        outsideClose = $("#outside-close");

    navbarBtn.add(outsideClose).on("click", function() {
        navbarBtn.toggleClass('open');

        sidebar.toggleClass('closed');

        outsideClose.toggleClass('closed')
    })
    


});

