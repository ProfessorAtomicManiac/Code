package CaesarCipher;

public class Main {

    public static void main(String[] args) {
        // CaesarCipher Tests
        CaesarCipher test1 = new CaesarCipher("Hello World! Coding is bruh[]-=-+_", 29);
        test1.encrypt();
        System.out.println(test1.getData());
        test1.decrypt();
        System.out.println(test1.getData());


        // SubCipher Tests
        // Note: I'm assuming the encoder array consists only of capital letters
        // The encryption is case sensitive
        char[] encoder = {'X', 'N',  'Y', 'A', 'H', 'P', 'O', 'G', 'Z', 'Q', 'W', 'B', 'T', 'S', 'F', 'L', 'R', 'C', 'V', 'M', 'U', 'E', 'K', 'J', 'D', 'I'};
        SubCipher test2 = new SubCipher("cryptOgrAPhy...", encoder);
        test2.encrypt();
        System.out.println(test2.getData());
        // Result: ycdlmFocXLgd...
        SubCipher test3 = new SubCipher("Mgzv YzLGhC MHjM YXssFM nh ahYCDLMHA 29sm", encoder);
        test3.decrypt();
        System.out.println(test3.getData());
        // Result: This CiPHeR TExT CAnnOT be deCRYPTED 29nt

    }
}