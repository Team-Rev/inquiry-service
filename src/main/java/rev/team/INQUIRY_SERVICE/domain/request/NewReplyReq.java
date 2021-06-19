package rev.team.INQUIRY_SERVICE.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NewReplyReq {

    private Long inquiry_id;

    private String userId;

    private String content;
}
