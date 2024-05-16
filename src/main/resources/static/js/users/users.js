document.addEventListener('DOMContentLoaded', function () {
    const userList = document.getElementById('userList');

    userList.addEventListener('click', function(event) {
        const target = event.target;
        
        if (target.classList.contains('delete-button')) {
            const userId = target.getAttribute('data-id');
            fetch('/user/' + userId, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    console.log('User deleted successfully');
                    window.location.reload();
                } else {
                    console.error('Failed to delete user');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    });
});