package modulefactory.postcode.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "POST_CODE")
public class PostCode implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "POST_CODE_VALUE", length = 6, nullable = false)
    private String postCode;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    public PostCode() {
    }

    public PostCode(String postCode, String address) {
        this.postCode = postCode;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
