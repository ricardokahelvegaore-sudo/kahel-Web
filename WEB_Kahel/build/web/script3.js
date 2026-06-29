/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
// 1. Capturar el ID de la categoría que viene en la URL (?cat=1, ?cat=2, etc.)
const urlParams = new URLSearchParams(window.location.search);
const IdCategoria = urlParams.get('cat')|| "1";

// Tus 6 categorías oficiales para pintar el título arriba
const nombresCategorias = {
    "1": "Tortas y Postres",
    "2": "Postres por porciones",
    "3": "Queques",
    "4": "Bocaditos",
    "5": "Torta Personalizadas",
    "6": "Pastelería Fina"
};

// Si existe la categoría, ponemos el título flotante en index3.html
if (IdCategoria && nombresCategorias[IdCategoria]) {
    const tituloHtml = document.getElementById('categoria-titulo');
    if (tituloHtml) {
        tituloHtml.innerText = `🍰 ${nombresCategorias[IdCategoria].toUpperCase()}`;
    }
}

// 2. IR A LA BASE DE DATOS: Le pedimos al Servlet los productos de esta categoría
if (IdCategoria) {
    fetch(`ObtenerProductosServlet?cat=${IdCategoria}`)
        .then(response => response.json())
        .then(productosReal => { 
            dibujarTarjetasProductos(productosReal);
        })
        .catch(err => {
            console.error("Error conectando al Servlet Buscador:", err);
            const contenedor = document.getElementById('productos-contenedor');
            if (contenedor) {
                contenedor.innerHTML = "<p style='color:red; text-align:center;'>Error al cargar los productos desde la BD.</p>";
            }
        });
}

// 3. DIBUJAR LAS TARJETAS CON PRECIO, STOCK Y SELECTOR DE CANTIDAD
function dibujarTarjetasProductos(listaProductos) {
    const contenedor = document.getElementById('productos-contenedor');
    if (!contenedor) return;
    
    contenedor.innerHTML = ""; // Limpiamos la pantalla

    // DEPURACIÓN FRONTEND: Ver en la consola del navegador (F12) qué llaves están llegando
    console.log("Estructura real del producto recibido:", listaProductos[0]);

    if (!listaProductos || listaProductos.length === 0) {
        contenedor.innerHTML = "<p style='text-align:center; color:#666;'>No hay productos registrados en esta categoría por el momento.</p>";
        return;
    }

    // Recorremos los productos asegurando las llaves exactas generadas por tu Servlet ("id", "nombre", "precio", "stock")
    listaProductos.forEach(prod => {
        // Validamos el stock (Tu servlet envía "stock" en minúsculas)
        const stockDisponible = parseInt(prod.stock) || 0;
        const tieneStock = stockDisponible > 0;

        // Armamos la imagen dinámicamente con la carpeta física y el prod.id
        const rutaImagen = `img/producto-${prod.id}.jpg`;

        contenedor.innerHTML += `
            <div class="tarjeta" style="opacity: ${tieneStock ? '1' : '0.5'}; border: 1px solid #ddd; padding: 15px; border-radius: 10px; text-align: center; background: white; box-shadow: 0 4px 6px rgba(0,0,0,0.05);">
                <img src="${rutaImagen}" alt="${prod.nombre}" onerror="this.src='img/default.jpg';" style="width:100%; max-height:200px; object-fit:cover; border-radius:8px;">
                
                <h3 style="margin: 10px 0 5px 0; font-size: 18px; color: #333;">${prod.nombre}</h3>
                
                <p style="font-size: 13px; color: #777; margin-bottom: 10px;">Delicioso y fresco del día</p>
                
                <p style="font-weight: bold; color: #ff4757; margin: 5px 0; font-size: 16px;">S/ ${parseFloat(prod.precio).toFixed(2)}</p>
                
                <p style="font-size: 13px; color: ${tieneStock ? '#2ed573' : '#ff4757'}; font-weight: bold; margin-bottom: 10px;">
                    ${tieneStock ? `Stock disponible: ${stockDisponible}` : '❌ AGOTADO'}
                </p>
                
                ${window.location.search.includes('cat=3') ? `
                    <div style="margin: 10px 0; background: #f9f9f9; padding: 6px; border-radius: 5px; border: 1px dashed #ccc;">
                        <input type="checkbox" id="chispas-${prod.id}" style="cursor:pointer;">
                        <label for="chispas-${prod.id}" style="font-size: 12px; font-weight: bold; color: #555; cursor:pointer;">
                            ¿Añadir chispas? (+ S/ 7.00) 🍫
                        </label>
                    </div>
                ` : ''}
                
                ${tieneStock ? `
                    <div style="margin: 10px 0; display: flex; align-items: center; justify-content: center; gap: 10px;">
                        <label style="font-size: 13px; font-weight: bold; color: #555;">Cantidad:</label>
                        <input type="number" id="cant-${prod.id}" value="1" min="1" max="${stockDisponible}" 
                               style="width: 55px; padding: 5px; border-radius: 5px; border: 1px solid #ccc; text-align: center; font-weight: bold;">
                    </div>
                    
                    <button onclick="validarYAgregar(${prod.id}, '${prod.nombre.replace(/'/g, "\\'")}', ${prod.precio}, ${stockDisponible})" 
                            style="margin-top:5px; padding:10px; background: #25D366; color:white; border:none; border-radius:10px; font-weight:bold; width:100%; cursor:pointer;">
                            Agregar al Carrito 🛒
                        </button>
                ` : `
                    <button disabled style="margin-top:10px; padding:10px; background: #a4b0be; color:white; border:none; border-radius:10px; font-weight:bold; width:100%; cursor: not-allowed;">
                        Agotado Temporalmente ❌
                    </button>
                `}
            </div>
        `;
    });
}

