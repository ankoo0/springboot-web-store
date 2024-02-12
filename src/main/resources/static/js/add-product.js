const imageInput = document.getElementById('image-input');
const images = document.querySelector('.images-container');

function removeImage(imageContainer) {
    imageContainer.remove()
    const fileToRemove = imageContainer.querySelector('img').getAttribute('name');
    const files = imageInput.files;
    const newFiles = [];
    for (let i = 0; i < files.length; i++) {
        if (files[i].name !== fileToRemove) {
            newFiles.push(files[i]);
        }
    }

    const fileList = new DataTransfer();
    for (const file of newFiles) {
        fileList.items.add(file);
    }
    alert(fileList.files.length)
    imageInput.files = fileList.files;

    alert(imageInput.files.length)
}

imageInput.addEventListener('change', () => {
    const files = imageInput.files;


    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();

        reader.addEventListener('load', () => {
            const image = new Image();
            image.src = reader.result.toString();
            image.classList.add('review-image');
            image.setAttribute('name', file.name )

            const imgContainer = document.createElement('div')
            imgContainer.classList.add('image-container')
            imgContainer.appendChild(image)

            // Create remove button
            const removeButton = document.createElement('div');
            removeButton.classList.add('remove-img-btn')
            removeButton.innerText = 'X';
            removeButton.addEventListener('click', () => {
                removeImage(imgContainer);
            });
            imgContainer.appendChild(removeButton);

            // Check if file is an image and limit count to 10
            if (file.type.startsWith('image/') && images.children.length < 10) {
                images.appendChild(imgContainer);
            }
        });

        reader.readAsDataURL(file);
    }
});