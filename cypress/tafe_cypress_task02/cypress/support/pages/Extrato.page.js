import BasePage from "./Base.page"

const basePage = new BasePage
export default class ExtratoPage{
    btnExtrato = '#btn-EXTRATO'
    btnVoltar = "#btnBack"

    textoVerifica = 'div[class="bank-statement__ContainerTransaction-sc-7n8vh8-12 fCYQeb"] > div:nth-child(2) > div:nth-child(2) > p[id="textDescription"]'


    clicarBtnExtrato(){
        basePage.clicarBtn(this.btnExtrato)
    }

    clicarBtnVoltar(){
       basePage.clicarBtn(this.btnVoltar)
    }

    verificaExtrato(descricao){
        basePage.verificaTextoEqual(this.textoVerifica, descricao)
    }
}