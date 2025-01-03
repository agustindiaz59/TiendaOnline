// Ejecutar un cambio de imagen cada 5 segundos en el slider, si el contenedor de slider existe.
export function startSlider() {
    if (document.querySelector('#container-slider')) {
        setInterval(() => fntExecuteSlide("next"), 5000); // Llama la función para cambiar la imagen automáticamente cada 5000 ms (5 segundos).
    }
}

// ------------------------------ LIST SLIDER -------------------------
// Si existe un contenedor de lista de puntos de navegación en el slider
export function handleNavClicks() {
    if (document.querySelector('.listslider')) {
        let link = document.querySelectorAll(".listslider li a");

        link.forEach(function (link) {
            link.addEventListener('click', function (e) {
                e.preventDefault(); // Evita que el enlace navegue a otro lugar
                let item = this.getAttribute('itlist');
                let arrItem = item.split("_");
                fntExecuteSlide(arrItem[1]);
                return false; // Detenemos la propagación del evento
            });
        });
    }
}

// Función principal para ejecutar el cambio de diapositiva
export function fntExecuteSlide(side) {
    let parentTarget = document.getElementById('slider');
    let elements = parentTarget.getElementsByTagName('li');
    let curElement, nextElement;

    // Buscamos cuál es la diapositiva actualmente visible (opacidad 1)
    for (let i = 0; i < elements.length; i++) {
        if (elements[i].style.opacity == 1) {
            curElement = i;
            break;
        }
    }

    // Determinamos cuál será la próxima diapositiva
    if (side == 'prev' || side == 'next') {
        if (side == "prev") {
            nextElement = (curElement == 0) ? elements.length - 1 : curElement - 1; // Si es anterior, mostramos la diapositiva anterior o la última.
        } else {
            nextElement = (curElement == elements.length - 1) ? 0 : curElement + 1; // Si es siguiente, mostramos la siguiente o la primera.
        }
    } else {
        nextElement = side;
        side = (curElement > nextElement) ? 'prev' : 'next';
    }

    // RESALTA LOS PUNTOS: Se cambia la clase para resaltar el punto de la diapositiva activa
    let elementSel = document.getElementsByClassName("listslider")[0].getElementsByTagName("a");
    elementSel[curElement].classList.remove("item-select-slid");
    elementSel[nextElement].classList.add("item-select-slid");

    elements[curElement].style.opacity = 0;
    elements[curElement].style.zIndex = 0;
    elements[nextElement].style.opacity = 1;
    elements[nextElement].style.zIndex = 1;
}