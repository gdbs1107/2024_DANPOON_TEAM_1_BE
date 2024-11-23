package trend.project.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.domain.Address;
import trend.project.domain.Member;
import trend.project.repository.AddressRepository;
import trend.project.repository.MemberRepository;
import trend.project.web.dto.memberDTO.MemberProfileFixDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberProfileFixServiceImpl implements MemberProfileFixService {


    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public String fixMemberUsername(String memberUsername, String newUsername){

        Member memberByUsername = getMemberByUsername(memberUsername);
        memberByUsername.setUsername(newUsername);
        memberRepository.save(memberByUsername);

        return memberByUsername.getUsername();
    }


    @Override
    public void fixPassword(String username, String newPassword){

        Member memberByUsername = getMemberByUsername(username);
        memberByUsername.setPassword(bCryptPasswordEncoder.encode(newPassword));
        memberRepository.save(memberByUsername);
    }


    @Override
    public String fixName(String username, String newName){

        Member memberByUsername = getMemberByUsername(username);
        memberByUsername.setName(newName);
        memberRepository.save(memberByUsername);

        return memberByUsername.getName();
    }


    @Override
    public String fixPhoneNumber(String username, String newNumber){

        Member memberByUsername = getMemberByUsername(username);
        memberByUsername.setPhoneNumber(newNumber);
        memberRepository.save(memberByUsername);

        return memberByUsername.getPhoneNumber();
    }


    @Override
    public String fixEmail(String username, String newEmail){


        Member memberByUsername = getMemberByUsername(username);
        memberByUsername.setEmail(newEmail);
        memberRepository.save(memberByUsername);

        return memberByUsername.getEmail();
    }



    @Override
    public void fixAddress(String username, MemberProfileFixDTO.MemberProfileRequestDTO request) {
        // 1. 사용자 가져오기
        Member memberByUsername = getMemberByUsername(username);

        // 2. 기존 주소 가져오기
        List<Address> addresses = memberByUsername.getAddress();

        if (addresses == null || addresses.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자는 주소 정보가 없습니다.");
        }

        // 3. 첫 번째 주소 가져오기
        Address currentAddress = addresses.get(0);

        // 4. 새로운 주소 생성
        Address newAddress = Address.builder()
                .province(request.getProvince())
                .city(request.getCity())
                .build();

        // 5. 기존 주소 삭제 및 리스트에서 제거
        addressRepository.delete(currentAddress); // DB에서 삭제
        addresses.remove(0); // 리스트에서 삭제

        // 6. 새로운 주소 추가
        addresses.add(0, newAddress); // 새 주소를 0번째에 추가

        // 7. 변경된 사용자 정보 저장
        memberRepository.save(memberByUsername);
    }






    // 회원 찾는 메서드
    public Member getMemberByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(()-> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
