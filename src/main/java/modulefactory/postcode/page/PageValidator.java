package modulefactory.postcode.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 12. 2.
 * Time: 오전 8:00
 * To change this template use File | Settings | File Templates.
 */
public class PageValidator {

    private final Logger logger = LoggerFactory.getLogger(PageValidator.class);

    public void validate(int pageItemSize, int pageNumber, int navigationSize) {
        if (0 >= pageNumber) {
            String msg = "페이지 번호는 1부터 시작해야 합니다! (요청 페이지 번호=" + pageNumber + ")";
            logger.error(msg);
            throw new UnCorrentPageArgumentException(msg);
        }
    }
}
