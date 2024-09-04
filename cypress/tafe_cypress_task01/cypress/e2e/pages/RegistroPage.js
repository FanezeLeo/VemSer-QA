export default class RegistroPage{
    url = 'https://bugbank.netlify.app/'
    email = ':nth-child(2) > .input__default'
    nome = ':nth-child(3) > .input__default'
    senha = ':nth-child(4) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
    confirmandoSenha = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default'
    btnSaldo = '#toggleAddBalance'
    btnCadastrar = '.styles__ContainerFormRegister-sc-7fhc7g-0 > .style__ContainerButton-sc-1wsixal-0'
    btnRegistrar = '.ihdmxA'
    textoSucesso = '#modalText'
    btnFecharAvisoSucesso = "#btnCloseModal"
    textoSenhasNaoSaoIguais = "#modalText"
    textoEmailEmBraco = "form > div[class = \"style__ContainerFieldInput-sc-s3e9ea-0 kOeYBn input__child\"] > p.input__warging"

    navegar(){
        cy.visit(this.url)
    }

    clicarBtnRegistrar(){
        cy.get(this.btnRegistrar).click()
    }

    preencherEmail(email){
        cy.get(this.email).type(email +"", { force: true })
    }
    
    preencherNome(nome){
        cy.get(this.nome).type(nome + "", { force: true })
    }

    preencherSenha(senha){
        cy.get(this.senha).type(senha + "", { force: true })
    }

    preencherConfirmandoSenha(confirmandoSenha){
        cy.get(this.confirmandoSenha).type(confirmandoSenha + "", { force: true })
    }

    clicarBtnSaldo(){
        cy.get(this.btnSaldo).click({ force: true })
    }

    clicarBtnCadastrar(){
        cy.get(this.btnCadastrar).click({ force: true })
    }

    clicarBtnFecharAvisoSucesso(){
        cy.get(this.btnFecharAvisoSucesso).click({ force: true })
    }

    verificaTextSucesso(){
        cy.get(this.textoSucesso).should('be.visible',{ force: true });
    }

    verificaTextoSenhasNaoSaoIguais(){
        cy.get(this.textoSenhasNaoSaoIguais).should('have.text', "As senhas não são iguais.\n");

    }

    verificaTextoEmailEmBranco(){
        cy.get(this.textoEmailEmBraco).should('be.visible');

    }

    

    fazerRegistroComSaldoComSucesso(dados) {
        this.preencherNome(dados.nome);
        this.preencherEmail(dados.email);
        this.preencherSenha(dados.senha);
        this.preencherConfirmandoSenha(dados.senha);
        this.clicarBtnSaldo()
        this.clicarBtnCadastrar();
    }

    tentarRegistroComNomeEmBranco(dados) {
        this.preencherEmail(dados.email);
        this.preencherSenha(dados.senha);
        this.preencherConfirmandoSenha(dados.senha);
        this.clicarBtnSaldo()
        this.clicarBtnCadastrar();
    }

    tentarRegistroComEmailEmBranco(dados) {
        this.preencherNome(dados.nome);
        this.preencherSenha(dados.senha);
        this.preencherConfirmandoSenha(dados.senha);
        this.clicarBtnSaldo()
        this.clicarBtnCadastrar();
    }

    tentarRegistroComSenhasDiferentes(dados) {
        this.preencherNome(dados.nome);
        this.preencherEmail(dados.email);
        this.preencherSenha(dados.senha);
        this.preencherConfirmandoSenha("senhadiferente");
        this.clicarBtnSaldo()
        this.clicarBtnCadastrar();
    }
    
}