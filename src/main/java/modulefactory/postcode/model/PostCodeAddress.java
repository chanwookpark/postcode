package modulefactory.postcode.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "POST_CODE_ADDRESS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="addressType",
        discriminatorType=DiscriminatorType.STRING
)
public class PostCodeAddress implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * 우편번호
     */
    @Column(name = "POST_CODE", length = 6, nullable = false)
    private String postCode;

    /**
     * 시도명
     */
    private String cityDoName;

    /**
     * 시군구명
     */
    private String siGunGuName;

    public PostCodeAddress(String postCode, String cityDoName, String siGunGuName) {
        this.postCode = postCode;
        this.cityDoName = cityDoName;
        this.siGunGuName = siGunGuName;
    }

    public PostCodeAddress() {
    }

    public PostCodeAddress(String postCode) {
        this.postCode = postCode;
    }

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

    public String getCityDoName() {
        return cityDoName;
    }

    public void setCityDoName(String cityDoName) {
        this.cityDoName = cityDoName;
    }

    public String getSiGunGuName() {
        return siGunGuName;
    }

    public void setSiGunGuName(String siGunGuName) {
        this.siGunGuName = siGunGuName;
    }
}
