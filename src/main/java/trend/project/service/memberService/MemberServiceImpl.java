package trend.project.service.memberService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.repository.AddressRepository;
import trend.project.repository.MemberRepository;
import trend.project.web.dto.MemberJoinDTO;

import java.time.LocalDateTime;
import java.util.List;

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
                .FollowerCount(0)
                .FollowCount(0)
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



    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
