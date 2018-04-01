
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;


public class HttpRequest {

    public void  executePost(String targetURL, String urlParameters){

        try {

            String postUrl = targetURL;// put in your url
            Gson gson = new Gson();
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(gson.toJson(urlParameters));//gson.tojson() converts your pojo to json
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            HttpResponse  response = httpClient.execute(post);
            String serverResponse = EntityUtils.toString(response.getEntity());
            if(serverResponse == "OK")
            {
                System.out.println("success");

            }
            else
            {
                System.out.println(serverResponse);
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  executeGet(String targetURL, String urlParameters){

        try {

            URIBuilder builder = new URIBuilder(targetURL);// put in your url
            Gson gson = new Gson();
            builder.addParameter("parma1",urlParameters);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(String.valueOf(builder));
            get.setHeader("Content-type", "application/json");
            HttpResponse  response = httpClient.execute(get);
            Object serverResponse = (EntityUtils.toString(response.getEntity()));
            if(serverResponse == "OK")
            {
                System.out.println("success");

            }
            else
            {
                System.out.println(serverResponse);
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
