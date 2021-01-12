const getProduct = () => {
    return fetch("/api/products")
        .then(response => response.json())
        .catch((error) => console.log(error))
}

const createHtmlElementFromString = (template) => {
    let parent = document.createElement("div");
    parent.innerHTML = template.trim();

    return parent.firstChild;
}

const createProductComponent = (product) => {
    const template = `
        <li class="product">
            <h3>${product.description}</h3>
            <div class="product__image-container">
                <img class="product__image" src="${product.picture}"/>
            </div>
            <span class="product__price">${product.price}</span>
            <button
                class="product__add-to-basket"
                data-product-id="${product.productId}"
            >
                Add to basket
            </button>
        </li>
    `;

    return createHtmlElementFromString(template);
}

const handleAddToBasket = (productId) => {
    console.log(`i am adding to basket prod with id: ${productId}`);

    return fetch(`/api/add-product/${productId}`, {
      method: 'POST',
    })
}

const refreshBasketComponent = (offer) => {
    document.querySelector('.basket__items-count-value')
        .innerText = offer.productsCount;

    document.querySelector('.basket__items-total-value')
        .innerText = `${offer.total} PLN`;
}

const refreshCurrentOffer = () => {
    return fetch("/api/current-offer")
        .then(response => response.json())
        .then(offer => refreshBasketComponent(offer));
}

const initializeAddToBasketHandler = (element) => {
    const button = element.querySelector('button.product__add-to-basket');

    button.addEventListener('click', (event) => {
        const productId = event.target.getAttribute('data-product-id');
        handleAddToBasket(productId)
            .then(() => refreshCurrentOffer())
        ;
    });

    return element;
}

(() => {
    const productsList = document.querySelector(".products");

    getProduct()
        .then((products) => {
            products
                .map(product => createProductComponent(product))
                .map(liEl => initializeAddToBasketHandler(liEl))
                .forEach(liEl => {
                    productsList.appendChild(liEl);
                })
        });

    refreshCurrentOffer()
        .then(offer => refreshBasketComponent(offer));
})();
