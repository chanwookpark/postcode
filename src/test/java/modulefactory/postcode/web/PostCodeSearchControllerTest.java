package modulefactory.postcode.web;

import modulefactory.postcode.config.AppContextConfig;
import modulefactory.postcode.config.WebContextConfig;
import modulefactory.postcode.model.PlainPostCodeAddress;
import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.service.PostCodeSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebContextConfig.class, AppContextConfig.class})
@Transactional
public class PostCodeSearchControllerTest {
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    PostCodeSearchService service;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();

        PostCodeSearchController controller = wac.getBean(PostCodeSearchController.class);
        service = mock(PostCodeSearchService.class);
        controller.setSearchService(service);

    }

    @Test
    public void simpleGet() throws Exception {
        when(service.search("문래", "PLAIN")).thenReturn(new ArrayList<PostCodeAddress>(){{
            add(new PlainPostCodeAddress("150701", "서울특별시", "영등포구", "문래동1가", "우리벤처타운"));
            add(new PlainPostCodeAddress("150702", "서울특별시", "영등포구", "문래동2가", "우리벤처타운"));
            add(new PlainPostCodeAddress("150703", "서울특별시", "영등포구", "문래동3가", "우리벤처타운"));
        }});

        mockMvc.perform(get("/postCode/web?address=문래&addressType=PLAIN").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].postCode").value("150701"))
                .andExpect(jsonPath("$[0].cityDoName").value("서울특별시"))
                .andExpect(jsonPath("$[0].siGunGuName").value("영등포구"))
                .andExpect(jsonPath("$[0].eupMyeonDongRiName").value("문래동1가"))
                .andExpect(jsonPath("$[0].detailAddress").value("우리벤처타운"))
        ;
    }
}
