import BasePage from "./Base.page"

const basePage = new BasePage

export default class LoginPage{
    email = '.style__ContainerFormLogin-sc-1wbjw6k-0 > :nth-child(1) > .input__default';
    senha = '.style__ContainerFormLogin-sc-1wbjw6k-0 > .login__password > .style__ContainerFieldInput-sc-s3e9ea-0 > .input__default';

    btnLogin = '.otUnI';
    btnSair = '#btnExit';
    btnFecharAvisoErro = '#btnCloseModal';

    textoLoginIncorreto = '#modalText';
    textoAvisoEmailEmBranco = '.style__ContainerFormLogin-sc-1wbjw6k-0 > :nth-child(1) > .input__warging';



    preencherEmail(email) {
        basePage.preencherCampo(this.email, email)
    }

    preencherSenha(senha) {
        basePage.preencherCampo(this.senha, senha)
    }

    clicarBtnLogin() {
        basePage.clicarBtn(this.btnLogin)
    }

    clicarBtnSair() {
        basePage.clicarBtn(this.btnSair)
    }

    clicarBtnFecharAvisoErro() {
        basePage.clicarBtn(this.btnFecharAvisoErro)
    }

    verificaTextoLoginIncorreto() {
        basePage.verificaTextoExist(this.textoLoginIncorreto)
    }

    verificaTextoEmailEmBranco() {
        basePage.verificaTextoExist(this.textoAvisoEmailEmBranco)
    }
}