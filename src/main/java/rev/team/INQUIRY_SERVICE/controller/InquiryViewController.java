package rev.team.INQUIRY_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rev.team.INQUIRY_SERVICE.domain.request.NewInquiryReq;
import rev.team.INQUIRY_SERVICE.domain.request.NewReplyReq;
import rev.team.INQUIRY_SERVICE.domain.response.InquiryListRes;
import rev.team.INQUIRY_SERVICE.service.InquiryManipulateService;
import rev.team.INQUIRY_SERVICE.service.InquiryViewService;

import java.util.List;

@RestController
public class InquiryViewController {

    InquiryViewService inquiryViewService;
    InquiryManipulateService inquiryManipulateService;

    @Autowired
    public InquiryViewController(InquiryViewService inquiryViewService, InquiryManipulateService inquiryManipulateService) {
        this.inquiryViewService = inquiryViewService;
        this.inquiryManipulateService = inquiryManipulateService;
    }

    // TODO: 메인 문의 글 가져오기 (목록 보여주기 용)
    @GetMapping("/inquiryList")
    public List<InquiryListRes> getPointReason(@RequestBody String userId, Pageable pageable) {
        return inquiryViewService.getInquiryList(userId, pageable);
    }

    // TODO: 새 문의 글 작성
    @PostMapping("/createInquiry")
    public String createInquiry(@RequestBody NewInquiryReq newInquiryReq) {
        return inquiryManipulateService.insert(newInquiryReq);
    }

    // TODO: 새 답변 글 작성
    @PostMapping("/createReply")
    public String createReply(@RequestBody NewReplyReq newReplyReq) {
        return inquiryManipulateService.insert(newReplyReq);
    }

    // TODO: 문의 글 수정

    // TODO: 답변 글 수정

    // TODO: 문의 글 삭제
    // 메인 문의면 이어지는 애들 다 삭제, 서브 문의면 그것만 삭제

    // TODO: 답변 글 삭제
}
