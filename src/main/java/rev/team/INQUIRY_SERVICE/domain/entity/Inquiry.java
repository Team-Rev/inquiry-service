package rev.team.INQUIRY_SERVICE.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    private String userId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postDate;

    // true: 처리 완료, false: 처리 전
    // 서브 문의에서는 안 쓰임
    @Column(columnDefinition = "boolean default false")
    private boolean processing;

    // 0: 메인 문의, 1~n: 서브 문의
    private Long parentId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // n:1 관계
    @JoinColumn(name = "inquiryReplyId") // 참조키
    private InquiryReply inquiryReplyId; // FK
}