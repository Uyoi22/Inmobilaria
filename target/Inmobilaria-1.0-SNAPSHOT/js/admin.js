document.addEventListener("DOMContentLoaded", function() {
    loadTransactions();
});

async function loadTransactions() {
    try {
        const response = await fetch('api/getTransactions');
        if (!response.ok) {
            throw new Error(`Network response was not ok: ${response.statusText}`);
        }
        const data = await response.json();
        const transactionList = document.getElementById('transactionList');
        transactionList.innerHTML = ''; // Clear any existing content
        data.forEach(transaction => {
            const transactionCard = createTransactionCard(transaction);
            transactionList.appendChild(transactionCard);
        });
    } catch (error) {
        console.error('There was a problem with the fetch operation for transactions:', error);
    }
}

function createTransactionCard(transaction) {
    const card = document.createElement('div');
    card.classList.add('property-card');
    card.innerHTML = `
        <h3>Transaccion ID: ${transaction.transaccion_id}</h2>
        <p>Tipo: ${transaction.tipo}</p>
        <p>Fecha: ${transaction.fecha}</p>
        <p>Inmueble ID: ${transaction.inmueble_id}</p>
        <p>Famoso ID: ${transaction.famoso_id}</p>
        <p>Precio: ${transaction.precio}</p>
        <p>Comision: ${transaction.comision}</p>
    `;
    return card;
}
