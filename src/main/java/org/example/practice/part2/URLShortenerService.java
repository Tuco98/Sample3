package org.example.practice.part2;

import java.util.HashMap;
import java.util.Map;

public class URLShortenerService {
    Map<String,UrlEntry> shortToLong = new HashMap<>();
    Map<String,String> longToShort = new HashMap<>();

    static long counter = 1;


    EncoderDecoder encoderDecoder = new EncoderDecoderImpl();

    String shorten(String longUrl){
        if(longToShort.containsKey(longUrl)){
            return "short.ly/"+shortToLong.get(longUrl);
        }


        String shortCode = encoderDecoder.encode(counter++);

        longToShort.put(longUrl,shortCode);
        shortToLong.put(shortCode,new UrlEntry(longUrl,-1));
        return "short.ly/"+shortCode;

    }
    String expand(String shortUrl){
        String url = shortUrl.substring(shortUrl.lastIndexOf("/")+1);
        UrlEntry urlEntry = shortToLong.get(url);

        if(urlEntry.getExpTime()!= -1 && urlEntry.getExpTime()>System.currentTimeMillis()){
            String longurl = urlEntry.getUrl();
            shortToLong.remove(shortUrl);
            longToShort.remove(longurl);
            return null;
        }

        return urlEntry.getUrl();
    }

    String shorten(String longUrl, String customAlias){
        if(longToShort.containsKey(longUrl)){
            return "short.ly/"+shortToLong.get(longUrl);
        }

        if(shortToLong.containsKey(customAlias)){
            throw new RuntimeException("Alias already exists");
        }

        longToShort.put(longUrl,customAlias);
        shortToLong.put(customAlias,new UrlEntry(longUrl,-1));
        return "short.ly/"+customAlias;
    }

    String shorten(String longUrl, int ttlSeconds){
        if(longToShort.containsKey(longUrl)){
            return "short.ly/"+shortToLong.get(longUrl);
        }

        long exp = System.currentTimeMillis()+ttlSeconds;
        String shortCode = encoderDecoder.encode(counter++);

        longToShort.put(longUrl,shortCode);
        shortToLong.put(shortCode,new UrlEntry(longUrl,exp));
        return "short.ly/"+shortCode;
    }
}
