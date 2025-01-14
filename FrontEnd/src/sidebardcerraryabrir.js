export function toggleFilterDisplay() {
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