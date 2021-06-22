package rev.team.INQUIRY_SERVICE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rev.team.INQUIRY_SERVICE.domain.request.DetailInquiryReq;
import rev.team.INQUIRY_SERVICE.domain.response.InquiryListRes;
import rev.team.INQUIRY_SERVICE.service.InquiryViewService;

import java.util.List;

@RestController
public class InquiryViewController {

    InquiryViewService inquiryViewService;

    @Autowired
    public InquiryViewController(InquiryViewService inquiryViewService) {
        this.inquiryViewService = inquiryViewService;
    }

    // TODO: 메인 문의 글 가져오기 (목록 보여주기 용)
    @GetMapping("/inquiryList")
    public List<InquiryListRes> getMainInquiryList(@RequestBody String userId, Pageable pageable) {
        return inquiryViewService.getMainInquiryList(userId, pageable);
    }

    // TODO: 상세 문의 글 가져오기
    @GetMapping("/inquiryDetail")
    public List<DetailInquiryReq> getInquiryDetail(@PathVariable Long id) {
        return inquiryViewService.getInquiryDetail(id);
    }

}
