 $(document).ready(function() {
    delete Object.prototype.toJSON;
    delete Array.prototype.toJSON;
    //delete Hash.prototype.toJSON;
    delete String.prototype.toJSON;
 })       


$(document).ready(function () {    
    $("#bookForm").on('submit', function (event) {
        event.preventDefault();       
        var formData = {};        
        $("#bookForm").find(":input").each(function () {
                if(this.name === "authorsCollection" || this.name === "genreId" 
                || this.name === "languageId" || this.name === "publisherId"){
                    formData[this.name] = JSON.parse($(this).val());
                } else {
                    formData[this.name] = $(this).val();
                }
                  
        })                
        $.ajax({
            type: "POST",
            url: "tier3/books",
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: "application/json;",
            statusCode: {
                201: function () {
                    alert("Book created successfully");
                }
            }
        });
    });
});

$(document).ready(function createGenreSelect() {
    var allGenre;
    $.ajax({
        url: "tier3/genre",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find any genre!");
        },
        success: function (data) {
            allGenre = data;
            generateGenreSelect(allGenre);
        }
    });
});

$(document).ready(function createLanguageSelect() {
    var allLanguages;
    $.ajax({
        url: "tier3/languages",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find any languages!");
        },
        success: function (data) {
            allLanguages = data;
            generateLanguageSelect(allLanguages);
        }
    });
});

$(document).ready(function createPublisherSelect() {
    var allPublishers;
    $.ajax({
        url: "tier3/publishers",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find any publishers!");
        },
        success: function (data) {
            allPublishers = data;
            generatePublisherSelect(allPublishers);
        }
    });
});

$(document).ready(function createAuthorSelect() {
    var allAuthors;
    $.ajax({
        url: "tier3/authors",
        data: {
            format: 'json'
        },
        error: function () {
            alert("Could not find any authors!");
        },
        success: function (data) {
            allAuthors = data;
            generateAuthorSelect(allAuthors);
        }
    });
});

function generateGenreSelect(allGenre) {
    for (var i = 0; i < allGenre.length; i++)
        $('#genreId').append(
            "<option value= '" + JSON.stringify(allGenre[i]) + "'>" + allGenre[i].name + "</option>"
        )
}

function generateLanguageSelect(allLanguages) {
    for (var i = 0; i < allLanguages.length; i++)
        $('#languageId').append(
            "<option value= '" + JSON.stringify(allLanguages[i]) + "'>" + allLanguages[i].name + "</option>"
        )
}

function generatePublisherSelect(allPublishers) {
    for (var i = 0; i < allPublishers.length; i++)
        $('#publisherId').append(
            "<option value= '" + JSON.stringify(allPublishers[i]) + "'>" + allPublishers[i].name + "</option>"
        )
}

function generateAuthorSelect(allAuthors) {
    for (var i = 0; i < allAuthors.length; i++) {
        
        var authorsCollection = [(allAuthors[i])];
        $('#authorsCollection').append(
            "<option value= '" + JSON.stringify(authorsCollection) + "'>"
            + allAuthors[i].firstName + " " + allAuthors[i].lastName + "</option>");
        //console.log(JSON.stringify(allAuthors[i]));
    }


}