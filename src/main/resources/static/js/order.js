const products = JSON.parse(localStorage.getItem('cart'));
const orderForm = document.querySelector('.order-form');
const sendOrderBtn = document.querySelector('.send-order-btn');

function copyObject(obj) {
    const newObj = {};
    for (let key in obj) {
        //copy all the fields
        newObj[key] = obj[key];
    }

    return newObj;
}

function copyFormDataToObj(formData) {
    const targetObj = {
        // orderedProducts : copyObject(products)
    }
    for (let [key, value] of formData.entries()) {
        targetObj[key] = value;
    }
    return targetObj
}


orderForm.addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission
    // Your code to handle the form submission

});


sendOrderBtn.addEventListener('click',()=>{
    const formData = new FormData(orderForm);
    // const orderDetails = copyFormDataToObj(formData)
    const orderDetails = copyFormDataToObj(formData);

    orderDetails.orderedProducts = Object.values(products)


    alert("f")
    console.log(JSON.stringify(orderDetails))
    fetch(`/order`, {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderDetails)
    }).then(res=>{
        if (res.status===200){
            localStorage.removeItem('cart')
        }

    })
})


