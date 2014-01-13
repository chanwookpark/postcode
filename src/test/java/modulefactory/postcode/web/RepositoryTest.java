package modulefactory.postcode.web;

import modulefactory.postcode.config.AppContextConfig;
import modulefactory.postcode.repository.PlainPostCodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 19.
 * Time: 오후 12:38
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextConfig.class)
public class RepositoryTest {

    @Autowired
    private PlainPostCodeRepository repository;

    @Test
    public void configForJpa(){
        assertThat(repository, is(notNullValue()));
    }

}
