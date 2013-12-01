package modulefactory.postcode.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 25.
 * Time: 오전 8:31
 * To change this template use File | Settings | File Templates.
 */
public class PageInformation implements Serializable {

    private int pageNumber;
    private int pageSize;
    private long totalCount;
    private int totalPage;
    private Integer[] navigationNumber;
    private boolean enablePrevious = false;
    private boolean enableNext = false;
    private int numberGroupCount;

    public PageInformation() {
    }

    public PageInformation(int pageNumber, int pageSize, long totalCount, int totalPage, int navigationSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;

        createPageNavigation(navigationSize);
    }

    public void createPageNavigation(int navigationSize) {
        Assert.notLessThan(0, navigationSize, "Navigation Size는 0보다 작을 수 없습니다.");

        int navigationCount = calNavigationCount(navigationSize);

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 1; i <= navigationSize; i++) {
            if (i == navigationCount) {
                addIfPossible(list, pageNumber);
            } else if (i > navigationCount) {
                addIfPossible(list, pageNumber + (i - navigationCount));
            } else if (i < navigationCount) {
                addIfPossible(list, pageNumber - (navigationCount - i));
            }
        }
        setNumberGroupCount(navigationCount);
        this.navigationNumber = list.toArray(new Integer[list.size()]);

        // 이전버튼 활성화 처리 여부
        if (this.navigationNumber[0] > navigationSize) {
            this.enablePrevious = true;
        }

        // 다음버튼 활성화 처리 여부
        if (this.navigationNumber.length == navigationSize && this.navigationNumber[4] < this.totalPage) {
            this.enableNext = true;
        }
    }

    private int calNavigationCount(int navigationSize) {
        int count = this.pageNumber % navigationSize;
        if (0 == count) {
            return navigationSize;
        }
        return count;
    }

    private void addIfPossible(List<Integer> list, int count) {
        if (count <= this.totalPage) {
            list.add(count);
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Integer[] getNavigationNumber() {
        return navigationNumber;
    }

    public void setNavigationNumber(Integer[] navigationNumber) {
        this.navigationNumber = navigationNumber;
    }

    public boolean isEnablePrevious() {
        return enablePrevious;
    }

    public void setEnablePrevious(boolean enablePrevious) {
        this.enablePrevious = enablePrevious;
    }

    public boolean isEnableNext() {
        return enableNext;
    }

    public void setEnableNext(boolean enableNext) {
        this.enableNext = enableNext;
    }

    public int getNumberGroupCount() {
        return numberGroupCount;
    }

    public void setNumberGroupCount(int numberSetCount) {
        this.numberGroupCount = numberSetCount;
    }
}
