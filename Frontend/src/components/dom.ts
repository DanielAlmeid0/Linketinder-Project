import { COMPETENCIAS } from "../models/competencias";

export function el<K extends keyof HTMLElementTagNameMap>(
    tag: K, texto?: string, classe?: string,
): HTMLElementTagNameMap[K] {
const elemento = document.createElement(tag);
  if (texto !== undefined) elemento.textContent = texto;
  if (classe) elemento.className = classe;
  return elemento;
}

export function renderizarCheckboxes(container: HTMLElement): void {
  container.replaceChildren();
  COMPETENCIAS.forEach((comp) => {
    const label = el('label', undefined, 'checkbox');
    const input = el('input');
    input.type = 'checkbox';
    input.name = 'competencias';
    input.value = comp;
    label.append(input, ` ${comp}`);
    container.append(label);
  });
}