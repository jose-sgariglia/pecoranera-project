
let tags = [];
let selectedTags = [];

//tagsValues to HTML element
function tagsToHtml(el, selected) {
    return `<span id="${el.key}" class="filter-tag">${el.value}</span>`;
}


//Load tags with ajax
let loadTags = function(elem){
    elem.on("load", function(){
        $.ajax({
            url: 'assets/tags.json',
            dataType: 'json',
            success: function(data) {
        
                $.each(data, function(key, val) {
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
};

//Move tags from one place to another
let moveToListAndSort = function(source, target, isSelected) {
    
    $(source).on('click', '.filter-tag', function() {

        if(isSelected){
            //if added to selected Tags, put only id into the array
            selectedTags.push($(this).attr("id"));
        } else {
            //else find clicked attribute and remove
            selectedTags.splice(selectedTags.map(e => e.key).indexOf($(this).attr("id")), 1);
        }
        
        $(this).remove();

        $(target).append($(this));
        
        //sorting tags
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

//reset Tag choice
let resetTags = function(resetButton, fromElem, toElem){
    resetButton.on("click", function(){
        selectedTags.length = 0;

        fromElem.children('.filter-tag').each(function(){
            $(this).remove;

            toElem.append($(this));

            toElem.children('.filter-tag').detach().sort(function(a, b) {
                let valueA = parseInt(a.getAttribute("id"));
                let valueB = parseInt(b.getAttribute("id"));
                
                if (valueA > valueB) {
                    return 1;
                }
        
                if (valueA < valueB) {
                    return -1;
                }
                
                return 0;
            }).appendTo(toElem);
        })
    });
}

export {loadTags, moveToListAndSort, resetTags};