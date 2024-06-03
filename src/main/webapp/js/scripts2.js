document.addEventListener("DOMContentLoaded", function () {
    init();
});

async function init() {
    try {
        await loadProperties();
        await loadAuctions();
    } catch (error) {
        console.error('Error during initialization:', error);
    }
}

async function loadProperties() {
    try {
        const response = await fetch('api/properties?estado=compra');
        if (!response.ok) {
            throw new Error(`Network response was not ok: ${response.statusText}`);
        }
        const data = await response.json();
        const propertyList = document.getElementById('propertyList');
        propertyList.innerHTML = ''; // Clear any existing content
        data.forEach(property => {
            const propertyCard = createPropertyCard(property);
            propertyList.appendChild(propertyCard);
        });
    } catch (error) {
        console.error('There was a problem with the fetch operation for properties:', error);
    }
}

async function loadAuctions() {
    try {
        const response = await fetch('api/properties?estado=subasta');
        if (!response.ok) {
            throw new Error(`Network response was not ok: ${response.statusText}`);
        }
        const data = await response.json();
        const auctionList = document.getElementById('auctionList');
        auctionList.innerHTML = ''; // Clear any existing content
        data.forEach(property => {
            const propertyCard = createAuctionCard(property);
            auctionList.appendChild(propertyCard);
        });
    } catch (error) {
        console.error('There was a problem with the fetch operation for auctions:', error);
    }
}

function createPropertyCard(property) {
    const card = document.createElement('div');
    card.classList.add('property-card');
    let tipo = 0;
    switch (property.tipo) {
        case "casa":
            tipo = 1;
            break;
        case "apartamento":
            tipo = 2;
            break;
        case "estudio":
            tipo = 3;
            break;
        case "terreno":
            tipo = 4;
            break;
    }
    card.innerHTML = `
        <h2 id="tipo">${property.tipo}</h2>
        <p>Ubicacion: ${property.ubicacion}</p>
        <p>Valor: ${property.valor}</p>
        <p>Famoso ID: ${property.famoso_id}</p>
        <button onclick="showDetails(${property.id}, ${property.valor}, ${tipo})">Mas Detalles</button>
    `;
    return card;
}

function createAuctionCard(property) {
    const card = document.createElement('div');
    card.classList.add('property-card');
    let tipo = 0;
    switch (property.tipo) {
        case "casa":
            tipo = 1;
            break;
        case "apartamento":
            tipo = 2;
            break;
        case "estudio":
            tipo = 3;
            break;
        case "terreno":
            tipo = 4;
            break;
    }
    card.innerHTML = `
        <h2 id="tipo">${property.tipo}</h2>
        <p>Ubicacion: ${property.ubicacion}</p>
        <p>Valor actual: ${property.valor}</p>
        <p>Famoso ID: ${property.famoso_id}</p>
        <button onclick="placeBid(${property.id}, ${property.valor})">Participar en la subasta</button>
        <button onclick="endAuction(${property.id}, ${property.famoso_id}, ${property.valor}, ${tipo})">Finalizar Subasta</button>
    `;
    return card;
}

function showDetails(propertyId, propertyValor, propertyTipo) {
    const nuevoFamosoId = prompt('Ingrese el nuevo Famoso ID:');
    if (nuevoFamosoId) {
        updateProperty(propertyId, nuevoFamosoId, propertyValor, propertyTipo);
    }
}

async function updateProperty(propertyId, nuevoFamosoId, propertyValor, propertyTipo) {
    try {
        let tipo = "hola";
        switch (propertyTipo) {
            case 1:
                tipo = "casa";
                break;
            case 2:
                tipo = "apartamento";
                break;
            case 3:
                tipo = "estudio";
                break;
            case 4:
                tipo = "terrreno";
                break;
        }
        const response = await fetch('api/updateProperty', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: propertyId,
                famosoId: nuevoFamosoId,
                precio: propertyValor,
                tipo: tipo
            })
        });
        const data = await response.json();
        if (response.ok) {
            alert('Propiedad actualizada exitosamente.');
            init(); // Refresh the property lists
        } else {
            alert('Error al actualizar la propiedad: ' + data.error);
        }
    } catch (error) {
        console.error('There was a problem with the fetch operation for updating property:', error);
    }
}

function placeBid(propertyId, currentValue) {
    const nuevoFamosoId = prompt('Ingrese su Famoso ID:');
    const newValue = prompt('Ingrese su puja (debe ser mayor que el valor actual):', currentValue + 1);
    if (nuevoFamosoId && newValue && parseFloat(newValue) > currentValue) {
        sendBid(propertyId, parseFloat(newValue), parseInt(nuevoFamosoId));
    } else {
        alert('La puja debe ser mayor que el valor actual.');
    }
}

async function sendBid(propertyId, newValue, nuevoFamosoId) {
    try {
        const response = await fetch('api/placeBid', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: propertyId,
                valor: newValue,
                famosoId: nuevoFamosoId
            })
        });
        const data = await response.json();
        if (response.ok) {
            alert('Puja realizada exitosamente.');
            init(); // Refresh the auction list
        } else {
            alert('Error al realizar la puja: ' + data.error);
        }
    } catch (error) {
        console.error('There was a problem with the fetch operation for placing a bid:', error);
    }
}

async function endAuction(propertyId, propertyFamosoId, propertyValor, propertyTipo) {
    try {
        let tipo = "hola";
        switch (propertyTipo) {
            case 1:
                tipo = "casa";
                break;
            case 2:
                tipo = "apartamento";
                break;
            case 3:
                tipo = "estudio";
                break;
            case 4:
                tipo = "terrreno";
                break;
        }
        const response = await fetch('api/endAuction', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: propertyId,
                famosoId: propertyFamosoId,
                precio: propertyValor,
                tipo: tipo
            })
        });
        const data = await response.json();
        if (response.ok) {
            alert('Subasta finalizada exitosamente.');
            init(); // Refresh the auction list
        } else {
            alert('Error al finalizar la subasta: ' + data.error);
        }
    } catch (error) {
        console.error('There was a problem with the fetch operation for ending auction:', error);
    }
}