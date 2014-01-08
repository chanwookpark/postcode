package modulefactory.postcode.service;

import modulefactory.postcode.model.PostCodeAddress;
import org.springframework.data.domain.Page;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 19.
 * Time: 오전 8:22
 * To change this template use File | Settings | File Templates.
 */
public interface PostCodeSearchService {

    Page<PostCodeAddress> search(String address, String addressType, int pageItemSize, int pageNumber);
}
