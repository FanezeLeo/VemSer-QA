import LoginPage from "./pages/Login.page";
import RegistroPage from "./pages/Registro.page";
import TransferenciaPage from "./pages/Transferencia.page"
const loginPage = new LoginPage(); 
const registroPage = new RegistroPage();
const transferenciaPage = new TransferenciaPage(); 


// login
Cypress.Commands.add('fazerLogin', (dados) => {
    loginPage.preencherEmail(dados.email);
    loginPage.preencherSenha(dados.senha);
    loginPage.clicarBtnLogin();
});

// registro
Cypress.Commands.add("fazerRegistroComSucesso", (dados) => {
    registroPage.preencherNome(dados.nome);
    registroPage.preencherEmail(dados.email);
    registroPage.preencherSenha(dados.senha);
    registroPage.preencherConfirmandoSenha(dados.senha);
    registroPage.clicarBtnSaldo();
    registroPage.clicarBtnCadastrar();
});

Cypress.Commands.add("tentarRegistroComNomeEmBranco", (dados) => {
    registroPage.preencherEmail(dados.email);
    registroPage.preencherSenha(dados.senha);
    registroPage.preencherConfirmandoSenha(dados.senha);
    registroPage.clicarBtnSaldo();
    registroPage.clicarBtnCadastrar();
});

Cypress.Commands.add("tentarRegistroComEmailEmBranco", (dados) => {
    registroPage.preencherNome(dados.nome);
    registroPage.preencherSenha(dados.senha);
    registroPage.preencherConfirmandoSenha(dados.senha);
    registroPage.clicarBtnSaldo();
    registroPage.clicarBtnCadastrar();
});

Cypress.Commands.add("tentarRegistroComSenhasDiferentes", (dados) => {
    registroPage.preencherNome(dados.nome);
    registroPage.preencherEmail(dados.email);
    registroPage.preencherSenha(dados.senha);
    registroPage.preencherConfirmandoSenha("senhadiferente");
    registroPage.clicarBtnSaldo();
    registroPage.clicarBtnCadastrar();
});

// transferência
Cypress.Commands.add("fazerTransferenciaComSucesso", (primeirosDigitos, ultimoDigito, dados) => {
    transferenciaPage.preencherNumeroConta(primeirosDigitos);
    transferenciaPage.preencherDigito(ultimoDigito);
    transferenciaPage.preencherValor(dados.valor);
    transferenciaPage.preencherDescricao(dados.descricao);
    transferenciaPage.clicarBtnTransferirValor();
});

Cypress.Commands.add("tentarTransferenciaComSaldoInsuficiente", (primeirosDigitos, ultimoDigito, dados) => {
    transferenciaPage.preencherNumeroConta(primeirosDigitos);
    transferenciaPage.preencherDigito(ultimoDigito);
    transferenciaPage.preencherValor("1000000000");
    transferenciaPage.preencherDescricao(dados.descricao);
    transferenciaPage.clicarBtnTransferirValor();
});
