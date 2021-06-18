package rev.team.INQUIRY_SERVICE.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rev.team.INQUIRY_SERVICE.domain.entity.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {


    public Page<Inquiry> findByUserId(String userId, Pageable pageable);
}
