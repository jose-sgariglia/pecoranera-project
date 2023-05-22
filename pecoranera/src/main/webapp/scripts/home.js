$(document).ready(function() {
    let chiSiamo = $("#chisiamo .side-text").first();
    let images = $("#chisiamo .side-images");

    $.fn.isInViewport = function (){
        var elementTop = $(this).offset().top;
        var elementBottom = elementTop + $(this).outerHeight() -20;

        var viewportTop = $(window).scrollTop();
        var viewportBottom = viewportTop + $(window).height();

        return elementBottom > viewportTop && elementTop < viewportBottom;
    }

    $(window).on('resize scroll', function() {
        if (chiSiamo.isInViewport()) {
            console.log("VISIBILE");
            chiSiamo.addClass('insight');
            images.children("img").addClass('insight')
            
        } else {
            console.log("NOT! VISIBILE");
            chiSiamo.removeClass('insight');
            images.children("img").removeClass('insight')
        }
    });


});