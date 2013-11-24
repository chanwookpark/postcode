package modulefactory.postcode.repository;

import modulefactory.postcode.model.PlainPostCodeAddress;
import modulefactory.postcode.model.PostCodeAddress;
import modulefactory.postcode.model.StreetPostCodeAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StreetPostCodeRepository extends JpaRepository<StreetPostCodeAddress, Long>{

    @Query(value = "SELECT p " +
                   "FROM StreetPostCodeAddress p " +
                   "WHERE " +
                   " p.cityDoName LIKE %:address% OR " +
                   " p.siGunGuName LIKE %:address% OR " +
                   " p.streetName LIKE %:address% OR " +
                   " p.streetDetailName LIKE %:address% OR " +
                   " p.buildingName LIKE %:address% "
    )
    Page<PostCodeAddress> findPostCode(@Param("address") String address, Pageable pageRequest);
}
