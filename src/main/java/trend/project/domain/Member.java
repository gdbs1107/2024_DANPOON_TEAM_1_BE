package trend.project.domain;

import jakarta.persistence.*;
import lombok.*;
import trend.project.domain.enumClass.Role;
import trend.project.domain.enumClass.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    // 회원 ACTIVE,INACTIVE 상태 추가
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    // 회원 비활성화 상태 날짜를 기록하는 필드
    private LocalDateTime inactiveDate;

    // 팔로우 수
    @Column(nullable = false)
    private Integer FollowCount = 0;

    // 팔로워 수
    @Column(nullable = false)
    private Integer FollowerCount = 0;




    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Address> address=new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberFollow> memberFollows=new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberFollower> memberFollowers=new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<MemberProfileImage> memberProfileImages=new ArrayList<>();



    /* 연관관계 메서드 */

    // status를 INACTIVE로 변경하는 메서드
    public void setInactive() {

        this.status = Status.INACTIVE;
        this.inactiveDate = LocalDateTime.now();  // 비활성화 날짜 기록
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
