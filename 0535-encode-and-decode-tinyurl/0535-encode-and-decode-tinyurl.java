import java.util.*;

public class Codec {

    HashMap<String, String> map = new HashMap<>();
    int id = 0;

    public String encode(String longUrl) {
        String shortUrl = "http://tinyurl.com/" + id;
        map.put(shortUrl, longUrl);
        id++;
        return shortUrl;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}