package tw.brad.spring11.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import tw.brad.spring11.entity.Member;
import tw.brad.spring11.repo.MemberRepo;
import tw.brad.spring11.util.BCrypt;

@ExtendWith(MockitoExtension.class)
public class MemberServiceV2 {

    @Mock
    MemberRepo repo; //假的

    @InjectMocks
    MemberService service;

    private Member saved;

    @BeforeEach
    void setUp() {
        saved = new Member();
        saved.setId(12L);
        saved.setPw("123");
    }

    //
    @Test
    void register_emailExists_shouldThrow_andNeverSave() {
        // account already exist
        when(repo.existsByEmail("mm@mm.tw")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> service.register("mm@mm.tw", "123", "MM"));

        // account already exist,can not save
        verify(repo).existsByEmail("mm@mm.tw");
        verify(repo, never()).save(any(Member.class));

    }

    @Test
    void register_success_shouldHashPw_andReturnId() {
        when(repo.existsByEmail("mm@mm.tw")).thenReturn(false);
        when(repo.save(any(Member.class))).thenReturn(saved);

        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);

        Member m = service.register("mm@mm.tw", "123", "MM");

        assertEquals(12L, m.getId());

        verify(repo).existsByEmail("mm@mm.tw");
        verify(repo).save(captor.capture());

        Member arg = captor.getValue();
        assertEquals("MM", arg.getEmail());

        assertNotNull(arg.getPw());
        assertNotEquals("123", arg.getPw());
        assertTrue(BCrypt.checkpw("123", arg.getPw()));

    }
}
