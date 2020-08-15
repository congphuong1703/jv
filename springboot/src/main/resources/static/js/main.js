
const form = document.querySelector('.form-block');
const fullName = document.querySelector('#full-name');
const userName = document.querySelector('#username');
const email = document.querySelector('#email');
const password = document.querySelector('#password');
const confirmPassword = document.querySelector('#confirm-password');
const error = document.querySelector('.error-input');
/*
form.addEventListener('submit', (e) => {

    let message = [];
    if (fullName.value === '' || fullName.value == null) {
        message.push('Full name is required !');
    }

    if (password.value < 8) {
        message.push('Password must greater than 8 characters !');
    }
    if (message.length > 0 ){
        e.preventDefault();
        error.innerText = message.join(', ');
    }
})*/
