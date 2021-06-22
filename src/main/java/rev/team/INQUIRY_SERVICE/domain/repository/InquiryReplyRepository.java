package rev.team.INQUIRY_SERVICE.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rev.team.INQUIRY_SERVICE.domain.entity.InquiryReply;

@Repository
public interface InquiryReplyRepository extends JpaRepository<InquiryReply, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE inquiry_reply SET content = :content WHERE (inquiry_id = :id);", nativeQuery = true)
    void updatePost(Long id, String content);

    @Query(value = "SELECT * FROM inquiry_reply WHERE (inquiry_reply_id = :id);", nativeQuery = true)
    InquiryReply findReplyById(InquiryReply id);
}