'use strict';

// Ejecutar un cambio de imagen cada 5 segundos en el slider, si el contenedor de slider existe.
function startSlider() {
    if (document.querySelector('#container-slider')) {
        setInterval(() => fntExecuteSlide("next"), 5000); // Llama la función para cambiar la imagen automáticamente cada 5000 ms (5 segundos).
    }
}

// ------------------------------ LIST SLIDER -------------------------
// Si existe un contenedor de lista de puntos de navegación en el slider
function handleNavClicks() {
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
function fntExecuteSlide(side) {
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

// Función para manejar el toggle del menú
function handleMenuToggle() {
    const menuToggle = document.getElementById("menuToggle");
    const nav = document.querySelector(".nav");
    const abrirMenuLabel = document.querySelector('.abrirmenu');
    
    menuToggle.addEventListener("change", function() {
        if (menuToggle.checked) {
            // Si el menú está marcado como activo, se muestra
            nav.classList.add("active");
            nav.style.opacity = "1";
            nav.style.left = "0"; // Mover el menú a la vista

            // Ocultamos el label para abrir el menú
            abrirMenuLabel.style.opacity = "0";
            setTimeout(() => {
                abrirMenuLabel.style.display = "none";
            }, 300); 
        } else {
            // Si el menú no está activo, se oculta
            nav.classList.remove("active");
            nav.style.opacity = "0";
            setTimeout(() => {
                nav.style.left = "-100%"; 
            }, 300); 

            // Si el tamaño de la pantalla es menor a 768px, mostramos el label
            if (window.innerWidth < 768) {
                abrirMenuLabel.style.display = "block";
                setTimeout(() => {
                    abrirMenuLabel.style.opacity = "1"; 
                }, 10);
            }
        }
    });
}

// Función para manejar el cambio de tamaño de la ventana
function handleResize() {
    window.addEventListener("resize", function() {
        const nav = document.querySelector(".nav");
        const menuToggle = document.getElementById("menuToggle");
        const abrirMenuLabel = document.querySelector('.abrirmenu');

        if (window.innerWidth >= 768) {
            // Si el ancho de la ventana es mayor o igual a 768px, cerramos el menú y ocultamos el label
            nav.classList.remove("active");
            menuToggle.checked = false;
            nav.style.opacity = "1"; 
            nav.style.left = "-100%"; 

            abrirMenuLabel.style.display = "none";
        } else {
            // Si el menú está activo y el tamaño de pantalla es pequeño, lo mostramos correctamente
            if (menuToggle.checked) {
                nav.style.opacity = "1"; 
                nav.style.left = "0";
                abrirMenuLabel.style.display = "none";
            } else {
                // Si el menú está cerrado, mostramos el label
                abrirMenuLabel.style.display = "block";
                abrirMenuLabel.style.opacity = "1";
            }
        }
    });
}

// Función para manejar la apertura de submenús en el menú lateral
function handleSidebarMenu() {
    const menuButtons = document.querySelectorAll('.sidebar__menu-btn');
    
    menuButtons.forEach((btn) => {
        btn.addEventListener('click', (event) => {
            const submenu = event.target.nextElementSibling;
            submenu.classList.toggle('sidebar__submenu--open');
        });
    });
}

// Función para manejar la selección de imágenes de productos
function handleProductImageSelection() {
    const producto = document.getElementById('producto');
    const productoImagen = producto.querySelector('.producto__imagen');
    const thumbs = producto.querySelector('.producto__thumbs');

    thumbs.addEventListener('click', (e) => {
        if (e.target.tagName === 'IMG') {
            const imagenSrc = e.target.src;
            const lastIndex = imagenSrc.lastIndexOf('/');
            const nombreImagen = imagenSrc.substring(lastIndex + 1);
            productoImagen.src = `./img/tennis/${nombreImagen}`;
        }
    });
}

// Función para manejar el cambio de color del producto
function handleProductColorChange() {
    const producto = document.getElementById('producto');
    const productoImagen = producto.querySelector('.producto__imagen');
    const propiedadColor = producto.querySelector('#propiedad-color');
    
    propiedadColor.addEventListener('click', (e) => {
        if (e.target.tagName === 'INPUT') {
            productoImagen.src = `./img/tennis/${e.target.value}.jpg`;
        }
    });
}

// Función para manejar la cantidad de productos
function handleProductQuantity() {
    const producto = document.getElementById('producto');
    const btnIncrementarCantidad = producto.querySelector('#incrementar-cantidad');
    const btnDisminuirCantidad = producto.querySelector('#disminuir-cantidad');
    const inputCantidad = producto.querySelector('#cantidad');
    
    btnIncrementarCantidad.addEventListener('click', () => {
        inputCantidad.value = parseInt(inputCantidad.value) + 1;
    });

    btnDisminuirCantidad.addEventListener('click', () => {
        if (parseInt(inputCantidad.value) > 1) {
            inputCantidad.value = parseInt(inputCantidad.value) - 1;
        }
    });
}

function toggleSidebar() {
  const sidebar = document.querySelector('.sidebar');
  sidebar.classList.toggle('active');
}

function closeSidebar() {
  const sidebar = document.querySelector('.sidebar');
  sidebar.classList.remove('active');
}

// Iniciar el slider
startSlider();

// Configurar los enlaces de navegación de las listas de puntos
handleNavClicks();

// Llamamos a las funciones al cargar el DOM
document.addEventListener("DOMContentLoaded", function() {
    handleMenuToggle();
    handleResize();
    handleSidebarMenu();
    handleProductImageSelection();
    handleProductColorChange();
    handleProductQuantity();
});

document.querySelector('.btsfiltrar').addEventListener('click', toggleSidebar);
document.querySelector('.cerrarbotonbtn').addEventListener('click', closeSidebar);
