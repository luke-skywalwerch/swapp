document.getElementById('directorInput').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        searchMovies();
    }
});

function searchMovies() {
    const director = document.getElementById('directorInput').value;
    window.location.href = '/movies?director=' + encodeURIComponent(director);
}
