export default class LoginPage{
    url = 'https://www.saucedemo.com/v1/'
    username = '[data-test="username"]'
    senha = '[data-test="password"]'
    btnLogin = '#login-button'
    texto = '[data-test="error"]'

    navegar(url){
        cy.visit(url)
    }

    navegar1(){
        cy.visit(this.url)
    }

    preencherUsername(username){
        cy.get(this.username).type(username)
    }
    
    preencherSenha(senha){
        cy.get(this.senha).type(senha)
    }

    clicarBtnLogin(){
        cy.get(this.btnLogin).click()
    }
}