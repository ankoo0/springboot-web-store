const reviewsTabBtn = document.querySelector('#reviews-btn')
const expandReviewsBtn = document.querySelector('.expand-reviews-btn')
const productId = window.location.pathname.split('/').pop();
let page = 0;

const loader = `<div class="loader-container">
    <div class="loader-inner"></div>
    <div class="loader-outer"></div>
</div>`

let isVisited = false
reviewsTabBtn.addEventListener('click',()=>{
    if (!isVisited){
        isVisited = true
        document.querySelector('.user-reviews').lastElementChild.insertAdjacentHTML('beforebegin',loader)
        setTimeout(()=>{
            fetchReviews()
        },3000)


    }
})

expandReviewsBtn.addEventListener('click',()=>{
    document.querySelector('.user-reviews').lastElementChild.insertAdjacentHTML('beforebegin',loader)
    setTimeout(()=>{
        fetchReviews()
    },3000)



})





function createReviewRating(rating) {
    const whole = Math.floor(rating)
    const remainder = rating-whole
    console.log(whole + ' ' + remainder)
    let ratingElement = ``;
    for (let i = 1; i <= 5; i++) {
        if (i <= whole) {
            const ratingPart = `<img src="/ui/rating/star_full.svg"/>`
            ratingElement += ratingPart;
        }
        if (i - whole === 1 && remainder > 0) {
            const ratingPart = `<img src="/ui/rating/star_half.svg"/>`
            ratingElement += ratingPart;
        }
        if (i - whole === 1 && !remainder > 0){
            const ratingPart = `<img src="/ui/rating/star_empty.svg"/>`
            ratingElement += ratingPart;
        }
        if (i - whole >=2) {
            const ratingPart = `<img src="/ui/rating/star_empty.svg"/>`
            ratingElement += ratingPart;
        }
    }

    return ratingElement
}

function createReviewImages(reviewImages){
    console.log(reviewImages)
    let reviewImagesElement = ``;


    for (let i = 0; i <reviewImages.length ; i++) {
        const path = `/images/`+reviewImages[i]
        alert(reviewImages[i])
       reviewImagesElement +=`<div class="review-img-container"><img class="review-img" src="${path}"/></div>`
    }
    console.log(reviewImagesElement)
    return reviewImagesElement;
}

function createReview(review) {

   const reviewImages =  ` <div class="review-image-set">
        ${review.reviewImagePaths.length===0? '' : createReviewImages(review.reviewImagePaths)}
    </div>`
    const reviewElement = `<div class="review">
        <div class="review-user-info">
            <div class="review-user-avatar">
                <img src="/images/${review.userAvatar}" alt="user avatar">
            </div>
            <div class="review-username">${review.username}</div>
            <div class="review-date">${review.dateTime}</div>
        </div>
        
        ${reviewImages}
        <h3>${review.title}</h3>
        <div class="review-rating">
        ${createReviewRating(review.rating)}
        </div>
        <p>${review.review}</p>
        <div class="user-rating"></div>
        <div class="rate-review">
           <button class="like-review-btn"><span class="material-symbols-outlined">thumb_up<h5>${review.likes}</h5></span></button>
           <button class="dislike-review-btn"><span class="material-symbols-outlined">thumb_down<h5>${review.dislikes}</h5></span></button> 
        </div>
    </div>`


    return reviewElement;
}

function fetchReviews(){
    fetch(`/reviews/${productId}`, {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: page
    }).then(res => res.json()).then(res => {
        alert(JSON.stringify(res))
        if (res.length===0){
            document.querySelector('.expand-reviews-btn').remove()
            document.querySelector('.loader-container').remove()
        } else {
            // console.log(res)
            let array ='';
            for (const review in res) {
                array += createReview(res[review])
            }

            document.querySelector('.loader-container').remove()
            document.querySelector('.user-reviews').lastElementChild.insertAdjacentHTML('beforebegin',array)

            const reviews = document.querySelectorAll('.review')

            reviews.forEach((review, index) => {
                setTimeout(() => {
                    review.classList.add('show');
                }, index * 100);
            });
            page+=1
        }

    })
}

