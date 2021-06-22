package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.Builder;
import lombok.Getter;
import rev.team.INQUIRY_SERVICE.domain.entity.InquiryReply;

import java.time.LocalDateTime;

@Builder
@Getter
public class DetailInquiryReq {

    private String userId;

    private String title;

    private String content;

    private LocalDateTime postDate;

    private boolean processing;

    private InquiryReply inquiryReplyId;

}
