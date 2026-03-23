import java.util.*;

public class ShowConhecimento {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static int pontuacao = 0, acertos = 0, total = 0, dicas = 3;
    static String nomeJogador;
    
    // 30 PERGUNTAS REAIS + DINÂMICAS
    static String[][] perguntas = {
        {"Matemática", "15 × 7 = ?", "B", "A)95 B)105 C)115 D)125"},
        {"Matemática", "Raiz quadrada de 144?", "B", "A)10 B)12 C)14 D)16"},
        {"Matemática", "25% de 200?", "B", "A)25 B)50 C)75 D)100"},
        {"Matemática", "2³ = ?", "B", "A)4 B)8 C)9 D)16"},
        {"Matemática", "Área quadrado 5x5?", "B", "A)10 B)25 C)20 D)50"},
        {"Geografia", "Capital do Brasil?", "C", "A)SP B)RJ C)Brasília D)BH"},
        {"Geografia", "Maior oceano do mundo?", "A", "A)Pacífico B)Atlântico C)Índico D)Ártico"},
        {"Geografia", "País mais populoso?", "B", "A)Índia B)China C)EUA D)Indonésia"},
        {"Geografia", "Rio mais longo?", "A", "A)Amazonas B)Nilo C)Mississipi D)Yangtzé"},
        {"Geografia", "Continente mais quente?", "B", "A)Ásia B)África C)Austrália D)América"},
        {"História", "Quem descobriu o Brasil?", "B", "A)Colombo B)Cabral C)Vespucci D)Magalhães"},
        {"História", "Independência do Brasil?", "C", "A)1810 B)1820 C)1822 D)1830"},
        {"História", "2ª Guerra Mundial?", "B", "A)1914-18 B)1939-45 C)1945-50 D)1950-55"},
        {"História", "Queda da Bastilha?", "B", "A)1776 B)1789 C)1799 D)1815"},
        {"Ciências", "Planeta mais próximo do Sol?", "D", "A)Vênus B)Terra C)Marte D)Mercúrio"},
        {"Ciências", "Elemento químico H?", "B", "A)Hélio B)Hidrogênio C)Oxigênio D)Carbono"},
        {"Ciências", "Forma do DNA?", "C", "A)Linha B)Círculo C)Dupla-hélice D)Espiral"},
        {"Ciências", "Maior planeta?", "A", "A)Júpiter B)Saturno C)Urano D)Netuno"},
        {"Cultura", "Autor de 'Dom Casmurro'?", "A", "A)Machado B)Alencar C)Rosa D)Ramos"},
        {"Cultura", "Quem pintou Mona Lisa?", "B", "A)Van Gogh B)Da Vinci C)Picasso D)Monet"},
        {"Cultura", "Vocalista principal Beatles?", "A", "A)John Lennon B)Paul McCartney C)George Harrison D)Ringo"},
        {"Cultura", "Oscar 2023 Melhor Filme?", "C", "A)Top Gun B)Avatar 2 C)Everything Everywhere D)Tár"},
        {"Matemática", "π ≈ ?", "B", "A)2.14 B)3.14 C)4.14 D)5.14"},
        {"Geografia", "Capital França?", "B", "A)Londres B)Paris C)Berlim D)Madri"},
        {"História", "Império Romano caiu?", "C", "A)300 d.C. B)400 d.C. C)476 d.C. D)600 d.C."},
        {"Ciências", "Velocidade da luz?", "C", "A)300km/s B)300k km/h C)300.000km/s D)3M km/s"},
        {"Cultura", "'O Pequeno Príncipe'?", "B", "A)Tolstói B)Saint-Exupéry C)Hemingway D)Orwell"}
    };
    
    public static void main(String[] args) {
        limparTela();
        cabecalho();
        nomeJogador = lerNome();
        
        System.out.println("\u001B[36m��� Vamos começar " + nomeJogador + "! ���\u001B[0m");
        pausar(1500);
        
        jogar();
        mostrarFinal();
    }
    
    static String lerNome() {
        System.out.print("\u001B[33m��� Digite seu nome: \u001B[0m");
        String nome = scanner.nextLine().trim();
        return nome.isEmpty() ? "JOGADOR" : nome;
    }
    
