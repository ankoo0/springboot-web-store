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

fetch('/catalog/filter', {
    method: 'post',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    body: subcategory
    // add query if present

}).then(res => res.json()).then(res => {
    console.log(res)

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
        ch.addEventListener('click', ()=>{})
    })
})

function createFilter(attrJSON) {
    let filter = ``;
    filter += `<div style="display: flex; flex-direction: row; justify-content: space-between; position: sticky"><h3>Apply filters:</h3> <span class="material-symbols-outlined">filter_alt</span></div>`;
    const hiddenPagination = `<input name="page" value="1" class="checkbox" checked type="hidden"/>`;
    filter += hiddenPagination;


    // Iterate over attributes and create a single div element to contain all attributes
    const allAttributesDiv = document.createElement('div');
    for (const attr in attrJSON) {
        const attrHtml = createAttributesContainer(attrJSON[attr]);
        allAttributesDiv.innerHTML += attrHtml // Assuming createAttributesContainer returns HTML content
    }

    const wrapper = document.createElement('div');
    wrapper.append(allAttributesDiv)

    filter += wrapper.innerHTML; // Append the HTML content of the parametersWrapper

    filter += createSubmitBtn();
    filterForm.innerHTML = filter;
}

function createSubmitBtn() {
    return `<button type="submit" class="submit-filter-btn">Find</button>`
}

function createAttributesContainer(attribute) {
    const attrContainer = document.createElement('div')
    attrContainer.classList.add('attr-container')

    const attributeValuesContainer = document.createElement('div')
    attributeValuesContainer.classList.add('attribute-values')

    const header = createHeader(attribute)
    const attributeValueSet = createAttributeValueSet(attribute);

        attributeValuesContainer.innerHTML=attributeValueSet.join('');

        attrContainer.innerHTML = header + attributeValuesContainer.outerHTML

    return attrContainer.outerHTML
}

function createHeader(attribute) {
    console.log(attribute.data)
    if (attribute.data.description===null){
        return ``
    }
    return `<div class="attribute-header"><h4>${attribute.name}</h4><div class="attribute-info-badge">?<div class="attribute-info"><p>${attribute.data.description}</p></div></div></div>`
}

function createAttributeValueSet(attribute) {

    const checkboxes = [];
    let initMap = decodeURL()

    const attributeData = attribute.data.values;

    for (let i = 0; i < attributeData.length; i++) {

        let isChecked = false

        if (typeof initMap !== "undefined" && initMap.hasOwnProperty(attribute.name)) {
            initMap[attribute.name].find(e => e === attribute[i]) ? isChecked = true : isChecked = false
        }
        // incorrect id output
        const checkbox = `<div class="check-container"><input name="${attribute.name}" value="${attributeData[i]}" class="filter-checkbox" id="${attribute.name + "-" + i}" type="checkbox" ${isChecked ? 'checked' : ''} /> <label for="${attribute.name}">${attributeData[i]}</label></div>`
        checkboxes.push(checkbox)
    }
    return checkboxes;
}
