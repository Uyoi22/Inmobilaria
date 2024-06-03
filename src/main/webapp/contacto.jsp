<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Metropólitanos - Contacto</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <header>
            <div class="logo">Metropólitanos</div>
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

        <section class="contact">
            <h1>Contacto</h1>
            <div class="contact-info">
                <h2>Información de Contacto</h2>
                <p>Teléfono: +34 123 456 789</p>
                <p>Email: contacto@metropolitanos.com</p>
                <p>Dirección: Calle Falsa 123, Madrid, España</p>
            </div>
            <div class="contact-form">
                <h2>Formulario de Contacto</h2>
                <form id="contactForm">
                    <label for="name">Nombre:</label>
                    <input type="text" id="name" name="name" required>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>

                    <label for="message">Mensaje:</label>
                    <textarea id="message" name="message" required></textarea>

                    <button type="submit">Enviar</button>
                </form>
            </div>
        </section>

        <footer>
            <div class="footer-content">
                <p>© 2024 Metropólitanos. Todos los derechos reservados.</p>
                <div class="social-links">
                    <a href="#">Facebook</a>
                    <a href="#">Twitter</a>
                    <a href="#">Instagram</a>
                </div>
            </div>
        </footer>

        <script src="js/scripts.js"></script>
    </body>
</html>
