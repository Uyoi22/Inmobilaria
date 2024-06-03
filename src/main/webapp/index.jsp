<%-- 
    Document   : index
    Created on : 28/05/2024, 10:03:02 p. m.
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Metropólitanos - Inicio</title>
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

        <section class="banner">
            <div class="banner-content">
                <h1>Explora las propiedades de los famosos</h1>
                <button onclick="scrollToSection('destacadas')">Ver Propiedades Destacadas</button>
            </div>
        </section>

        <section class="destacadas" id="destacadas">
            <h2>Propiedades Destacadas</h2>
            <div class="property-list">
                <div class="dest-card">
                    <img src="img/property1.jpg" alt="Mansión en Beverly Hills">
                    <h3>Mansión en Beverly Hills</h3>
                </div>
                <div class="dest-card">
                    <img src="img/property2.jpg" alt="Apartamento en Nueva York">
                    <h3>Apartamento en Nueva York</h3>
                </div>
                <div class="dest-card">
                    <img src="img/property3.jpg" alt="Villa en Miami">
                    <h3>Villa en Miami</h3>
                </div>
            </div>
        </section>

        <section class="testimonios">
            <h2>Testimonios</h2>
            <div class="testimonials">
                <!-- Aquí se añadirán los testimonios -->
                <div class="testimonial-card">
                    <p>"Una experiencia increíble. Muy profesional."</p>
                    <h4>- John Doe</h4>
                </div><div class="testimonial-card">
                    <p>"Encontré la casa de mis sueños gracias a Metropólitanos."</p>
                    <h4>- Jane Smith</h4>
                </div></div>
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
    </body>
</html>
