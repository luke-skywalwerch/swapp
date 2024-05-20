function addMovie() {
    const title = document.getElementById('title').value;
    const duration = parseInt(document.getElementById('duration').value);
    const actors = document.getElementById('actors').value.split(',').map(actor => actor.trim());
    const directorName = document.getElementById('directorName').value;
    const directorNationality = document.getElementById('directorNationality').value;
    const year = parseInt(document.getElementById('year').value);

    const movie = {
        title: title,
        duration: duration,
        actors: actors,
        details: {
            director: {
                name: directorName,
                nationality: directorNationality
            },
            year: year
        }
    };

    fetch('/movies/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(movie)
    }).then(response => {
        if (response.ok) {
            window.location.href = "/movies";
        } else {
            return response.text().then(text => {
                alert("Error: " + text);
            });
        }
    }).catch(error => {
        alert("Error: " + error);
    });
}

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("addMovieForm").addEventListener("submit", function (event) {
        event.preventDefault();
        var movieInfo = document.getElementById("movieInfo").value;

        var formData = {
            movieInfo: movieInfo
        };

        var jsonData = JSON.stringify(formData);
        fetch("/movies/addjson", {
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
                    alert("Error: " + text);
                });
            }
        }).catch(error => {
            alert("Error: " + error);
        });
    });

    document.getElementById('addMovieButton').addEventListener('click', function (event) {
        event.preventDefault();
        addMovie();
    });
});