$(document).ready(function () {    
    $("#registrationForm").on('submit', function (event) {
        event.preventDefault();     
                
        var formData = {};        
        $("#registrationForm").find(":input").each(function () {                
                    formData[this.name] = $(this).val();
                             
        })
        formData.roles = [1];
        console.log(JSON.stringify(formData));                
        $.ajax({
            type: "POST",
            url: "tier3/users",
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: "application/json;",
            statusCode: {
                201: function () {
                    alert("User created successfully");
                },
                409: function() {
                    alert("A user with this username already exists, please try changing it.")
                }
            }
        });
    });
});