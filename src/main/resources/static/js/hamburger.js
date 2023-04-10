const openBtn = document.querySelector('.hamburger-nav')
const mobileMenu = document.querySelector('.mobile-menu')
const closeBtn = document.querySelector('.close-menu-btn')
const categories = document.querySelectorAll('.mobile-category-item');


categories.forEach(function (category){
    // alert(category);
    category.addEventListener('click', function (){
        let arrowHelper = category.querySelector('.mobile-category-main > span');
        arrowHelper.classList.toggle('is-active');
        let subcategoryList = category.querySelector('.mobile-subcategory-list');
            subcategoryList.classList.toggle('is-active');

        // for (let i =0; i< subcategories.length; i++){
        //     if (subcategories[i].className==='mobile-subcategory-list'){
        //         subcategories[i].style.display='block';
        //     }
        // }
    });
});

closeBtn.addEventListener('click', function () {
    closeBtn.classList.toggle('is-active')
    mobileMenu.classList.toggle('is-active')
})
openBtn.addEventListener('click', function () {
    openBtn.classList.toggle('is-active')
    mobileMenu.classList.toggle('is-active')
    console.log(categories)
})