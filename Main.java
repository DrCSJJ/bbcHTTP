import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        int statusCode, contentLength;                                                                  //Initialising Storage and Scanner for input
        String url, urlCheck;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter URL of desired Website, Type \"END\" to end program.");

        while (scanner.hasNextLine()) {                                                       //Searching for if the scanner has a next and storing the URL in url String
            url = scanner.nextLine();

            if (url.length() > 4) {
                urlCheck = url.substring(0, 4);                                             //eliminating false URls with IFs statements
                if (urlCheck.equals("http") == false) {
                    url = "";
                    System.out.println("Please enter a correct URL");
                }
            } else {
                url = "";
                System.out.println("Please enter a correct URL");
            }

            if (url.equals("END")) {                                                              //Exit Program IF
                System.exit(0);
            } else if (url != "") {
                try {
                    HttpClient client = HttpClient.newHttpClient();                             //Initialising connection to URL and building the response
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();
                    HttpResponse<String> response =
                            client.send(request, HttpResponse.BodyHandlers.ofString());         //Storing content length and the status code into their respective INT
                    statusCode = response.statusCode();
                    contentLength = response.body().length();

                    System.out.println("{");                                                    //Printing in JSON Format
                    System.out.println("  \"Url\": \"" + url + "\",");
                    System.out.println("  \"Status-code\": \"" + statusCode + "\",");
                    System.out.println("  \"Content-length\": \"" + contentLength + "\",");
                    System.out.println("}");
                    Thread.sleep(500);
                } catch (IOException e) {
                    url = "";
                    System.out.println("Please enter a correct URL");                           //Catch errors that occur in the fetching of website data
                } catch (InterruptedException e) {
                    url = "";
                    System.out.println("Please enter a correct URL");
                } catch (IllegalArgumentException e) {
                    url = "";
                    System.out.println("Please enter a correct URL");
                }
            }
        }
    }
}
