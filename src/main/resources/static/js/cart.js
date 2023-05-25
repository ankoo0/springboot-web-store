const items = JSON.parse(window.localStorage.getItem("cart")) || [];

window.onload = () => {
    initShoppingCart();


}

function initShoppingCart() {
    fetch('/main/cart-items', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(items)
    }).then(res => res.json())
        .then(function (res) {
            console.log(res);
            createProductCarts(res)
            createRemoveButtons();
            createIncrementButtons()
            createDecrementButtons()

        });
//src="data:image/png;base64,${json.img}
}


function createIncrementButtons() {
    const incrementButtons = document.querySelectorAll(".increment-btn[data-itemid]");
    incrementButtons.forEach((btn) => {
        btn.addEventListener("click", () => {
            const itemId = parseInt(btn.dataset.itemid);
            const selector = ".quantity-input[data-itemid=" + "\"" + itemId + "\"" + "]";
            const quantityinput = document.querySelector(selector)
            let quantity = parseInt(quantityinput.value);
            if (quantity + 1 > 99) {
                quantityinput.value = "99";
            } else {
                quantityinput.value = (quantity + 1).toString();
                changeQuantity(true, itemId)
            }

            console.log(incrementButtons)
        })
    })
}


function createDecrementButtons() {
    const decrementButtons = document.querySelectorAll(".decrement-btn[data-itemid]");
    decrementButtons.forEach((btn) => {
        btn.addEventListener("click", () => {
            const itemId = parseInt(btn.dataset.itemid);
            const selector = ".quantity-input[data-itemid=" + "\"" + itemId + "\"" + "]";
            const quantityinput = document.querySelector(selector)
            let quantity = parseInt(quantityinput.value);
            if (quantity - 1 < 1) {
                quantityinput.value = "1";
            } else {
                quantityinput.value = (quantity - 1).toString();
                changeQuantity(false, itemId)
            }

            console.log(decrementButtons)
        })
    })
}


function changeQuantity(isIncremental, itemId) {
    if (isIncremental) {
        for (let i = 0; i < items.length; i++) {
            if (items[i].id === itemId) {
                if (!(parseInt(items[i].quantity) >= 99)) {
                    items[i].quantity = parseInt(items[i].quantity) + 1;
                    window.localStorage.setItem("cart", JSON.stringify(items))
                }
            }
        }
    } else {
        for (let i = 0; i < items.length; i++) {

            if (items[i].id === itemId) {
                // alert(!(parseInt(items[i].quantity)<=1))
                if ((parseInt(items[i].quantity) > 1)) {
                    items[i].quantity = parseInt(items[i].quantity) - 1;
                    window.localStorage.setItem("cart", JSON.stringify(items))
                }
            }
        }
    }
}


function createRemoveButtons() {
    const cards = document.querySelectorAll('.cart-item')
    const removeButtons = document.querySelectorAll(".delete-btn");

    cards.forEach(card => {
        card.style.transition = 'all 0.5s ease-in-out';
    });
    removeButtons.forEach(button => {
        button.addEventListener("click", function () {
            console.log("hello")
            const itemId = parseInt(button.dataset.itemid)
            console.log(itemId)
            // const storageIndex = items.find(item=>item.id===itemId).id;
            for (let i = 0; i < items.length; i++) {
                console.log(items[i].id);
                if (items[i].id === itemId) {
                    items.splice(i, 1)
                    window.localStorage.setItem("cart", JSON.stringify(items));
                    const itemCard = document.getElementById(itemId.toString());

                    itemCard.classList.add('removing')
                    // itemCard.parentNode.removeChild(itemCard);
                    itemCard.addEventListener('transitionend', () => {
                        itemCard.remove();
                        // setTimeout(()=>{},30)
                        // cards.forEach(card => {
                        //     card.style.width = `calc(${100 / cards.length}% - 10px)`;
                        // });
                    });
                    console.log(itemId)
                    console.log(items)
                }
            }


        })
    })
}


function createProductCarts(productsJson) {
    for (let i = 0; i < productsJson.length; i++) {
        let product = productsJson[i];
        document.querySelector(".cart-items-container").insertAdjacentHTML('afterbegin',
            `
                        <div class="cart-item" id="${product.id}">
                       
                            <div class="product-img-container">
                              <img src="/images/${product.mainThumbnailPath}" alt="item">
                            </div>   
                             
                            <div class="product-info">
                              <h3>${product.name} </h3>
                              <p>${product.shortDescription}</p>
                            </div>                 
                            
                            <div class="item-actions-container">
                            <div class="quantity">
                            <button class="decrement-btn" data-itemid="${product.id}">-</button>
                            <input value="${parseInt(items[i].quantity)}" class="quantity-input" data-itemid="${product.id}">
                            <button class="increment-btn" data-itemid="${product.id}">+</button>
                          
                            </div>
                            <div class="options-btn-container">
                            <button class="add-to-favorites-btn"><span class="material-symbols-outlined">favorite</span></button>
                            <div class="btn-separator"></div>
                            <button data-itemid="${product.id}" class="delete-btn"><span class="material-symbols-outlined">delete_forever</span></button>
                            </div>
                            </div>
                             
                             <div class="price-container">
                             <h4>${product.price} $</h4>
                             </div>
                        </div>
                 `);

        productCards = document.querySelectorAll('.cart-item')
        productCards.forEach((card, index) => {
            setTimeout(() => {
                card.classList.add('show');
            }, index * 100);
        });
    }
}

