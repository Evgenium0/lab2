package checkurlisvalid;

import java.net.URL;
public class zadacha3 {
    public static boolean validateURL(String urlString)
    {
        try
        {
           new URL(urlString).toURI();
        }
        catch (Exception e)
        {
          System.out.println(e);
          return false;
        }
        return true;
    }
    public static void main(String[] args){
        String link = "http://example.com";

        System.out.println(validateURL(link));

    }
}
