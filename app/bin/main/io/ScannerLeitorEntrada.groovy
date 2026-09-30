package io

class ScannerLeitorEntrada implements LeitorEntrada {
    private final Scanner scanner

    ScannerLeitorEntrada(Scanner scanner) {
        this.scanner = scanner
    }

    @Override
    String lerLinha() {
        return scanner.nextLine()
    }
}
