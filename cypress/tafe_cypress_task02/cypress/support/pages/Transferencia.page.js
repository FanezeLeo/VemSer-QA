import BasePage from "./Base.page"

const basePage = new BasePage

export default class TransferenciaPage{
    numeroConta = ':nth-child(1) > .input__default';
    digito = '.account__data > :nth-child(2) > .input__default';
    valor = '.styles__ContainerFormTransfer-sc-1oow0wh-0 > :nth-child(2) > .input__default';
    descricao = ':nth-child(3) > .input__default';

    btnTranferencia = '#btn-TRANSFERÊNCIA';
    btnTransferirValor = '.style__ContainerButton-sc-1wsixal-0';
    btnFecharAviso = '#btnCloseModal';
    btnVoltar = '#btnBack';

    textoCodigoConta = '#textAccountNumber > span';
    textoContaInvalida = '#modalText';
    textoSaldoInsuficiente = '#modalText';

    clicarBtnTransferencia() {
        basePage.clicarBtn(this.btnTranferencia)
    }
    
    clicarBtnTransferirValor() {
        basePage.clicarBtn(this.btnTransferirValor)
    }
    
    clicarBtnFecharAviso() {
        basePage.clicarBtn(this.btnFecharAviso)
    }
    
    clicarBtnVoltar() {
        basePage.clicarBtn(this.btnVoltar)
    }

    preencherNumeroConta(numeroConta) {
        basePage.preencherCampo(this.numeroConta, numeroConta, { force: true })
    }
    
    preencherDigito(digito) {
        basePage.preencherCampo(this.digito, digito, { force: true })
    }
    
    preencherValor(valor) {
        basePage.preencherCampo(this.valor, valor, { force: true })
    }
    
    preencherDescricao(descricao) {
        basePage.preencherCampo(this.descricao, descricao, { force: true })
    }

    retornaCodigo() {
        return cy.get(this.textoCodigoConta).invoke('text', { force: true })
    }
    
    verificaTextoContaInvalida() {
        basePage.verificaTextoEqual(this.textoContaInvalida, "Conta inválida ou inexistente")
    }
    
    verificaTextoSaldoInsuficiente() {
        basePage.verificaTextoEqual(this.textoSaldoInsuficiente, "Você não tem saldo suficiente para essa transação")
    }
}