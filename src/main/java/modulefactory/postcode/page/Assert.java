package modulefactory.postcode.page;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 12. 2.
 * Time: 오전 8:24
 * To change this template use File | Settings | File Templates.
 */
public class Assert {
    public static void notLessThan(int base, int target, String message) {
        if (base >= target) {
            throw new IllegalArgumentException(message);
        }
    }
}
