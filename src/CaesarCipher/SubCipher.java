package CaesarCipher;

public class SubCipher {

    private char[] encoder;
    private String data;

    public SubCipher(String data, char[] encoder) {
        this.data = data;
        this.encoder = encoder;
    }

    public String getData() {
        return data;
    }

    public void encrypt() {
        char[] enData = new char[data.length()]; // enData = encrypted data

        // Iterate Through each letter and find position in encoder
        for (int i = 0; i < data.length(); i++) {
            // Isolate whether character is capitalized or not and other characters such as periods
            if (data.charAt(i) >= 'a' && data.charAt(i) <= 'z') {
                enData[i] = (char) (encoder[(data.charAt(i) - 'a')]-'A'+'a');
                //System.out.println("Lowercase Letter");
            } else if (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z') {
                enData[i] = encoder[(data.charAt(i) - 'A')];
                //System.out.println("Capital Letter");
            } else {
                enData[i] = data.charAt(i);
            }
        }

        data = new String(enData);
    }

    public int findIndex(char c) {
        for (int k = 0; k < encoder.length; k++) {
            if (c == encoder[k]) return k;
        }
        return -1;
    }

    public void decrypt() {
        char[] deData = new char[data.length()]; // deData = decrypted data

        // Iterate Through each letter and find position in alphabet
        for (int i = 0; i < data.length(); i++) {
            // Isolate whether character is capitalized or not and other characters such as periods
            if (data.charAt(i) >= 'a' && data.charAt(i) <= 'z') {
                deData[i] = (char) ('a' + findIndex((char) (data.charAt(i)-'a'+'A')));
                //System.out.println(findIndex(data.charAt(i)-'a'+'A'));
            } else if (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z') {
                deData[i] = (char) ('A' + findIndex(data.charAt(i)));
                //System.out.println("Capital Letter");
            } else {
                deData[i] = data.charAt(i);
            }
        }

        data = new String(deData);
    }

}