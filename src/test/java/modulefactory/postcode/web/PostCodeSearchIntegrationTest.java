package modulefactory.postcode.web;

import modulefactory.postcode.config.AppContextConfig;
import modulefactory.postcode.config.WebContextConfig;
import modulefactory.postcode.model.PlainPostCodeAddress;
import modulefactory.postcode.model.StreetPostCodeAddress;
import modulefactory.postcode.repository.PlainPostCodeRepository;
import modulefactory.postcode.repository.StreetPostCodeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebContextConfig.class, AppContextConfig.class})
@EnableTransactionManagement
@Transactional
public class PostCodeSearchIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PlainPostCodeRepository plainPostCodeRepositoryrepository;

    @Autowired
    private StreetPostCodeRepository streetPostCodeRepository;
    private ResultActions resultActions;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void searchByAddress() throws Exception {
        plainPostCodeRepositoryrepository.save(getPlainAdressData(3));

        //like search
        mockMvc.perform(get("/search")
                .param("address", "문래").param("addressType", "PLAIN")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$contents[0].postCode").value("150-700"))
                .andExpect(jsonPath("$contents[0].address").value("서울특별시 영등포구 문래동0가 우리벤처타운 "))
                .andExpect(jsonPath("$contents[1].postCode").value("150-701"))
                .andExpect(jsonPath("$contents[1].address").value("서울특별시 영등포구 문래동1가 우리벤처타운 "))
                .andExpect(jsonPath("$contents[2].postCode").value("150-702"))
                .andExpect(jsonPath("$contents[2].address").value("서울특별시 영등포구 문래동2가 우리벤처타운 "))
        ;
    }

    @Test
    public void searchByStreetAddress() throws Exception {
        streetPostCodeRepository.save(getStreetAdressData(2));

        //like search
        mockMvc.perform(get("/search")
                .param("address", "강남대로").param("addressType", "STREET")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$contents[0].postCode").value("135-100"))
                .andExpect(jsonPath("$contents[0].address").value("서울특별시 강남구 강남대로 150길 좋은빌딩 "))
                .andExpect(jsonPath("$contents[1].postCode").value("135-101"))
                .andExpect(jsonPath("$contents[1].address").value("서울특별시 강남구 강남대로 151길 좋은빌딩 "))
        ;
    }

    @Test
    public void searchStreetForPaging() throws Exception {

        streetPostCodeRepository.save(getStreetAdressData(10));

        resultActions = mockMvc.perform(
                get("/search")
                    .param("address", "강남대로")
                    .param("addressType", "STREET")
                    .param("_pageItemSize", "3")
                    .param("_pageNumber", "1")
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        );
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$contents[0].postCode").value("135-100"))
                .andExpect(jsonPath("$contents[1].postCode").value("135-101"))
                .andExpect(jsonPath("$contents[2].postCode").value("135-102"))
        ;

        mockMvc.perform(
                get("/search")
                    .param("address", "강남대로")
                    .param("addressType", "STREET")
                    .param("_pageItemSize", "3")
                    .param("_pageNumber", "2")
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$contents[0].postCode").value("135-103"))
                .andExpect(jsonPath("$contents[1].postCode").value("135-104"))
                .andExpect(jsonPath("$contents[2].postCode").value("135-105"))
        ;

        mockMvc.perform(
                get("/search")
                    .param("address", "강남대로")
                    .param("addressType", "STREET")
                    .param("_pageItemSize", "3")
                    .param("_pageNumber", "3")
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$contents[0].postCode").value("135-106"))
                .andExpect(jsonPath("$contents[1].postCode").value("135-107"))
                .andExpect(jsonPath("$contents[2].postCode").value("135-108"))
        ;

        mockMvc.perform(
                get("/search")
                    .param("address", "강남대로")
                    .param("addressType", "STREET")
                    .param("_pageItemSize", "3")
                    .param("_pageNumber", "4")
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$contents[0].postCode").value("135-109"))
        ;
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
            postCodeAddresses.add(new PlainPostCodeAddress("1507" + suffixPostCode, "서울특별시", "영등포구", "문래동" + i + "가", "우리벤처타운"));
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
            postCodeAddresses.add(new StreetPostCodeAddress("1351" + suffixPostCode, "서울특별시", "강남구", "강남대로", "15" + i + "길", "좋은빌딩"));
        }
        return postCodeAddresses;
    }
}
