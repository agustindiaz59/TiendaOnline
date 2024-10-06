// Espera a que el DOM se cargue completamente
document.addEventListener("DOMContentLoaded", function() {
    const menuToggle = document.getElementById("menuToggle");
    const nav = document.querySelector(".nav");
    const abrirMenuLabel = document.querySelector('.abrirmenu');

    menuToggle.addEventListener("change", function() {
        if (menuToggle.checked) {
            nav.classList.add("active");
            nav.style.opacity = "1";
            nav.style.left = "0";

            // Cambia la opacidad a 0 y luego oculta el label
            abrirMenuLabel.style.opacity = "0";
            setTimeout(() => {
                abrirMenuLabel.style.display = "none";
            }, 300); // Tiempo de la transición
        } else {
            nav.classList.remove("active");
            nav.style.opacity = "0";
            setTimeout(() => {
                nav.style.left = "-100%";
            }, 300);

            // Muestra el label solo si la pantalla es menor a 768px
            if (window.innerWidth < 768) {
                abrirMenuLabel.style.display = "block";
                setTimeout(() => {
                    abrirMenuLabel.style.opacity = "1";
                }, 10); // Espera para asegurar que el display block ocurra antes de cambiar la opacidad
            }
        }
    });

    // Escucha los cambios en el tamaño de la ventana
    window.addEventListener("resize", function() {
        // Si el ancho de la ventana es mayor o igual a 768px, cierra el menú y oculta el label
        if (window.innerWidth >= 768) {
            nav.classList.remove("active"); // Cierra el menú
            menuToggle.checked = false; // Resetea el estado del checkbox
            nav.style.opacity = "1"; // Asegúrate de que la opacidad sea 0
            nav.style.left = "-100%"; // Mueve el menú fuera de la vista

            // Oculta el label 'abrirmenu' para pantallas grandes
            abrirMenuLabel.style.display = "none";
        } else {
            // Si el menú está activo, asegúrate de mostrarlo correctamente en pantalla pequeña
            if (menuToggle.checked) {
                nav.style.opacity = "1"; // Restablece la opacidad si el menú está activo
                nav.style.left = "0"; // Mueve el menú a la vista

                // Asegúrate de ocultar el label cuando el menú esté abierto en pantalla pequeña
                abrirMenuLabel.style.display = "none";
            } else {
                // Asegúrate de que el label esté visible cuando el menú esté cerrado en pantallas pequeñas
                abrirMenuLabel.style.display = "block";
                abrirMenuLabel.style.opacity = "1";
            }
        }
    });
});

const menuButtons = document.querySelectorAll('.sidebar__menu-btn');

menuButtons.forEach((btn) => {
  btn.addEventListener('click', (event) => {
    const submenu = event.target.nextElementSibling;
    submenu.classList.toggle('sidebar__submenu--open');
  });
});