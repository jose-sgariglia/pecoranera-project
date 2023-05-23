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

    $('body').bind('beforeunload',function(){
        window.scrollTo({ top: 0, behavior: 'smooth' });
    });

    $(window).on('resize scroll', function() {
        if (chiSiamo.isInViewport()) {
            //console.log("VISIBILE");
            chiSiamo.addClass('insight');
            images.children("img").addClass('insight')
            
        } else {
            //console.log("NOT! VISIBILE");
            chiSiamo.removeClass('insight');
            images.children("img").removeClass('insight')
        }
    });

    $("#menu-choice a").on('mouseover', function () {
        $(this).addClass('active-choice');
        $(this).siblings().addClass('inactive-choice');
    });

    $("#menu-choice a").on('mouseleave', function(){
        $(this).removeClass('active-choice');
        $(this).siblings().removeClass('inactive-choice');
    });


});