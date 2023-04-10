const reviewBtn = document.querySelector('.rating-btn');
const reviewModal = document.querySelector('.review-modal');
const closeModalBtn = document.querySelector('#modal-close-btn');
const blurredOverlay = document.querySelector('.overlay-blurred');
const body = document.querySelector('body');

reviewBtn.addEventListener('click',openReviewModal);
closeModalBtn.addEventListener('click',closeReviewModal);
blurredOverlay.addEventListener('click',closeReviewModal)
document.addEventListener('keydown',escButtonCloseModal );


function closeReviewModal(){
    blurredOverlay.style.display = 'none';
    reviewModal.style.display = 'none';
    body.style.overflow = 'auto';
}

function openReviewModal(){
    blurredOverlay.style.display = 'block';
    reviewModal.style.display = 'block';
    body.style.overflow = 'hidden';
}

function escButtonCloseModal(event) {
    if (event.keyCode === 27) {
        setTimeout(function(){
            blurredOverlay.style.display = 'none';
            reviewModal.style.display = 'none';
            body.style.overflow = 'auto';
        })
    }
}