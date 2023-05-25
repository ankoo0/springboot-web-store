const sortingContainer = document.querySelector('.sorting-container');
const sortingParameters = document.querySelector('.sorting-parameters')

sortingContainer.addEventListener('click',()=>{
    sortingParameters.classList.contains('show') ? sortingParameters.style.opacity = '0' : sortingParameters.style.opacity = '1'
    sortingParameters.classList.toggle('show')
})