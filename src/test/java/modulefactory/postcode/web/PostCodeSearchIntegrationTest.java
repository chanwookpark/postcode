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

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void searchByAddress() throws Exception {
        final ArrayList<PlainPostCodeAddress> postCodeAddresses = new ArrayList<PlainPostCodeAddress>();
        postCodeAddresses.add(new PlainPostCodeAddress("150701", "서울특별시", "영등포구", "문래동1가", "우리벤처타운"));
        postCodeAddresses.add(new PlainPostCodeAddress("150702", "서울특별시", "영등포구", "문래동2가", "우리벤처타운"));
        postCodeAddresses.add(new PlainPostCodeAddress("150703", "서울특별시", "영등포구", "문래동3가", "우리벤처타운"));
        plainPostCodeRepositoryrepository.save(postCodeAddresses);

        //like search
        mockMvc.perform(get("/postCode/search")
                .param("address", "문래동").param("addressType", "PLAIN")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].postCode").value("150701"))
                .andExpect(jsonPath("$[0].cityDoName").value("서울특별시"))
                .andExpect(jsonPath("$[0].siGunGuName").value("영등포구"))
                .andExpect(jsonPath("$[0].eupMyeonDongRiName").value("문래동1가"))
                .andExpect(jsonPath("$[0].detailAddress").value("우리벤처타운"))
        ;
    }

    @Test
    public void searchByStreetAddress() throws Exception {
        final List<StreetPostCodeAddress> postCodes = new ArrayList<StreetPostCodeAddress>();
        postCodes.add(new StreetPostCodeAddress("135169", "서울특별시", "강남구", "강남대로", "150길", "좋은빌딩"));
        postCodes.add(new StreetPostCodeAddress("135169", "서울특별시", "강남구", "강남대로", "150길", "110-10"));
        postCodes.add(new StreetPostCodeAddress("136169", "서울특별시", "강남구", "강남대로", "160길", "나쁜빌딩"));
        streetPostCodeRepository.save(postCodes);

        //like search
        mockMvc.perform(get("/postCode/search")
                .param("address", "강남대로").param("addressType", "STREET")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].postCode").value("135169"))
                .andExpect(jsonPath("$[0].cityDoName").value("서울특별시"))
                .andExpect(jsonPath("$[0].siGunGuName").value("강남구"))
                .andExpect(jsonPath("$[0].streetName").value("강남대로"))
                .andExpect(jsonPath("$[0].streetDetailName").value("150길"))
                .andExpect(jsonPath("$[0].buildingName").value("좋은빌딩"))
        ;
    }

}
