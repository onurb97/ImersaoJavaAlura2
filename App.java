import java.net.URI;
import java.net.URL;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os tops 250 TV Shows
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send (request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)+
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeProgamasDeTV = parser.parse(body);
        System.out.println(ListaDeProgamasDeTV.size());
        System.out.println(ListaDeProgamasDeTV.get(0));
        
        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> programa : ListaDeProgamasDeTV) {

            String urlImage = programa.get("image");
            String titulo = programa.get("title");

            InputStream inputStream = new URL (urlImage).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria (inputStream, nomeArquivo);
                
            System.out.println(programa.get("title"));
            System.out.println();
            
        }
            
            
            
        }

    }   



