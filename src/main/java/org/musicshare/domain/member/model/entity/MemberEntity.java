package org.musicshare.domain.member.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.musicshare.domain.member.model.Member;
import org.musicshare.domain.member.model.MemberInfo;
import org.musicshare.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member", indexes = {
    @Index(name = "idx_nickname", columnList = "authorNickname")
})
public class MemberEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String profileImageUrl;
    private String introduceText;

    public MemberEntity(Member member) {
        this.id = member.getId();
        this.nickname = member.getInfo().getNickname();
        this.profileImageUrl = member.getInfo().getProfileImageUrl();
    }

    public Member toMember() {
        return Member.builder()
            .id(this.id)
            .info(new MemberInfo(this.nickname, this.profileImageUrl))
            .build();
    }

}