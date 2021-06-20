import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author bsantosdias
 */

public class TestaFreqPalavra {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FreqPalavra tree = new FreqPalavra();
        String[] palavrasArquivo = LerArquivo();
        tree = populaArvore(palavrasArquivo);
        tree.inOrdem();
    }

    private static FreqPalavra populaArvore(String[] palavrasArquivo) {
        FreqPalavra tree = new FreqPalavra();

        for (String string : palavrasArquivo) {
            if(string == null){
            break;
            }
            tree.insere(string);
        }

        return tree;
    }

    private static String[] LerArquivo() throws FileNotFoundException, IOException {

        String[] texto = new String[200];
        boolean letraAnteriorDiferenteAZ = false;
        int textoPonteiro = 0;
        FileReader leEntrada = new FileReader(
                "C:\\Users\\");
        Pattern pattern = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE); // Padrão para ler só letras.

        String caractere;
        do {
            caractere = String.valueOf((char) leEntrada.read());
            Matcher matcher = pattern.matcher(caractere); // Verifica se o caractere em questão é uma letra e armazena
                                                          // na matcher.
            boolean matchFound = matcher.find(); // Se for letra, matchfound recebe a letra da vez.

            if (!matchFound && texto[0] == null) { // Se for primeira rodada e for letra, a mesma será armazenada na

                texto[textoPonteiro] = caractere;
                textoPonteiro++;
            } else {
                if (matchFound) { // Se for alguma letra, verificará:
                    if (letraAnteriorDiferenteAZ) {// Se caractere anterior não era uma letra, pulará para a proxima
                        if (texto[textoPonteiro] != null) {
                            textoPonteiro++;
                        }
                    }
                    if (texto[textoPonteiro] == null) { // Se casa atual da array estiver vazia, letra será adicionada a
                                                        // ela sem sobrepor.
                        texto[textoPonteiro] = caractere;
                    } else { // Se já possuir algo na casa atual, adicionara a nova letra aos que já existem
                             // na casa.
                        texto[textoPonteiro] += caractere;
                    }
                    letraAnteriorDiferenteAZ = false;
                } else {// Se não for letra:
                    if (texto[textoPonteiro] != null) {
                        textoPonteiro++;
                    }

                }
            }
        } while (caractere.compareTo("0") != 0); // Leitura rodará enquanto caractere da vez for diferente de zero.
        leEntrada.close();

        return texto;
    }
}
