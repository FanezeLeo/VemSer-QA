import LoginPage from "../pages/LoginPage.js";
import loginData from "../../fixtures/LoginData.json"

const loginPage = new LoginPage
describe('login', () =>{

    it('teste1 - Validar login com dados invalidos', () => {
        loginPage.navegar1()
        loginPage.preencherUsername("teste")
        loginPage.preencherSenha("teste")
        loginPage.clicarBtnLogin()
    })

    it('teste2 - Validar login com dados invalidos', () => {
        loginPage.navegar1()
        loginPage.preencherUsername('Mailton@gmail.com')
        loginPage.preencherSenha('123456789')
        loginPage.clicarBtnLogin()
    })

    it('teste3 - Validar login com dados invalidos', () => {
        loginPage.navegar1()
        
    })
})