package modulefactory.postcode.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 19.
 * Time: 오전 1:30
 * To change this template use File | Settings | File Templates.
 */
public class PostCode implements Serializable{

    private String postCode;

    private String address;

    public PostCode() {
    }

    public PostCode(String postCode, String address) {
        this.postCode = postCode;
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
