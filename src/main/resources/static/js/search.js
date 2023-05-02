const search = document.querySelector(".search-input");
const closeSearchBtn = document.querySelector(".close-search-btn");
const searchModal = document.querySelector(".mobile-search-modal");

let typingTimer;                //timer identifier
let doneTypingInterval = 500;  //time in ms (5 seconds)

//on keyup, start the countdown
search.addEventListener('keyup', () => {
    clearTimeout(typingTimer);

    if (search.value) {
        typingTimer = setTimeout(doneTyping, doneTypingInterval);
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

        let template = `<div class="search-category">
            <div class="search-category-img"><img src="${subcategoryJson[key].categoryImage}" alt="category"/></div>
            <h4 class="search-category-title"><a href="main/${subcategoryJson[key].category.toLowerCase()}/${subcategoryJson[key].subcategoryName.toLowerCase()}?page=1&q=${search.value}">${subcategoryJson[key].subcategoryName}</a></h4>
            <p>Found ${key} products</p>
        </div>`;
        subcategoryContainer.insertAdjacentHTML('afterbegin', template)
    }
}

closeSearchBtn.addEventListener('click', () => {
    searchModal.style.display = "none"
})

function fillSearchPreview(productsJson) {
    let previewContainer = document.querySelector(".search-preview");
    previewContainer.innerHTML = '';
    for (let i = 0; i < productsJson.length; i++) {
        let product = productsJson[i];
        previewContainer.insertAdjacentHTML('afterbegin',
            `
            <div id="${product.id}" style="border: 1px solid #333333; padding: 10px; margin: 20px">
               <h3>${product.name}</h3>
               <p>${product.shortDescription}</p>
               <p>${product.price}</p>
               <img  style="max-height: 200px; max-width: 200px" src="/images/${product.mainThumbnailPath}" alt="item">
               <button  ><a href="/main/${product.category}/${product.subcategory}/${product.id}">open</a></button>
            </div>
        `);
    }
}

// console.log(search)
// search.addEventListener('input',e =>{
//     const query = e.target.value;
//     const queryJSON = {
//         "query" : query
//     }
//     console.log(query)
//
//     doSearch(queryJSON)
//
//
// })
//
// function doSearch(queryJSON) {
//
//         fetch('/search', {
//                 method: 'post',
//                 headers: {
//                     'Accept': 'application/json',
//                     'Content-Type': 'application/json'
//                 },
//                 body: JSON.stringify(queryJSON)
//             }
//         ).then(res => res.json())
//             .then(function (res){
//
//             })
//
// }





