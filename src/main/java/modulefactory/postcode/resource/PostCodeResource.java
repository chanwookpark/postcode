package modulefactory.postcode.resource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 25.
 * Time: 오전 8:29
 * To change this template use File | Settings | File Templates.
 */
public class PostCodeResource extends ResourceSupport {


    /**
     * 우편번호 주소 유형
     */
    private String addressType;

    private List<PostCodeResourceEntity> contents;

    public PostCodeResource() {
    }

    public PostCodeResource(List<PostCodeResourceEntity> contents, PageInformation page) {
        super(page);
        this.contents = contents;
    }

    public List<PostCodeResourceEntity> getContents() {
        return contents;
    }

    public void setContents(List<PostCodeResourceEntity> contents) {
        this.contents = contents;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
}
