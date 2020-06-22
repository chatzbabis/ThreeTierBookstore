$(document).ready(function(){
    $("#genreForm").on('submit', function(event){
        event.preventDefault();        
        var formData = {};
        $("#genreForm").find(":input").each(function(){
            formData[this.name] = $(this).val()
        })    
        $.ajax({
            type: "POST",
            url: "tier3/genre",
            data: (JSON.stringify(formData)),
            dataType: "json",
            contentType: "application/json; charset=utf-8", 
            statusCode: {
                201: function() {
                    alert("Genre created succesfully!");
                }
            }                                    
          });   
    });
});