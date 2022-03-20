import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;



public class Main {

    public static void main (String[] args) throws IOException, InterruptedException {
        String url;                                             //initialise strings and ints to be used and the scanner to read StdIn
        int statusCode, contentLength;
        Scanner scanner = new Scanner(System.in);

        url = scanner.next();                                   //Scanning for StdIn input of next URL

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        statusCode = response.statusCode();





        System.out.println("\"Url\": \""+ url + "\"," );
        System.out.println("\"Status-code\": \""+ statusCode + "\"," );
        System.out.println("\"Content-length\": \""+ contentLength + "\"," );

    }
}
