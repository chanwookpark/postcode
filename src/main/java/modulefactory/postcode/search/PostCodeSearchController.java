package modulefactory.postcode.search;

import modulefactory.postcode.model.PostCode;
import modulefactory.postcode.service.PostCodeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 우편번호 찾기
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

    @RequestMapping(value ={"/search/view"}, method = RequestMethod.GET)
    public String viewSearchPage(){

        return "search/view";
    }

    @RequestMapping(value ={"/search"}, method = RequestMethod.GET)
    @ResponseBody
    public List<PostCode> searchPostCode(@RequestParam(value = "address") String address){

        // Repository 조회 전에 파라미터 구성하기
        final List<PostCode> postCodes = searchService.search(address);

        return postCodes;
    }

}
