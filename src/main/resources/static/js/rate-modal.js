const reviewModal = document.querySelector('.review-modal')
const openReviewModalBtn = document.querySelector('.open-rating-btn')
const closeReviewModalBtn = document.querySelector('.close-review-btn')
const closeLoginModalBtn = document.querySelector('.close-login-btn')
const sendReviewBtn = document.querySelector('.submit-review-btn')

closeLoginModalBtn.addEventListener('click',()=>{
    document.querySelector('.login-modal').classList.toggle('show')
    document.querySelector('.overlay-blurred').classList.toggle('show')
    document.querySelector('body').style.overflow='auto'
})

closeReviewModalBtn.addEventListener('click',()=>{
    reviewModal.classList.toggle('show')
    document.querySelector('.overlay-blurred').classList.toggle('show')
    document.querySelector('body').style.overflow='auto'
})

openReviewModalBtn.addEventListener('click',()=>{
    // alert(openReviewModalBtn.dataset.auth==='true')
    if (openReviewModalBtn.dataset.auth==='true'){
        reviewModal.classList.toggle('show')
        document.querySelector('.overlay-blurred').classList.toggle('show')
        document.querySelector('body').style.overflow='hidden'
    } else {
        document.querySelector('.login-modal').classList.toggle('show')
        document.querySelector('.overlay-blurred').classList.toggle('show')
        document.querySelector('body').style.overflow='hidden'
    }

})



const imageInput = document.getElementById('image-input');
const imageContainer = document.querySelector('.review-image-container');

imageInput.addEventListener('change', () => {
    const files = imageInput.files;

    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();

        reader.addEventListener('load', () => {
            const image = new Image();
            image.src = reader.result.toString();
            image.classList.add('review-image');
            imageContainer.appendChild(image);
        });

        reader.readAsDataURL(file);
    }
});