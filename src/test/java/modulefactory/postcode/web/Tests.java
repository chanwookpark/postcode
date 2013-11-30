package modulefactory.postcode.web;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 28.
 * Time: 오후 7:27
 * To change this template use File | Settings | File Templates.
 */
public class Tests {

    @Test
    public void test(){
        String postCode = "123456";
        String front = postCode.substring(0, 3);
        String back = postCode.substring(3, 6);
        System.out.println(front);
        System.out.print(back);

    }

}