// 4. VALIDAR QUE LA CANTIDAD NO SUPERE EL STOCK EXISTENTE EN LA BD
function validarYAgregar(id, nombre, precio, stockMaximo) {
    const inputCantidad = document.getElementById(`cant-${id}`);
    const cantidadElegida = parseInt(inputCantidad.value);

    if (isNaN(cantidadElegida) || cantidadElegida <= 0) {
        alert("Por favor, ingresa una cantidad válida mayor a 0.");
        return;
    }

    if (cantidadElegida > stockMaximo) {
        alert(`¡Alerta de Stock! Solo tenemos ${stockMaximo} unidades disponibles de este producto.`);
        return;
    }

    // === AGREGAR SOLO ESTAS LÍNEAS AQUÍ ABAJO ===
    const checkboxChispas = document.getElementById(`chispas-${id}`);
    if (checkboxChispas && checkboxChispas.checked) {
        precio = parseFloat(precio) + 7.00;
        nombre = nombre + " (Con Chispas 🍫)";
    }
    // ============================================

    // Si pasa las pruebas de stock, lo guardamos en la memoria del carrito
    guardarEnCarrito(id, nombre, precio, cantidadElegida, stockMaximo);
}

// 5. GUARDAR EN EL LOCALSTORAGE CON LA CANTIDAD EXACTA
function guardarEnCarrito(id, nombre, precio, cantidad, stockMaximo) {
    let carrito = JSON.parse(localStorage.getItem('carrito')) || [];
    
    // === CAMBIA SOLO ESTA LÍNEA DE ABAJO ===
    // Ahora busca que coincidan tanto el ID como el NOMBRE exacto
    let itemExistente = carrito.find(p => p.id === id && p.nombre === nombre);
    // ======================================

    if (itemExistente) {
        // Si el producto ya estaba en el carrito (con el mismo nombre), sumamos las cantidades
        if ((itemExistente.cantidad + cantidad) > stockMaximo) {
            alert(`No puedes agregar más. Ya tienes ${itemExistente.cantidad} en tu carrito y el stock máximo en BD es ${stockMaximo}.`);
            return;
        }
        itemExistente.cantidad += cantidad;
    } else {
        // Si no existe un producto con ese ID y ese NOMBRE exacto, lo mete como una fila nueva
        carrito.push({ id: id, nombre: nombre, precio: precio, cantidad: cantidad });
    }

    localStorage.setItem('carrito', JSON.stringify(carrito));
    alert(`¡Agregado con éxito! Cantidad: ${cantidad}x ${nombre} 🍰`);
}
