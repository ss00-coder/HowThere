package com.howthere.app.entity.program;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import com.howthere.app.type.Verified;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PROGRAM_RESERVATION")
@ToString @Getter
@NoArgsConstructor
public class ProgramReservation extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    private Verified verified;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    @ToString.Exclude
    private Member host;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @ToString.Exclude
    private Program program;

    public void setVerified(Verified verified) {
        this.verified = verified;
    }

    @Builder
    public ProgramReservation(Verified verified, Member member, Member host, Program program) {
        this.verified = verified;
        this.member = member;
        this.host = host;
        this.program = program;
    }
}
