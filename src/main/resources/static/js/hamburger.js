const openBtn = document.querySelector('.hamburger-nav')
const mobileMenu = document.querySelector('.mobile-menu')
const closeBtn = document.querySelector('.close-menu-btn')

closeBtn.addEventListener('click', function () {
    closeBtn.classList.toggle('is-active')
    mobileMenu.classList.toggle('is-active')
})
openBtn.addEventListener('click', function () {
    openBtn.classList.toggle('is-active')
    mobileMenu.classList.toggle('is-active')
})