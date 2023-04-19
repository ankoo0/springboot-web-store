const search = document.querySelector(".search-input");
const closeSearchBtn = document.querySelector(".close-search-btn");
const searchModal = document.querySelector(".mobile-search-modal");

console.log(search)
search.addEventListener('input',e =>{
    const query = e.target.value;
    console.log(query)
})

closeSearchBtn.addEventListener('click',() =>{
    searchModal.style.display="none"
})