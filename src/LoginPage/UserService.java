package LoginPage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {

    Scanner scanner = new Scanner(System.in);
    public Map<String, String> userInfos = new HashMap<>();

    public void register() {
        System.out.println("İsim soyisim giriniz : ");
        String name = scanner.nextLine().trim();


        String email;
        boolean isValidEmail;
        do {
            System.out.println("Email giriniz : ");
            email = scanner.nextLine().trim();
            isValidEmail = validateEmail(email);
            if (this.userInfos.containsKey(email)) {
                System.out.println("Bu email ile kayıtlı kullanıcı zaten var.Başka bir email deneyiniz!");
                isValidEmail = false;
            }
        } while (!isValidEmail);

        String password;
        boolean isValidPassword;
        do {
            System.out.println("Şifrenizi giriniz : ");
            password = scanner.nextLine().trim();
            isValidPassword = validatePassword(password);
        } while (!isValidPassword);

        User user = new User(name, email, password);
        this.userInfos.put(user.getEmail(), user.getPassword());
        System.out.println("Tebrikler,kayıt işlemi başarıyla gerçekleşti.");
        System.out.println("Email ve şifrenizi kullanarak sisteme  giriş yapabilirsiniz");

    }

    public void login() {
        System.out.println("Email giriniz : ");
        String email = scanner.nextLine().trim();
        if (this.userInfos.containsKey(email)) {
            int counter = 3;
            while (counter > 0) {
                System.out.println("Şifrenizi giriniz : ");
                String password = scanner.nextLine().trim();
                if (this.userInfos.get(email).equals(password)) {
                    System.out.println("Harika,sisteme giiriş yaptınız.Hoşgeldiniz!");
                    counter = 0;
                } else {
                    counter--;
                    if (counter == 0) {
                        System.out.println("3 defa hatalı giriş yaptınız!");
                    } else {
                        System.out.println("Şifreniz yanlış; tekrar deneyiniz.Kalan hakkınız : " + counter);
                    }
                }
            }

        } else {
            System.out.println("Sisteme kayıtlı kullanıcı bulunamadı!");
            System.out.println("Üyeyseniz bilgilerinizi kontrol ediniz, değilseniz üye olunuz.");
        }

    }


    public boolean validateEmail(String email) {
        boolean isValid;
        boolean hasSpace = email.contains(" ");
        boolean isContainsAt = email.contains("@");

        if (hasSpace) {
            System.out.println("Email boşluk içeremez!");
            isValid = false;
        } else if (!isContainsAt) {
            System.out.println("Email @ sembolünü içermelidir.");
            isValid = false;
        } else {
            String firstPart = email.split("@")[0];
            String secondPart = email.split("@")[1];
            boolean isExistInvalidCharacters = firstPart.replaceAll("[A-Za-z0-9-._]", "").length() > 0;
            boolean checkEmailDomain = secondPart.equals("gmail.com") || secondPart.equals("hotmail.com") || secondPart.equals("yahoo.com");

            if (isExistInvalidCharacters) {
                System.out.println("Email kullanıcı adı harf,rakam ve -._ sembolleri dışında karakter içeremez!");
            } else if (!checkEmailDomain) {
                System.out.println("Email kullanıcı adı gmail.com,hotmail.com,yahoo.com ile bitmelidir!");

            }
            isValid = !isExistInvalidCharacters && checkEmailDomain;
        }
        if (!isValid) {
            System.out.println("Geçersiz email,tekrar deneyiniz!");
        }
        return isValid;
    }


    public boolean validatePassword(String password) {
        boolean isValid;
        boolean hasSpace = password.contains(" ");
        boolean islengthGTSix = password.length() >= 6;
        boolean upperLetter = password.replaceAll("[^A-Z]", "").length() > 0;
        boolean lowerLetter = password.replaceAll("[^a-z]", "").length() > 0;
        boolean digits = password.replaceAll("[^0-9]", "").length() > 0;
        boolean symbols = password.replaceAll("\\P{Punct}", "").length() > 0;

        if (hasSpace) {
            System.out.println("Şifre boşluk içeremez!");

        } else if (!islengthGTSix) {
            System.out.println("Şifre en az 6 tane karakter olmalıdır!");

        } else if (!upperLetter) {
            System.out.println("Şifre en az 1 tane büyük harf içermelidir!");

        } else if (!lowerLetter) {
            System.out.println("Şifre en az 1 tane küçük harf içermelidir!");

        } else if (!digits) {
            System.out.println("Şifre en az 1 tane rakam içermelidir!");

        } else if (!symbols) {
            System.out.println("Şifre en az 1 tane sembol içermelidir!");

        }
        isValid = !hasSpace && islengthGTSix && upperLetter && lowerLetter && digits && symbols;

        if (!isValid){
            System.out.println("Geçersiz şifre,tekrar deneyiniz!");
        }


        return isValid;
    }


}
