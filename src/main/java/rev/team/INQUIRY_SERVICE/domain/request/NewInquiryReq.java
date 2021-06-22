package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewInquiryReq {

    private String userId;

    private String content;

    private String title;

    // 0: 메인 문의, 1~n: 서브 문의
    private Long parentId;
}
