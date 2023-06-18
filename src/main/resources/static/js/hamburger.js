const openBtn = document.querySelector('.hamburger-nav')
const mobileMenu = document.querySelector('.mobile-menu')
const closeBtn = document.querySelector('.close-mobile-nav-btn')
const categories = document.querySelectorAll('.mobile-category-item');
const overlay = document.querySelector('.overlay-blurred')
const body = document.querySelector('body');

overlay.addEventListener('click', ()=>{
    openBtn.classList.toggle('is-active')
    mobileMenu.classList.toggle('is-active')
    overlay.classList.toggle('show')
    body.style.overflowY='auto'
    // body.style.position='static'
})

categories.forEach(function (category){
    category.addEventListener('click', function (){
        let arrowHelper = category.querySelector('.mobile-category-main > span');
        arrowHelper.classList.toggle('is-active');
        let subcategoryList = category.querySelector('.mobile-subcategory-list');
            subcategoryList.classList.toggle('is-active');

            category.classList.toggle('active')

    });
});

closeBtn.addEventListener('click', function () {
    closeBtn.classList.toggle('is-active')
    mobileMenu.classList.toggle('is-active')
    overlay.classList.toggle('show')
   body.style.overflowY='auto'
    // body.style.position='static'

})
openBtn.addEventListener('click', function () {
    openBtn.classList.toggle('is-active')
    mobileMenu.classList.toggle('is-active')
    overlay.classList.toggle('show')
    body.style.overflowY='hidden'
    // body.style.position='fixed'

    console.log(categories)
})