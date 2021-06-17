package rev.team.INQUIRY_SERVICE.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rev.team.INQUIRY_SERVICE.domain.entity.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
