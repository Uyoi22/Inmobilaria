<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Metrop�litanos - Servicios</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <header>
            <div class="logo">Metrop�litanos</div>
            <nav>
                <ul>
                    <li><a href="index.jsp">Inicio</a></li>
                    <li><a href="properties.jsp">Propiedades</a></li>
                    <li><a href="auctions.jsp">Subastas</a></li>
                    <li><a href="servicios.jsp">Servicios</a></li>
                    <li><a href="contacto.jsp">Contacto</a></li>
                    <li><a href="cuenta.jsp">Mi Cuenta</a></li>
                </ul>
            </nav>
        </header>

        <section class="services">
            <h1>Servicios</h1>
            <div class="service-list">
                <div class="service-card">
                    <h3>Venta Directa</h3>
                    <p>Ofrecemos un servicio de venta directa de propiedades de famosos con total transparencia.</p>
                    <button id="miBoton" class="miboton">M�s Detalles</button>
                </div>
                <div class="service-card">
                    <h3>Lista de transacciones</h3>
                    <p>Participa en subastas exclusivas de propiedades de celebridades.</p>
                    <button id="miBoton2" class="miboton2">M�s Detalles</button>
                </div>
                <div class="service-card">
                    <h3>Inventario de Bienes por Famoso</h3>
                    <p>Inventario de bienes por Famoso, con trazabilidad tributaria.</p>
                    <button id="miBoton3" class="miboton3">M�s Detalles</button>
                </div>
                <div class="service-card">
                    <h3>Recaudaci�n y Pagos</h3>
                    <p>Valor recaudado por comisiones y Valor pagado en impuestos por pa�s.</p>
                    <button  id="miBoton4" class="miboton4">M�s Detalles</button>
                </div>
            </div>
        </section>

        <footer>
            <div class="footer-content">
                <p>� 2024 Metrop�litanos. Todos los derechos reservados.</p>
                <div class="social-links">
                    <a href="#">Facebook</a>
                    <a href="#">Twitter</a>
                    <a href="#">Instagram</a>
                </div>
            </div>
        </footer>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Selecciona el bot�n por su ID u otra forma adecuada
                const button = document.getElementById('miBoton');

                // Agrega un evento de clic al bot�n
                button.addEventListener('click', function () {
                    // Define la URL a la que quieres dirigirte
                    const link = 'http://localhost:8080/Inmobilaria/registro.jsp';

                    // Cambia la ubicaci�n actual de la ventana del navegador a la URL especificada
                    window.location.href = link;
                });

                const button2 = document.getElementById('miBoton2');

                // Agrega un evento de clic al bot�n
                button2.addEventListener('click', function () {
                    // Define la URL a la que quieres dirigirte
                    const link2 = 'http://localhost:8080/Inmobilaria/admin.jsp';

                    // Cambia la ubicaci�n actual de la ventana del navegador a la URL especificada
                    window.location.href = link2;
                });

                const button3 = document.getElementById('miBoton3');

                // Agrega un evento de clic al bot�n
                button3.addEventListener('click', function () {
                    // Define la URL a la que quieres dirigirte
                    const link3 = 'http://localhost:8080/Inmobilaria/inventario.jsp';

                    // Cambia la ubicaci�n actual de la ventana del navegador a la URL especificada
                    window.location.href = link3;
                });
                
                const button4 = document.getElementById('miBoton4');

                // Agrega un evento de clic al bot�n
                button4.addEventListener('click', function () {
                    // Define la URL a la que quieres dirigirte
                    const link4 = 'http://localhost:8080/Inmobilaria/recaudacion.jsp';

                    // Cambia la ubicaci�n actual de la ventana del navegador a la URL especificada
                    window.location.href = link4;
                });
            });
        </script>
    </body>
</html>
