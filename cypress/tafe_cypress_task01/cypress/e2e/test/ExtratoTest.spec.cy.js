import LoginPage from "../pages/LoginPage.js";
import RegistroPage from "../pages/RegistroPage.js";
import TransferenciaPage from "../pages/TransferenciaPage.js";
import ExtratoPage from "../pages/ExtratoPage.js";

import { faker } from '@faker-js/faker';

const loginPage = new LoginPage
const registroPage = new RegistroPage
const transferenciaPage = new TransferenciaPage
const extratoPage = new ExtratoPage

describe('transferencia', () =>{
    const dadosRegistro = {
        nome : faker.person.fullName(),
        email : faker.internet.email(),
        senha : faker.string.alphanumeric(8)
    }

    const dadosRegistro2 = {
        nome : faker.person.fullName(),
        email : faker.internet.email(),
        senha : faker.string.alphanumeric(8)
    }

    const dadosTransferencia = {
        valor: faker.number.float({ min: 100, max: 1000, precision: 0.01 }),
        descricao: faker.lorem.sentence()
    }

    it('teste1 - Validar criação de extrato', () => {
        loginPage.navegar()
        registroPage.clicarBtnRegistrar()
        registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro)
        registroPage.clicarBtnFecharAvisoSucesso()
        loginPage.fazerLoginComSucesso(dadosRegistro)
        const codigo = transferenciaPage.retornaCodigo() + ""
        loginPage.clicarBtnSair()
        registroPage.clicarBtnRegistrar()
        registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro2)
        registroPage.clicarBtnFecharAvisoSucesso()
        loginPage.fazerLoginComSucesso(dadosRegistro2)
        transferenciaPage.clicarBtnTransferencia()
        const [primeirosDigitos, ultimoDigito] = codigo.split('-')
        transferenciaPage.fazerTransferenciaComSucesso(primeirosDigitos+"", ultimoDigito+"", dadosTransferencia)
        transferenciaPage.clicarBtnFecharAviso()
        transferenciaPage.clicarBtnVoltar()
        extratoPage.clicarBtnExtrato()
        extratoPage.textoVerifica()
        extratoPage.btnVoltar()
    })
})