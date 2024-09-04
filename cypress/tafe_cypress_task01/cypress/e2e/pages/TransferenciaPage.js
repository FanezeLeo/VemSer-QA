export default class TransferenciaPage{
    url = 'https://bugbank.netlify.app/'
    numeroConta = ':nth-child(1) > .input__default'
    digito = '.account__data > :nth-child(2) > .input__default'
    valor = '.styles__ContainerFormTransfer-sc-1oow0wh-0 > :nth-child(2) > .input__default'
    descricao = ':nth-child(3) > .input__default'
    btnTranferencia = '#btn-TRANSFERÊNCIA'
    textoCodigoConta = '#textAccountNumber > span'
    btnTransferirValor = ".style__ContainerButton-sc-1wsixal-0"
    btnFecharAviso = "#btnCloseModal"
    btnVoltar = "#btnBack"
    textoContaInvalida = "#modalText"
    textoSaldoInsuficiente = "#modalText"

    navegar(){
        cy.visit(this.url)
    }
    
    preencherNumeroConta(numeroConta){
        cy.get(this.numeroConta).type(numeroConta, { force: true })
    }
    
    preencherDigito(digito){
        cy.get(this.digito).type(digito, { force: true })
    }
    
    preencherValor(valor){
        cy.get(this.valor).type(valor, { force: true })
    }
    
    preencherDescricao(descricao){
        cy.get(this.descricao).type(descricao, { force: true })
    }
    
    clicarBtnTransferencia(){
        cy.get(this.btnTranferencia).click({ force: true })
    }
    
    retornaCodigo(){
        return cy.get(this.textoCodigoConta).invoke('text', { force: true });
    }
    
    clicarBtnTransferirValor(){
        cy.get(this.btnTransferirValor).click({ force: true })
    }

    clicarBtnFecharAviso(){
        cy.get(this.btnFecharAviso).click({ force: true })
    }

    clicarBtnVoltar(){
        cy.get(this.btnVoltar).click({ force: true })
    }

    verificaTextoContaInvalida(){
        cy.get(this.textoContaInvalida).should('have.text', "Conta inválida ou inexistente");
    }

    verificaTextoSaldoInsuficiente(){
        cy.get(this.textoSaldoInsuficiente).should('have.text', "Você não tem saldo suficiente para essa transação");
    }

    fazerTransferenciaComSucesso(primeirosDigitos, ultimoDigito, dados) {
        this.preencherNumeroConta(primeirosDigitos);
        this.preencherDigito(ultimoDigito);
        this.preencherValor(dados.valor);
        this.preencherDescricao(dados.descricao);
        this.clicarBtnTransferirValor();
    }

    tentarTransferenciaComSaldoInsuficiente(primeirosDigitos, ultimoDigito, dados) {
        this.preencherNumeroConta(primeirosDigitos);
        this.preencherDigito(ultimoDigito);
        this.preencherValor("1000000000");
        this.preencherDescricao(dados.descricao);
        this.clicarBtnTransferirValor();
    }
}