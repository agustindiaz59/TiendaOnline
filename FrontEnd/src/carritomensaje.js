// Función para mostrar el mensaje
export function mostrarMensaje() {
    const mensaje = document.getElementById('mensaje');
    mensaje.classList.add('mensaje-visible');
    
    // Después de 3 segundos, ocultamos el mensaje
    setTimeout(() => {
        mensaje.classList.remove('mensaje-visible');
    }, 3000); // El mensaje se oculta después de 3 segundos
}

// Función para asociar la acción al botón
export function agregarEventoBoton() {
    const botonAgregar = document.getElementById('boton-agregar');
    if (botonAgregar) {
        botonAgregar.addEventListener('click', mostrarMensaje);
    }
}