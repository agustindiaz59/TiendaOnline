'use strict';

// mensaje.js
function saludar() {
    console.log("¡Hola, el botón ha sido presionado!");
}

// Función para mostrar el mensaje
function mostrarMensaje() {
    const mensaje = document.getElementById('mensaje');
    mensaje.classList.add('mensaje-visible');
    
    // Después de 3 segundos, ocultamos el mensaje
    setTimeout(() => {
        mensaje.classList.remove('mensaje-visible');
    }, 3000); // El mensaje se oculta después de 3 segundos
}

// Función para asociar la acción al botón
function agregarEventoBoton() {
    const botonAgregar = document.getElementById('boton-agregar');
    if (botonAgregar) {
        botonAgregar.addEventListener('click', mostrarMensaje);
    }
}

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

function toggleFilterDisplay() {
  const btnFiltrar = document.querySelector('.btn-filtrar');
  const filterContainer = document.querySelector('.filter-container');
  const closeFilterBtn = document.querySelector('#close-filter');
  const body = document.body; // Accedemos al body del documento

  // Función para verificar si la pantalla está en modo móvil
  function checkScreenWidth() {
    return window.innerWidth <= 968;
  }

  // Alternar la visibilidad del filter-container con el botón 'btn-filtrar' solo en pantallas móviles
  btnFiltrar.addEventListener('click', () => {
    if (checkScreenWidth()) {
      // Alternar entre mostrar/ocultar el contenedor
      filterContainer.style.display = filterContainer.style.display === 'none' || filterContainer.style.display === '' ? 'block' : 'none';
      btnFiltrar.classList.toggle('active');
      
      // Aplicar overflow: hidden en el body cuando el filtro está visible
      if (filterContainer.style.display === 'block') {
        body.style.overflow = 'hidden'; // Evitar el scroll en el body
      } else {
        body.style.overflow = ''; // Restablecer el scroll en el body
      }
    }
  });

  // Cerrar el filter-container con el botón 'close-filter' solo en pantallas móviles
  closeFilterBtn.addEventListener('click', () => {
    if (checkScreenWidth()) {
      filterContainer.style.display = 'none'; // Ocultar el contenedor
      btnFiltrar.classList.remove('active'); // Remover la clase 'active' del botón de filtrar si está visible
      body.style.overflow = ''; // Restablecer el scroll en el body
    }
  });

  // Detecta cuando se redimensiona la ventana para ajustar el display del filter-container y el estado del botón
  window.addEventListener('resize', () => {
    if (!checkScreenWidth()) {
      // Si la pantalla es más ancha que 968px (modo escritorio)
      filterContainer.style.display = 'block';  // Asegurarse de que el contenedor esté visible
      btnFiltrar.classList.remove('active');   // Eliminar la clase 'active' del botón de filtrar
      body.style.overflow = ''; // Restablecer el scroll en el body
    } else {
      // Si la pantalla es más pequeña que 968px (modo móvil)
      filterContainer.style.display = 'none';  // Asegurarse de que el contenedor esté oculto
      body.style.overflow = ''; // Restablecer el scroll en el body
    }
  });

  // Asegurarse de que el filtro esté oculto al cargar la página si es un dispositivo móvil
  if (checkScreenWidth()) {
    filterContainer.style.display = 'none';  // Ocultar el filtro si es un dispositivo móvil al cargar la página
    body.style.overflow = ''; // Restablecer el scroll en el body
  } else {
    filterContainer.style.display = 'block';  // Mostrar el filtro si es una pantalla grande
    btnFiltrar.classList.remove('active');   // Asegurarse de que el botón no esté activo
    body.style.overflow = ''; // Restablecer el scroll en el body
  }
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("miBoton").onclick = function() {
        saludar();
    };
});

// Llamamos a la función para asociar el evento al botón
agregarEventoBoton();

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

// Llamar a la función para activar el filtro cuando se haga clic en el botón
toggleFilterDisplay();
