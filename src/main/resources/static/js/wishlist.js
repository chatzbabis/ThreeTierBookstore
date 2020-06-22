var username;
$(document).ready(function () {

    $.ajax({
        async: false,
        url: "tier3/userdetails",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went Wrong!");
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
            alert("Something went Wrong!");
        },
        success: function (data) {
            user = data;
            console.log(user)
        }
    });
});


$(document).ready(function () {
    var booksInWishlist;
    $.ajax({
        url: "tier3/wishlistItems/" + user.id,
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went Wrong!");
        },
        success: function (data) {
            booksInWishlist = data;
            console.log(data);
            for (var i = 0; i < booksInWishlist.length; i++) {
                console.log(booksInWishlist[i].book);
                generatebooksInWishlist(booksInWishlist[i].book, booksInWishlist[i]);
            }
        }
    });
});

function generatebooksInWishlist(bookInWishlist, wishlistItem) {
    $('#itemsArea').append(
        '<div class="card mb-3" style="max-height: 200px; min-width: 100%; background-color: #CDD0C0;">' +
        '<div class="row no-gutters">' +
        '<div class="col-md-2">' +
        "<a href='/book.html?id=" + bookInWishlist.id + "'>" +
        '<img src="' + bookInWishlist.imageUrl + '" class="card-img" alt="..." style="max-height: 200px; max-width: 150px">' + "</a>" +
        '</div>' +
        '<div class="col-md-8">' +
        '<div class="card-body border-left border-right h-100">' +
        '<h5 class="card-title">' + bookInWishlist.title + '</h5>' +
        '<p class="card-text">' + bookInWishlist.authorsCollection[0].firstName + ' '
        + bookInWishlist.authorsCollection[0].lastName + '</p>' +
        '<p class="card-text">' + bookInWishlist.publisherId.name + '</p>' +
        '<button id="removeItem" class="btn border text-white" onclick="removeItem('+ wishlistItem.id +')" style="background-color: #373737">Remove from wishlist</button>' +
        '</div>' +
        '</div>' +
        '<div class="col-md-2">' +
        '<div class="card-body h-100">' +
        '<p class="card-text">Unit Price: $' + (bookInWishlist.price / 100) + '</p>' +        
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>'
    );    
}

//function for removing items
function removeItem(wishlistItemId) {    
    $.ajax({
        url: 'tier3/wishlistItems/' + wishlistItemId,
        type: 'DELETE',
        data: {
            format: 'json'
        },
        success: function () {
            alert("Book removed from your wishlist!")
            $('#itemsArea').empty();
            $.ajax({
                url: "tier3/wishlistItems/" + user.id,
                data: {
                    format: 'json'
                },
                error: function () {
                    alert("Something went Wrong!");
                },
                success: function (data) {
                    booksInWishlist = data;
                    console.log(data);
                    for (var i = 0; i < booksInWishlist.length; i++) {
                        console.log(booksInWishlist[i].book);
                        generatebooksInWishlist(booksInWishlist[i].book, booksInWishlist[i]);
                    }
                }
            });

        },
        error: function () {
            alert("Could not remove book!")
        }
    });
}


