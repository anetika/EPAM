function uploadButtonController(){
    let fileInput = document.getElementById('content');
    let uploadButton = document.getElementById('upload_button');
    uploadButton.disabled = fileInput.value === "";
}