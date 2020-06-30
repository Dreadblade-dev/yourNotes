function usernameIsBusy() {
    document.getElementById("usernameIsBusy").style.display = "inline-block";
}

function emailIsBusy() {
    document.getElementById("emailIsBusy").style.display = "inline-block";
}

function passwordMismatch() {
    let form = document.getElementById("signUpForm");
    let password = document.getElementById("inputPassword").value;
    let repeatedPassword = document.getElementById("inputRepeatPassword").value;
    if (password !== repeatedPassword) {
        document.getElementById("passwordMismatch").style.display = "inline-block";
    } else {
        form.submit();
    }
}