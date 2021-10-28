function checkSamePasswords() {
    let password = document.getElementById("password").value;
    let repeatedPassword = document.getElementById("repeated_password").value;
    let submitButton = document.getElementById("submit-button")
    submitButton.disabled = password !== repeatedPassword;
}