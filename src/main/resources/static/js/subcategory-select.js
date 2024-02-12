const categorySelect = document.querySelector('.category-select');
const subcategoryContainer = document.querySelector('.subcategory-container');
const subcategories = subcategoryContainer.querySelectorAll('.subcategory-select');

toggleSubcategorySelect()
showSubcategorySelect()

function showSubcategorySelect(){
    categorySelect.addEventListener('change', () => {
     toggleSubcategorySelect()
    });
}

function toggleSubcategorySelect(){
    const selectedOption = categorySelect.options[categorySelect.selectedIndex];
    const selectedCategory = selectedOption.dataset.id;


    subcategories.forEach(subcategory => {
        if (subcategory.dataset.id===selectedCategory){
            subcategory.style.display = 'block';
        } else {
            subcategory.style.display = 'none';
        }

    });
}

