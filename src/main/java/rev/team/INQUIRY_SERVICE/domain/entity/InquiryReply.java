package rev.team.INQUIRY_SERVICE.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class InquiryReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryReplyId;

    private String userId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postDate;
}
