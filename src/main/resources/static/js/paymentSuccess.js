var username;
$(document).ready(function () {

    $.ajax({
        async: false,
        url: "tier3/userdetails",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went wrong!");
        },
        success: function (data) {
            username = data.username;
            console.log(data.username);
        }
    });
});

var user;
$(document).ready(function () {

    $.ajax({
        async: false,
        url: "tier3/user/" + username,
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went wrong!");
        },
        success: function (data) {
            user = data;
            console.log(user)
        }
    });
});

$(document).ready(function () {
    console.log(sessionStorage.getItem("orderAddress"));
    orderAddress = JSON.parse(sessionStorage.getItem("orderAddress"));
    addressId = orderAddress.id;
    var jsonData = {};
    jsonData.deliveryAddressId = { id: addressId };
    jsonData.statusId = { id: 2 };
    jsonData.userId = { id: user.id };
    console.log(JSON.stringify(jsonData));
    $.ajax({
        type: "POST",
        url: "/tier3/order",
        data: JSON.stringify(jsonData),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        statusCode: {
            201: function () {
                console.log("order completed successfully")
            }
        },
        error: {
            function() {
                alert('Something went wrong, please try again!');
            }

        }
    });
})

