package rev.team.INQUIRY_SERVICE.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rev.team.INQUIRY_SERVICE.domain.entity.Inquiry;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query(value = "SELECT * FROM inquiry WHERE (parent_id > 0);", nativeQuery = true)
    Page<Inquiry> findParentsByUserId(String userId, Pageable pageable);

    @Query(value = "SELECT * FROM inquiry WHERE (inquiry_id = :id);", nativeQuery = true)
    Inquiry findParentById(Long id);

    @Query(value = "SELECT * FROM inquiry WHERE (parent_id = :id) ORDER BY post_date;", nativeQuery = true)
    List<Inquiry> findChildById(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inquiry SET title = :title, content = :content WHERE (inquiry_id = :id);", nativeQuery = true)
    void updatePost(Long id, String title, String content);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inquiry SET processing = true WHERE (inquiry_id = :id);", nativeQuery = true)
    void updateProcessing(Long id);

}
