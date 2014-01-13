package modulefactory.postcode.web;

import framewise.page.PageInformation;
import framewise.page.PageValidator;
import framewise.page.PagingParam;
import modulefactory.postcode.model.PostCodeAddress;
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

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = {"text/plain", "text/html", "*/*"})
    public String searchPostCodeForView(
            @RequestParam(value = "address") String address,
            @RequestParam(value = "addressType") String addressType,
            @RequestParam(value = "_templateKey", defaultValue = "postcode-results") String templateKey,
            PagingParam paging, ModelMap model) {

        PostCodeResource resource = getPostCodeResource(address, addressType, paging);

        model.put(DATA_KEY, resource);
        model.put(TEMPLATE_KEY, templateKey);
        model.put(VIEW_PATH_OVERRIDE, "https://raw.github.com/githkdh/githkdh.github.io/master/hosting/dust/postcode.markup.js");

        return "search/result";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = {"application/*"})
    @ResponseBody
    public ResponseEntity<PostCodeResource> searchPostCode(
            @RequestParam(value = "address") String address, @RequestParam(value = "addressType") String addressType,
            PagingParam paging) {

        PostCodeResource resource = getPostCodeResource(address, addressType, paging);

        return new ResponseEntity<PostCodeResource>(resource, HttpStatus.OK);
    }

    public PostCodeResource getPostCodeResource(String address, String addressType, PagingParam paging) {
        new PageValidator().validate(paging);

        // Repository 조회 전에 파라미터 구성하기
        final Page<PostCodeAddress> addressData = searchService.search(address, addressType, paging);

        PageInformation page = new PageInformation(paging.getPageNumber(), paging.getPageItemSize(), addressData.getTotalElements(), addressData.getTotalPages(), paging.getNavigationSize());
        return new PostCodeResource(addressData, page, addressType);
    }

}
