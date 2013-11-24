package modulefactory.postcode.web;

import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.service.PostCodeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 우편번호 찾기
 *
 * @author chanwook
 */
@Controller
@RequestMapping(value = "/postCode")
public class PostCodeSearchController {

    private PostCodeSearchService searchService;

    @Autowired
    public void setSearchService(PostCodeSearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = {"/search/view"}, method = RequestMethod.GET)
    public String viewSearchPage() {

        return "search/view";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    @ResponseBody
    public List<PostCodeAddress> searchPostCode(
            @RequestParam(value = "address") String address, String addressType,
            @RequestParam(value = "_pageItemSize", required = false, defaultValue = "0") int pageItemSize,
            @RequestParam(value = "_pageNumber", required = false, defaultValue = "10") int pageNumber) {

        // Repository 조회 전에 파라미터 구성하기
        final Page<PostCodeAddress> postCodeAddresses = searchService.search(address, addressType, pageItemSize, pageNumber);

        //TODO 우선 기능 돌아가게 하고 웹과의 페이징 나우젱..
        return postCodeAddresses.getContent();
    }

}
