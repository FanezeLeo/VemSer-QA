export default class BasePage{

    clicarBtn(btn){
        cy.get(btn).click({ force: true })
    }

    preencherCampo(campo, dado){
        cy.get(campo).type(dado, { force: true })
    }

    verificaTextoEqual(lerTexto, texto){
        cy.get(lerTexto).should('have.text', texto, { force: true });
    }

    verificaTextoExist(texto){
        cy.get(texto).should('be.visible');
    }

}