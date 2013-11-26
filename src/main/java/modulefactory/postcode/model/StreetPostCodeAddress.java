package modulefactory.postcode.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 24.
 * Time: 오후 3:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue("STREET")
public class StreetPostCodeAddress extends PostCodeAddress {

    /**
     * 도로명
     */
    private String streetName;

    /**
     * 도로명 상세주소
     */
    private String streetDetailName;

    /**
     * 건물명
     */
    private String buildingName;

    public StreetPostCodeAddress() {
    }

    public StreetPostCodeAddress(String postCode, String cityDoName, String siGunGuName, String streetName, String streetDetailName, String buildingName) {
        super(postCode, cityDoName, siGunGuName);
        this.streetName = streetName;
        this.streetDetailName = streetDetailName;
        this.buildingName = buildingName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetDetailName() {
        return streetDetailName;
    }

    public void setStreetDetailName(String streetDetailName) {
        this.streetDetailName = streetDetailName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Override
    public boolean isStreetAddress() {
        return true;
    }
}
