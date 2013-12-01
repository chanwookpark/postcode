package modulefactory.postcode.resource;

import modulefactory.postcode.page.PageInformation;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 28.
 * Time: 오후 12:29
 * To change this template use File | Settings | File Templates.
 */
public class PageInformationTests {

    public static final int NAVIGATION_SIZE = 5;
    public static final int TOTAL_PAGE = 12;
    public static final int TOTAL_COUNT = 60;

    @Test
    public void createNavigation() {
        PageInformation p = new PageInformation(1, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(2, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(3, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(4, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(5, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{1, 2, 3, 4, 5}));
        assertThat(p.isEnablePrevious(), is(false));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(6, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(7, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(8, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(9, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(10, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{6, 7, 8, 9, 10}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(true));

        p = new PageInformation(11, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{11, 12}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(false));

        p = new PageInformation(12, 5, TOTAL_COUNT, TOTAL_PAGE, NAVIGATION_SIZE);
        assertThat(p.getNavigationNumber(), is(new Integer[]{11, 12}));
        assertThat(p.isEnablePrevious(), is(true));
        assertThat(p.isEnableNext(), is(false));
    }
}
