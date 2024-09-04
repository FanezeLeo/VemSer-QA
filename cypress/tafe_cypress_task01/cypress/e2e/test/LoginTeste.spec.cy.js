import LoginPage from "../pages/LoginPage.js";
import RegistroPage from "../pages/RegistroPage.js";
import { faker } from '@faker-js/faker';

const loginPage = new LoginPage
const registroPage = new RegistroPage

describe('login', () =>{
    
    const dadosRegistro = {
        nome : faker.person.fullName(),
        email : faker.internet.email(),
        senha : faker.string.alphanumeric(8)
    }

    it('teste1 - Validar login com dados validos', () => {
        loginPage.navegar()
        registroPage.clicarBtnRegistrar()
        registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro)
        registroPage.clicarBtnFecharAvisoSucesso()
        loginPage.fazerLoginComSucesso(dadosRegistro)
        loginPage.clicarBtnSair()
    })

    it('teste2 - Tentar login com dados invalidos', () => {
        loginPage.navegar()
        loginPage.fazerLoginComSucesso(dadosRegistro)
        loginPage.verificaTextoLoginIncorreto()
        loginPage.clicarBtnFecharAvisoErro()
    })

    it('teste2 - Tentar login com email em branco', () => {
        loginPage.navegar()
        loginPage.preencherSenha(faker.internet.email())
        loginPage.clicarBtnLogin()
        loginPage.verificaTextoEmailEmBranco()
    })


})