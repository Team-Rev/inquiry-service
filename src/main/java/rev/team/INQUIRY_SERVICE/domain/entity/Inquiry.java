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
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    private String userId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postDate;

    // true = 처리 완료
    // false = 처리 전
    @Column(columnDefinition = "boolean default false")
    private boolean processing;

    // true = 새 문의
    // false = 재 문의
    @Column(columnDefinition = "boolean default true")
    private boolean checkInquiry;

    // check = true 해당 문의의 답변
    // check = false 어떤 답변을 향한 재 문의인가?
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // n:1 관계
    @JoinColumn(name = "inquiryReplyId") // 참조키
    private InquiryReply inquiryReplyId; // FK

}
