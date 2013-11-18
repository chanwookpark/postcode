package modulefactory.postcode.service;

import modulefactory.postcode.model.PostCode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 19.
 * Time: 오전 8:22
 * To change this template use File | Settings | File Templates.
 */
public interface PostCodeSearchService {
    List<PostCode> search(String address);
}