    static void jogar() {
        int numPergunta = 1;
        while (pontuacao < 2000 && numPergunta <= 30) {
            limparTela();
            status();
            
            String[] p = pegarPergunta();
            mostrarPergunta(p, numPergunta);
            
            String resp = lerResposta();
            if (processarResposta(resp, p[2])) {
                pontuacao += 100;
                acertos++;
                System.out.println(green("✅ ACERTOU! +" + 100 + " pts ➤ TOTAL: " + pontuacao + " ���"));
            } else {
                System.out.println(red("❌ ERROU! Resposta: " + p[2] + " ���"));
                pausar(2500);
                break;
            }
            
            total++;
            numPergunta++;
            pausar(1800);
        }
    }
    
    static String[] pegarPergunta() {
        if (random.nextInt(4) == 0) { // 25% perguntas extras
            return new String[]{"Matemática", "Qual é 100 ÷ 25?", "B", "A)2 B)4 C)5 D)10"};
        }
        return perguntas[random.nextInt(perguntas.length)];
    }
    
    static void mostrarPergunta(String[] p, int num) {
        System.out.println(cyan("═══════════════════════════════════════"));
        System.out.println(green("  ��� PERGUNTA " + num + "  |  " + p[0]));
        System.out.println("\n" + p[1]);
        System.out.println("\n" + p[3]);
        System.out.println(cyan("═══════════════════════════════════════"));
    }
    
    static String lerResposta() {
        System.out.print(yellow("➤ A/B/C/D/dica/parar: "));
        return scanner.nextLine().toUpperCase().trim();
    }
    
    static boolean processarResposta(String resp, String correta) {
        if (resp.equals("DICA") && dicas > 0) {
            dicas--;
            System.out.println(purple("��� DICA USADA! (" + dicas + " restantes)"));
            pausar(1500);
            return true;
        }
        if (resp.equals("PARAR")) {
            System.out.println(yellow("��� Jogo pausado pelo jogador!"));
            return false;
        }
        return resp.equals(correta);
    }
    
    static void status() {
        double pct = total > 0 ? (acertos * 100.0 / total) : 0;
        System.out.printf(yellow("%s | ��� %d pts | ��� %.0f%% | ��� %d dicas\n\n"),
            nomeJogador, pontuacao, pct, dicas);
    }
    
    static void mostrarFinal() {
        limparTela();
        System.out.println(green("��� FINALIZADO ���"));
        System.out.println(yellow("��� " + nomeJogador));
        System.out.println(yellow("��� " + pontuacao + " pontos"));
        System.out.printf(yellow("��� %.1f%% acerto (%d/%d)\n"), 
            total > 0 ? (acertos * 100.0 / total) : 0, acertos, total);
        System.out.println(yellow("��� Dicas usadas: " + (3-dicas) + "/3"));
        
        String nivel = pontuacao >= 1500 ? "��� LENDÁRIO" : 
                      pontuacao >= 700 ? "⭐ EXCELENTE" : 
                      pontuacao >= 300 ? "✅ BOM" : "��� INICIANTE";
        System.out.println(green(nivel));
        
        System.out.print("\nJogar novamente? (S/N): ");
        if (scanner.nextLine().toUpperCase().startsWith("S")) {
            reset();
            pausar(1000);
            jogar();
        }
        scanner.close();
    }
    
    static void reset() {
        pontuacao = acertos = total = 0;
        dicas = 3;
    }
    
    static void cabecalho() {
        System.out.println(green("╔══════════════════════════════════════╗"));
        System.out.println(green("║           ��� SHOW DO CONHECIMENTO ���        ║"));
        System.out.println(green("╚══════════════════════════════════════╝"));
    }
    
    // UTILITÁRIOS
    static String green(String text) { return "\u001B[32m" + text + "\u001B[0m"; }
    static String yellow(String text) { return "\u001B[33m" + text + "\u001B[0m"; }
    static String cyan(String text) { return "\u001B[36m" + text + "\u001B[0m"; }
    static String purple(String text) { return "\u001B[35m" + text + "\u001B[0m"; }
    static String red(String text) { return "\u001B[31m" + text + "\u001B[0m"; }
    
    static void limparTela() {
        System.out.print("\033[H\033[2J");
    }
    
    static void pausar(int ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}
