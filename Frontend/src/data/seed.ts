import type { Candidato } from '../models/Candidato';
import type { Empresa } from '../models/Empresa';
import type { Vaga } from '../models/Vaga';

export const empresasSeed: Empresa[] = [
  { id: 'e1', nome: 'Arroz-Gostoso', email: 'rh@arroz.com', cnpj: '12.345.678/0001-90',
    pais: 'Brasil', estado: 'CE', cep: '63900-000', descricao: 'Empresa de alimentos.' },
  { id: 'e2', nome: 'ZG Soluções', email: 'rh@zg.com', cnpj: '98.765.432/0001-10',
    pais: 'Brasil', estado: 'SP', cep: '01000-000', descricao: 'Consultoria de software.' },
];

export const vagasSeed: Vaga[] = [
  { id: 'v1', empresaId: 'e1', titulo: 'Dev Backend', descricao: 'APIs em Java.', competencias: ['Java', 'Spring', 'SQL'] },
  { id: 'v2', empresaId: 'e2', titulo: 'Dev Frontend', descricao: 'Interfaces web.', competencias: ['TypeScript', 'Angular'] },
  { id: 'v3', empresaId: 'e2', titulo: 'Analista de Dados', descricao: 'Análises e relatórios.', competencias: ['Python', 'SQL'] },
];

const base = { sobrenome: 'Silva', dataNascimento: '2000-01-01', estado: 'CE', cep: '63900-000', descricao: 'Perfil de exemplo.' };

export const candidatosSeed: Candidato[] = [
  { ...base, id: 'c1', nome: 'Ana', email: 'ana@mail.com', cpf: '111.111.111-11', formacao: 'Ciência da Computação', competencias: ['Python', 'SQL'] },
  { ...base, id: 'c2', nome: 'Bruno', email: 'bruno@mail.com', cpf: '222.222.222-22', formacao: 'Sistemas de Informação', competencias: ['Java', 'Spring', 'SQL'] },
  { ...base, id: 'c3', nome: 'Carla', email: 'carla@mail.com', cpf: '333.333.333-33', formacao: 'Engenharia de Software', competencias: ['TypeScript', 'Angular', 'JavaScript'] },
  { ...base, id: 'c4', nome: 'Diego', email: 'diego@mail.com', cpf: '444.444.444-44', formacao: 'Análise e Desenvolvimento de Sistemas', competencias: ['Java', 'Python'] },
  { ...base, id: 'c5', nome: 'Elisa', email: 'elisa@mail.com', cpf: '555.555.555-55', formacao: 'Ciência da Computação', competencias: ['Python', 'Groovy'] },
];