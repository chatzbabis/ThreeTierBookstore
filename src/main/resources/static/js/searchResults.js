//ajax call to get search results
var booksByKeyword2;
$(document).ready(function () {

    var booksByKeyword;
    var paramName = "keyword";
    $.ajax({
        async: false,
        url: "tier3/search/" + getKeywordFromUrl(paramName).replace("+", " ").trim(),
        data: {
            format: 'json'
        },
        error: function () {
            $('#mainContentArea').empty();
                $('#mainContentArea').append(
                    "<div class='jumbotron col-12 border border-dark text-center' style='background-color: #CDD0C0;'>" +
                    "<h1 class='display-4' style='font-family: Chelsea Market, cursive;'>Could not find any results!</h1>" +
                    "<p class='lead'>Maybe it's because of a typo, or maybe you're looking for one of the very few books " +
                    "that do not belong in our collection. You can either try again from the search bar above or simply cick on the button below and go back to our homepage to get some ideas.</p>" +
                    "<hr class='my-4'>" +                    
                    "<p class='lead text-center'>" +
                      "<a class='btn btn-lg' style='background-color: #c0b283' href='/index.html' role='button'>Back to Homepage!</a>" +
                   " </p>" +
                  "</div>"
                );
        },
        success: function (data) {            
            booksByKeyword = data;
            booksByKeyword2 = data;
            generateSearchResults(booksByKeyword);
            generateFilters(booksByKeyword);           
        }        
    });    
});

//get the search parameters from the url
function getKeywordFromUrl(paramName) {
    var url = new URL(window.location.href);
    var c = url.searchParams.get(paramName);
    return c;
    // var results = new RegExp('[\?&]' + paramName + '=([^&#]*)').exec(window.location.href);
    // return results[1] || 0;
}

//generate search reults
function generateSearchResults(booksByKeyword) {
    for (i = 0; i < booksByKeyword.length; i++) {
        $('.row.searchResults').append(
            "<div class='card card-custom mx-2 mb-3' style='max-width: 176.25px; max-height:421.2px;'>" +
            "<a href='/book.html?id=" + booksByKeyword[i].id + "'>" +
            "<img class='card-img-top' src='" + booksByKeyword[i].imageUrl + "' alt='Card image cap'>" + "</a>" +
            "<div class='card-body' style='background-color: #CDD0C0'>" +
            "<p class='card-title' style='font-weight: bold;'>" + booksByKeyword[i].title + "</h5>" +
            "<p class='card-text'>" + booksByKeyword[i].authorsCollection[0].firstName + " "
            + booksByKeyword[i].authorsCollection[0].lastName + "<br>" + (booksByKeyword[i].price / 100) + "$" + "</p>" +
            "</div>" +
            "</div>")
    }


}

//methods to retrieve lists of authors/publishers etc to be used as filter options
//for now they use dummy data
function getListOfAuthors(booksByKeyword) {
    var listOfAuthors = new Array();
    for (var j = 0; j < booksByKeyword.length; j++) {
        for (var k = 0; k < booksByKeyword[j].authorsCollection.length; k++) {
            var authorFullName = booksByKeyword[j].authorsCollection[k].firstName + " " + booksByKeyword[j].authorsCollection[k].lastName;
            listOfAuthors.push(authorFullName);
        }
        var uniqueAuthors = [];
        $.each(listOfAuthors, function (i, el) {
            if ($.inArray(el, uniqueAuthors) === -1) uniqueAuthors.push(el);
        });
    }
    return uniqueAuthors
}

function getListOfPublishers(booksByKeyword) {
    var listOfPublishers = new Array();
    for (var j = 0; j < booksByKeyword.length; j++) {
        var publisherName = booksByKeyword[j].publisherId.name;
        listOfPublishers.push(publisherName);
    }
    var uniquePublishers = [];
    $.each(listOfPublishers, function (i, el) {
        if ($.inArray(el, uniquePublishers) === -1) uniquePublishers.push(el);
    });
    return uniquePublishers;

}

function getListOfYears(booksByKeyword) {
    var listOfYears = new Array();
    for (var j = 0; j < booksByKeyword.length; j++) {
        var publicationYear = booksByKeyword[j].publicationYear;
        listOfYears.push(publicationYear);
    }
    var uniqueYears = [];
    $.each(listOfYears, function (i, el) {
        if ($.inArray(el, uniqueYears) === -1) uniqueYears.push(el);
    });
    return uniqueYears;
}

