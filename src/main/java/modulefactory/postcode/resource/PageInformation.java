package modulefactory.postcode.resource;

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

    public PageInformation(int pageNumber, int pageSize, long totalCount, int totalPage) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }

    public void createPageNavigation(int navigationSize) {

        int navigationCount = this.pageNumber % navigationSize;

        List<Integer> list = new ArrayList<Integer>();
        //FIXME 로직 개선! 엉망이네!
        if (this.pageNumber == 0 || navigationCount == 1) {
            //this.navigationNumber = new Integer[]{pageNumber, pageNumber + 1, pageNumber + 2, pageNumber + 3, pageNumber + 4};
//            list.add(pageNumber);
//            for (int i = 1; i < navigationSize; i++) {
//                list.add(i);
//            }
            addIfPossible(list, pageNumber);
            addIfPossible(list, pageNumber + 1);
            addIfPossible(list, pageNumber + 2);
            addIfPossible(list, pageNumber + 3);
            addIfPossible(list, pageNumber + 4);

            setNumberGroupCount(1);

        } else if (navigationCount == 2) {
            //this.navigationNumber = new Integer[]{pageNumber - 1, pageNumber, pageNumber + 1, pageNumber + 2, pageNumber + 3};
            addIfPossible(list, pageNumber - 1);
            addIfPossible(list, pageNumber);
            addIfPossible(list, pageNumber + 1);
            addIfPossible(list, pageNumber + 2);
            addIfPossible(list, pageNumber + 3);

            setNumberGroupCount(2);
        } else if (navigationCount == 3) {
//            this.navigationNumber = new Integer[]{pageNumber - 2, pageNumber - 1, pageNumber, pageNumber + 1, pageNumber + 2};
            addIfPossible(list, pageNumber - 2);
            addIfPossible(list, pageNumber - 1);
            addIfPossible(list, pageNumber);
            addIfPossible(list, pageNumber + 1);
            addIfPossible(list, pageNumber + 2);

            setNumberGroupCount(3);
        } else if (navigationCount == 4) {
//            this.navigationNumber = new Integer[]{pageNumber - 3, pageNumber - 2, pageNumber - 1, pageNumber, pageNumber + 1};
            addIfPossible(list, pageNumber - 3);
            addIfPossible(list, pageNumber - 2);
            addIfPossible(list, pageNumber - 1);
            addIfPossible(list, pageNumber);
            addIfPossible(list, pageNumber + 1);

            setNumberGroupCount(4);
        } else if (navigationCount == 0) {
//            this.navigationNumber = new Integer[]{pageNumber - 4, pageNumber - 3, pageNumber - 2, pageNumber - 1, pageNumber};
            addIfPossible(list, pageNumber - 4);
            addIfPossible(list, pageNumber - 3);
            addIfPossible(list, pageNumber - 2);
            addIfPossible(list, pageNumber - 1);
            addIfPossible(list, pageNumber);

            setNumberGroupCount(5);
        }
        this.navigationNumber = list.toArray(new Integer[list.size()]);

        // 이전버튼 활성화 처리
        if (this.navigationNumber[0] > navigationSize) {
            this.enablePrevious = true;
        }

        if (this.navigationNumber.length == navigationSize && this.navigationNumber[4] < this.totalPage) {
            this.enableNext = true;
        }
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

    public void setNumberGroupCount(int numberSetCount) {
        this.numberGroupCount = numberSetCount;
    }

    public int getNumberGroupCount() {
        return numberGroupCount;
    }
}
