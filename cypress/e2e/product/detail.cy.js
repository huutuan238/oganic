beforeEach(() => {
    cy.visit('/product/1');
})
it('Test product detail', () => {
    cy
        .get("#product-details")
        .find('.product__details__price')
        .should('have.text', 10);
    cy
        .get("#product-details")
        .find('.product-name')
        .should('have.text', "MEAT");
})

it('Increment quatity', () => {
    cy
        .get("#product-details")
        .find('.inc')
        .click();
    cy
        .get("#product-details")
        .find('.quatity-value')
        .should('have.value', 2);
})
