document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('pageNumber').textContent = currentPage;

    const characterLinks = document.querySelectorAll('.character-link');
    let lastOpened = null;

    characterLinks.forEach(link => {
        link.addEventListener('click', function () {
            const characterId = this.getAttribute('data-uid');
            const detailsDiv = document.getElementById('character-details-' + characterId);

            if (lastOpened && lastOpened !== detailsDiv) {
                lastOpened.classList.remove('open');
            }

            if (detailsDiv.classList.contains('open')) {
                detailsDiv.classList.remove('open');
            } else {
                fetchCharacterDetails(characterId, detailsDiv);
            }

            lastOpened = detailsDiv;
        });
    });
});

function fetchCharacterDetails(id, detailsDiv) {
    fetch('/api/character/' + id)
        .then(response => response.json())
        .then(data => {
            detailsDiv.innerHTML = `
                <p>Height: ${data.height}</p>
                <p>Mass: ${data.mass}</p>
                <p>Hair Color: ${data.hair_color}</p>
            `;

            detailsDiv.classList.add('open');
        });
}

var currentPage = new URLSearchParams(window.location.search).get('page') || 1;

function changePage(delta) {
    currentPage = parseInt(currentPage) + delta;
    currentPage = Math.max(1, currentPage);

    window.location.href = '/characters?page=' + currentPage;
}
