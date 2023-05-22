$(document).ready(function() {
    let navbarBtn = $("#nav-button"),
        sidebar = $("#sidebar"),
        outsideClose = $("#outside-close");

    navbarBtn.add(outsideClose).on("click", function() {
        navbarBtn.toggleClass('open');

        sidebar.toggleClass('closed');

        outsideClose.toggleClass('closed')
    })
    
    $(window).scroll(function() {
        if ($(document).scrollTop() > 1) {
            $('nav').removeClass('navbar-home');
        }
        else {
            $('nav').addClass('navbar-home');
        }
    });

});

