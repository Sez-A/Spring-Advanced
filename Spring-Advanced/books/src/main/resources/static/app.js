$('#loadBooks').click(() => {
    reloadBooks()
});

function reloadBooks() {
    $("#authors-container").empty();

    fetch("http://localhost:8080/books")
        .then(response => response.json())
        .then(json => json.forEach(book => {
            let tableRow = '<tr>' +
                '<td>' + book.title + '</td>' +
                '<td>' + book.author.name + '</td>' +
                '<td>' + book.isbn + '</td>' +
                '<td>' +
                '<button class="edit-btn" data-book-id="' + book.id + '"> Edit </button>' +
                '<button class="delete-btn" data-book-id="' + book.id + '"> Delete </button>' +
                '</td>' +
                '</tr>'
            $("#authors-container").append(tableRow)
        }))
}

$('body').on('click', 'button.delete-btn', function () {
    let bookId = $(this).data('book-id');
    fetch('http://localhost:8080/books/' + bookId, {
        method: 'DELETE'
    }).then(_ => reloadBooks())
});

// $(document).ready(function () {
//     let book = {};
//     $('#submit-btn').click(function () {
//         book.title = $('title').val();
//         book.author = $('author').val();
//         book.isbn = $('isbn').val();
//         let bookObj = JSON.stringify(book);
//         $.ajax({
//             url:  'http://localhost:8080/books/createBook',
//             method: 'POST',
//             data: bookObj,
//             contentType: 'application/json',
//             success: function () {
//                 alert("Saved successfully")
//             },
//             error: function (error) {
//                 alert(error)
//             }
//         })
//     })
// })

$(document).ready(
    function () {

        // SUBMIT FORM
        $("#bookForm").submit(function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                title: $("#title").val(),
                isbn: $("#isbn").val(),
                //author: $("#author").val()
            }

            // DO POST
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "http://localhost:8080/books/createBook",
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function (result) {
                    if (result.status == "success") {
                        $("#postResultDiv").html(
                            "" + result.data.bookName
                            + "Post Successfully! <br>"
                            + "---> Congrats !!" + "</p>");
                    } else {
                        $("#postResultDiv").html("<strong>Error</strong>");
                    }
                    console.log(result);
                },
                error: function (e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }
            });

        }
    })