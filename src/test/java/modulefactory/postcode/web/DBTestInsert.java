package modulefactory.postcode.web;

import modulefactory.postcode.config.AppContextConfig;
import modulefactory.postcode.model.PlainPostCodeAddress;
import modulefactory.postcode.model.StreetPostCodeAddress;
import modulefactory.postcode.repository.PlainPostCodeRepository;
import modulefactory.postcode.repository.StreetPostCodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppContextConfig.class})
public class DBTestInsert {
    private MockMvc mockMvc;

    @Autowired
    private PlainPostCodeRepository plainPostCodeRepositoryrepository;

    @Autowired
    private StreetPostCodeRepository streetPostCodeRepository;

    @Test
    public void insertPlainAddressData() throws Exception {
        plainPostCodeRepositoryrepository.deleteAll();
        plainPostCodeRepositoryrepository.save(getPlainAdressData(55));
    }

    @Test
    public void insertStreetAddressData() throws Exception {
        streetPostCodeRepository.deleteAll();
        streetPostCodeRepository.save(getStreetAdressData(55));
    }

    private List<PlainPostCodeAddress> getPlainAdressData(int size) {
        final ArrayList<PlainPostCodeAddress> postCodeAddresses = new ArrayList<PlainPostCodeAddress>();
        for (int i = 0; i < size; i++) {
            String suffixPostCode = "";
            //10보다 작을 때는 0을 붙여줘야 함
            if (i < 10) {
                suffixPostCode = "0" + i;
            } else {
                suffixPostCode = Integer.toString(i);
            }
            postCodeAddresses.add(new PlainPostCodeAddress("1507" + suffixPostCode, "서울특별시", "영등포구", "영등포" + i + "가", "우리벤처타운"));
        }
        return postCodeAddresses;
    }

    private List<StreetPostCodeAddress> getStreetAdressData(int size) {
        final ArrayList<StreetPostCodeAddress> postCodeAddresses = new ArrayList<StreetPostCodeAddress>();
        for (int i = 0; i < size; i++) {
            String suffixPostCode = "";
            //10보다 작을 때는 0을 붙여줘야 함
            if (i < 10) {
                suffixPostCode = "0" + i;
            } else {
                suffixPostCode = Integer.toString(i);
            }
            postCodeAddresses.add(new StreetPostCodeAddress("1351" + suffixPostCode, "서울특별시", "강남구", "양재길", "15" + i + "길", "좋은빌딩"));
        }
        return postCodeAddresses;
    }
}
