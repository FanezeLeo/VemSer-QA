export default class ExtratoPage{
    url = 'https://bugbank.netlify.app/'
    btnExtrato = '#btn-EXTRATO'
    textoVerifica = 'div[class="bank-statement__ContainerTransaction-sc-7n8vh8-12 fCYQeb"] > div:nth-child(2) > div:nth-child(2) > p[id="textDescription"]'
    btnVoltar = "#btnBack"
    
    navegar(){
        cy.visit(this.url)
    }

    clicarBtnExtrato(){
        cy.get(this.btnExtrato).click()
    }

    clicarBtnVoltar(){
        cy.get(this.btnVoltar).click()
    }

    verificaExtrato(descricao){
        cy.get(this.textoVerifica).should('have.text', descricao);
    }

    fazerLoginComSucesso(dados) {
        this.preencherEmail(dados.email);
        this.preencherSenha(dados.senha);
        this.clicarBtnLogin();
    }
}