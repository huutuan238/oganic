const register = () => {
    console.log('register');
    const form = document.querySelector('#register-form');
    const userData = Object.fromEntries(new FormData(form).entries());
    fetch('http://localhost:8080/register', {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData),
    })
        .then(response => response.json())
        .then(data => {
            console.log("registedddd!!!");
        })
        .catch(error => console.error('Error:', error));
}