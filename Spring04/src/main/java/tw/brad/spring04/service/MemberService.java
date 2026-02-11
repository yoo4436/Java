package tw.brad.spring04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.brad.spring04.entity.Member;
import tw.brad.spring04.entity.Profile;
import tw.brad.spring04.repo.MemberRepo;
import tw.brad.spring04.repo.ProfileRepo;

@Service
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private ProfileRepo profile;

    @Transactional
    public Member save(Member member, Profile profile) {
        member.setProfile(profile);
        return memberRepo.save(member);
    }
}
