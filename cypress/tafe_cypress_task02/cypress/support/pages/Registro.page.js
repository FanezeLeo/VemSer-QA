import BasePage from "./Base.page"

const basePage = new BasePage

export default class RegistroPage{
    email = ':nth-child(2) > .input__default';
    nome = ':nth-child(3) > .input__default';
    senha = ':nth-child(4) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default';
    confirmandoSenha = ':nth-child(5) > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default';

    btnSaldo = '#toggleAddBalance';
    btnCadastrar = '.styles__ContainerFormRegister-sc-7fhc7g-0 > .style__ContainerButton-sc-1wsixal-0';
    btnRegistrar = '.ihdmxA';
    btnFecharAvisoSucesso = '#btnCloseModal';

    textoSucesso = '#modalText';
    textoSenhasNaoSaoIguais = '#modalText';
    textoEmailEmBraco = 'form > div[class="style__ContainerFieldInput-sc-s3e9ea-0 kOeYBn input__child"] > p.input__warging';
    
    clicarBtnRegistrar() {
        basePage.clicarBtn(this.btnRegistrar)
    }
    
    clicarBtnSaldo() {
        basePage.clicarBtn(this.btnSaldo)
    }
    
    clicarBtnCadastrar() {
        basePage.clicarBtn(this.btnCadastrar)
    }
    
    clicarBtnFecharAvisoSucesso() {
        basePage.clicarBtn(this.btnFecharAvisoSucesso)
    }

    preencherEmail(email) {
        basePage.preencherCampo(this.email, email + "", { force: true })
    }
    
    preencherNome(nome) {
        basePage.preencherCampo(this.nome, nome + "", { force: true })
    }
    
    preencherSenha(senha) {
        basePage.preencherCampo(this.senha, senha + "", { force: true })
    }
    
    preencherConfirmandoSenha(confirmandoSenha) {
        basePage.preencherCampo(this.confirmandoSenha, confirmandoSenha + "", { force: true })
    }

    verificaTextSucesso() {
        basePage.verificaTextoExist(this.textoSucesso, { force: true })
    }
    
    verificaTextoSenhasNaoSaoIguais() {
        basePage.verificaTextoEqual(this.textoSenhasNaoSaoIguais, "As senhas não são iguais.\n", { force: true })
    }
    
    verificaTextoEmailEmBranco() {
        basePage.verificaTextoExist(this.textoEmailEmBraco)
    }
}