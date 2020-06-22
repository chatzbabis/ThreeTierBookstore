var username;
$(document).ready(function () {

    $.ajax({
        async: false,
        url: "tier3/userdetails",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find books of this genre!");
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
    var booksInBasket;
    $.ajax({
        async: false,
        url: "tier3/basketItems/" + user.id,
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went wrong!");
        },
        success: function (data) {
            
            booksInBasket = data;
            console.log(data);

            for (var i = 0; i < booksInBasket.length; i++) {
                generatebooksInBasket(booksInBasket[i].book, data[i].quantity, booksInBasket[i]);
            }
            calcTotalPrice();

        }
    });
});

var itemCount = 0;
var totalPrice = 0;
function generatebooksInBasket(bookInBasket, quantity, basketItem) {
    $('#itemsArea').append(
        '<div class="card mb-3" style="max-height: 200px; min-width: 100%; background-color: #CDD0C0;">' +
        '<div class="row no-gutters">' +
        '<div class="col-md-2">' +
        "<a href='/book.html?id=" + bookInBasket.id + "'>" +
        '<img src="' + bookInBasket.imageUrl + '" class="card-img" alt="..." style="max-height: 200px; max-width: 150px">' + "</a>" +
        '</div>' +
        '<div class="col-md-8">' +
        '<div class="card-body border-left border-right h-100">' +
        '<h5 class="card-title">' + bookInBasket.title + '</h5>' +
        '<p class="card-text">' + bookInBasket.authorsCollection[0].firstName + ' '
        + bookInBasket.authorsCollection[0].lastName + '</p>' +
        '<p class="card-text">' + bookInBasket.publisherId.name + '</p>' +
        '<button id="removeItem" onclick="removeItem(' + basketItem.id + ')" class="btn border text-white" style="background-color: #373737">Remove from basket</button>' +
        '</div>' +
        '</div>' +
        '<div class="col-md-2">' +
        '<div class="card-body h-100">' +
        '<p class="card-text">Unit Price: $' + (bookInBasket.price / 100) + '</p>' +
        '<label for="quantity">Quantity</label>' +
        '<input id="quantityInput' +basketItem.id +'" class="form-control" type="number" id="quantity" onchange="changeQuantity(' + basketItem.id + ')" value=' + quantity + ' name="quantity" style="box-sizing: border-box;"/>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>'
    );
    itemCount = itemCount + quantity;
    totalPrice = totalPrice + ((bookInBasket.price / 100) * quantity);
    console.log(basketItem);    
}

function calcTotalPrice() {
    $('#itemCount').text(
        itemCount + " items are currently in your basket."
    );
    $('#totalPrice').text(
        "Your current total is: " + totalPrice
    )
}


//function for removing items
function removeItem(basketItemId) {
    console.log("mpike");
    $.ajax({
        url: 'tier3/basketItems/' + basketItemId,
        type: 'DELETE',
        data: {
            format: 'json'
        },
        success: function () {
            alert("Book removed from your basket!")
            $('#itemsArea').empty();
            $.ajax({
                async: false,
                url: "tier3/basketItems/" + user.id,
                data: {
                    format: 'json'
                },
                error: function () {
                    alert("Could not find books of this genre!");
                },
                success: function (data) {
                    ajaxData = data;
                    booksInBasket = data;
                    console.log(data);

                    for (var i = 0; i < booksInBasket.length; i++) {
                        generatebooksInBasket(booksInBasket[i].book, data[i].quantity, booksInBasket[i]);
                    }
                    calcTotalPrice();

                }
            });

        },
        error: function () {
            alert("Could not remove book!")
        }
    });
}

//function for changing item quantity
function changeQuantity(basketItemId, basketItemQuant) {
    updatedBasketItem = {};
    updatedBasketItem.id = basketItemId;
    var correctId = "#quantityInput" + basketItemId
    updatedBasketItem.quantity = JSON.parse($(correctId).val());
    console.log(JSON.stringify(updatedBasketItem));
    $.ajax({
        url: 'tier3/basketItems/',
        type: 'PUT',
        data: JSON.stringify(updatedBasketItem),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
       statusCode: {
           200: function() {
               console.log('Quantity changed');
               location.reload();
           }
       } 
    });
}

$(document).ready(function() {
    $('#checkoutButton').on('click', function() {
        window.location.href = "/createOrSelectDeliveryAddress.html";
    })
})


