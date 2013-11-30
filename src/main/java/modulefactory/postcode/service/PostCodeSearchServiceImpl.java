package modulefactory.postcode.service;

import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.repository.PlainPostCodeRepository;
import modulefactory.postcode.repository.StreetPostCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class PostCodeSearchServiceImpl implements PostCodeSearchService {

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
    public Page<PostCodeAddress> search(String address, String addressType, int pageItemSize, int pageNumber) {

        Page<PostCodeAddress> postCodeAddressList = null;
        //Paging 정보 구성하기
        Pageable pageRequest = createPageable(pageItemSize, pageNumber);
        if ("PLAIN".equals(addressType)) {
//            postCodeAddressList = plainPostCodeRepository.findByAddress(address, pageRequest);
            postCodeAddressList = plainPostCodeRepository.findByAddress(address, pageRequest);
        } else if ("STREET".equals(addressType)) {
            postCodeAddressList = streetPostCodeRepository.findPostCode(address, pageRequest);
        }

        return postCodeAddressList;
    }

    private Pageable createPageable(int pageItemSize, int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageItemSize);
        return pageRequest;
    }
}
