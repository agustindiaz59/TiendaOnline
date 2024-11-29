
document.querySelector('.btsfiltrar').addEventListener('click', function() {
    const sidebar = document.querySelector('.sidebar');
    sidebar.classList.toggle('active'); // Alterna la clase 'active' para abrir o cerrar el sidebar
  });
  document.querySelector('.cerrarbotonbtn').addEventListener('click', function() {
    const sidebar = document.querySelector('.sidebar');
    sidebar.classList.remove('active'); // Elimina la clase 'active' para cerrar el sidebar
});