$(document).ready(function() {
    $.ajax({
        url: "tier3/userdetails",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went wrong!");
        },
        success: function (data) {
            if(data === 'anonymousUser'){
                console.log("you are not logged in")
            }
            if(data !== 'anonymousUser'){
                var username = data.username;           
            $('#navUserWelcome').append(
                '<a class="nav-link" href="#"><i class="fas fa-door-open"></i> &nbsp; Welcome, '+ username +'</a>'
                );
            $('#navLoginLogout').empty().append(
                '<a class="nav-link" href="/perform_logout"><i class="fas fa-sign-out-alt"></i> Logout</a>' 
            )
            $('#navSignUp').remove();
            $('#basketLink').attr('href', '/basket.html');
            $('#wishlistLink').attr('href', '/wishlist.html');
            }
            console.log(data.authorities)
            console.log(data.authorities.includes("ROLE_admin"))
            for(var i = 0; i< data.authorities.length; i++) {
                if(data.authorities[i].authority === "ROLE_admin"){                    
                    $('#adminDashMessage').append(
                        '<p>To view your dashboard, click <a href="/adminDash.html">here!</a></p>'
                    )
                    $('#adminDashMessage').css('background-color', '#CDD0C0')
                }
            }         
        }
    });
})