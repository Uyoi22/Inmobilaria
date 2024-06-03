document.addEventListener("DOMContentLoaded", function() {
    fetch("api/recaudacion")
        .then(response => response.json())
        .then(data => {
            // Mostrar el valor recaudado por comisiones
            document.getElementById("valorComisiones").textContent = data.totalComisiones.toFixed(2) + " COP";

            // Llenar la tabla de impuestos por país
            const impuestosTabla = document.getElementById("impuestosTabla").querySelector("tbody");
            data.impuestosPorPais.forEach(item => {
                const row = document.createElement("tr");
                const paisCell = document.createElement("td");
                paisCell.textContent = item.nacionalidad; // Cambiado de "pais" a "nacionalidad"
                const valorCell = document.createElement("td");
                valorCell.textContent = item.totalImpuestos.toFixed(2) + " COP";
                row.appendChild(paisCell);
                row.appendChild(valorCell);
                impuestosTabla.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error al obtener los datos de recaudación:", error);
        });
});
