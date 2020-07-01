let count = 20.5;

function changeUsernameIsBusy() {
    document.getElementById("changeUsernameIsBusy").style.display = "block";
    document.querySelector("main").style.marginTop = count + 3.5 + "rem";
}

function changeUsernameInvalidPassword() {
    document.getElementById("changeUsernameInvalidPassword").style.display = "inline-block";
    document.querySelector("main").style.marginTop = count + 3.5 + "rem";
}

function changeEmailIsBusy() {
    document.getElementById("changeEmailIsBusy").style.display = "block";
    document.querySelector("main").style.marginTop = count + 3.5 + "rem";
}

function changeEmailInvalidPassword() {
    document.getElementById("changeEmailInvalidPassword").style.display = "inline-block";
    document.querySelector("main").style.marginTop = count + 3.5 + "rem";
}

function changePasswordInvalidPassword() {
    document.getElementById("changePasswordInvalidPassword").style.display = "inline-block";
    document.querySelector("main").style.marginTop = count + 3.5 + "rem";
}

function deleteAccountInvalidPassword() {
    document.getElementById("deleteAccountInvalidPassword").style.display = "inline-block";
    document.querySelector("main").style.marginTop = count + 3.5 + "rem";
}

function changePasswordMismatch() {
    let form = document.getElementById("changePasswordForm");
    let password = document.getElementById("newPassword").value;
    let repeatedPassword = document.getElementById("repeatNewPassword").value;
    if (password !== repeatedPassword) {
        document.getElementById("changePasswordPasswordMismatch").style.display = "block";
        document.querySelector("main").style.marginTop = count + 4.5 + "rem";
    } else {
        form.submit();
    }
}

function deleteAccountPasswordMismatch() {
    let form = document.getElementById("deleteAccountForm");
    let password = document.getElementById("deleteAccountPassword").value;
    let repeatedPassword = document.getElementById("deleteAccountRepeatPassword").value;
    if (password !== repeatedPassword) {
        document.getElementById("deleteAccountPasswordMismatch").style.display = "block";
        document.querySelector("main").style.marginTop = count + 4.5 + "rem";
    } else {
        form.submit();
    }
}