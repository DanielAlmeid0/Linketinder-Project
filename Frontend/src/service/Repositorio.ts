export class Repositorio<T extends { id: string }> {
    private readonly chave: string;
    constructor(chave: string){
        this.chave = chave;
    }

    listar(): T[] {
        const bruto = localStorage.getItem(this.chave)
        return bruto ? (JSON.parse(bruto) as T[]) : []
    }

    salvar(item: T): void {
        const itens = this.listar();
        itens.push(item);
        localStorage.setItem(this.chave, JSON.stringify(itens))
    }

    estaVazio(): boolean {
        return this.listar().length === 0;
    }

    salvarLista(itens: T[]): void {
        localStorage.setItem(this.chave, JSON.stringify(itens))
    }
}