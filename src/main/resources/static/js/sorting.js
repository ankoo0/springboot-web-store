document.querySelector('.sorting-container').addEventListener('click',()=>{
    if (document.querySelector('.sorting-parameters').style.display==='flex'){
        document.querySelector('.sorting-parameters').style.display='none'
    } else {
        document.querySelector('.sorting-parameters').style.display='flex'
    }

})