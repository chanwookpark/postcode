package modulefactory.postcode.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 24.
 * Time: 오후 3:17
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue("PLAIN")
public class PlainPostCodeAddress extends PostCodeAddress {

    /**
     * 읍면동리명
     */
    private String eupMyeonDongRiName;

    /**
     * 건물명 및 상세주소
     */
    private String detailAddress;

    public PlainPostCodeAddress() {
    }

    public PlainPostCodeAddress(String postCode, String cityDoName, String siGunGuName, String eupMyeonDongRiName, String detailAddress) {
        super(postCode, cityDoName, siGunGuName);
        this.eupMyeonDongRiName = eupMyeonDongRiName;
        this.detailAddress = detailAddress;
    }

    public String getEupMyeonDongRiName() {
        return eupMyeonDongRiName;
    }

    public void setEupMyeonDongRiName(String eupMyeonDongRiName) {
        this.eupMyeonDongRiName = eupMyeonDongRiName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
