import './style.css';
import { candidatosSeed, empresasSeed, vagasSeed } from './data/seed';
import {
  iniciarCadastroCandidato, renderizarVagas, repoCandidatos, repoVagas,
} from './views/candidatoView';
import {
  iniciarCadastroEmpresa, renderizarPerfilEmpresa, repoEmpresas,
} from './views/empresaView';

// Dados iniciais (só se ainda estiver vazio)
if (repoCandidatos.estaVazio()) repoCandidatos.salvarLista(candidatosSeed);
if (repoEmpresas.estaVazio()) repoEmpresas.salvarLista(empresasSeed);
if (repoVagas.estaVazio()) repoVagas.salvarLista(vagasSeed);

const telas = document.querySelectorAll<HTMLElement>('.tela');

function mostrarTela(id: string): void {
  telas.forEach((tela) => (tela.hidden = tela.id !== id));
  if (id === 'perfil-candidato') renderizarVagas();
  if (id === 'perfil-empresa') renderizarPerfilEmpresa();
}

document.querySelectorAll<HTMLButtonElement>('nav button').forEach((botao) => {
  botao.addEventListener('click', () => mostrarTela(botao.dataset.tela!));
});

iniciarCadastroCandidato();
iniciarCadastroEmpresa();
mostrarTela('cadastro-candidato');