const tabs = Array.from(document.querySelectorAll('.tab-nav')) || []
const underline = document.querySelector('.tab-indicator')
const contentContainer = document.querySelector('.tab-overflow')
const content = Array.from(document.querySelectorAll('.tab-content')) || [];
const tabBody = document.querySelector('.tab-body')


let lastIndex =0;

tabs.forEach((tab,index)=>{

    if(index===0){
        tab.classList.toggle('active')
        underline.style.left = tab.offsetLeft + 'px'
        underline.style.width = tab.offsetWidth + 'px'

    }

    tab.addEventListener('click', (e)=>{
        underline.style.left = e.target.offsetLeft + 'px'
        underline.style.width = e.target.offsetWidth + 'px'


        tabs.forEach((t)=>{
            t.classList.remove('active')
        })


        const style = contentContainer.style.transform
        const currentTranslate = +(style.replace(/[^\d.]/g, ''));

        if(index===0){

            contentContainer.style.transform = `translate(0px)`

        } else if (index>lastIndex){
            const step = index-lastIndex
            const flipSign = Math.abs(step)
            const offset = 1312* flipSign
            const newTranslate = currentTranslate+offset
            contentContainer.style.transform = `translate(-${newTranslate}px)`
        } else if(index<lastIndex){
            const step = index-lastIndex
            const flipSign = Math.abs(step)
            const offset = 1312* flipSign
            const newTranslate = currentTranslate-offset
            contentContainer.style.transform = `translate(-${newTranslate}px)`
        }

        lastIndex = index

        tab.classList.toggle('active')
    })
})