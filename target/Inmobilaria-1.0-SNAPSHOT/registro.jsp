<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Metropólitanos - Matrícula del Inmueble</title>
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

        <section class="registration">
            <h1>Matrícula del Inmueble y Exposición Pública</h1>
            <div class="registration-content">
                <div class="registration-info">
                    <h2>Registrar su Inmueble</h2>
                    <form id="registrationForm" method="post" action="registerProperty" enctype="multipart/form-data">
                        <label for="propertyType">Tipo de Inmueble:</label>
                        <select id="propertyType" name="propertyType" required>
                            <option value="casa">Casa</option>
                            <option value="apartamento">Apartamento</option>
                            <option value="estudio">Estudio</option>
                            <option value="terreno">Terreno</option>
                        </select>
                        
                        <label for="propertyAddress">Dirección del Inmueble:</label>
                        <input type="text" id="propertyAddress" name="propertyAddress" required>

                        <label for="value">Valor del inmueble:</label>
                        <input type="number" id="value" name="value" required>
                        
                        <label for="famosoId">Id del famoso:</label>
                        <input type="number" id="famosoId" name="famosoId  " required>
                        
                        <label for="propertyState">Estado del Inmueble:</label>
                        <select id="propertyState" name="propertyState" required>
                            <option value="compra">Compra</option>
                            <option value="subasta">Subasta</option>
                        </select>

                        <button type="submit">Registrar Inmueble</button>
                    </form>
                </div>
                <div class="exposure-info">
                    <h2>Exposición Pública</h2>
                    <p>Una vez registrado, su inmueble será promocionado en nuestra plataforma y otros canales de publicidad para asegurar una máxima exposición. Nuestros servicios incluyen:</p>
                    <ul>
                        <li>Listados en nuestro sitio web.</li>
                        <li>Publicaciones en redes sociales.</li>
                        <li>Anuncios en portales inmobiliarios.</li>
                        <li>Tours virtuales y visitas programadas.</li>
                    </ul>
                </div>
            </div>
        </section>

        <footer>
            <div class="footer-content">
                <p>© 2024 Metropólitanos. Todos los derechos
