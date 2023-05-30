const notifyModal = document.querySelector('.notify-modal')
const openNotifyModalBtn = document.querySelector('.open-notify-btn')
const closeNotifyModalBtn = document.querySelector('.close-notify-btn')

closeNotifyModalBtn.addEventListener('click',()=>{
    notifyModal.classList.toggle('show')
})

openNotifyModalBtn.addEventListener('click',()=>{
    notifyModal.classList.toggle('show')
})