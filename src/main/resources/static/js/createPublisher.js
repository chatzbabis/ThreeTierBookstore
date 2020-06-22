$(document).ready(function(){    
    $("#publisherForm").on('submit', function(event){
        event.preventDefault();        
        var formData = {};
        $("#publisherForm").find(":input").each(function(){
            formData[this.name] = $(this).val()
        })    
        $.ajax({
            type: "POST",
            url: "tier3/publishers",
            data: (JSON.stringify(formData)),
            dataType: "json",
            contentType: "application/json; charset=utf-8", 
            statusCode: {
                201: function() {                    
                    alert("Publisher created successfully!");
                    //check check
                }
            }                                    
          });   
    });
});