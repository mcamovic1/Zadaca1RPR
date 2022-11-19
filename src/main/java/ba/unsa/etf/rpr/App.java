package ba.unsa.etf.rpr;

/**
 * Klasa App sluzi za parsiranje ulaza s konzole iz args parametra
 */
public class App {

    public static void main(String[] args) {
        ExpressionEvaluator ee = new ExpressionEvaluator();
        for(int i = 0; i < args.length; i++) {
            try {
                Double rezultat = ee.evaluate(args[i]);
                System.out.println("Rezultat za izraz " + args[i] + " je " + rezultat);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
