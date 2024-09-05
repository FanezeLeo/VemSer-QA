import LoginPage from "../pages/LoginPage.js";
import RegistroPage from "../pages/RegistroPage.js";
import TransferenciaPage from "../pages/TransferenciaPage.js";

import { faker } from '@faker-js/faker';

const loginPage = new LoginPage
const registroPage = new RegistroPage
const transferenciaPage = new TransferenciaPage

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

    it('teste1 - Validar transferencia com sucesso', () => {
        loginPage.navegar()
        registroPage.clicarBtnRegistrar()
        registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro)
        registroPage.clicarBtnFecharAvisoSucesso()
        loginPage.fazerLoginComSucesso(dadosRegistro)
        const codigo = transferenciaPage.retornaCodigo()
        loginPage.clicarBtnSair()
        registroPage.clicarBtnRegistrar()
        registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro2)
        registroPage.clicarBtnFecharAvisoSucesso()
        loginPage.fazerLoginComSucesso(dadosRegistro2)
        transferenciaPage.clicarBtnTransferencia()
        const [primeirosDigitos, ultimoDigito] = codigo.split('-')
        transferenciaPage.fazerTransferenciaComSucesso(primeirosDigitos+"", ultimoDigito+"", dadosTransferencia)
        transferenciaPage.clicarBtnFecharAviso()
    })

    it('teste2 - Tentar transferencia com conta invalida', () => {
        loginPage.navegar()
        registroPage.clicarBtnRegistrar()
        registroPage.fazerRegistroComSaldoComSucesso(dadosRegistro2)
        registroPage.clicarBtnFecharAvisoSucesso()
        loginPage.fazerLoginComSucesso(dadosRegistro2)
        transferenciaPage.clicarBtnTransferencia()
        transferenciaPage.fazerTransferenciaComSucesso("000", "1", dadosTransferencia)
        transferenciaPage.verificaTextoContaInvalida()
        transferenciaPage.clicarBtnFecharAviso()
    })

    it('teste3 - Tentar transferencia com saldo insuficiente', () => {
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
        transferenciaPage.tentarTransferenciaComSaldoInsuficiente(primeirosDigitos+"", ultimoDigito+"", dadosTransferencia)
        transferenciaPage.clicarBtnFecharAviso()
    })

})