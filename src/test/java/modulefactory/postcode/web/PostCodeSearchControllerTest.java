package modulefactory.postcode.web;

import framewise.page.PagingParam;
import modulefactory.postcode.config.AppContextConfig;
import modulefactory.postcode.config.WebContextConfig;
import modulefactory.postcode.model.PlainPostCodeAddress;
import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.service.PostCodeSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebContextConfig.class, AppContextConfig.class})
@Transactional
public class PostCodeSearchControllerTest {
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    PostCodeSearchService service;
    private ResultActions resultActions;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();

        PostCodeSearchController controller = wac.getBean(PostCodeSearchController.class);
        service = mock(PostCodeSearchService.class);
        controller.setSearchService(service);

    }

    @Test
    public void simpleGet() throws Exception {
        Page<PostCodeAddress> page = new PageImpl<PostCodeAddress>(new ArrayList<PostCodeAddress>() {{
            add(new PlainPostCodeAddress("150701", "서울특별시", "영등포구", "문래동1가", "우리벤처타운"));
            add(new PlainPostCodeAddress("150702", "서울특별시", "영등포구", "문래동2가", "우리벤처타운"));
            add(new PlainPostCodeAddress("150703", "서울특별시", "영등포구", "문래동3가", "우리벤처타운"));
        }});
        when(service.search("문래", "PLAIN", new PagingParam(1, 10, 5))).thenReturn(page);

        resultActions = mockMvc.perform(get("/postCode/search?address=문래&addressType=PLAIN").accept(MediaType.parseMediaType("application/json;charset=UTF-8")));
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$contents[0].address").value("서울특별시 영등포구 문래동1가 우리벤처타운 "))
                .andExpect(jsonPath("$contents[0].postCode").value("150701"))
                .andExpect(jsonPath("$contents[1].address").value("서울특별시 영등포구 문래동2가 우리벤처타운 "))
                .andExpect(jsonPath("$contents[1].postCode").value("150702"))
                .andExpect(jsonPath("$contents[2].address").value("서울특별시 영등포구 문래동3가 우리벤처타운 "))
                .andExpect(jsonPath("$contents[2].postCode").value("150703"))
        ;
    }

}
