import type { Empresa } from '../models/Empresa';
import { Repositorio } from '../service/Repositorio';
import { validarEmpresa } from '../service/validacao';
import { el } from '../components/dom';
import { desenharGrafico } from '../components/grafico';
import { repoCandidatos } from './candidatoView';

export const repoEmpresas = new Repositorio<Empresa>('linketinder:empresas');

export function iniciarCadastroEmpresa(): void {
  const form = document.querySelector<HTMLFormElement>('#form-empresa')!;

  form.addEventListener('submit', (evento) => {
    evento.preventDefault();
    const d = new FormData(form);
    const texto = (campo: string) => String(d.get(campo) ?? '').trim();

    const empresa: Empresa = {
      id: crypto.randomUUID(),
      nome: texto('nome'),
      email: texto('email'),
      cnpj: texto('cnpj'),
      pais: texto('pais'),
      estado: texto('estado'),
      cep: texto('cep'),
      descricao: texto('descricao'),
    };

    const erro = validarEmpresa(empresa);
    if (erro) { alert(erro); return; }

    repoEmpresas.salvar(empresa);
    form.reset();
    alert('Empresa cadastrada com sucesso!');
  });
}

export function renderizarPerfilEmpresa(): void {
  const candidatos = repoCandidatos.listar();
  const lista = document.querySelector<HTMLElement>('#lista-candidatos')!;
  lista.replaceChildren();

  candidatos.forEach((cand, indice) => {
    const card = el('article', undefined, 'card');
    card.append(
      el('h3', `Candidato #${indice + 1}`),
      el('p', `Formação: ${cand.formacao}`),
      el('p', `Competências: ${cand.competencias.join(', ')}`),
    );
    lista.append(card);
  });

  const canvas = document.querySelector<HTMLCanvasElement>('#grafico-competencias')!;
  desenharGrafico(canvas, candidatos);
}