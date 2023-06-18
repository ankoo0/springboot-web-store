const cartBtnArray = [...document.querySelectorAll(".add-to-cart-btn")];
const cartNotifications = document.querySelectorAll(".notification.cart");
const cart = JSON.parse(window.localStorage.getItem("cart")) || [];

const favBtnArray = [...document.querySelectorAll(".add-to-favorites-btn")];
const favNotifications = document.querySelectorAll(".notification.favorites");
const favorites = JSON.parse(window.localStorage.getItem("favorites")) || [];
initCartNotification(cartNotifications);
initFavNotification(favNotifications)
initFavButtons(favBtnArray)
initCartButtons(cartBtnArray);



cartBtnArray.forEach((button) => {

    button.addEventListener('click', function (e) {

        const itemIndex = cart.findIndex(item => item.id === parseInt(button.dataset.id));


        if (button.classList.contains('added')) {
            cart.splice(itemIndex, 1)
            button.classList.remove('added')
            button.innerHTML = 'Add to cart'
            updateCartNotification(false);
        } else {
            button.classList.add('added')
            button.innerHTML = 'In Cart'

            let newItem = {
                id: parseInt(button.dataset.id),
                quantity: 1
            };
            cart.push(newItem);
            updateCartNotification(true);
        }


        window.localStorage.setItem("cart", JSON.stringify(cart));
    })
})

function updateCartNotification(isIncrement) {
    let count = cartProductCount();
    if (isIncrement) {
        if (cart.length === 0) {
            cartNotifications.forEach(n => n.style.display = "flex");
            cartNotifications.forEach(n => n.innerHTML = "1");
        } else {
            cartNotifications.forEach(n => n.style.display = "flex");
            console.log(count)
            if (count > 99) {

                cartNotifications.forEach(n => n.innerHTML = "99+");
            } else {
                cartNotifications.forEach(n => n.innerHTML = (count).toString());
            }
        }
    } else {
        if (count === 0) {
            cartNotifications.forEach(n => n.style.display = "none");
            cartNotifications.forEach(n => n.innerHTML = "0");
        } else {
            cartNotifications.forEach(n => n.innerHTML = "" + (count))
        }
    }


}


function cartProductCount() {
    let count = 0;
    for (let i = 0; i < cart.length; i++) {
        count += 1;
    }
    return count;
}


function initCartNotification(notification) {
    const count = cartProductCount();
    if (count !== 0) {
        cartNotifications.forEach(n => n.style.display = "flex");
        if (count < 99) {
            cartNotifications.forEach(n => n.innerHTML = count.toString());
        } else {
            cartNotifications.forEach(n => n.innerHTML = "99+");
        }

    }
}

function initCartButtons(cartButtons) {

    for (let i = 0; i < cart.length; i++) {
        const index = cartButtons.findIndex(b => parseInt(b.dataset.id) === cart[i].id);
        if (index > -1) {
            cartButtons[index].classList.add('added')
            cartButtons[index].innerHTML = 'In Cart'
        }
    }
}






favBtnArray.forEach((button) => {

    button.addEventListener('click', function (e) {

        const itemIndex = favorites.findIndex(item => item.id === parseInt(button.dataset.id));
        console.log(itemIndex)

        if (button.classList.contains('added')) {
            favorites.splice(itemIndex, 1)
            button.classList.remove('added')
            // button.innerHTML = 'Add to cart'
            updateFavNotification(false)
        } else {
            button.classList.add('added')
            // button.innerHTML = 'In Cart'

            let newItem = {
                id: parseInt(button.dataset.id)
            };
            favorites.push(newItem);
            updateFavNotification(true)
        }


        window.localStorage.setItem("favorites", JSON.stringify(favorites));
    })
})

function updateFavNotification(isIncrement) {
    let count = favProductCount();
    if (isIncrement) {
        if (favorites.length === 0) {
            favNotifications.forEach(n => n.style.display = "flex");
            favNotifications.forEach(n => n.innerHTML = "1");
        } else {
            favNotifications.forEach(n => n.style.display = "flex");
            console.log(count)
            if (count > 99) {

                favNotifications.forEach(n => n.innerHTML = "99+");
            } else {
                favNotifications.forEach(n => n.innerHTML = (count).toString());
            }
        }
    } else {
        if (count === 0) {
            favNotifications.forEach(n => n.style.display = "none");
            favNotifications.forEach(n => n.innerHTML = "0");
        } else {
            favNotifications.forEach(n => n.innerHTML = "" + (count))
        }
    }


}


function favProductCount() {
    let count = 0;
    for (let i = 0; i < favorites.length; i++) {
        count += 1;
    }
    return count;
}


function initFavNotification(notification) {
    const count = favProductCount();

    if (count !== 0) {
        favNotifications.forEach(n => n.style.display = "flex");
        if (count < 99) {
            favNotifications.forEach(n => n.innerHTML = count.toString());
        } else {
            favNotifications.forEach(n => n.innerHTML = "99+");
        }

    }
}

function initFavButtons(cartButtons) {

    for (let i = 0; i < favorites.length; i++) {
        const index = favBtnArray.findIndex(b => parseInt(b.dataset.id) === favorites[i].id);
        if (index > -1) {
            favBtnArray[index].classList.add('added')
            // cartButtons[index].innerHTML = 'In Cart'
        }
    }
}






