const swiper = new Swiper('.swiper', {
    // Optional parameters
    direction: 'horizontal',
    loop: true,
    autoHeight:true,

    // If we need pagination
    pagination: {
        el: '.swiper-pagination',
    },

    // Navigation arrows
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    // And if we need scrollbar
    scrollbar: {
        el: '.swiper-scrollbar',
    },

    zoom: {
        maxRatio: 1.5,
        minRatio: 1
    },



});

swiper.on("slideChangeTransitionStart", swiper.zoom.out);
// And when transition has finished scale it up.
swiper.on("slideChangeTransitionEnd", swiper.zoom.in);
