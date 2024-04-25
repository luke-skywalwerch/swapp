document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("addMovieForm").addEventListener("submit", function (event) {
        event.preventDefault();
        var movieInfo = document.getElementById("movieInfo").value;

        var formData = {
            movieInfo: movieInfo
        };

        var jsonData = JSON.stringify(formData);
        fetch("/movies/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonData
        }).then(response => {
            if (response.ok) {
                window.location.href = "/movies";
            } else {
                return response.text().then(text => {
                    alert("Error: " + text)
                });
            }
        }).catch(error => {
            alert("Error: " + error)
        });
    });
});