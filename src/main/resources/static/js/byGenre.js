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

$(document).ready(function () {
    var booksByGenre;
    var paramName = "genre";
    $.ajax({
        url: "tier3/genre/" + decodeURIComponent(getBookGenreFromUrl(paramName)),
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find books of this genre!");
        },
        success: function (data) {
            booksByGenre = data;            
            generateByGenreResults(booksByGenre);
        }
    });
});

function getBookGenreFromUrl(paramName) {
    var results = new RegExp('[\?&]' + paramName + '=([^&#]*)').exec(window.location.href);
    return results[1] || 0;
}

function generateByGenreResults(booksByGenre) {
    for (i = 0; i < booksByGenre.length; i++) {        
            $('.row.byGenre').append(
                "<div class='card card-custom mx-2 mb-3' style='max-width: 176.25px; max-height:421.2px;'>" +
                "<a href='/book.html?id=" + booksByGenre[i].id + "'>" +
                "<img class='card-img-top' src='" + booksByGenre[i].imageUrl + "' alt='Card image cap'>" + "</a>" +
                "<div class='card-body' style='background-color: #CDD0C0'>" +
                "<p class='card-title' style='font-weight: bold;'>" + booksByGenre[i].title + "</h5>" +
                "<p class='card-text'>" + booksByGenre[i].authorsCollection[0].firstName + " "
                + booksByGenre[i].authorsCollection[0].lastName + "<br>" + (booksByGenre[i].price / 100) + "$" + "</p>" +                
                "</div>" +
                "</div>")        
    }   
}

function getQuoteOfTheDay(author) {    
    $('#quote').text(author.quote);
    $('#quoteAuthor').text(author.firstName + " " + author.lastName);
    $('#quoteImage').attr('src', author.imageUrl);
}

