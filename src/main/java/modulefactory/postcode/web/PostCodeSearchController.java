package modulefactory.postcode.web;

import modulefactory.postcode.model.PlainPostCodeAddress;
import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.model.StreetPostCodeAddress;
import modulefactory.postcode.resource.PageInformation;
import modulefactory.postcode.resource.PostCodeResource;
import modulefactory.postcode.resource.PostCodeResourceEntity;
import modulefactory.postcode.service.PostCodeSearchService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    public ResponseEntity<PostCodeResource> searchPostCode(
            @RequestParam(value = "address") String address, String addressType,
            @RequestParam(value = "_pageItemSize", required = false, defaultValue = "10") int pageItemSize,
            @RequestParam(value = "_pageNumber", required = false, defaultValue = "0") int pageNumber) {

        // Repository 조회 전에 파라미터 구성하기
        final Page<PostCodeAddress> addressData = searchService.search(address, addressType, pageItemSize, pageNumber);

        //TODO 별도로 뽑아내 정리해내기 (model to resource)
//        ModelMapper mapper = new ModelMapper();
//        Type targetListType = new TypeToken<List<PostCodeResourceEntity>>() {}.getType();
//        List<PostCodeResourceEntity> responseEntities = mapper.map(postCodeAddresses.getContent(), targetListType);
        List<PostCodeResourceEntity> responseEntities = mapToResourceEntity(addressData);
        PostCodeResource resource =
                new PostCodeResource(responseEntities, new PageInformation(pageNumber, pageItemSize, addressData.getTotalElements(), addressData.getTotalPages()));
        resource.setAddressType(addressType);

        ResponseEntity<PostCodeResource> responseResource = new ResponseEntity<PostCodeResource>(resource, HttpStatus.OK);

        //TODO 우선 기능 돌아가게 하고 웹과의 페이징 나우젱..
//        return postCodeAddresses.getContent();
        return responseResource;
    }

    private List<PostCodeResourceEntity> mapToResourceEntity(Page<PostCodeAddress> postCodeAddresses) {
        List<PostCodeResourceEntity> entities = new ArrayList<PostCodeResourceEntity>();
        for (PostCodeAddress address : postCodeAddresses) {
            PostCodeResourceEntity entity = new PostCodeResourceEntity();
            entity.setId(address.getId());
            entity.setPostCode(address.getPostCode());

            StringBuilder addressBuilder = new StringBuilder();
            addAddress(addressBuilder, address.getCityDoName());
            addAddress(addressBuilder, address.getSiGunGuName());

            if (address.isStreetAddress()) {
                StreetPostCodeAddress streetAddress = (StreetPostCodeAddress) address;
                addAddress(addressBuilder, streetAddress.getStreetName());
                addAddress(addressBuilder, streetAddress.getStreetDetailName());
                addAddress(addressBuilder, streetAddress.getBuildingName());
            } else {
                PlainPostCodeAddress plainAddress = (PlainPostCodeAddress) address;
                addAddress(addressBuilder, plainAddress.getEupMyeonDongRiName());
                addAddress(addressBuilder, plainAddress.getDetailAddress());
            }
            entity.setAddress(addressBuilder.toString());
            entities.add(entity);
        }
        return entities;
    }

    private void addAddress(StringBuilder addressBuilder, String addressText) {
        addressBuilder.append(addressText);
        addressBuilder.append(" ");
    }


}
