import LoginPage from "../support/pages/Login.page.js";
import RegistroPage from "../support/pages/Registro.page.js";
import { faker } from '@faker-js/faker';

const loginPage = new LoginPage();
const registroPage = new RegistroPage();

describe('login', () => {

    beforeEach(() => {
        const login = {
          nome: faker.person.fullName(),
          email: faker.internet.email(),
          senha: faker.string.alphanumeric(8)
        };
      
        cy.writeFile('cypress/fixtures/RegistroData.json', login);
        cy.visit("/");
    });
      
    it('teste1 - Validar login com dados válidos', () => {
        cy.fixture('RegistroData').then((login) => {
          registroPage.clicarBtnRegistrar();
          cy.fazerRegistroComSucesso(login);
          registroPage.clicarBtnFecharAvisoSucesso();
          cy.fazerLogin(login);
          loginPage.clicarBtnSair();
        });
    });

    it('teste2 - Tentar login com dados inválidos', () => {
        cy.fixture('RegistroData').then((login) => {
            cy.fazerLogin(login); // Verifique se `dadosRegistro` deveria ser `login`
            loginPage.verificaTextoLoginIncorreto();
            loginPage.clicarBtnFecharAvisoErro();
        });
    });

    it('teste3 - Tentar login com email em branco', () => {
        const senha = faker.internet.password();
        loginPage.preencherSenha(senha);
        loginPage.clicarBtnLogin();
        loginPage.verificaTextoEmailEmBranco();
    });
});
