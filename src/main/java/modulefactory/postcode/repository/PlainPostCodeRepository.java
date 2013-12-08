package modulefactory.postcode.repository;

import modulefactory.postcode.model.PlainPostCodeAddress;
import modulefactory.postcode.model.PostCodeAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlainPostCodeRepository extends JpaRepository<PlainPostCodeAddress, Long>{

    @Query(value = "SELECT p " +
                   "FROM PlainPostCodeAddress p " +
                   "WHERE " +
                   " p.cityDoName LIKE %:address% OR " +
                   " p.siGunGuName LIKE %:address% OR " +
                   " p.eupMyeonDongRiName LIKE %:address% OR " +
                   " p.detailAddress LIKE %:address% "
    )
    Page<PostCodeAddress> findByAddress(@Param("address") String address, Pageable pageable);

}
