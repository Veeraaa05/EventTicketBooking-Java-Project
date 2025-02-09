let slideIndex = 0; // Current slide position
const slides = document.querySelector('.slides');
const totalSlides = document.querySelectorAll('.slides img').length;

// Function to move slides
function moveSlide(step) {
    slideIndex += step;

    // Loop to the start or end if out of range
    if (slideIndex < 0) {
        slideIndex = totalSlides - 1;
    } else if (slideIndex >= totalSlides) {
        slideIndex = 0;
    }

    // Update slide position using transform property
    slides.style.transform = `translateX(-${slideIndex * 100}%)`;
}
