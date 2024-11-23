package trend.project.service.memberService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.domain.Address;
import trend.project.domain.Member;
import trend.project.domain.enumClass.Role;
import trend.project.domain.enumClass.Status;
import trend.project.repository.AddressRepository;
import trend.project.repository.MemberRepository;
import trend.project.web.dto.memberDTO.MemberGetProfileDTO;
import trend.project.web.dto.memberDTO.MemberJoinDTO;
import trend.project.web.dto.memberDTO.MemberProfileFindDTO;
import trend.project.web.dto.memberDTO.MemberSearchDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AddressRepository addressRepository;



    @Override
    public MemberJoinDTO.MemberJoinResponseDTO joinMember(MemberJoinDTO.MemberJoinRequestDTO request){


        /* converter 메서드는 기본적으로 static 메모리를 할당받아 사용하기 때문에
        * bCryptPasswordEncoder를 주입받을 수 없어 회원가입만 컨버터를 사용하지 않겠습니다 */

        duplicateUsername(request.getUsername());

        Member newMember= Member.builder()
                .username(request.getUsername())
                .password(encodePassword(request.getPassword()))
                .name(request.getName())
                .nickname(request.getNickname())
                .role(Role.ROLE_USER)
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .followerCount(0)
                .followCount(0)
                .status(Status.ACTIVE)
                .build();

        Member savedMember = memberRepository.save(newMember);

        Address newAddress = Address.builder()
                .province(request.getProvince())  // 시도
                .city(request.getCity())
                .company(null)
                .member(savedMember)              // 주소를 회원과 연결
                .build();

        addressRepository.save(newAddress);


        return MemberJoinDTO.MemberJoinResponseDTO.builder()
                .MemberId(savedMember.getId())
                .build();

    }



    @Override
    public MemberGetProfileDTO.MemberGetProfileResponseByRecentDTO getMemberProfileSortUpdateDate(Long userId) {

        Member findMember = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // Plan 리스트를 DTO 리스트로 변환
        List<MemberGetProfileDTO.MemberGetProfilePlanResponseDTO> planResponseDTOList = findMember.getPlan().stream()
                .map(plan -> MemberGetProfileDTO.MemberGetProfilePlanResponseDTO.builder()
                        .title(plan.getTitle())
                        .town(plan.getLocation().getTown())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .likeCount(plan.getLikesCount())
                        .commentCount(plan.getCommentCount())
                        .build())
                .collect(Collectors.toList());



        return MemberGetProfileDTO.MemberGetProfileResponseByRecentDTO.builder()
                .name(findMember.getName())
                .planCount(getPlanCountByMemberId(userId))
                .followingCount(findMember.getFollowCount())
                .followerCount(findMember.getFollowerCount())
                .planListByUpdateDate(planResponseDTOList)
                .build();



    }


    // N+1 발생
    @Override
    public MemberGetProfileDTO.MemberGetProfileResponseByLikeCountDTO getMemberProfileSortLikeCount(Long userId) {

        Member findMember = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // Plan 리스트를 DTO 리스트로 변환
        List<MemberGetProfileDTO.MemberGetProfilePlanResponseDTO> planResponseDTOList = findMember.getPlan().stream()
                .map(plan -> MemberGetProfileDTO.MemberGetProfilePlanResponseDTO.builder()
                        .title(plan.getTitle())
                        .town(plan.getLocation().getTown())
                        .startDate(plan.getStartDate())
                        .endDate(plan.getEndDate())
                        .likeCount(plan.getLikesCount())
                        .commentCount(plan.getCommentCount())
                        .build())
                .collect(Collectors.toList());




        // 좋아요 순 정렬
        List<MemberGetProfileDTO.MemberGetProfilePlanResponseDTO> planResponseDTOListByLikeCount = new ArrayList<>(planResponseDTOList);
        planResponseDTOListByLikeCount.sort(Comparator.comparing(MemberGetProfileDTO.MemberGetProfilePlanResponseDTO::getLikeCount).reversed());


        return MemberGetProfileDTO.MemberGetProfileResponseByLikeCountDTO.builder()
                .name(findMember.getName())
                .planCount(getPlanCountByMemberId(userId))
                .followingCount(findMember.getFollowCount())
                .followerCount(findMember.getFollowerCount())
                .planListByLikeCount(planResponseDTOListByLikeCount)
                .build();



    }







    @Override
    public void deleteMember(String username) {

        Member findMember = getMemberByUsername(username);

        findMember.setInactive();  // 객체의 status 필드 수정

    }


    @Override
    public MemberProfileFindDTO.FindMemberUsernameResponseDTO getUsernamesWithPhone(MemberProfileFindDTO.FindMemberUsernameWithPhoneNumbersRequestDTO request){

        Member byPhoneNumber = memberRepository.findByPhoneNumber(request.getPhoneNumber());

        if (byPhoneNumber == null) {
            throw new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }

        return MemberProfileFindDTO.FindMemberUsernameResponseDTO.builder()
                .username(byPhoneNumber.getUsername())
                .build();

    }


    @Override
    public MemberProfileFindDTO.FindMemberUsernameResponseDTO getUsernamesWithEmail(MemberProfileFindDTO.FindMemberUsernameWithEmailsRequestDTO request){


        Member byEmail = memberRepository.findByEmail(request.getEmail());
        if (byEmail == null) {
            throw new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }


        return MemberProfileFindDTO.FindMemberUsernameResponseDTO.builder()
                .username(byEmail.getUsername())
                .build();
    }

    // 비밀번호 재설정 메서드
    @Override
    public MemberProfileFindDTO.FindMemberPasswordResponseDTO getPassword(MemberProfileFindDTO.FindMemberPasswordRequestDTO request) {

        Member byUsername = getMemberByUsername(request.getUsername());

        if (!request.getName().equals(byUsername.getName())) {
            // username이 일치하지 않음 오류
            throw new MemberCategoryHandler(ErrorStatus.MEMBER_VALID_USERNAME);
        }

        if (!request.getEmail().equals(byUsername.getEmail())) {
            // email이 일치하지 않음 오류
            throw new MemberCategoryHandler(ErrorStatus.MEMBER_VALID_EMAIL);
        }


        // 새로운 비밀번호 생성 (UUID 활용)
        String newPasswordByBcrypt = encodePassword("tempPassword");

        byUsername.setPassword(newPasswordByBcrypt);

        return MemberProfileFindDTO.FindMemberPasswordResponseDTO.builder()
                .password(byUsername.getPassword())
                .build();

    }


    @Override
    public List<MemberSearchDTO.MemberSearchResponseDTO> searchMember(String name){

        List<Member> searchMembers = memberRepository.findAllByNameContainingIgnoreCase(name);

        List<MemberSearchDTO.MemberSearchResponseDTO> searchMember = searchMembers.stream()
                .map(member -> MemberSearchDTO.MemberSearchResponseDTO.builder()
                        .name(member.getName())
                        .followerCount(member.getFollowerCount())
                        .imageLink(
                                member.getMemberProfileImages() != null && !member.getMemberProfileImages().isEmpty()
                                        ? member.getMemberProfileImages().get(0).getImageLink()
                                        : null
                        )
                        .build())
                .collect(Collectors.toList());

        return searchMember;

    }










    // 매일 자정에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void deleteOldInactiveMembers() {

        LocalDateTime cutoffDate = LocalDateTime.now().minusDays(30);  // 30일 이전 날짜 계산
        List<Member> membersToDelete = memberRepository.findInactiveMembersForDeletion(Status.INACTIVE, cutoffDate);

        // 30일 지난 회원 삭제
        memberRepository.deleteAll(membersToDelete);

    }


    // username 중복 검사 메서드
    public void duplicateUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new MemberCategoryHandler(ErrorStatus.MEMBER_USERNAME_DUPLICATE);
        }
    }


    // 회원 찾는 메서드
    public Member getMemberByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(()-> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }


    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public int getPlanCountByMemberId(Long userId) {
        // 유저 찾기
        Member findMember = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // Plan 개수 반환
        return findMember.getPlan().size();
    }
}
