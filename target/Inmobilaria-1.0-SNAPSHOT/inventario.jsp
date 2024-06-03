<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inventario de Bienes por Famoso</title>
        <link rel="stylesheet" href="css/styles2.css">
    </head>
    <body>
        <header>
            <h1>Inventario de Bienes por Famoso</h1>
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
        <main>
            <section id="inventory">
                <label for="famosoId">Seleccione un famoso:</label>
                <select id="famosoId">
                    <!-- Options will be populated dynamically -->
                </select>
                <div id="inventoryList"></div>
            </section>
        </main>
        <script src="js/inventory.js"></script>
    </body>
</html>
