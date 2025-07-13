(() => {
    // hide banner
    $("#hero-banner").hide();
    $("li[name='product']").addClass("active");
    $('.hero__categories ul').slideToggle(400);
})();

const addCart = (productId) => {
    const totalProductCartEle = document.getElementById("total-product-card");
    const currentTotal = Number(totalProductCartEle.innerText);
    totalProductCartEle.textContent = currentTotal + 1;
    // TODO: Implement with session
    // fetch('http://localhost:8080/cart', {
    //     method: 'POST', 
    //     headers: {
    //         'Content-Type': 'application/json',
    //     },
    //     body: JSON.stringify({
    //         productId: productId,
    //         userId: 1,
    //     }),
    // })
    //     .then(response => response.json())
    //     .then(data => {
    //         console.log(productId);
    //     })
    //     .catch(error => console.error('Error:', error));
};