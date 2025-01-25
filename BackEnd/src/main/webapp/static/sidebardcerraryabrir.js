// Evento para abrir o cerrar el sidebar al hacer clic en el botón de filtro
document.querySelector('.btsfiltrar').addEventListener('click', function() {
  const sidebar = document.querySelector('.sidebar'); // Seleccionamos el sidebar
  sidebar.classList.toggle('active'); // Alterna la clase 'active' para abrir o cerrar el sidebar
});

// Evento para cerrar el sidebar al hacer clic en el botón de cerrar
document.querySelector('.cerrarbotonbtn').addEventListener('click', function() {
  const sidebar = document.querySelector('.sidebar'); // Seleccionamos el sidebar
  sidebar.classList.remove('active'); // Elimina la clase 'active' para cerrar el sidebar
});