function getListOfLanguages(booksByKeyword) {
    var listOfLanguages = new Array();
    for (var j = 0; j < booksByKeyword.length; j++) {
        var language = booksByKeyword[j].languageId.name;
        listOfLanguages.push(language);
    }
    var uniqueLanguages = [];
    $.each(listOfLanguages, function (i, el) {
        if ($.inArray(el, uniqueLanguages) === -1) uniqueLanguages.push(el);
    });
    return uniqueLanguages;
}

function getMinMaxPrice(booksByKeyword) {
    var listOfPrices = new Array();
    for (var j = 0; j < booksByKeyword.length; j++) {
        var price = booksByKeyword[j].price;
        listOfPrices.push(price);
    }
    return listOfPrices;
}

function generateFilters(booksByKeyword) {
    var listOfAuthors = getListOfAuthors(booksByKeyword);
    for (var k = 0; k < listOfAuthors.length; k++) {
        console.log("inside list of authors");
        $('#authorFilter').append(
            "<label class='form-check'>" +
            "<input id='" + listOfAuthors[k] + "' class='form-check-input' onChange='handleChange()' type='checkbox' name='author' checked value='" + listOfAuthors[k] + "'>" +
            "<span class='form-check-label'>" +
            listOfAuthors[k] +
            "</span>"
        )
        console.log("finished building list of authors");
    }
    var listOfPublishers = getListOfPublishers(booksByKeyword)
    for (var k = 0; k < listOfPublishers.length; k++) {
        $('#publisherFilter').append(
            "<label class='form-check'>" +
            "<input id=" + listOfPublishers[k] + " class='form-check-input' onChange='handleChange()' type='checkbox' checked value='" + listOfPublishers[k] + "'>" +
            "<span class='form-check-label'>" +
            listOfPublishers[k] +
            "</span>"
        )
    }
    var listOfYears = getListOfYears(booksByKeyword)
    for (var k = 0; k < listOfYears.length; k++) {
        $('#yearFilter').append(
            "<label class='form-check'>" +
            "<input id=" + listOfYears[k] + " class='form-check-input' onChange='handleChange()' type='checkbox' checked name='year' value='" + listOfYears[k] + "'>" +
            "<span class='form-check-label'>" +
            listOfYears[k] +
            "</span>"
        )
    }
    var listOfLanguages = getListOfLanguages(booksByKeyword)
    for (var k = 0; k < listOfLanguages.length; k++) {
        $('#languageFilter').append(
            "<label class='form-check'>" +
            "<input id=" + listOfLanguages[k] + " class='form-check-input' onChange='handleChange()' type='checkbox' checked value='" + listOfLanguages[k] + "'>" +
            "<span class='form-check-label'>" +
            listOfLanguages[k] +
            "</span>"
        )
    }
    var minMaxPrice = getMinMaxPrice(booksByKeyword)
    var sortedMinMaxPrice = minMaxPrice.sort((a, b) => a - b);
    var slider = document.getElementById("myRange");
    var output = document.getElementById("showBar");
    // Display the default slider value
    $('#myRange').attr("min", sortedMinMaxPrice[0]);
    $('#myRange').attr("max", sortedMinMaxPrice[sortedMinMaxPrice.length - 1]);
    $('#myRange').attr("value", sortedMinMaxPrice[sortedMinMaxPrice.length - 1]);
    output.innerHTML = "Maximum Price: " + (slider.value / 100);
    // Update the current slider value (each time you drag the slider handle)
    slider.onchange = function () {
        output.innerHTML = "Maximum Price: " + (this.value / 100);
        handleChange();
    }
}


function handleChange() {
    $('.row.searchResults').empty();
    generateSearchResults(booksByKeyword2.filter(filter))

}

function filter(book) {            
        
        if($('#myRange').val() < book.price) {
            console.log("mpike");
            return false;
        }
        
        return document.getElementById(book.authorsCollection[0].firstName + " " + book.authorsCollection[0].lastName).checked
        && document.getElementById(book.publisherId.name).checked
        && document.getElementById(book.publicationYear).checked
        && document.getElementById(book.languageId.name).checked;
}











