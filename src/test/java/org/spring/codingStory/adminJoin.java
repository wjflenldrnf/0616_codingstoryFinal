package org.spring.codingStory;

import ch.qos.logback.classic.pattern.FileOfCallerConverter;
import org.junit.jupiter.api.Test;
import org.spring.codingStory.approval.entity.ApprovalDivEntity;
import org.spring.codingStory.approval.entity.ApprovalStatusEntity;
import org.spring.codingStory.approval.repository.ApprovalDivRepository;
import org.spring.codingStory.approval.repository.ApprovalStatusRepository;
import org.spring.codingStory.config.FileConfig;
import org.spring.codingStory.mRank.entity.RankEntity;
import org.spring.codingStory.mRank.repository.MRankRepository;
import org.spring.codingStory.member.dto.MemberDto;
import org.spring.codingStory.member.dto.MemberFileDto;
import org.spring.codingStory.member.entity.MemberEntity;
import org.spring.codingStory.member.entity.MemberFileEntity;
import org.spring.codingStory.member.repository.MemberFileRepository;
import org.spring.codingStory.member.repository.MemberRepository;
import org.spring.codingStory.member.role.Role;
import org.spring.codingStory.member.serviceImpl.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class adminJoin {

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private MemberService memberService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private MemberFileRepository memberFileRepository;
  @Autowired
  private MRankRepository mRankRepository;
  @Autowired
  private ApprovalDivRepository approvalDivRepository;
  @Autowired
  private ApprovalStatusRepository approvalStatusRepository;

  @Test
  void admin() throws IOException {

    MemberDto memberDto;
    String adminFile = "admin.jpg";

    MemberEntity memberEntity = memberRepository.save(
            MemberEntity.builder()
                    .userEmail("admin@naver.com")
                    .userPw(passwordEncoder.encode("1234"))
                    .name("관리자")
                    .department("노원점")
                    .mRank("사장")
                    .address("서울")
                    .phoneNumber("0101234")
                    .role(Role.ADMIN)
                    .memberAttachFile(0)
                    .build());

    RankEntity rankEntity=RankEntity.builder()
            .rankName("사원")
            .build();
    RankEntity rankEntity1=RankEntity.builder()
            .rankName("팀장")
            .build();
    RankEntity rankEntity2=RankEntity.builder()
            .rankName("지점장")
            .build();
    RankEntity rankEntity3=RankEntity.builder()
            .rankName("사장")
            .build();

    mRankRepository.save(rankEntity);
    mRankRepository.save(rankEntity1);
    mRankRepository.save(rankEntity2);
    mRankRepository.save(rankEntity3);

    //보고서 진행 상태
    ApprovalStatusEntity approvalStatusEntity1 = approvalStatusRepository.save(
        ApprovalStatusEntity.builder()
            .apvStatus("진행중")
            .build()
    );
    ApprovalStatusEntity approvalStatusEntity2 = approvalStatusRepository.save(
        ApprovalStatusEntity.builder()
            .apvStatus("승인")
            .build()
    );
    ApprovalStatusEntity approvalStatusEntity3 = approvalStatusRepository.save(
        ApprovalStatusEntity.builder()
            .apvStatus("반려")
            .build()
    );
    
  //보고서 종류
    ApprovalDivEntity approvalDivEntity1 = approvalDivRepository.save(
        ApprovalDivEntity.builder()
            .apvDivName("업무 보고서")
            .build()
    );
    ApprovalDivEntity approvalDivEntity2 = approvalDivRepository.save(
        ApprovalDivEntity.builder()
            .apvDivName("회의결과 보고서")
            .build()
    );
    ApprovalDivEntity approvalDivEntity3= approvalDivRepository.save(
        ApprovalDivEntity.builder()
            .apvDivName("휴가 보고서")
            .build()
    ); ApprovalDivEntity approvalDivEntity4= approvalDivRepository.save(
        ApprovalDivEntity.builder()
            .apvDivName("결제 청구서")
            .build()
    );



  }



}

