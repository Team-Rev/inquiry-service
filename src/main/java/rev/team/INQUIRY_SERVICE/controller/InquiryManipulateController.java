package rev.team.INQUIRY_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rev.team.INQUIRY_SERVICE.domain.request.EditInquiryReq;
import rev.team.INQUIRY_SERVICE.domain.request.EditReplyReq;
import rev.team.INQUIRY_SERVICE.domain.request.NewInquiryReq;
import rev.team.INQUIRY_SERVICE.domain.request.NewReplyReq;
import rev.team.INQUIRY_SERVICE.service.InquiryManipulateService;

@RestController
public class InquiryManipulateController {

    InquiryManipulateService inquiryManipulateService;

    @Autowired
    public InquiryManipulateController(InquiryManipulateService inquiryManipulateService) {
        this.inquiryManipulateService = inquiryManipulateService;
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
    @PatchMapping("/editInquiry")
    public String editInquiry(@RequestBody EditInquiryReq editInquiryReq) {
        return inquiryManipulateService.update(editInquiryReq);
    }

    // TODO: 답변 글 수정
    @PatchMapping("/editReply")
    public String editReply(@RequestBody EditReplyReq editReplyReq) {
        return inquiryManipulateService.update(editReplyReq);
    }

    // TODO: 문의 글 삭제
    @DeleteMapping("/deleteInquiry")
    public String deleteInquiry(@RequestParam Long id) {
        return inquiryManipulateService.deleteInquiry(id);
    }

    // TODO: 답변 글 삭제
    @DeleteMapping("/deleteReply")
    public String deleteReply(@RequestParam Long id) {
        return inquiryManipulateService.deleteReply(id);
    }

    // TODO: 문의 해결 여부 변경
    @PatchMapping("/updateProcessing")
    public String updateProcessing(@RequestParam Long inquiryId) {
        return inquiryManipulateService.updateProcessing(inquiryId);
    }

}
