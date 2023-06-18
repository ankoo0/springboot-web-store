const shrinkHeader = ()=>{
    let scrollPosition = 0;
    console.log(scrollPosition)
    const header = document.querySelector('header')
    const secondaryNav = document.querySelector('.secondary-nav')
    const categories = document.querySelector('.category-dropdowns')
    window.addEventListener('scroll',()=>{
        if(scrollPosition < window.pageYOffset) {
            categories.classList.toggle('shrink',true)
            secondaryNav.classList.toggle('shrink',true)
            header.classList.toggle('shrink',true)
            scrollPosition=window.pageYOffset
            console.log('down' + scrollPosition)
        } else {
            categories.classList.toggle('shrink',false)
            secondaryNav.classList.toggle('shrink',false)
            header.classList.toggle('shrink',false)
            scrollPosition=window.pageYOffset
            console.log('up' + scrollPosition)
        }
    })
}

shrinkHeader()