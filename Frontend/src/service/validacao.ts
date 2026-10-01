import type { Candidato } from '../models/Candidato';
import type { Empresa } from '../models/Empresa';

const EMAIL = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const CPF = /^\d{3}\.?\d{3}\.?\d{3}-?\d{2}$/;
const CNPJ = /^\d{2}\.?\d{3}\.?\d{3}\/?\d{4}-?\d{2}$/;
const CEP = /^\d{5}-?\d{3}$/;

export function validarCandidato(c: Candidato): string | null {
  if (!c.nome || !c.sobrenome) return 'Informe nome e sobrenome.';
  if (!EMAIL.test(c.email)) return 'E-mail inválido.';
  if (!CPF.test(c.cpf)) return 'CPF inválido (ex.: 123.456.789-00).';
  if (!c.dataNascimento) return 'Informe a data de nascimento.';
  if (!CEP.test(c.cep)) return 'CEP inválido (ex.: 63900-000).';
  if (!c.formacao) return 'Informe a formação.';
  if (c.competencias.length === 0) return 'Selecione ao menos uma competência.';
  return null;
}

export function validarEmpresa(e: Empresa): string | null {
  if (!e.nome) return 'Informe o nome da empresa.';
  if (!EMAIL.test(e.email)) return 'E-mail inválido.';
  if (!CNPJ.test(e.cnpj)) return 'CNPJ inválido (ex.: 12.345.678/0001-90).';
  if (!e.pais) return 'Informe o país.';
  if (!CEP.test(e.cep)) return 'CEP inválido.';
  return null;
}