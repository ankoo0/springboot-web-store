const search = document.querySelector(".search-input");
const resetSearchBtn = document.querySelector('.close-search-btn')
const searchBtn = document.querySelector(".search-btn");
const searchModal = document.querySelector(".search-modal");

let typingTimer;                //timer identifier
let doneTypingInterval = 500;  //time in ms (5 seconds)




resetSearchBtn.addEventListener('click',(e)=>{
    e.preventDefault()
    search.value=''
    collapseSearchModal()
    resetSearchBtn.style.visibility='hidden'

})

function collapseSearchModal(){
    searchModal.style.maxHeight='0px'
    searchModal.querySelector('.search-preview').innerHTML=''
    searchModal.querySelector('.found-subcategories').innerHTML=''
}



// const onOutsideClick = (event) => {
//     // const isClickInsideElement = searchModal.contains(event.target);
//     if (!searchModal.contains(event.target)) {
//         searchModal.style.visibility = 'hidden'
//         searchModal.style.maxHeight = '0px'
//         document.removeEventListener('click', onOutsideClick)
//     }
// }
// document.addEventListener('click',onOutsideClick );
//on keyup, start the countdown
search.addEventListener('keyup', () => {
    clearTimeout(typingTimer);

    if (search.value.length>0) {
        resetSearchBtn.style.visibility='visible'
        searchModal.style.maxHeight='500px'
        typingTimer = setTimeout(doneTyping, doneTypingInterval);
    } else {
        resetSearchBtn.style.visibility='hidden'
        searchModal.style.maxHeight='0px'
    }
});



//user is "finished typing," do something
function doneTyping() {

    const query = search.value;

    const queryJSON = {
        "query": query
    }
    console.log(query)
    fetch('/search', {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(queryJSON)
        }
    ).then(res => res.json())
        .then(function (res) {
            console.log(res)
            fillSearchPreview(res)
            document.querySelectorAll('.search-product').forEach((card, index) => {
                setTimeout(() => {
                    card.classList.add('show');
                }, index * 200);
            });
            // document.addEventListener('click',onOutsideClick );

        })

    getProductsCount(queryJSON)
}

function getProductsCount(queryJSON) {
    fetch('/search/count', {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(queryJSON)
        }
    ).then(res => res.json())
        .then(function (res) {
            console.log(res)
            fillSubcategoryCount(res)
        })
}

function fillSubcategoryCount(subcategoryJson) {

    let subcategoryContainer = document.querySelector(".found-subcategories");
    subcategoryContainer.innerHTML = '';
    for (let key in subcategoryJson) {

        const template = `<div class="search-category">
            <div class="search-category-img"><img src="${subcategoryJson[key].categoryImage}" alt="category"/></div>
            <h4 class="search-category-title"><a href="main/${subcategoryJson[key].category.toLowerCase()}/${subcategoryJson[key].subcategoryName.toLowerCase()}?page=1&q=${search.value}">${subcategoryJson[key].subcategoryName}</a></h4>
            <p>Found ${key} products</p>
        </div>`;
        subcategoryContainer.insertAdjacentHTML('afterbegin', template)
    }
}


function fillSearchPreview(productsJson) {

    const previewContainer = document.querySelector(".search-preview");
    previewContainer.innerHTML = '';
    for (let i = 0; i < productsJson.length; i++) {
        let product = productsJson[i];
        previewContainer.insertAdjacentHTML('afterbegin',
            `
            <div class="search-product" id="${product.id}">
            <div style="height: 100%; width: 20%" class="search-product-img-container">
              <img  style="height: auto; width: 100%" src="/images/${product.mainThumbnailPath}" alt="item">
            </div>
            <div class="search-product-description">
            <div class="search-product-title">
             <a href="/main/${product.category}/${product.subcategory}/${product.id}"><h3>${product.name}</h3></a>
            </div>
               <p>${product.shortDescription}</p>
               <p>${product.price}</p>
            </div>
            </div>
        `);
    }
}




