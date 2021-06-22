package rev.team.INQUIRY_SERVICE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rev.team.INQUIRY_SERVICE.domain.entity.Inquiry;
import rev.team.INQUIRY_SERVICE.domain.entity.InquiryReply;
import rev.team.INQUIRY_SERVICE.domain.repository.InquiryReplyRepository;
import rev.team.INQUIRY_SERVICE.domain.repository.InquiryRepository;
import rev.team.INQUIRY_SERVICE.domain.request.DetailInquiryReq;
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

    public List<InquiryListRes> getMainInquiryList(String userId, Pageable pageable) {
        Page<Inquiry> inquiries = inquiryRepository.findParentsByUserId(userId, pageable);
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

    public List<DetailInquiryReq> getInquiryDetail(Long id) {

        List<DetailInquiryReq> inquiryDetail = new LinkedList<>();

        /*
         * 1. 메인 문의 + 답변 inquiryDetail 에 add
         */
        Inquiry mainInquiry = inquiryRepository.findParentById(id);

        // 메인 문의
        DetailInquiryReq detailInquiry = DetailInquiryReq.builder()
                .userId(mainInquiry.getUserId())
                .title(mainInquiry.getTitle())
                .content(mainInquiry.getContent())
                .postDate(mainInquiry.getPostDate())
                .processing(mainInquiry.isProcessing())
                .build();
        inquiryDetail.add(detailInquiry);

        // 메인 문의 답변
        InquiryReply mainReply = inquiryReplyRepository.findReplyById(mainInquiry.getInquiryReplyId());
        detailInquiry = detailInquiry.builder()
                .userId(mainReply.getUserId())
                .content(mainReply.getContent())
                .postDate(mainReply.getPostDate())
                .build();
        inquiryDetail.add(detailInquiry);


        /*
         * 2. 서브 문의들만 추출 + 정렬
         */
        List<Inquiry> childInquiries = inquiryRepository.findChildById(id);
        List<DetailInquiryReq> childDetailInquiries = new LinkedList<>();

        for (Inquiry child : childInquiries) {
            DetailInquiryReq detailInquiryReq = DetailInquiryReq.builder()
                    .userId(child.getUserId())
                    .content(child.getContent())
                    .postDate(child.getPostDate())
                    .inquiryReplyId(child.getInquiryReplyId())
                    .build();
            childDetailInquiries.add(detailInquiryReq);
        }

        // 4. childInquiry for-each 로 1개 씩 꺼내서 inquiryDetail 에 add + 해당 답변
        for (DetailInquiryReq detailInquiryReq : childDetailInquiries) {

            // 서브 문의
            detailInquiryReq = DetailInquiryReq.builder()
                    .userId(detailInquiryReq.getUserId())
                    .content(detailInquiryReq.getContent())
                    .postDate(detailInquiryReq.getPostDate())
                    .build();
            inquiryDetail.add(detailInquiryReq);

            // 서브 문의 답변
            InquiryReply inquiryReply = inquiryReplyRepository.findReplyById(detailInquiryReq.getInquiryReplyId());
            detailInquiryReq = DetailInquiryReq.builder()
                    .userId(inquiryReply.getUserId())
                    .content(inquiryReply.getContent())
                    .postDate(inquiryReply.getPostDate())
                    .build();
            inquiryDetail.add(detailInquiryReq);
        }

        return inquiryDetail;
    }
}