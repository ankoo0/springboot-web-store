const path = window.location.pathname;
const subcategory = path.substring(1, path.length).split('/')[2]
const filterForm = document.querySelector('.filtering-form')
let productCards = document.querySelectorAll('.product-card')
const queryString = window.location.search;


productCards.forEach((card, index) => {
    setTimeout(() => {
        card.classList.add('show');
    }, index * 100);
});


function convertParamsToMap(paramsArray) {
    const paramsMap = {};
    paramsArray.forEach(paramString => {
        const [key, value] = paramString.split('=');
        if (paramsMap[key]) {
            paramsMap[key].push(value);
        } else {
            paramsMap[key] = [value];
        }
    });

    return paramsMap;
}


function decodeURL() {
    let map;
    const paramsArray = decodeURIComponent(queryString).substring(1).replaceAll('+', ' ').split("&").filter(p => !p.includes("page") && !p.includes("order") && !p.includes("sortBy") && !p.includes("q"))
    paramsArray.forEach(p => {
        const param = p.split("=")
        const key = param[0]
        const value = param[1]
        map = convertParamsToMap(paramsArray)
    })

    return map;
}

// function initFilterState() {
//
// }


fetch('/main/filter', {
    method: 'post',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    body: subcategory
    // add query if present

}).then(res => res.json()).then(res => {

    createFilter(res)

    let checkboxes = document.querySelectorAll(".checkbox")

    let checkSet = document.querySelectorAll(".check-set-btn")
    checkSet.forEach(ch => {
        let checkDiv = document.querySelector('.check-div')
        ch.addEventListener('click', () => {
            checkDiv.classList.toggle('show')
            checkDiv.classList.contains('show') ? checkDiv.style.visibility = 'visible' : checkDiv.style.visibility = 'hidden'
        })
    })

    const attrInfoBadgesArray = document.querySelectorAll('.attribute-info-badge')

    attrInfoBadgesArray.forEach((badge) => {
        badge.addEventListener('click', () => {

            badge.querySelector('.attribute-info').classList.toggle('show')
        })
    })


    checkboxes.forEach(ch => {
        ch.addEventListener('click', {})
    })
})

function createFilter(attrJSON) {
    let filter = ``;
    filter += `<h3>Apply filters:</h3>`
    const hiddenPagination = `<input name="page" value="1" class="checkbox" checked type="hidden"/>`
    filter += hiddenPagination
    for (const attrName in attrJSON) {
        filter += createAttributesContainer(attrJSON[attrName], attrName)
    }

    filter += createSubmitBtn()
    filterForm.innerHTML = filter
}

function createSubmitBtn() {
    return `<button type="submit" class="submit-filter-btn">Find</button>`
}

function createAttributesContainer(checkboxesData, attrName) {
    const attrContainer = document.createElement('div')
    attrContainer.classList.add('attr-container')

    const attributeValuesContainer = document.createElement('div')
    attributeValuesContainer.classList.add('attribute-values')

    const header = createHeader(attrName, null)
    const attributeValueSet = createAttributeValueSet(checkboxesData, attrName);

    // if (attributeValueSet.length < 6) {
        attributeValuesContainer.innerHTML=attributeValueSet.join('');

        attrContainer.innerHTML = header + attributeValuesContainer.outerHTML

    // } else {
        // const expandAttrBtn =   `<div class="check-set-btn">All ${attributeValueSet.length} variants</div>`

        // const attrMenu = createAttributeMenu(attributeValueSet)
        // const expandAttrBtn = createExpandBtn(attrMenu, attributeValueSet.length)
        // expandAttrBtn attrMenu
        // const firstAttributes = attributeValueSet.join('')
        // alert(firstAttributes)
        //.slice(0, 6)
        // attrContainer.innerHTML = header + firstAttributes
        // + expandAttrBtn
        // return attrContainer.outerHTML
    // }
    return attrContainer.outerHTML
}

function createHeader(attrName, attrDescription) {
    return `<div class="attribute-header"><h4>${attrName}</h4><div class="attribute-info-badge">?<div class="attribute-info">lorem ipsum dfgsjdfgeayfgdhscbvsdvhbsdfhsbfhshgfaagehfe</div></div></div>`
}

// function createExpandBtn(attrMenu, length) {
//     const expandBtn = document.createElement('div')
//     expandBtn.innerHTML = `All ${length} variants` + attrMenu
//     expandBtn.classList.add('check-set-btn')
//     return expandBtn.outerHTML
// }


// function createAttributeMenu(attributeValueSet) {
//     const attrMenu = document.createElement('div')
//     // attrMenu.style.visibility = 'hidden'
//     attrMenu.innerHTML = attributeValueSet.join('')
//     attrMenu.classList.add('check-div')
//     return attrMenu.outerHTML
// }

function createAttributeValueSet(checkboxesData, attrName) {

    const checkboxes = [];
    let initMap = decodeURL()

    for (let i = 0; i < checkboxesData.length; i++) {

        let isChecked = false

        if (typeof initMap !== "undefined" && initMap.hasOwnProperty(attrName)) {
            initMap[attrName].find(e => e === checkboxesData[i]) ? isChecked = true : isChecked = false
        }
        // incorrect id output
        const checkbox = `<div class="check-container"><input name="${attrName}" value="${checkboxesData[i]}" class="filter-checkbox" id="${attrName + "-" + i}" type="checkbox" ${isChecked ? 'checked' : ''} /> <label for="${attrName}">${checkboxesData[i]}</label></div>`
        checkboxes.push(checkbox)
    }
    return checkboxes;
}
