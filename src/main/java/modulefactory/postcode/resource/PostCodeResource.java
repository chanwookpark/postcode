package modulefactory.postcode.resource;

import framewise.page.PageInformation;
import modulefactory.postcode.model.PlainPostCodeAddress;
import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.model.StreetPostCodeAddress;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
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

    public PostCodeResource(Page<PostCodeAddress> addresses, PageInformation page, String addressType) {
        super(page);
        this.contents = convertToResource(addresses);
        this.addressType = addressType;
    }

    private List<PostCodeResourceEntity> convertToResource(Page<PostCodeAddress> postCodeAddresses) {
        List<PostCodeResourceEntity> entities = new ArrayList<PostCodeResourceEntity>();
        for (PostCodeAddress address : postCodeAddresses) {
            PostCodeResourceEntity entity = new PostCodeResourceEntity();
            entity.setId(address.getId());
            entity.setPostCode(withDash(address.getPostCode()));

            StringBuilder addressBuilder = new StringBuilder();
            addAddress(addressBuilder, address.getCityDoName());
            addAddress(addressBuilder, address.getSiGunGuName());

            if (address.isStreetAddress()) {
                StreetPostCodeAddress streetAddress = (StreetPostCodeAddress) address;
                addAddress(addressBuilder, streetAddress.getStreetName());
                addAddress(addressBuilder, streetAddress.getStreetDetailName());
                addAddress(addressBuilder, streetAddress.getBuildingName());
            } else {
                PlainPostCodeAddress plainAddress = (PlainPostCodeAddress) address;
                addAddress(addressBuilder, plainAddress.getEupMyeonDongRiName());
                addAddress(addressBuilder, plainAddress.getDetailAddress());
            }
            entity.setAddress(addressBuilder.toString());
            entities.add(entity);
        }
        return entities;
    }

    private String withDash(String postCode) {
        String front = postCode.substring(0, 3);
        String back = postCode.substring(3, 6);
        return front + "-" + back;
    }

    private void addAddress(StringBuilder addressBuilder, String addressText) {
        addressBuilder.append(addressText);
        addressBuilder.append(" ");
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
