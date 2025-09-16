package validation;

import java.util.regex.Pattern;

public class ClientPJValidator implements ClientValidator {

    private static final Pattern CNPJ_PATTERN = Pattern.compile("\\d{14}");

    @Override
    public boolean validate(model.Client client) {
        String document = client.getDocumentIdentification();
        if (document == null || document.isBlank()) {
            return false;
        }

        if (!CNPJ_PATTERN.matcher(document).matches()) {
            return false;
        }

        if (document.chars().distinct().count() == 1) {
            return false;
        }

        return validateDigits(document); 
    }

    private boolean validateDigits(String document) {
        try {
            int sum1 = 0, sum2 = 0;
            int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            for (int i = 0; i < 12; i++) {
                int num = document.charAt(i) - '0';
                sum1 += num * weights1[i];
                sum2 += num * weights2[i];
            }

            int digit1 = sum1 % 11;
            digit1 = (digit1 < 2) ? 0 : (11 - digit1);

            sum2 += digit1 * weights2[12];
            int digit2 = sum2 % 11;
            digit2 = (digit2 < 2) ? 0 : (11 - digit2);

            return digit1 == (document.charAt(12) - '0') && digit2 == (document.charAt(13) - '0');

        } catch (Exception e) {
            return false;
        }
    }    
}
