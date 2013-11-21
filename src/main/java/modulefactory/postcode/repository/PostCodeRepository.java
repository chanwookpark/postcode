package modulefactory.postcode.repository;

import modulefactory.postcode.model.PostCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostCodeRepository extends JpaRepository<PostCode, Long>{

    @Query(value = "SELECT p FROM PostCode p WHERE p.address LIKE %:address%")
    List<PostCode> findPostCode(@Param("address")String address);
}
