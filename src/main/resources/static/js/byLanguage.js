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

$(document).ready(function () {
    var booksByLanguage;
    var paramName = "language";
    $.ajax({
        url: "tier3/language/" + decodeURIComponent(getBookGenreFromUrl(paramName)),
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find books of this language!");
        },
        success: function (data) {
            booksByLanguage = data;
            generateByGenreResults(booksByLanguage);
        }
    });
});

function getBookGenreFromUrl(paramName) {
    var results = new RegExp('[\?&]' + paramName + '=([^&#]*)').exec(window.location.href);
    return results[1] || 0;
}

function generateByGenreResults(booksByLanguage) {
    for (i = 0; i < booksByLanguage.length; i++) {        
            $('.row.byLanguage').append(
                "<div class='card card-custom mx-2 mb-3' style='max-width: 176.25px; max-height:421.2px;'>" +
                "<a href='/book.html?id=" + booksByLanguage[i].id + "'>" +
                "<img class='card-img-top' src='" + booksByLanguage[i].imageUrl + "' alt='Card image cap'>" + "</a>" +
                "<div class='card-body' style='background-color: #CDD0C0'>" +
                "<p class='card-title' style='font-weight: bold;'>" + booksByLanguage[i].title + "</h5>" +
                "<p class='card-text'>" + booksByLanguage[i].authorsCollection[0].firstName + " "
                + booksByLanguage[i].authorsCollection[0].lastName + "<br>" + (booksByLanguage[i].price / 100) + "$" + "</p>" +
                "</div>" +
                "</div>")        
    }
}

function getQuoteOfTheDay(author) {
    $('#quote').text(author.quote);
    $('#quoteAuthor').text(author.firstName + " " + author.lastName);
    $('#quoteImage').attr('src', author.imageUrl);
}