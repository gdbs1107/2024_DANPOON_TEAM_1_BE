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
    public void fixAddress(String username, MemberProfileFixDTO.MemberProfileRequestDTO request){

        Member memberByUsername = getMemberByUsername(username);

        Address newAddress = Address.builder()
                .province(request.getProvince())
                .city(request.getCity())
                .build();

        addressRepository.delete((Address) memberByUsername.getAddress());
        memberByUsername.setAddress(null);
        memberByUsername.setAddress(newAddress);

        memberRepository.save(memberByUsername);
    }






    // 회원 찾는 메서드
    public Member getMemberByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(()-> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
