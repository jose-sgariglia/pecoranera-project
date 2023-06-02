$(document).ready(function(){

    let tags = [];
    let selectedTags = [];
    //let unselectedTags = [];

    let navBarHeight = $("nav").outerHeight();
    let searchBar = $("#searchbar");

    let sticky = searchBar.offset();;


    let filterButton = $("#filtri-btn")
    let filterBar = $("#filter-fieldset");
    filterBar.slideUp();

    //tagsValues to HTML element
    function tagsToHtml(el, selected) {
        return `<span id="${el.key}" class="filter-tag">${el.value}</span>`;
    }


    //Loadtags with ajax
    $(window).on("load", function(){
        $.ajax({
            url: 'assets/tags.json',
            dataType: 'json',
            success: function(data) {
     
               $.each(data, function(key, val) {
     
                    // tagElements.push('<span id="' + key + '" class="filter-tag unselected">' + val + '</span>');    
                    tags.push({"key": key, "value": val});
     
               });
     
               tags.forEach(element => {
                    $("#filter-choice div").append(tagsToHtml(element, false));
               });
     
            },
           statusCode: {
              404: function() {
                alert('Errore, json file non trovato');
              }
            }
         });
    });

    //toggle filterbar
    filterButton.on("click", function(){
        $(this).toggleClass("active");
        filterBar.slideToggle(700);
    });

    //prevent form distribution
    $(window).submit(function(e){
        e.preventDefault();
    });

    //Reset important data on window resize
    $(window).resize(function(){
        searchBar.css("top", navBarHeight + "px");
        navBarHeight = $("nav").outerHeight();
        sticky = searchBar.offset();
    });


    //Make searchbar sticky on scroll
    $(window).on("scroll resize", function() {

        if ($(document).scrollTop() >= sticky.top - navBarHeight) {
            searchBar.css("top", navBarHeight + "px");
            $("#upcoming").css("margin-bottom", "calc(" + searchBar.outerHeight() + "px + 5vh)");
            searchBar.addClass('sticky');
        }
        else {
            searchBar.removeClass('sticky');
            $("#upcoming").css("margin-bottom", "0px");
        }
    
    });

    let moveToListAndSort = function(source, target, isSelected) {
        
        $(source).on('click', '.filter-tag', function() {
            //let element;

            if(isSelected){
                //element = tags.splice(tags.map(e => e.key).indexOf($(this).attr("id")), 1)[0];
                selectedTags.push($(this).attr("id"));
            } else {
                selectedTags.splice(selectedTags.map(e => e.key).indexOf($(this).attr("id")), 1);
                // element = selectedTags.splice(selectedTags.map(e => e.key).indexOf($(this).attr("id")), 1);
                //tags.push(element);
            }
            
            $(this).remove();
    
            $(target).append($(this));
    
            $(target).children('.filter-tag').detach().sort(function(a, b) {
                let valueA = parseInt(a.getAttribute("id"));
                let valueB = parseInt(b.getAttribute("id"));
              
                if (valueA > valueB) {
                    return 1;
                }
        
                if (valueA < valueB) {
                    return -1;
                }
              
                return 0;
            }).appendTo(target);
        });


    };


    $("input[type=reset]").on("click", function(){
        selectedTags.length = 0;

        $("#selected-filter div").children('.filter-tag').each(function(){
            $(this).remove;

            $("#filter-choice div").append($(this));

            $("#filter-choice div").children('.filter-tag').detach().sort(function(a, b) {
                let valueA = parseInt(a.getAttribute("id"));
                let valueB = parseInt(b.getAttribute("id"));
              
                if (valueA > valueB) {
                    return 1;
                }
        
                if (valueA < valueB) {
                    return -1;
                }
              
                return 0;
            }).appendTo($("#filter-choice div"));
        })
    });

    moveToListAndSort("#selected-filter div", "#filter-choice div", false);
    moveToListAndSort("#filter-choice div", "#selected-filter div", true);
});