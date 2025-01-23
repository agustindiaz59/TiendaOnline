import { saludar } from './mensaje.js';

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("miBoton").onclick = function() {
        saludar();
    };
});

import { agregarEventoBoton } from './carritomensaje.js';

// Llamamos a la función para asociar el evento al botón
agregarEventoBoton();


import { startSlider, handleNavClicks, fntExecuteSlide } from './app.js';

// Iniciar el slider
startSlider();

// Configurar los enlaces de navegación de las listas de puntos
handleNavClicks();



import { handleMenuToggle,  handleResize, handleSidebarMenu, handleProductImageSelection, handleProductColorChange, handleProductQuantity } from './script.js';  // Asegúrate de que la ruta sea correcta

// Llamamos a las funciones al cargar el DOM
document.addEventListener("DOMContentLoaded", function() {
    handleMenuToggle();
    handleResize();
    handleSidebarMenu();
    handleProductImageSelection();
    handleProductColorChange();
    handleProductQuantity();
});

import { toggleFilterDisplay } from './sidebardcerraryabrir.js';

// Llamar a la función para activar el filtro cuando se haga clic en el botón
toggleFilterDisplay();


