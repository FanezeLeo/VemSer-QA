import RegistroPage from "../pages/RegistroPage.js";
import { faker } from '@faker-js/faker';


const registroPage = new RegistroPage
describe('registro', () =>{
    const dadosRegistro = {
        nome : faker.person.fullName(),
        email : faker.internet.email(),
        senha : faker.string.alphanumeric(8)
    }

    it('teste1 - Validar registro com dados validos', () => {
        registroPage.navegar()
        registroPage.clicarBtnRegistrar()
        registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro)
        registroPage.verificaTextSucesso()
        registroPage.clicarBtnFecharAvisoSucesso()
    })

    it('teste2 - Tentar registro com nome em branco', () => {
        registroPage.navegar()
        registroPage.clicarBtnRegistrar()
        registroPage.tentarRegistroComNomeEmBranco(dadosRegistro)
        registroPage.clicarBtnFecharAvisoSucesso()
    })

    it('teste3 - Tentar registro com email em branco', () => {
        registroPage.navegar()
        registroPage.clicarBtnRegistrar()
        registroPage.tentarRegistroComEmailEmBranco(dadosRegistro)
    })

    it('teste4 - Tentar registro com senhas diferentes', () => {
        registroPage.navegar()
        registroPage.clicarBtnRegistrar()
        registroPage.tentarRegistroComSenhasDiferentes(dadosRegistro)
        registroPage.verificaTextoSenhasNaoSaoIguais()
        registroPage.clicarBtnFecharAvisoSucesso()
    })



})