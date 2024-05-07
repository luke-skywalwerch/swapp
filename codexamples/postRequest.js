// add event for when DOM is already rendered, this is used to avoid accessing an element that is not rendered yet
document.addEventListener("DOMContentLoaded", function () {

    // add an event to element with id=submitButton in the "submit" action, in this case the button is defined in html
    // with type=submit, but the event could also be "click" or any other if its not a submit
    document.getElementById("submitButton").addEventListener("submit", function (event) {

        // this stops default behaviour, here it is used to stop the default submit due to the type=submit in the button
        event.preventDefault();

        // get the value of the inputs with id=name and id=surname
        var name = document.getElementById("name").value;
        var surname = document.getElementById("surname").value;

        // create an object to submit 
        var formData = {
            name: name,
            surname: surname
        };

        // build a json from the object
        var jsonData = JSON.stringify(formData);

        // make the request, method POST to the url /booking
        fetch("/booking", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonData
        }).then(response => {
            // handle the success if needed
            alert("success: " + response)
        }).catch(error => {
            // handle the error if needed
            alert("Error: " + error)
        });
    });
});