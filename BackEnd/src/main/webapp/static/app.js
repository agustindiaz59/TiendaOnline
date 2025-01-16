// Ejecutar un cambio de imagen cada 5 segundos en el slider, si el contenedor de slider existe.
if(document.querySelector('#container-slider')){
    setInterval('fntExecuteSlide("next")',5000); // Llama la función para cambiar la imagen automáticamente cada 5000 ms (5 segundos).
}

// ------------------------------ LIST SLIDER -------------------------
// Si existe un contenedor de lista de puntos de navegación en el slider
if(document.querySelector('.listslider')){
    // Seleccionamos todos los enlaces dentro de la lista de navegación de puntos
    let link = document.querySelectorAll(".listslider li a");
    
    // Iteramos sobre cada enlace para agregar un evento de clic
    link.forEach(function(link) {
        // Al hacer clic en un punto, prevenimos la acción por defecto
        link.addEventListener('click', function(e){
            e.preventDefault(); // Evita que el enlace navegue a otro lugar
            // Obtenemos el atributo 'itlist' del enlace que contiene el índice de la diapositiva
            let item = this.getAttribute('itlist');
            // Dividimos el atributo 'itlist' para obtener el índice
            let arrItem = item.split("_");
            // Llamamos a la función para cambiar la diapositiva a la seleccionada
            fntExecuteSlide(arrItem[1]);
            return false; // Detenemos la propagación del evento
        });
    });
}

// Función principal para ejecutar el cambio de diapositiva
function fntExecuteSlide(side){
    // Obtenemos el contenedor del slider
    let parentTarget = document.getElementById('slider');
    // Obtenemos todas las diapositivas (li)
    let elements = parentTarget.getElementsByTagName('li');
    let curElement, nextElement;

    // Buscamos cuál es la diapositiva actualmente visible (opacidad 1)
    for(var i=0; i<elements.length; i++){
        if(elements[i].style.opacity == 1){
            curElement = i;
            break;
        }
    }

    // Determinamos cuál será la próxima diapositiva
    if(side == 'prev' || side == 'next'){
        if(side == "prev"){
            nextElement = (curElement == 0) ? elements.length - 1 : curElement - 1; // Si es anterior, mostramos la diapositiva anterior o la última.
        } else {
            nextElement = (curElement == elements.length - 1) ? 0 : curElement + 1; // Si es siguiente, mostramos la siguiente o la primera.
        }
    } else {
        // Si se pasa un índice directamente, lo usamos como próximo índice
        nextElement = side;
        // Determinamos si la acción fue anterior o siguiente según la posición de los elementos
        side = (curElement > nextElement) ? 'prev' : 'next';
    }

    // RESALTA LOS PUNTOS: Se cambia la clase para resaltar el punto de la diapositiva activa
    let elementSel = document.getElementsByClassName("listslider")[0].getElementsByTagName("a");
    elementSel[curElement].classList.remove("item-select-slid"); // Remueve el resaltado del punto anterior
    elementSel[nextElement].classList.add("item-select-slid"); // Añade el resaltado al nuevo punto

    // Ocultamos la diapositiva actual y la mostramos en el siguiente
    elements[curElement].style.opacity = 0;
    elements[curElement].style.zIndex = 0;
    elements[nextElement].style.opacity = 1;
    elements[nextElement].style.zIndex = 1;
}