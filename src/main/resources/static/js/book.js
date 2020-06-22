var bookId = decodeURIComponent(getBookIdFromUrl("id"));
$(document).ready(function () {
    var book;
    var paramName = "id";
    $.ajax({
        url: "tier3/books/" + decodeURIComponent(getBookIdFromUrl(paramName)),
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find this book!");
        },
        success: function (data) {
            book = data;
            generateBookInfo(book);
        }
    });
});

$(document).ready(function () {    
    var author;
    $.ajax({
        url: "tier3/quote",
        data: {
            format: 'json'
        },
        error: function() {
            alert("Could not find an author!");
        },
        success: function (data) {
            author = data;
            getQuoteOfTheDay(author);                            
        }
    });
});

function getBookIdFromUrl(paramName) {
    var results = new RegExp('[\?&]' + paramName + '=([^&#]*)').exec(window.location.href);
    return results[1] || 0;
}

function generateBookInfo(book) {
    $('#bookTitle').text(book.title);
    $('#bookImage').attr('src', book.imageUrl);
    if (book.authorsCollection.length > 1) { $('#authorName').text("Collective") }
    else {
        $('#authorName').text(book.authorsCollection[0].firstName + " " + book.authorsCollection[0].lastName);
    }
    $('#bookPrice').text("Price: " + (book.price / 100) + "$");
    $('#bookAvailability').text(convertStockLevelToMessage(book));
    $('#bookDescription').text(book.description);
    $('#bookOriginalTitle').text("Original Title: " + book.originalTitle);
    $('#bookPages').text("Number of Pages: " + book.nrOfPages);
    $('#bookPublisher').text("Publisher: " + book.publisherId.name);
    $('#bookISBN').text("ISBN: " + book.isbn);
    $('#bookYear').text("Publication Year: " + book.publicationYear);
    $('#bookGenre').text("Genre: " + book.genreId.name);
}

function convertStockLevelToMessage(book) {
    var booksRemaining = book.inventory.stockLevel;
    var availabilityMessage;
    if (booksRemaining < 10 && booksRemaining > 0) {
        availabilityMessage = "Only a few copies left!";
    } else if (booksRemaining == 0) {
        availabilityMessage = "Out of Stock"
    }
    else {
        availabilityMessage = "Readily available"
    }
    return availabilityMessage;
}

function getQuoteOfTheDay(author) {    
    $('#quote').text(author.quote);
    $('#quoteAuthor').text(author.firstName + " " + author.lastName);
    $('#quoteImage').attr('src', author.imageUrl);
}

//add book to basket

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

var basketId;
$(document).ready(function () {
    var booksInBasket;
    $.ajax({
        async: false,
        url: "tier3/basket/" + user.id,
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went wrong!");
        },
        success: function (data) {
            basketId = data.id
            console.log(basketId);
            console.log(data);
            

        }
    });
});

var wishlistId;
$(document).ready(function () {    
    $.ajax({
        async: false,
        url: "tier3/wishlist/" + user.id,
        data: {
            format: 'json'
        },
        error: function () {
            alert("Something went wrong");
        },
        success: function (data) {
            wishlistId = data.id
            console.log(basketId);
            console.log(data);
            

        }
    });
});


$(document).ready(function() {
    $('#addToBasket').on('click', function() {
        var bookToBeAdded = {}
        bookToBeAdded.quantity = 1;
        bookToBeAdded.basketId = {id: basketId};
        bookToBeAdded.book = {id: JSON.parse(bookId)};        
        $.ajax({
            type: "POST",
            url: "/tier3/basketItems",
            data: JSON.stringify(bookToBeAdded),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            statusCode: {
                201: function() {
                    alert("Book added succesfully!");
                },
                409: function() {
                    alert("This item is already in your basket!")
                }
            },
            error: { function() {
                alert('Something went wrong, please try again!');
            }
                
            } 
        });
        
    });

    $('#addToWishlist').on('click', function() {
        var bookToBeAdded = {}
        bookToBeAdded.quantity = 1;
        bookToBeAdded.wishlistId = {id: wishlistId};
        bookToBeAdded.book = {id: JSON.parse(bookId)};        
        $.ajax({
            type: "POST",
            url: "/tier3/wishlistItems",
            data: JSON.stringify(bookToBeAdded),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            statusCode: {
                201: function() {
                    alert("Book added succesfully!");
                },
                409: function() {
                    alert("This item is already in your wishlist!")
                }
            },
            error: { function() {
                alert('Something went wrong, please try again!');
            }
                
            }  
        });
        
    });
})




