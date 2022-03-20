import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;



public class Main {

    public static void main (String[] args) throws IOException, InterruptedException {
        int statusCode, contentLength;
        String url;
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()){
            url = scanner.nextLine();
            if (url != "") {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();
                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());
                statusCode = response.statusCode();
                contentLength = response.body().length();

                System.out.println("\"Url\": \"" + url + "\",");
                System.out.println("\"Status-code\": \"" + statusCode + "\",");
                System.out.println("\"Content-length\": \"" + contentLength + "\",");
                Thread.sleep(500);
            }
        }




    }
}
