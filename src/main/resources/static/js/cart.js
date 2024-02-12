const items = JSON.parse(window.localStorage.getItem("cart")) || [];

initShoppingCart();


function initShoppingCart() {
    if (items.length===0){
        const cartContainer = document.querySelector('.cart-container')
        cartContainer.innerHTML=''
        // cartContainer.style.justifyContent='center'
        // cartContainer.style.alignItems='center'
        // cartContainer.style.flexDirection='column'
        const noCartItems = document.createElement('div')
        const noItemsText = document.createElement('h3')
        noItemsText.innerText='There is nothing in your cart ☹️'
        noCartItems.appendChild(noItemsText)
        cartContainer.appendChild(noCartItems)

    }else {
        fetch('/cart/items', {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(items)
        }).then(res => res.json())
            .then( res => {

                // alert(JSON.stringify(res));
                createProductCarts(res)
                createRemoveButtons();
                createIncrementButtons()
                createDecrementButtons()
                cardsInitAnimation()
                changeTotalPrice()

            });

    }

//src="data:image/png;base64,${json.img}
}

function cardsInitAnimation(){
    let productCards = document.querySelectorAll('.cart-item')
    productCards.forEach((card, index) => {
        setTimeout(() => {
            card.classList.add('show');
        }, index * 100);
    });
}

function changeTotalPrice(){
    const prices = document.querySelectorAll('.price-container > .price')

    let totalSum = 0;
    prices.forEach(price =>{
        const priceText = price.textContent;
        const priceValue = parseFloat(priceText.replace('$', ''));
        totalSum += priceValue;
        // alert(totalSum)
    })

    // alert(document.querySelector('.total-pricing > h4').innerHTML)
    document.querySelector('.total-pricing > h4').innerHTML='Total: ' + parseFloat(totalSum).toFixed(2) + ' $'

}


function createIncrementButtons() {
    const incrementButtons = document.querySelectorAll(".increment-btn[data-itemid]");
    incrementButtons.forEach((btn) => {
        btn.addEventListener("click", () => {
            const itemId = parseInt(btn.dataset.itemid);
            const selector = ".quantity-input[data-itemid=" + "\"" + itemId + "\"" + "]";
            const quantityInput = document.querySelector(selector)
            let quantity = parseInt(quantityInput.value);
            if ((quantity + 1) > 99) {
                quantityInput.value = "99";
            } else {
                quantityInput.value = (quantity + 1).toString();
                changeQuantity(true, itemId)
            }

            changeItemPrice(itemId)
        })
    })
}


function createDecrementButtons() {
    const decrementButtons = document.querySelectorAll(".decrement-btn[data-itemid]");
    decrementButtons.forEach((btn) => {
        btn.addEventListener("click", () => {
            const itemId = parseInt(btn.dataset.itemid);
            const selector = ".quantity-input[data-itemid=" + "\"" + itemId + "\"" + "]";
            const quantityInput = document.querySelector(selector)
            let quantity = parseInt(quantityInput.value);
            if ((quantity - 1) < 1) {
                quantityInput.value = "1";
            } else {
                quantityInput.value = (quantity - 1).toString();
                changeQuantity(false, itemId)
            }

            changeItemPrice(itemId)
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

    // cards.forEach(card => {
    //     card.style.transition = 'all 0.5s ease-in-out';
    // });
    removeButtons.forEach(button => {
        button.addEventListener("click", function () {
            const itemId = parseInt(button.dataset.itemid)
            for (let i = 0; i < items.length; i++) {
                if (items[i].id === itemId) {
                    items.splice(i, 1)
                    window.localStorage.setItem("cart", JSON.stringify(items));
                    removeItem(itemId)
                }
            }
        })
    })
}

function removeItem(itemId){
    const itemCard = document.getElementById(itemId.toString());
    itemCard.classList.add('removing')
    itemCard.addEventListener('transitionend', () => {
        itemCard.remove();
        changeTotalPrice()
    });
}


function createProductCarts(productsJson) {
    for (let i = 0; i < productsJson.length; i++) {
        let product = productsJson[i];
        const productId = product.id;
        const subcategory = product.subcategory.toLowerCase();
        const category = product.category.toLowerCase();
        // alert(category + " " + subcategory)
        document.querySelector(".cart-items-container").insertAdjacentHTML('afterbegin',
            `
                        <div class="cart-item" id="${product.id}">
                       
                            <div class="product-img-container">
                              <img src="/images/${product.mainThumbnailPath}" alt="item">
                            </div>   
                             
                            <div class="product-info">
                              <a class="product-link" href="main/${category}/${subcategory}/${productId}"><h3>${product.name}</h3></a>
<!--                              <p>${product.shortDescription}</p>-->
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
                             <span class="price" style="transition: all 0.3s;" data-itemid="${product.id}" >${product.price} $</span>
                             </div>
                        </div>
                 `);


    }

}

function changeItemPrice(itemId){

    const item = items.find(item => item.id === itemId);

    fetch('/cart/price', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(item)
    }).then(res => res.json())
        .then(res=>{
            const selector = ".price[data-itemid=" + "\"" + itemId + "\"" + "]";
            const price = document.querySelector(selector)
            price.innerHTML=JSON.stringify(res) + ' $';
            changeTotalPrice()
        })

}

// order - all cart json verify empty json

