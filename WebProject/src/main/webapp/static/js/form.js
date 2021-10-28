function checkEmptyInput(element) {
    let submitButton = document.getElementById("submit-button");
    submitButton.disable = element.value === "";
}