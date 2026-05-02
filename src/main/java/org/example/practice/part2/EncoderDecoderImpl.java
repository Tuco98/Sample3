package org.example.practice.part2;

public class EncoderDecoderImpl implements EncoderDecoder{

    private String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Override
    public String encode(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0){
            int rem = (int) num%62;
            sb.append(BASE62.charAt(rem));
            num/=62;
        }
        return sb.reverse().toString();
    }

    @Override
    public String decode(String url) {
        return "";
    }
}
