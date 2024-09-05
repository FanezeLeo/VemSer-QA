import RegistroPage from "../support/pages/Registro.page.js";

import { faker } from '@faker-js/faker';


const registroPage = new RegistroPage
describe('registro', () =>{

    beforeEach(() => {
        const registro = {
          nome: faker.person.fullName(),
          email: faker.internet.email(),
          senha: faker.string.alphanumeric(8)
        };
      
        cy.writeFile('cypress/fixtures/RegistroData.json', registro);
        cy.visit("/");
    });


    it('teste1 - Validar registro com dados validos', () => {
        cy.fixture('RegistroData').then((registro) => {
            registroPage.clicarBtnRegistrar();
            cy.fazerRegistroComSucesso(registro);
            registroPage.clicarBtnFecharAvisoSucesso();
          });
    })

    it('teste2 - Tentar registro com nome em branco', () => {
        cy.fixture('RegistroData').then((registro) => {
            registroPage.clicarBtnRegistrar();
            cy.tentarRegistroComNomeEmBranco(registro);
            registroPage.clicarBtnFecharAvisoSucesso();
          });
    })

    it('teste3 - Tentar registro com email em branco', () => {
        cy.fixture('RegistroData').then((registro) => {
            registroPage.clicarBtnRegistrar();
            cy.tentarRegistroComEmailEmBranco(registro);
          });
    })

    it('teste4 - Tentar registro com senhas diferentes', () => {
        cy.fixture('RegistroData').then((registro) => {
            registroPage.clicarBtnRegistrar();
            cy.tentarRegistroComSenhasDiferentes(registro);
            registroPage.clicarBtnFecharAvisoSucesso();
          });
    })



})