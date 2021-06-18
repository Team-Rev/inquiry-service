package rev.team.INQUIRY_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rev.team.INQUIRY_SERVICE.domain.entity.Inquiry;
import rev.team.INQUIRY_SERVICE.domain.repository.InquiryReplyRepository;
import rev.team.INQUIRY_SERVICE.domain.repository.InquiryRepository;
import rev.team.INQUIRY_SERVICE.domain.response.InquiryListRes;

import java.util.LinkedList;
import java.util.List;

@Service
public class InquiryViewService {

    InquiryRepository inquiryRepository;
    InquiryReplyRepository inquiryReplyRepository;

    @Autowired
    public InquiryViewService(InquiryRepository inquiryRepository, InquiryReplyRepository inquiryReplyRepository) {
        this.inquiryRepository = inquiryRepository;
        this.inquiryReplyRepository = inquiryReplyRepository;
    }

    public List<InquiryListRes> getInquiryList(String userId, Pageable pageable) {
        Page<Inquiry> inquiries = inquiryRepository.findByUserId(userId, pageable);
        List<InquiryListRes> inquiryList = new LinkedList<>();

        for (Inquiry inquiry : inquiries) {
            InquiryListRes inquiryListRes = InquiryListRes.builder()
                    .title(inquiry.getTitle())
                    .postDate(inquiry.getPostDate())
                    .processing(inquiry.isProcessing())
                    .build();

            inquiryList.add(inquiryListRes);
        }
        return inquiryList;
    }
}
