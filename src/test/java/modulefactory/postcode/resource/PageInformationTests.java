package modulefactory.postcode.resource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 28.
 * Time: 오후 12:29
 * To change this template use File | Settings | File Templates.
 */
public class PageInformationTests {

    @Test
    public void createNavigation(){
        PageInformation p = new PageInformation(1, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(2, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(3, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(4, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(5, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(6, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(7, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(8, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(9, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(10, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(11, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{11, 12}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(false));

        p = new PageInformation(12, 5, 60, 12);
        p.createPageNavigation(5);
        assertThat(p.getNavigationNumber(), is(new Integer[]{11, 12}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(false));
    }
}
