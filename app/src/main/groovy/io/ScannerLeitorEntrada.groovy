package io

class ScannerLeitorEntrada {
    private final Scanner scanner

    ScannerLeitorEntrada(Scanner scanner) {
        this.scanner = scanner
    }

    @Override
    String lerLinha() {
        return scanner.nextLine()
    }
}
