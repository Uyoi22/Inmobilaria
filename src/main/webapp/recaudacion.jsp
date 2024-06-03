<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Recaudación y Pagos</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <header>
            <div class="logo">Metropolitanos</div>
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
        <section class="recaudacion">
            <h2>Recaudación y Pagos</h2>
            <div class="recaudacion-comisiones">
                <h3>Valor Recaudado por Comisiones</h3>
                <p id="valorComisiones">Cargando...</p>
            </div>
            <div class="recaudacion-impuestos">
                <h3>Valor Pagado en Impuestos por País</h3>
                <table id="impuestosTabla">
                    <thead>
                        <tr>
                            <th>País</th>
                            <th>Valor Pagado en Impuestos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Las filas se llenarán dinámicamente -->
                    </tbody>
                </table>
            </div>
        </section>
        <script src="js/recaudacion.js"></script>
    </body>
</html>
