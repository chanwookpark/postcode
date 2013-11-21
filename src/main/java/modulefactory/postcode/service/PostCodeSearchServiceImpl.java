package modulefactory.postcode.service;

import modulefactory.postcode.model.PostCode;
import modulefactory.postcode.repository.PostCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chanwook
 * Date: 2013. 11. 19.
 * Time: 오전 8:39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PostCodeSearchServiceImpl implements  PostCodeSearchService{

    private PostCodeRepository repository;

    @Autowired
    public void setRepository(PostCodeRepository repository) {
        this.repository = repository;
    }



    @Override
    public List<PostCode> search(String address) {

        List<PostCode> postCodeList = repository.findPostCode(address);
        return postCodeList;
    }
}
