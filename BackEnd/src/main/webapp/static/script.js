// Espera a que el DOM se cargue completamente antes de ejecutar el código
document.addEventListener("DOMContentLoaded", function() {
    // Obtener el toggle para el menú, la navegación y el label para abrir el menú
    const menuToggle = document.getElementById("menuToggle");
    const nav = document.querySelector(".nav");
    const abrirMenuLabel = document.querySelector('.abrirmenu');

    // Escuchar cambios en el checkbox del toggle del menú
    menuToggle.addEventListener("change", function() {
        if (menuToggle.checked) {
            // Si el menú está marcado como activo, se muestra
            nav.classList.add("active");
            nav.style.opacity = "1"; // Aseguramos que la opacidad esté en 1
            nav.style.left = "0"; // Mover el menú a la vista

            // Ocultamos el label para abrir el menú con una transición de opacidad
            abrirMenuLabel.style.opacity = "0";
            setTimeout(() => {
                abrirMenuLabel.style.display = "none"; // Después de la transición, ocultamos el label
            }, 300); // El tiempo de la transición es de 300 ms
        } else {
            // Si el menú no está activo, se oculta
            nav.classList.remove("active");
            nav.style.opacity = "0"; // Hacemos que el menú sea transparente
            setTimeout(() => {
                nav.style.left = "-100%"; // Mueve el menú fuera de la vista
            }, 300); // El tiempo de la transición es de 300 ms

            // Si el tamaño de la pantalla es menor a 768px, mostramos nuevamente el label
            if (window.innerWidth < 768) {
                abrirMenuLabel.style.display = "block";
                setTimeout(() => {
                    abrirMenuLabel.style.opacity = "1"; // Restablecemos la opacidad del label
                }, 10); // Esperamos a que el display block se haya aplicado antes de cambiar la opacidad
            }
        }
    });

    // Escuchar cambios en el tamaño de la ventana
    window.addEventListener("resize", function() {
        // Si el ancho de la ventana es mayor o igual a 768px, cerramos el menú y ocultamos el label
        if (window.innerWidth >= 768) {
            nav.classList.remove("active"); // Cierra el menú
            menuToggle.checked = false; // Resetea el estado del checkbox
            nav.style.opacity = "1"; // Aseguramos que la opacidad sea 1
            nav.style.left = "-100%"; // Mueve el menú fuera de la vista

            // Oculta el label 'abrirmenu' en pantallas grandes
            abrirMenuLabel.style.display = "none";
        } else {
            // Si el menú está activo y el tamaño de pantalla es pequeño, lo mostramos correctamente
            if (menuToggle.checked) {
                nav.style.opacity = "1"; // Restablecemos la opacidad
                nav.style.left = "0"; // Mueve el menú a la vista

                // Ocultamos el label cuando el menú esté abierto en pantalla pequeña
                abrirMenuLabel.style.display = "none";
            } else {
                // Si el menú está cerrado, mostramos el label
                abrirMenuLabel.style.display = "block";
                abrirMenuLabel.style.opacity = "1"; // Aseguramos que la opacidad esté a 1
            }
        }
    });
});

// Funcionalidad para los botones del menú lateral
const menuButtons = document.querySelectorAll('.sidebar__menu-btn');

menuButtons.forEach((btn) => {
  btn.addEventListener('click', (event) => {
    // Al hacer clic en un botón del menú, abrimos o cerramos su submenú
    const submenu = event.target.nextElementSibling;
    submenu.classList.toggle('sidebar__submenu--open');
  });
});

// PRODUCTO PARTE
const producto = document.getElementById('producto');
const productoImagen = producto.querySelector('.producto__imagen');
const thumbs = producto.querySelector('.producto__thumbs');

// Color
const propiedadColor = producto.querySelector('#propiedad-color');

// Cantidad
const btnIncrementarCantidad = producto.querySelector('#incrementar-cantidad');
const btnDisminuirCantidad = producto.querySelector('#disminuir-cantidad');
const inputCantidad = producto.querySelector('#cantidad');

// Funcionalidad de las miniaturas de las imágenes del producto
thumbs.addEventListener('click', (e) => {
	if (e.target.tagName === 'IMG') {
		// Si se hace clic en una miniatura, actualizamos la imagen principal del producto
		const imagenSrc = e.target.src;

		// Obtenemos la posición del último '/'
		const lastIndex = imagenSrc.lastIndexOf('/');

		// Cortamos la cadena para obtener solo el nombre de la imagen
		const nombreImagen = imagenSrc.substring(lastIndex + 1);

		// Cambiamos la imagen principal del producto con la miniatura seleccionada
		productoImagen.src = `./assets/imagen/${nombreImagen}`;
	}
});

// Cambiamos la imagen del producto dependiendo del color seleccionado
propiedadColor.addEventListener('click', (e) => {
	if (e.target.tagName === 'INPUT') {
		// Cuando se selecciona un color, cambiamos la imagen del producto
		productoImagen.src = `./img/tennis/${e.target.value}.jpg`;
	}
});

// Funcionalidad para aumentar o disminuir la cantidad de productos
btnIncrementarCantidad.addEventListener('click', (e) => {
	// Incrementamos la cantidad al hacer clic en el botón de aumentar
	inputCantidad.value = parseInt(inputCantidad.value) + 1;
});

btnDisminuirCantidad.addEventListener('click', (e) => {
	// Disminuimos la cantidad al hacer clic en el botón de disminuir, pero no permitimos que sea menor a 1
	if (parseInt(inputCantidad.value) > 1) {
		inputCantidad.value = parseInt(inputCantidad.value) - 1;
	}
});