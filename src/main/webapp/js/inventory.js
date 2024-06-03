document.addEventListener("DOMContentLoaded", function() {
    loadFamosos();
    document.getElementById('famosoId').addEventListener('change', loadInventory);
});

async function loadFamosos() {
    try {
        const response = await fetch('api/getFamosos');
        if (!response.ok) {
            throw new Error(`Network response was not ok: ${response.statusText}`);
        }
        const data = await response.json();
        const famosoSelect = document.getElementById('famosoId');
        data.forEach(famoso => {
            const option = document.createElement('option');
            option.value = famoso.famoso_id;
            option.textContent = famoso.nombre;
            famosoSelect.appendChild(option);
        });
    } catch (error) {
        console.error('There was a problem with the fetch operation for famosos:', error);
    }
}

async function loadInventory() {
    const famosoId = document.getElementById('famosoId').value;
    if (!famosoId) return;

    try {
        const response = await fetch(`api/getInventario?famosoId=${famosoId}`);
        if (!response.ok) {
            throw new Error(`Network response was not ok: ${response.statusText}`);
        }
        const data = await response.json();
        const inventoryList = document.getElementById('inventoryList');
        inventoryList.innerHTML = ''; // Clear any existing content
        data.forEach(item => {
            const itemCard = createInventoryCard(item);
            inventoryList.appendChild(itemCard);
        });
    } catch (error) {
        console.error('There was a problem with the fetch operation for inventory:', error);
    }
}

function createInventoryCard(item) {
    const card = document.createElement('div');
    card.classList.add('inventory-card');
    card.innerHTML = `
        <h2>Inmueble ID: ${item.inmueble_id}</h2>
        <p>Famoso: ${item.famoso_nombre}</p>
        <p>Tipo: ${item.tipo}</p>
        <p>Ubicacion: ${item.ubicacion}</p>
        <p>Valor: ${item.valor}</p>
        <p>Precio de Transaccion: ${item.precio}</p>
        <p>Comision: ${item.comision}</p>
        <p>Impuesto: ${item.impuesto}</p>
        <p>Fecha de Transaccion: ${item.fecha}</p>
    `;
    return card;
    //${new Date(item.fecha).toLocaleString()}
}
