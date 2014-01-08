package modulefactory.postcode.resource;

import framewise.page.PageInformation;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 25.
 * Time: 오전 8:18
 * To change this template use File | Settings | File Templates.
 */
public abstract class ResourceSupport implements Serializable {

    protected PageInformation page;

    protected ResourceSupport() {
    }

    protected ResourceSupport(PageInformation page) {
        this.page = page;
    }

    public PageInformation getPage() {
        return page;
    }

    public void setPage(PageInformation page) {
        this.page = page;
    }
}
