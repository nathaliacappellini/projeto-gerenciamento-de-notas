import java.util.Scanner;

public class ProgramaGerenciamentoNotas {
    public static void main(String[] args) {
        // Boas-vindas e explicação do programa
        System.out.println("Bem-vindo(a) ao programa de gerenciamento de notas! " +
                "Esta turma possui 10 alunos e 3 disciplinas.");

        // Variável para armazenar as notas
        double[][] notas = null;
        Scanner scanner = new Scanner(System.in);
        int opcao;

        // Loop principal para manter o programa rodando
        do {
            System.out.println(
                    "1 - Inserir Notas | 2 - Calcular Média do Aluno | 3 - Calcular Média da Turma | 4 - Ordenar Notas | 5 - Buscar Nota | 6 - Estatísticas | 7 - Sair");
            opcao = scanner.nextInt();

            // Switch para executar a opção escolhida
            switch (opcao) {
                case 1:
                    notas = inserirNotas();
                    break;
                case 2:
                    if (notas != null) {
                        calcularMediaAluno(notas);
                    } else {
                        System.out.println("Primeiro insira as notas (opção 1).");
                    }
                    break;
                case 3:
                    if (notas != null) {
                        calcularMediaTurma(notas);
                    } else {
                        System.out.println("Primeiro insira as notas (opção 1).");
                    }
                    break;
                case 4:
                    if (notas != null) {
                        ordenarNotas(notas);
                    } else {
                        System.out.println("Primeiro insira as notas (opção 1).");
                    }
                    break;
                case 5:
                    if (notas != null) {
                        System.out.println("Digite a nota que deseja buscar:");
                        double notaBuscada = scanner.nextDouble();
                        buscarNota(notas, notaBuscada);
                    } else {
                        System.out.println("Primeiro insira as notas (opção 1).");
                    }
                    break;
                case 6:
                    if (notas != null) {
                        exibirEstatisticas(notas);
                    } else {
                        System.out.println("Primeiro insira as notas (opção 1).");
                    }
                    break;
                case 7:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        } while (opcao != 7);

        scanner.close();
    }

    // Método para inserir as notas dos alunos
    public static double[][] inserirNotas() {
        Scanner scanner = new Scanner(System.in);
        double[][] matriz = new double[10][3];

        // Inserindo as notas para cada aluno e disciplina
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.println("Digite a nota do aluno " + (i + 1) + " na disciplina " + (j + 1) + ":");
                matriz[i][j] = scanner.nextDouble();
            }
        }
        return matriz;
    }

    // Método para calcular a média de cada aluno
    public static void calcularMediaAluno(double[][] notas) {
        double soma;
        System.out.println("------------------");
        System.out.println(" Média dos alunos");
        System.out.println("------------------");
        for (int i = 0; i < notas.length; i++) {
            soma = 0;
            for (int j = 0; j < notas[i].length; j++) {
                soma += notas[i][j];
            }
            System.out.println((i + 1) + " = " + String.format("%.1f", (soma / 3)));
        }
    }

    // Método para calcular a média da turma
    public static void calcularMediaTurma(double[][] notas) {
        double soma = 0;
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                soma += notas[i][j];
            }
        }
        System.out.println("-----------------");
        System.out.println(" Média da turma");
        System.out.println("-----------------");
        System.out.println(String.format("%.1f", (soma / 30)));
    }

    // Método de Selection Sort
    public static void ordenarNotas(double[][] notas) {
        double[] notasVetor = extrairNotasParaVetor(notas);

        // Algoritmo para ordenar
        for (int i = 0; i < notasVetor.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < notasVetor.length; j++) {
                if (notasVetor[j] < notasVetor[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            // Troca as notas
            double temp = notasVetor[i];
            notasVetor[i] = notasVetor[indiceMenor];
            notasVetor[indiceMenor] = temp;
        }

        reporMatrizComVetor(notas, notasVetor);

        System.out.println("------------------");
        System.out.println(" Notas ordenadas");
        System.out.println("------------------");
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                System.out.print(notas[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método para busca binária
    public static void buscarNota(double[][] notas, double notaBuscada) {
        // Ordenar notas antes de buscar
        ordenarNotas(notas);

        // Extrair as notas para o vetor
        double[] notasVetor = extrairNotasParaVetor(notas);

        int inicio = 0, fim = notasVetor.length - 1, meio;
        boolean encontrada = false;

        while (inicio <= fim) {
            meio = (inicio + fim) / 2;
            if (notasVetor[meio] == notaBuscada) {
                System.out.println("Nota " + notaBuscada + " encontrada na posição " + meio);
                encontrada = true;
                break;
            } else if (notasVetor[meio] < notaBuscada) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        if (!encontrada) {
            System.out.println("Nota " + notaBuscada + " não encontrada.");
        }
    }

    // Método para extrair notas da matriz para um vetor
    public static double[] extrairNotasParaVetor(double[][] notas) {
        int totalNotas = notas.length * notas[0].length;
        double[] notasVetor = new double[totalNotas];
        int k = 0;

        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                notasVetor[k] = notas[i][j];
                k++;
            }
        }

        return notasVetor;
    }

    // Método para repor a matriz com as notas do vetor
    public static void reporMatrizComVetor(double[][] notas, double[] notasVetor) {
        int k = 0;
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                notas[i][j] = notasVetor[k];
                k++;
            }
        }
    }

    // Método para exibir estatísticas
    public static void exibirEstatisticas(double[][] notas) {
        System.out.println("-------------------------------");
        System.out.println(" Estatísticas Gerais da Turma");
        System.out.println("-------------------------------");
        calcularMediaAluno(notas);
        calcularMediaTurma(notas);

        // Pegar maior e menor nota
        System.out.println("---------------------");
        System.out.println(" Maior e Menor Nota");
        System.out.println("---------------------");
        for (int j = 0; j < notas[0].length; j++) { // Para cada disciplina
            double maiorNota = Double.NEGATIVE_INFINITY;
            double menorNota = Double.POSITIVE_INFINITY;

            for (int i = 0; i < notas.length; i++) { // Para cada aluno
                double notaAtual = notas[i][j];
                if (notaAtual > maiorNota) {
                    maiorNota = notaAtual;
                }
                if (notaAtual < menorNota) {
                    menorNota = notaAtual;
                }
            }

            // Exibir a maior e menor nota
            System.out.println("Disciplina " + (j + 1) + ": Maior Nota = " + maiorNota + ", Menor Nota = " + menorNota);
        }
    }
}