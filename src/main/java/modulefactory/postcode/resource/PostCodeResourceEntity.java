package modulefactory.postcode.resource;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 25.
 * Time: 오전 8:18
 * To change this template use File | Settings | File Templates.
 */
public class PostCodeResourceEntity implements Serializable {

    private long id;

    /**
     * 우편번호
     */
    private String postCode;

    /**
     * (병합된) 주소
     */
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
