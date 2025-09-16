package validation;

import java.util.regex.Pattern;

import model.ClientPF;

public class ClientPFValidator implements ClientValidator {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");

    @Override
    public boolean validate(model.Client client) {

        if (!(client instanceof ClientPF)) {
            return false;
        }

        String document = client.getDocumentIdentification();

        if (document == null || document.isBlank()) {
            return false;
        }

        if (!CPF_PATTERN.matcher(document).matches()) {
            return false;
        }

        // rejeita sequências repetidas
        char first = document.charAt(0);
        if (document.chars().allMatch(c -> c == first)) {
            return false;
        }

        return validateDigits(document);        
    }    

    private boolean validateDigits(String document) {
        try {
            int sum1 = 0, sum2 = 0;
            for (int i = 0; i < 9; i++) {
                int num = document.charAt(i) - '0';
                sum1 += num * (10 - i);
                sum2 += num * (11 - i);
            }

            int digit1 = (sum1 * 10) % 11;
            if (digit1 == 10) digit1 = 0;

            sum2 += digit1 * 2;
            int digit2 = (sum2 * 10) % 11;
            if (digit2 == 10) digit2 = 0;

            return digit1 == (document.charAt(9) - '0') && digit2 == (document.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }
    }
}

