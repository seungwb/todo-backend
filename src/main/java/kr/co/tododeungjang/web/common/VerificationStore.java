package kr.co.tododeungjang.web.common;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VerificationStore {

    private final ConcurrentHashMap<String, String> codeMap = new ConcurrentHashMap<>();
    private final Set<String> verifiedEmails = ConcurrentHashMap.newKeySet();

    public void putCode(String email, String code) {
        codeMap.put(email, code);
    }

    public String getCode(String email) {
        return codeMap.get(email);
    }

    public void removeCode(String email) {
        codeMap.remove(email);
    }

    public void markVerified(String email) {
        verifiedEmails.add(email);
    }

    public boolean isVerified(String email) {
        return verifiedEmails.contains(email);
    }

    public void removeVerified(String email) {
        verifiedEmails.remove(email);
    }
}
