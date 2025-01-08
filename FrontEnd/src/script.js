// Función para manejar el toggle del menú
export function handleMenuToggle() {
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
export function handleResize() {
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
export function handleSidebarMenu() {
    const menuButtons = document.querySelectorAll('.sidebar__menu-btn');
    
    menuButtons.forEach((btn) => {
        btn.addEventListener('click', (event) => {
            const submenu = event.target.nextElementSibling;
            submenu.classList.toggle('sidebar__submenu--open');
        });
    });
}

// Función para manejar la selección de imágenes de productos
export function handleProductImageSelection() {
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
export function handleProductColorChange() {
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
export function handleProductQuantity() {
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