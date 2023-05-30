const sortingContainer = document.querySelector('.sorting-container');
const sortingParameters = document.querySelector('.sorting-parameters')
const sortingUIArrow = document.querySelector('.sorting-container > span')
sortingContainer.addEventListener('click',()=>{
    sortingParameters.classList.contains('show') ? sortingParameters.style.opacity = '0' : sortingParameters.style.opacity = '1'
    sortingUIArrow.classList.toggle('active')
    sortingParameters.classList.toggle('show')
})