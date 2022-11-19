package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * Klasa ExpressionEvaluator sadrzi jednu metodu "evaluate" koja treba da parsira neki izraz u vidu stringa
 */
public class ExpressionEvaluator {

    private Stack<Character> ops = new Stack<>();
    private Stack<Double> vals = new Stack<>();

    /**
     * Prazan konstruktor sluzi za inicijalizaciju jednog ExpressionEvaluator-a
     */
    public ExpressionEvaluator() {

    }

    /**
     * Validacija space-ova za evaluate funkciju
     */
    private boolean validniSpaceovi(String str) {


        for( int i = 0; i < str.length(); i++ ) {
            if(str.charAt(i) == ' ') return false;

            if(i != str.length() -1 && Character.isDigit( str.charAt(i) ) && Character.isDigit(str.charAt(i+1))) {
                continue;
            }

            if( i != str.length() - 1 && str.charAt(i) != ' ' && str.charAt(i+1) != ' ' ) return false;
            else i++;

        }
        return true;
    }

    /**
     * Funkcija evaluate prima string a vraca rezultat izraza koji je predstavljen stringom ili baca RuntimeException
     * ako string nije validan
     */
    public Double evaluate(String str) {
        ops.clear();
        vals.clear();

        if(!validniSpaceovi(str)) throw new RuntimeException("Desila se greska");

        for(int i = 0; i < str.length(); i++) {

            if( Character.isDigit( str.charAt(i) ) ) {
                int j;
                for(j = i + 1; j < str.length(); j++) {
                    if( !Character.isDigit(str.charAt(j)) ) break;
                }


                Double broj = 0.0;

                int temp = i;

                while(temp < j) {
                    broj  = broj * 10 + (str.charAt(temp) - '0');
                    temp++;
                }

                i = --j;

                vals.push(broj);
                continue;
            }

            if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/') {
                ops.push(str.charAt(i));
            } else if(str.charAt(i) == '(') {
                continue;
            } else if( str.charAt(i) == ')') {
                Character c = ops.pop();
                Double val = vals.pop();
                if(c == '+') {
                    val = vals.pop() + val;
                } else if( c == '-') {
                    val = vals.pop() - val;
                } else if(c == '/') {
                    val = vals.pop() / val;
                } else if(c == '*') {
                    val = vals.pop() * val;
                }
                vals.push(val);
            }
        }

        if(vals.isEmpty()) throw new RuntimeException("Desila se greska");

        Double rez = vals.pop();

        if(!vals.isEmpty() || !ops.isEmpty()) throw new RuntimeException("Desila se greska");

        return rez;
    }

}
