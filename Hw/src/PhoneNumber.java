/**
 * Simple class representing a 7-digit phone number in the form "XXX-XXXX" for a
 * phone in the immediate OSU area.
 */
public class PhoneNumber {

    /**
     * The phone number representation.
     */
    private String rep;

    /**
     * Constructor. {@code pNum} must be in the form "XXX-XXXX" where each "X"
     * is a digit '0'-'9'.
     */
    public PhoneNumber(String pNum) {
        this.rep = pNum;
    }

    public int mapLetter(char c) {
        if (c <= 'C') {
            return 2;
        } else if (c <= 'F') {
            return 3;
        } else if (c <= 'I') {
            return 4;
        } else if (c <= 'L') {
            return 5;
        } else if (c <= 'O') {
            return 6;
        } else if (c <= 'S') {
            return 7;
        } else if (c <= 'V') {
            return 8;
        } else {
            return 9;
        }
    }

    @Override
    public int hashCode() {
        int total = 0;
        for (int i = 0; i < this.rep.length(); i++) {
            char c = this.rep.charAt(i);
            if (c == '-') {
                continue;
            } else if (Character.isDigit(c)) {
                total += Character.digit(c, 10);
            } else {
                char u = Character.toUpperCase(c);
                if (u >= 'A' && u <= 'Z') {
                    total += this.mapLetter(u);
                }
            }
        }
        return total;
    }

//    @Override
//    public int hashCode() {
//
//        int length = this.rep.length();
//        int hashResult = 0;
//        for (int i = 0; i < length; i++) {
//            if (this.rep.charAt(i) != '-') {
//                hashResult += Character.digit(this.rep.charAt(i), 36);
//                // The reason why I choose 36 for radix is that it
//                // should cover 0-9 and A-Z
//            }
//        }
//        return hashResult;
//    }

}
