$(document).ready(function(){    
    $("#languageForm").on('submit', function(event){
        event.preventDefault();        
        var formData = {};
        $("#languageForm").find(":input").each(function(){
            formData[this.name] = $(this).val()
        })    
        $.ajax({
            type: "POST",
            url: "tier3/languages",
            data: (JSON.stringify(formData)),
            dataType: "json",
            contentType: "application/json; charset=utf-8", 
            statusCode: {
                201: function() {                    
                    alert("Language created successfully!");
                }
            }                                    
          });   
    });
});