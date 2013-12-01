package modulefactory.postcode.web;

import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.page.PageInformation;
import modulefactory.postcode.page.PageValidator;
import modulefactory.postcode.resource.PostCodeResource;
import modulefactory.postcode.service.PostCodeSearchService;
import modulefactory.postcode.temp.SampleDustView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String viewSearchPage(ModelMap model) {
        model.put(SampleDustView.CONTENT_KEY, "test");
        model.put(SampleDustView.TEMPLATE_KEY, "postcode-search");
        model.put(SampleDustView.VIEW_FILE_PATH, "mullae/postcode");
        return "search/view";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPostCode(
            @RequestParam(value = "address") String address, @RequestParam(value = "addressType") String addressType,
            @RequestParam(value = "_pageItemSize", required = false, defaultValue = "10") int pageItemSize,
            @RequestParam(value = "_pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "_navigationSize", required = false, defaultValue = "5") int navigationSize,
            ModelMap model) {

        new PageValidator().validate(pageItemSize, pageNumber, navigationSize);

        // Repository 조회 전에 파라미터 구성하기
        final Page<PostCodeAddress> addressData = searchService.search(address, addressType, pageItemSize, pageNumber);

        PageInformation page = new PageInformation(pageNumber, pageItemSize, addressData.getTotalElements(), addressData.getTotalPages(), navigationSize);
        PostCodeResource resource = new PostCodeResource(addressData, page, addressType);

        model.put(SampleDustView.CONTENT_KEY, resource);
        model.put(SampleDustView.TEMPLATE_KEY, "postcode-search");
        model.put(SampleDustView.VIEW_FILE_PATH, "mullae/postcode");

        return "/search/result";
    }

}
