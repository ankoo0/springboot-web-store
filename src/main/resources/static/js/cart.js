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


        });
//src="data:image/png;base64,${json.img}
}


function createRemoveButtons(){
    const removeButtons = document.querySelectorAll(".delete-btn");

    removeButtons.forEach(button =>{
        button.addEventListener("click", function (){
            console.log("hello")
            const itemId = parseInt(button.dataset.itemid)
            console.log(itemId)
            // const storageIndex = items.find(item=>item.id===itemId).id;
            for (let i = 0; i <items.length ; i++) {
                console.log(items[i].id);
                if (items[i].id ===itemId){
                    items.splice(i,1)
                    window.localStorage.setItem("cart",JSON.stringify(items));
                   const itemCard =  document.getElementById(itemId.toString());
                    itemCard.parentNode.removeChild(itemCard);
                    console.log(itemId)
                    console.log(items)
                }
            }
            // console.log(storageIndex)
            // console.log(items);
            // if (storageIndex!==-1){
            //
            //     items.splice(storageIndex,1)
            //     window.localStorage.setItem("cart",JSON.stringify(items));
            //     document.getElementById("#"+itemId).remove();
            //     console.log(items)
            // }
        })
    })
}



function createProductCarts(productsJson){
    for (let i = 0; i < productsJson.length; i++) {
        let product = productsJson[i];
        document.querySelector(".main-content > section").insertAdjacentHTML('afterbegin',
            `
                        <div id="${product.id}" style="border: 1px solid #333333; padding: 10px; margin: 20px">
                            <h3>${product.name} </h3>
                            <p>${product.shortDescription} </p>
                            <p>${product.price} </p>
                            <img  style="max-height: 200px; max-width: 200px" src="/images/${product.mainThumbnailPath}" alt="item">
                            <button data-itemid="${product.id}" class="delete-btn">remove</button>
                            <div style="display: flex">
                            <button>+</button>
                            <input>
                            <button>-</button>
                            </div>
                        </div>
                        `);
    }
}

