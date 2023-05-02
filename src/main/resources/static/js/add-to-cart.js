const cartBtnArray = [...document.querySelectorAll(".add-to-cart-btn")];
const cartNotifications = document.querySelectorAll(".notification.cart");
const cart = JSON.parse(window.localStorage.getItem("cart")) || [];

const favBtnArray = document.querySelectorAll(".add-to-favorites-btn");
const favNotifications = document.querySelectorAll(".notification.favorites");
const favorites = JSON.parse(window.localStorage.getItem("favorites")) || [];
initCartNotification(cartNotifications);
console.log(cart)

document.addEventListener('DOMContentLoaded', function() {
    // initCartNotification(cartNotifications);
    initFavNotification(favNotifications);
    initCartButtons(cartBtnArray);
}, false);

// window.onload = () => {
//
//
// }


cartBtnArray.forEach((button) => {
    console.log(button)
    button.addEventListener('click', function (e) {
        const btn = e.target;
        const itemIndex = cart.findIndex(item => item.id === parseInt(btn.dataset.id));


        console.log(btn.classList.contains('added'))
        if (btn.classList.contains('added')){
            cart.splice(itemIndex,1)
            btn.classList.remove('added')
            button.innerHTML='Add to cart'
            updateCartNotification(false);
            // alert(btn)
        } else {
            btn.classList.add('added')
            button.innerHTML='Remove from cart'
            // if (itemIndex > -1) {
            //     cart[itemIndex].quantity++;
            // } else {
                let newItem = {
                    id: parseInt(btn.dataset.id),
                    quantity: 1
                };
                cart.push(newItem);
            updateCartNotification(true);
            // }
        }



        window.localStorage.setItem("cart", JSON.stringify(cart));
    })
})

function updateCartNotification(isIncrement) {
    let count = cartProductCount();
    if (isIncrement){
        if (cart.length === 0) {
            cartNotifications.forEach(n=>n.style.display = "flex");
            cartNotifications.forEach(n=>n.innerHTML = "1");
        } else {
            cartNotifications.forEach(n=>n.style.display = "flex");
            console.log(count)
            if (count > 99) {

                cartNotifications.forEach(n=>n.innerHTML = "99+");
            } else {
                cartNotifications.forEach(n=>n.innerHTML = (count ).toString());
            }
        }
    } else {
        if (count===0){
            cartNotifications.forEach(n=>n.style.display = "none");
            cartNotifications.forEach(n=>n.innerHTML = "0");
        } else {
            cartNotifications.forEach(n=>n.innerHTML = "" +(count))
        }
    }


}


function cartProductCount() {
    let count = 0;
    for (let i = 0; i < cart.length; i++) {
        console.log(cart[i])
        // let item = JSON.parse(cart[i]);
        count += 1;
    }
    return count;
}


function initCartNotification(notification) {
    const count = cartProductCount();
    if (count !== 0) {
        cartNotifications.forEach(n=>n.style.display = "flex");
        if (count < 99) {
            cartNotifications.forEach(n=>n.innerHTML = count.toString());
        } else {
            cartNotifications.forEach(n=>n.innerHTML = "99+");
        }

    }
}

function initCartButtons(cartButtons){
    alert(Array.isArray(cartButtons))
    for (let i = 0; i <cart.length ; i++) {
        // alert((cart[i]).id)
       const index =  cartButtons.findIndex(b=>parseInt(b.dataset.id)===cart[i].id);
        if (index>-1){
            cartButtons[index].classList.add('added')
            cartButtons[index].innerHTML = 'Remove from cart'
        }
    }
}










favBtnArray.forEach((btn) => {
    btn.addEventListener('click', function () {

        updateFavNotification();

        const itemIndex = favorites.findIndex(item => item.id === parseInt(btn.dataset.id));

        if (itemIndex > -1) {
            favorites[itemIndex].quantity++;
        } else {
            let newItem = {
                id: parseInt(btn.dataset.id),
                quantity: 1
            };
            favorites.push(newItem);
        }
        window.localStorage.setItem("favorites", JSON.stringify(favorites));

    })
})

function updateFavNotification() {
    if (favorites.length === 0) {
        favNotifications.forEach(n=>n.style.display = "flex");
        favNotifications.forEach(n=>n.innerHTML = "1");
    } else {
        let count = favProductCount();
        console.log(count)
        if (count > 99) {
            favNotifications.forEach(n=>n.innerHTML = "99+");
        } else {
            favNotifications.forEach(n=>n.innerHTML = (count + 1).toString());
        }
    }

}


function favProductCount() {
    let count = 0;
    for (let i = 0; i < favorites.length; i++) {
        console.log(favorites[i])
        // let item = JSON.parse(cart[i]);
        count += parseInt(favorites[i].quantity);
    }
    return count;
}


function initFavNotification(notifications) {
    const count = favProductCount();
    if (count !== 0) {
        favNotifications.forEach(n=>n.style.display = "flex");
        if (count < 99) {
            favNotifications.forEach(n=>n.innerHTML = count.toString());
        } else {
            favNotifications.forEach(n=>n.innerHTML = "99+");
        }

    }
}
