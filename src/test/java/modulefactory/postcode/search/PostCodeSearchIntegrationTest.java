package modulefactory.postcode.search;

import modulefactory.postcode.config.AppContextConfig;
import modulefactory.postcode.config.WebContextConfig;
import modulefactory.postcode.model.PostCode;
import modulefactory.postcode.repository.PostCodeRepository;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
    private PostCodeRepository repository;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void searchByAddress() throws Exception {
        final ArrayList<PostCode> postCodes = new ArrayList<PostCode>();
        postCodes.add(new PostCode("100100", "문래동1가 100번지"));
        postCodes.add(new PostCode("100200", "문래동2가 200번지"));
        postCodes.add(new PostCode("100300", "문래동3가 300번지"));
        repository.save(postCodes);

        //like search
        mockMvc.perform(get("/postCode/search").param("address", "문래동").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].postCode").value("100100"))
                .andExpect(jsonPath("$[0].address").value("문래동1가 100번지"))
                .andExpect(jsonPath("$[1].postCode").value("100200"))
                .andExpect(jsonPath("$[1].address").value("문래동2가 200번지"))
                .andExpect(jsonPath("$[2].postCode").value("100300"))
                .andExpect(jsonPath("$[2].address").value("문래동3가 300번지"))
        ;
    }
}
