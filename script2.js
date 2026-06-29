/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
// Jala automáticamente los productos que el usuario agregó desde la pantalla de productos
let carrito = JSON.parse(localStorage.getItem('carrito')) || [];

function mostrarCarrito() {
    const container = document.getElementById('cart-items');
    container.innerHTML = '';
    
    let total = 0;
    
    if (carrito.length === 0) {
        container.innerHTML = '<p class="empty-msg">Tu carrito está vacío actualmente.</p>';
        document.getElementById('cart-total').innerText = "S/ 0.00";
        return;
    }
    
    // Recorremos el carrito calculando la suma exacta por cada producto
    carrito.forEach((item, index) => {
        let subtotalItem = item.precio * item.cantidad;
        total += subtotalItem;

        container.innerHTML += `
            <div class="cart-item">
                <div class="item-info">
                    <h4>${item.nombre}</h4>
                    <small>Precio Unitario: S/ ${item.precio.toFixed(2)}</small><br>
                    <small style="color:#007bff; font-weight:bold;">Subtotal: S/ ${subtotalItem.toFixed(2)}</small>
                </div>
                <div style="display: flex; align-items: center; gap: 15px;">
                    <span style="font-weight: bold; color: #333; background: #e2e8f0; padding: 5px 10px; border-radius: 5px;">
                        Cant: ${item.cantidad}
                    </span>
                    <button class="btn-eliminar" onclick="eliminarProducto(${index})">Eliminar</button>
                </div>
            </div>
        `;
    });
    
    // Inyecta la suma global exacta en el HTML
    document.getElementById('cart-total').innerText = `S/ ${total.toFixed(2)}`;
}

function eliminarProducto(index) {
    carrito.splice(index, 1); 
    localStorage.setItem('carrito', JSON.stringify(carrito)); 
    mostrarCarrito(); 
}

// ENVÍA EL CARRITO CON EL TOTAL EXACTO AL SERVLET DE JAVA
function enviarAlServidor() {
    if (carrito.length === 0) {
        alert("No hay productos en el carrito.");
        return;
    }

    let totalVenta = carrito.reduce((sum, item) => sum + (item.precio * item.cantidad), 0);

    // NUEVO: Enviamos el total Y TAMBIÉN la lista completa del carrito para que Java descuente el stock
    let datosCompra = {
        total: totalVenta,
        productos: carrito 
    };

    // Mandamos la data estructurada al Servlet sin recargar
    fetch('ProcesarCompraServlet', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(datosCompra)
    })
    .then(response => response.text())
    .then(data => {
        if (data === "OK") {
            alert("¡Pedido registrado con éxito en MySQL y stock actualizado! 🎂🍰");
            carrito = []; // Vaciamos el carrito de la pantalla
            localStorage.removeItem('carrito');
            mostrarCarrito();
        } else {
            alert("Error al registrar el pedido.");
        }
    })
    .catch(error => console.error('Error enviando al servidor:', error));
}

mostrarCarrito();

