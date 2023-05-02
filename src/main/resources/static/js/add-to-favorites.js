const favBtnArray = document.querySelectorAll(".add-to-favorites-btn");
const favNotifications = document.querySelectorAll(".notification.favorites");
const favorites = JSON.parse(window.localStorage.getItem("favorites")) || [];

console.log(favorites)

window.onload = () => {
    initFavNotification(favNotifications);
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
