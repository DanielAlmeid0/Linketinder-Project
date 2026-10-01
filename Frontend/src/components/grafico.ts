import Chart from 'chart.js/auto';
import type { Candidato } from '../models/Candidato';
import { COMPETENCIAS } from '../models/competencias';

let grafico: Chart | null = null;

export function contarPorCompetencia(candidatos: Candidato[]): Record<string, number> {
  const contagem: Record<string, number> = {};
  COMPETENCIAS.forEach((c) => (contagem[c] = 0));
  candidatos.forEach((cand) =>
    cand.competencias.forEach((c) => (contagem[c] = (contagem[c] ?? 0) + 1)),
  );
  return contagem;
}

export function desenharGrafico(canvas: HTMLCanvasElement, candidatos: Candidato[]): void {
  const dados = contarPorCompetencia(candidatos);
  grafico?.destroy(); // evita gráfico duplicado ao reabrir a tela
  grafico = new Chart(canvas, {
    type: 'bar',
    data: {
      labels: Object.keys(dados),
      datasets: [{ label: 'Candidatos por competência', data: Object.values(dados) }],
    },
    options: { scales: { y: { beginAtZero: true, ticks: { precision: 0 } } } },
  });
}