package modulefactory.postcode.resource;

import framewise.page.PageInformation;

import java.io.Serializable;

/**
 * @author chanwook
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
