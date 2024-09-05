import LoginPage from "../support/pages/Login.page.js";
import RegistroPage from "../support/pages/Registro.page.js";
import TransferenciaPage from "../support/pages/Transferencia.page.js";

import { faker } from '@faker-js/faker';

const loginPage = new LoginPage
const registroPage = new RegistroPage
const transferenciaPage = new TransferenciaPage

describe('transferencia', () =>{
    beforeEach(() => {
        const registro = {
          nome: faker.person.fullName(),
          email: faker.internet.email(),
          senha: faker.string.alphanumeric(8)
        };
      
        cy.writeFile('cypress/fixtures/RegistroData.json', registro);

        const transferencia = {
            valor: faker.number.float({ min: 100, max: 1000, precision: 0.01 }),
            descricao: faker.lorem.sentence()
          };
        
          cy.writeFile('cypress/fixtures/TransferenciaData.json', transferencia);
        cy.visit("/");
    });
    
    it('teste1 - Validar transferencia com sucesso', () => {
        cy.fixture('RegistroData').then((registro) => {   
            registroPage.clicarBtnRegistrar()
            cy.fazerRegistroComSucesso(registro)
            registroPage.clicarBtnFecharAvisoSucesso()
            cy.fazerLogin(registro)
            const codigo = transferenciaPage.retornaCodigo() + ""
            loginPage.clicarBtnSair()
        })

        const registro = {
            nome: faker.person.fullName(),
            email: faker.internet.email(),
            senha: faker.string.alphanumeric(8)
          };
        
          cy.writeFile('cypress/fixtures/RegistroData.json', registro);

        cy.fixture('RegistroData').then((registro) => {   
            registroPage.clicarBtnRegistrar()
            cy.fazerRegistroComSucesso(registro)
            registroPage.clicarBtnFecharAvisoSucesso()
            cy.fazerLogin(registro)
            transferenciaPage.clicarBtnTransferencia()
        })
        cy.fixture('TransferenciaData').then((transferencia) => {   
            // const [primeirosDigitos, ultimoDigito] = codigo.split('-')
            cy.fazerTransferenciaComSucesso(primeirosDigitos+"", ultimoDigito+"", transferencia)
            transferenciaPage.clicarBtnFecharAviso()
        })
    })

    // it('teste2 - Tentar transferencia com conta invalida', () => {
    //     registroPage.clicarBtnRegistrar()
    //     registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro2)
    //     registroPage.clicarBtnFecharAvisoSucesso()
    //     loginPage.fazerLoginComSucesso(dadosRegistro2)
    //     transferenciaPage.clicarBtnTransferencia()
    //     transferenciaPage.fazerTransferenciaComSucesso("000", "1", dadosTransferencia)
    //     transferenciaPage.verificaTextoContaInvalida()
    //     transferenciaPage.clicarBtnFecharAviso()
    // })

    // it('teste3 - Tentar transferencia com saldo insuficiente', () => {
    //     registroPage.clicarBtnRegistrar()
    //     registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro)
    //     registroPage.clicarBtnFecharAvisoSucesso()
    //     loginPage.fazerLoginComSucesso(dadosRegistro)
    //     const codigo = transferenciaPage.retornaCodigo() + ""
    //     loginPage.clicarBtnSair()
    //     registroPage.clicarBtnRegistrar()
    //     registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro2)
    //     registroPage.clicarBtnFecharAvisoSucesso()
    //     loginPage.fazerLoginComSucesso(dadosRegistro2)
    //     transferenciaPage.clicarBtnTransferencia()
    //     const [primeirosDigitos, ultimoDigito] = codigo.split('-')
    //     transferenciaPage.tentarTransferenciaComSaldoInsuficiente(primeirosDigitos+"", ultimoDigito+"", dadosTransferencia)
    //     transferenciaPage.clicarBtnFecharAviso()
    // })
})