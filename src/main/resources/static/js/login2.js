$(document).ready(function() {
    if(window.location.href.indexOf("error") > -1) {
        $("#errorMessage").append("<span class='border bg-danger text-center p-1'>Wrong username/password, try again!</span>");
    }
})

// experiment with login/logout change items
// $(document).ready(function() {
//     $('#loginForm').on('submit', function(){
//         if(window.location.href.indexOf("error") === -1) {
//             $("#errorMessage").append("<span class='border bg-danger text-center p-1'>Wrong username/password, try again!</span>");
//             sessionStorage.setItem('status','loggedIn');
//         }
//     })
    
// })
