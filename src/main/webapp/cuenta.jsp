<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Metropólitanos - Mi Cuenta</title>
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

        <section class="account">
            <h1>Mi Cuenta</h1>
            <div class="account-content">
                <div class="account-info">
                    <h2>Información del Usuario</h2>
                    <form id="accountForm">
                        <label for="username">Nombre de Usuario:</label>
                        <input type="text" id="username" name="username" required>
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required>
                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" name="password" required>
                        <button type="submit">Actualizar Información</button>
                    </form>
                </div>
                <div class="user-properties">
                    <h2>Mis Propiedades</h2>
                    <div class="property-list">
                        <!-- Aquí se añadirán las tarjetas de propiedades del usuario -->
                        <div class="property-card">
                            <img src="property1.jpg" alt="Propiedad 1">
                            <div class="property-details">
                                <h3>Propiedad 1</h3>
                                <p>Descripción breve de la propiedad 1.</p>
                                <button>Más Detalles</button>
                            </div>
                        </div>
                        <div class="property-card">
                            <img src="property2.jpg" alt="Propiedad 2">
                            <div class="property-details">
                                <h3>Propiedad 2</h3>
                                <p>Descripción breve de la propiedad 2.</p>
                                <button>Más Detalles</button>
                            </div>
                        </div>
                    </div>
                </div>
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
