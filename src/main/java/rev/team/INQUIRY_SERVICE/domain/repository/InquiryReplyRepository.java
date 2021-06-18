package rev.team.INQUIRY_SERVICE.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rev.team.INQUIRY_SERVICE.domain.entity.InquiryReply;

@Repository
public interface InquiryReplyRepository extends JpaRepository<InquiryReply, Long> {
}