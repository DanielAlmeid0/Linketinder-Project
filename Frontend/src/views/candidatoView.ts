import type { Candidato } from '../models/Candidato';
import type { Vaga } from '../models/Vaga';
import { Repositorio } from '../service/Repositorio';
import { validarCandidato } from '../service/validacao';
import { el, renderizarCheckboxes } from '../components/dom';

export const repoCandidatos = new Repositorio<Candidato>('linketinder:candidatos');
export const repoVagas = new Repositorio<Vaga>('linketinder:vagas');

export function iniciarCadastroCandidato(): void {
  const form = document.querySelector<HTMLFormElement>('#form-candidato')!;
  renderizarCheckboxes(form.querySelector<HTMLElement>('.competencias')!);

  form.addEventListener('submit', (evento) => {
    evento.preventDefault();
    const d = new FormData(form);
    const texto = (campo: string) => String(d.get(campo) ?? '').trim();

    const candidato: Candidato = {
      id: crypto.randomUUID(),
      nome: texto('nome'),
      sobrenome: texto('sobrenome'),
      email: texto('email'),
      cpf: texto('cpf'),
      dataNascimento: texto('dataNascimento'),
      estado: texto('estado'),
      cep: texto('cep'),
      descricao: texto('descricao'),
      formacao: texto('formacao'),
      competencias: d.getAll('competencias').map(String),
    };

    const erro = validarCandidato(candidato);
    if (erro) { alert(erro); return; }

    repoCandidatos.salvar(candidato);
    form.reset();
    alert('Candidato cadastrado com sucesso!');
  });
}

export function renderizarVagas(): void {
  const lista = document.querySelector<HTMLElement>('#lista-vagas')!;
  lista.replaceChildren();
  repoVagas.listar().forEach((vaga) => {
    const card = el('article', undefined, 'card');
    card.append(
      el('h3', vaga.titulo),
      el('p', vaga.descricao),
      el('p', `Competências: ${vaga.competencias.join(', ')}`),
    );
    lista.append(card);
  });
}