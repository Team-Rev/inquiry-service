package rev.team.INQUIRY_SERVICE.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rev.team.INQUIRY_SERVICE.domain.entity.Inquiry;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    Page<Inquiry> findMainPostByUserId(boolean checkInquiry, String userId, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inquiry SET title = title, content = content WHERE (inquiry_id = :id);", nativeQuery = true)
    void update(Long id, String title, String content);

}
