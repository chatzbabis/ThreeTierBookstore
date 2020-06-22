$(document).ready(function () {
    var allBooks;
    $.ajax({
        url: "tier3/books/bestseller/8",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find any books!");
        },
        success: function (data) {
            allBooks = data;
            generateCarouselofMostSold(allBooks);
        }
    });
});

$(document).ready(function () {
    var allBooks;
    $.ajax({
        url: "tier3/books/recent/8",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find any books!");
        },
        success: function (data) {
            allBooks = data;
            generateCarouselofMostRecent(allBooks);
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
        error: function () {
            alert("Could not find an author!");
        },
        success: function (data) {
            author = data;
            getQuoteOfTheDay(author);
        }
    });
});

$(document).ready(function (event) {
    $('#mainSearchForm').submit(function (event) {
        if (!$('#searchInput').val().trim().length) {
            event.preventDefault();
            alert("Our collection is endless, try entering a keyword to search throught it, otherwise you might get lost.");
        }
    })
})



function generateCarouselofMostSold(books) {
    var bookName;
    var imgUrl;
    var author;
    var price;
    var i = 0;
    for (i = 0; i < 4; i++) {
        $(".card-deck.firstPage.mostSold").append(
            "<div class='card mt-2 mb-2 mostSoldCard'> " +
            "<a href='/book.html?id=" + books[i].id + "'>" +
            "<img class='card-img-top' " +
            "src='" + books[i].imageUrl + "'" +
            "alt='Card image cap' /> " + "</a>" +
            "<div class='card-body d-flex flex-column h-100' style='background-color: #CDD0C0'>" +
            "<p class='card-title mt-auto' style='font-weight: bold;'>" + books[i].title + "</p>" +
            "<p class='card-text'>" + books[i].authorsCollection[0].firstName + " "
            + books[i].authorsCollection[0].lastName + "<br>" + (books[i].price / 100) + "$" + "</p>" +
            "</div>" +
            "</div>"
        );
    }
    for (i = 4; i < 8; i++) {
        $(".card-deck.secondPage.mostSold").append(
            "<div class='card mt-2 mb-2 mostSoldCard'> " +
            "<a href='/book.html?id=" + books[i].id + "'>" +
            "<img class='card-img-top' " +
            "src='" + books[i].imageUrl + "'" +
            "alt='Card image cap' /> " + "</a>" +
            "<div class='card-body d-flex flex-column h-100' style='background-color: #CDD0C0'>" +
            "<p class='card-title mt-auto' style='font-weight: bold;'>" + books[i].title + "</p>" +
            "<p class='card-text'>" + books[i].authorsCollection[0].firstName + " "
            + books[i].authorsCollection[0].lastName + "<br>" + (books[i].price / 100) + "$" + "</p>" +
            "</div>" +
            "</div>"
        );
    }

}

function generateCarouselofMostRecent(books) {
    var bookName;
    var imgUrl;
    var author;
    var price;
    var i = 0;
    for (i = 0; i < 4; i++) {
        $(".card-deck.firstPage.mostRecent").append(
            "<div class='card mt-2 mb-2 mostSoldCard'> " +
            "<a href='/book.html?id=" + books[i].id + "'>" +
            "<img class='card-img-top' " +
            "src='" + books[i].imageUrl + "'" +
            "alt='Card image cap' /> " + "</a>" +
            "<div class='card-body d-flex flex-column h-100' style='background-color: #CDD0C0'>" +
            "<p class='card-title mt-auto' style='font-weight: bold;'>" + books[i].title + "</p>" +
            "<p class='card-text'>" + books[i].authorsCollection[0].firstName + " "
            + books[i].authorsCollection[0].lastName + "<br>" + (books[i].price / 100) + "$" + "</p>" +
            "</div>" +
            "</div>"
        );
    }
    for (i = 4; i < 8; i++) {
        $(".card-deck.secondPage.mostRecent").append(
            "<div class='card mt-2 mb-2 mostSoldCard'> " +
            "<a href='/book.html?id=" + books[i].id + "'>" +
            "<img class='card-img-top' " +
            "src='" + books[i].imageUrl + "'" +
            "alt='Card image cap' /> " + "</a>" +
            "<div class='card-body d-flex flex-column h-100' style='background-color: #CDD0C0'>" +
            "<p class='card-title mt-auto' style='font-weight: bold;'>" + books[i].title + "</p>" +
            "<p class='card-text'>" + books[i].authorsCollection[0].firstName + " "
            + books[i].authorsCollection[0].lastName + "<br>" + (books[i].price / 100) + "$" + "</p>" +
            "</div>" +
            "</div>"
        );
    }

}

function getQuoteOfTheDay(author) {
    $('#quote').text(author.quote);
    $('#quoteAuthor').text(author.firstName + " " + author.lastName);
    $('#quoteImage').attr('src', author.imageUrl);
}


