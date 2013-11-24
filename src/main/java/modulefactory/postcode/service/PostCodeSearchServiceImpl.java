package modulefactory.postcode.service;

import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.repository.PlainPostCodeRepository;
import modulefactory.postcode.repository.StreetPostCodeRepository;
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

    private PlainPostCodeRepository plainPostCodeRepository;

    private StreetPostCodeRepository streetPostCodeRepository;

    @Autowired
    public void setPlainPostCodeRepository(PlainPostCodeRepository plainPostCodeRepository) {
        this.plainPostCodeRepository = plainPostCodeRepository;
    }

    @Autowired
    public void setStreetPostCodeRepository(StreetPostCodeRepository streetPostCodeRepository) {
        this.streetPostCodeRepository = streetPostCodeRepository;
    }

    @Override
    public List<PostCodeAddress> search(String address, String addressType) {

        List<PostCodeAddress> postCodeAddressList = null;
        if("PLAIN".equals(addressType)){
            postCodeAddressList = plainPostCodeRepository.findPostCode(address);
        }else if("STREET".equals(addressType)){
            postCodeAddressList = streetPostCodeRepository.findPostCode(address);
        }

        return postCodeAddressList;
    }
}
