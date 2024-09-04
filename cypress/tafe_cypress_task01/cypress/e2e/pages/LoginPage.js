export default class LoginPage{
    url = 'https://bugbank.netlify.app/'
    email = '.style__ContainerFormLogin-sc-1wbjw6k-0 > :nth-child(1) > .input__default'
    senha = '.style__ContainerFormLogin-sc-1wbjw6k-0 > .login__password > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
    btnLogin = '.otUnI'
    textoLoginIncorreto = '#modalText'
    btnSair = "#btnExit"
    btnFecharAvisoErro = "#btnCloseModal"
    textoAvisoEmailEmBranco = ".style__ContainerFormLogin-sc-1wbjw6k-0 > :nth-child(1) > .input__warging"

    navegar(){
        cy.visit(this.url)
    }

    preencherEmail(email){
        cy.get(this.email).type(email)
    }
    
    preencherSenha(senha){
        cy.get(this.senha).type(senha)
    }

    clicarBtnLogin(){
        cy.get(this.btnLogin).click()
    }

    clicarBtnSair(){
        cy.get(this.btnSair).click()
    }

    clicarBtnFecharAvisoErro(){
        cy.get(this.btnFecharAvisoErro).click()
    }

    verificaTextoLoginIncorreto(){
        cy.get(this.textoLoginIncorreto).should('be.visible');
    }

    verificaTextoEmailEmBranco(){
        cy.get(this.textoAvisoEmailEmBranco).should('be.visible');

    }

    fazerLoginComSucesso(dados) {
        this.preencherEmail(dados.email);
        this.preencherSenha(dados.senha);
        this.clicarBtnLogin();
    }
}