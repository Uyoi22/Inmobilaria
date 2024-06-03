// Ejemplo de subastas
var auctions = [
    {
        image: 'auction1.jpg',
        title: 'Subasta de Casa en Miami',
        description: 'Casa de lujo con 5 habitaciones y piscina.',
        endDate: '2024-06-30'
    },
    {
        image: 'auction2.jpg',
        title: 'Subasta de Apartamento en Nueva York',
        description: 'Apartamento moderno en el centro de la ciudad.',
        endDate: '2024-07-15'
    }
];

// Mostrar subastas
function showAuctions() {
    var auctionList = document.getElementById('auctionList');
    auctions.forEach(function (auction) {
        var auctionCard = document.createElement('div');
        auctionCard.className = 'auction-card';

        var auctionImage = document.createElement('img');
        auctionImage.src = auction.image;
        auctionCard.appendChild(auctionImage);

        var auctionTitle = document.createElement('h3');
        auctionTitle.textContent = auction.title;
        auctionCard.appendChild(auctionTitle);

        var auctionDescription = document.createElement('p');
        auctionDescription.textContent = auction.description;
        auctionCard.appendChild(auctionDescription);

        var auctionEndDate = document.createElement('p');
        auctionEndDate.textContent = 'Finaliza el: ' + auction.endDate;
        auctionCard.appendChild(auctionEndDate);

        var auctionButton = document.createElement('button');
        auctionButton.textContent = 'Participar';
        auctionButton.onclick = function () {
            alert('Participando en la subasta: ' + auction.title);
        };
        auctionCard.appendChild(auctionButton);

        auctionList.appendChild(auctionCard);
    });
}

// Llamar a la función para mostrar las subastas
document.addEventListener('DOMContentLoaded', showAuctions);

document.addEventListener('DOMContentLoaded', function () {
    var contactForm = document.getElementById('contactForm');

    contactForm.addEventListener('submit', function (e) {
        e.preventDefault();

        var name = document.getElementById('name').value;
        var email = document.getElementById('email').value;
        var message = document.getElementById('message').value;

        alert('Nombre: ' + name + '\nEmail: ' + email + '\nMensaje: ' + message);

        // Aquí puedes añadir el código para enviar el formulario a tu servidor
    });
});

// Manejar el envío del formulario de cuenta
document.getElementById('accountForm').addEventListener('submit', function (event) {
    event.preventDefault();

    var username = document.getElementById('username').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    // Aquí puedes añadir el código para enviar la actualización de la cuenta a tu servidor
    alert('Información actualizada para el usuario: ' + username);

    // Limpiar el formulario
    document.getElementById('accountForm').reset();
});

// Manejar el envío del formulario de registro del inmueble
document.getElementById('registrationForm').addEventListener('submit', function (event) {
    event.preventDefault();

    var ownerName = document.getElementById('ownerName').value;
    var propertyAddress = document.getElementById('propertyAddress').value;
    var propertyType = document.getElementById('propertyType').value;
    var documents = document.getElementById('documents').files;

    // Aquí puedes añadir el código para enviar la información del inmueble a tu servidor
    alert('Inmueble registrado para el propietario: ' + ownerName);

    // Limpiar el formulario
    document.getElementById('registrationForm').reset();
});

