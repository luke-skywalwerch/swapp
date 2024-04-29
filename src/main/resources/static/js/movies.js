
document.getElementById('directorInput').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        searchMovies();
    }
});

function buildSearchQuery() {
    const director = document.getElementById('directorInput').value.trim();
    const minDuration = document.getElementById('minDurationInput').value.trim();
    const maxDuration = document.getElementById('maxDurationInput').value.trim();

    let queryParts = [];

    if (director) {
        queryParts.push(`director=${encodeURIComponent(director)}`);
    }
    if (minDuration) {
        queryParts.push(`minDuration=${encodeURIComponent(minDuration)}`);
    }
    if (maxDuration) {
        queryParts.push(`maxDuration=${encodeURIComponent(maxDuration)}`);
    }

    return queryParts.length > 0 ? `?${queryParts.join('&')}` : '';
}

function searchMovies() {
    const query = buildSearchQuery();
    window.location.href = '/movies' + query;
}

document.addEventListener('DOMContentLoaded', function () {
    const query = buildSearchQuery();
    history.pushState({}, '', '/movies' + query);
});
