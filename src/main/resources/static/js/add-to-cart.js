const addBtnArray = document.querySelectorAll(".add-to-cart-btn");
const cartNotification = document.querySelector(".notification.cart");
const cart = JSON.parse(window.localStorage.getItem("cart")) || [];

console.log(cart)

window.onload = () => {
    initCartNotification(cartNotification);
}


addBtnArray.forEach((btn) => {
    btn.addEventListener('click', function () {

        updateCartNotification();

        const itemIndex = cart.findIndex(item => item.id === parseInt(btn.dataset.id));

        if (itemIndex > -1) {
            cart[itemIndex].quantity++;
        } else {
            let newItem = {
                id: parseInt(btn.dataset.id),
                quantity: 1
            };
            cart.push(newItem);
        }
        window.localStorage.setItem("cart", JSON.stringify(cart));
    })
})

function updateCartNotification() {
    if (cart.length === 0) {
        cartNotification.style.display = "flex";
        cartNotification.innerHTML = "1"
    } else {
        let count = cartProductCount();
        console.log(count)
        if (count > 99) {
            cartNotification.innerHTML = "99+"
        } else {
            cartNotification.innerHTML = (count + 1).toString();
        }
    }

}


function cartProductCount() {
    let count = 0;
    for (let i = 0; i < cart.length; i++) {
        console.log(cart[i])
        // let item = JSON.parse(cart[i]);
        count += parseInt(cart[i].quantity);
    }
    return count;
}


function initCartNotification(notification) {
    const count = cartProductCount();
    if (count !== 0) {
        cartNotification.style.display = "flex";
        if (count < 99) {
            cartNotification.innerHTML = count.toString();
        } else {
            cartNotification.innerHTML = "99+"
        }

    }
}
