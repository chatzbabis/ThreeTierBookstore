$(document).ready(function(){
    $("#authorForm").on('submit', function(event){
        event.preventDefault();        
        var formData = {};
        $("#authorForm").find(":input").each(function(){
            formData[this.name] = $(this).val()
        })    
        $.ajax({
            type: "POST",
            url: "tier3/authors",
            data: (JSON.stringify(formData)),
            dataType: "json",
            contentType: "application/json; charset=utf-8", 
            statusCode: {
                201: function() {
                    alert("Author created succesffully!");
                }
            }                                    
          });   
    });
});