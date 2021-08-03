package CaesarCipher;

public class CaesarCipher {

    private String data;
    private int shift; // How many letters to shift for the encryption

    static private int defaultShift = 3; // Default shift is 3 letters (Caesar Cipher). This is set to avoid literals

    public CaesarCipher(String data) {
        this.data = data;
        this.shift = defaultShift;
    }

    public CaesarCipher(String data, int shift) {
        this.data = data;

        // The Modulo 26 is so that if the user inputs more than 26, than the letters don't shift to the characters that are not letters
        this.shift = shift % 26;
    }

    public String getData() {
        return data;
    }

    public int getShift() {
        return shift;
    }

    // This changes the data to its encrypted form
    public void encrypt() {
        // Copy data
        char[] enData = new char[data.length()]; // enData = encrypted data

        // Iterate Through each letter and move it a number of letters
        for (int i = 0; i < data.length(); i++) {
            // Isolate whether character is capitalized or not and other characters such as periods
            if (data.charAt(i) >= 'a' && data.charAt(i) <= 'z') {
                enData[i] = (char) (((data.charAt(i) + shift) % 'a')+'a');
                //System.out.println("Lowercase Letter");
            } else if (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z') {
                enData[i] = (char) (((data.charAt(i) + shift) % 'A')+'A');
                //System.out.println("Capital Letter");
            } else {
                enData[i] = data.charAt(i);
            }
        }

        data = new String(enData);
    }

    // This changes the data to its decrypted form
    public void decrypt() {
        // Copy data
        char[] deData = new char[data.length()]; // deData = decrypted data

        // Iterate Through each letter and move it a number of letters
        for (int i = 0; i < data.length(); i++) {
            // Isolate whether character is capitalized or not and other characters such as periods
            if (data.charAt(i) >= 'a' && data.charAt(i) <= 'z') {
                deData[i] = (char) (((data.charAt(i) - shift) % 'a')+'a');
                //System.out.println("Lowercase Letter");
            } else if (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z') {
                deData[i] = (char) (((data.charAt(i) - shift) % 'A')+'A');
                //System.out.println("Capital Letter");
            } else {
                deData[i] = data.charAt(i);
            }
        }

        data = new String(deData);
    }

}
