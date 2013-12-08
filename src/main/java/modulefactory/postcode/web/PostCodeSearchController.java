package modulefactory.postcode.web;

import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.page.PageInformation;
import modulefactory.postcode.page.PageValidator;
import modulefactory.postcode.resource.PostCodeResource;
import modulefactory.postcode.service.PostCodeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static framewise.dustview.SimpleDustTemplateView.*;

/**
 * 우편번호 찾기
 *
 * @author chanwook
 */
@Controller
public class PostCodeSearchController {

    private PostCodeSearchService searchService;

    @Autowired
    public void setSearchService(PostCodeSearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/search.view", method = RequestMethod.GET)
    public String viewSearchPage() {
        return "search/view";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces= {"text/plain", "text/html", "*/*"})
    public String searchPostCodeForView(
            @RequestParam(value = "address") String address, @RequestParam(value = "addressType") String addressType,
            @RequestParam(value = "_pageItemSize", required = false, defaultValue = "10") int pageItemSize,
            @RequestParam(value = "_pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "_navigationSize", required = false, defaultValue = "5") int navigationSize,
            ModelMap model) {

        PostCodeResource resource = getPostCodeResource(address, addressType, pageItemSize, pageNumber, navigationSize);

        model.put(DATA_KEY, resource);
        model.put(TEMPLATE_KEY, "postcode-search");
        model.put(VIEW_PATH, "postcode");

        return "search/result";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = {"application/*"})
    @ResponseBody
    public ResponseEntity<PostCodeResource> searchPostCode(
            @RequestParam(value = "address") String address, @RequestParam(value = "addressType") String addressType,
            @RequestParam(value = "_pageItemSize", required = false, defaultValue = "10") int pageItemSize,
            @RequestParam(value = "_pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "_navigationSize", required = false, defaultValue = "5") int navigationSize) {

        PostCodeResource resource = getPostCodeResource(address, addressType, pageItemSize, pageNumber, navigationSize);

        return new ResponseEntity<PostCodeResource>(resource, HttpStatus.OK);
    }

    private PostCodeResource getPostCodeResource(String address, String addressType, int pageItemSize, int pageNumber, int navigationSize) {
        new PageValidator().validate(pageItemSize, pageNumber, navigationSize);

        // Repository 조회 전에 파라미터 구성하기
        final Page<PostCodeAddress> addressData = searchService.search(address, addressType, pageItemSize, pageNumber);

        PageInformation page = new PageInformation(pageNumber, pageItemSize, addressData.getTotalElements(), addressData.getTotalPages(), navigationSize);
        return new PostCodeResource(addressData, page, addressType);
    }

}
