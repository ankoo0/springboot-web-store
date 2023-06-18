const items = JSON.parse(window.localStorage.getItem("favorites")) || [];



    initFavorites();




function initFavorites() {
    alert(JSON.stringify(items))
    fetch('/favorites', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(items)
    }).then(res => res.json())
        .then(function (res) {
            alert(JSON.stringify(res))
            createFavoriteCards(res)
            createRemoveButtons();

            const favCards = document.querySelectorAll('.fav-item')
            favCards.forEach((card, index) => {
                setTimeout(() => {
                    card.classList.add('show');
                }, index * 100);
            });

        });

}


function createFavoriteCards(productsJson) {
    for (let i = 0; i < productsJson.length; i++) {
        let product = productsJson[i];
        document.querySelector(".favorites-list").insertAdjacentHTML('afterbegin',
            `
                        <div class="fav-item" id="${product.id}">
                       
                            <div class="product-img-container">
                              <img src="/images/${product.mainThumbnailPath}" alt="item">
                            </div>   
                             
                            <div class="product-info">
                              <h3>${product.name} </h3>
                              <p>${product.shortDescription}</p>
                            </div>                 
                            
                           
                            <div class="options-btn-container">
                            <button class="add-to-cart-btn"><span class="material-symbols-outlined">shopping_cart</span></button>
                            <div class="btn-separator"></div>
                            <button data-itemid="${product.id}" class="delete-btn"><span class="material-symbols-outlined">delete_forever</span></button>
                            </div>
                      
                             
                             <div class="price-container">
                             <h4>${product.price} $</h4>
                             </div>
                        </div>
                 `);





    }
}



function createRemoveButtons() {
    const cards = document.querySelectorAll('.fav-item')
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
                    window.localStorage.setItem("favorites", JSON.stringify(items));
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