package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewInquiryReq {

    private String username;

    private String title;

    private String content;

    // 0: 메인 문의, 1~n: 서브 문의
    private Long parentId;
}
