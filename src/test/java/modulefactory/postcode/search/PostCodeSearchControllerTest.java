package modulefactory.postcode.search;

import modulefactory.postcode.config.AppContextConfig;
import modulefactory.postcode.config.WebContextConfig;
import modulefactory.postcode.model.PostCode;
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
        when(service.search("문래")).thenReturn(new ArrayList<PostCode>(){{
            add(new PostCode("150701", "문래동1가"));
            add(new PostCode("150702", "문래동2가"));
            add(new PostCode("150703", "문래동3가"));
        }});

        mockMvc.perform(get("/postCode/search?address=문래").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].postCode").value("150701"))
                .andExpect(jsonPath("$[0].address").value("문래동1가"));
        ;
    }
}
