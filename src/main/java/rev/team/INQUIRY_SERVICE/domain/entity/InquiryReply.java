package rev.team.INQUIRY_SERVICE.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
public class InquiryReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryReplyId;

    private String username;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postDate;
}
