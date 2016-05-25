package org.salva.springsession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HttpSessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class MultiLoginService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MultiLoginService.class);

    public static void createMultiLogin(HttpServletRequest httpRequest) {
        HttpSessionManager sessionManager = (HttpSessionManager) httpRequest.getAttribute(HttpSessionManager.class.getName());
        String alias = httpRequest.getParameter("_s");
        @SuppressWarnings("unchecked")
        SessionRepository<Session> sessionRepository =
                (SessionRepository<Session>) httpRequest.getAttribute(SessionRepository.class.getName());


        for (Map.Entry<String, String> entry : sessionManager.getSessionIds(httpRequest).entrySet()) {
            String aliasId = entry.getKey();
            String sessionId = entry.getValue();

            Session storedSession = sessionRepository.getSession(sessionId);
            HttpSession httpSession = httpRequest.getSession(false);
            if (storedSession != null && storedSession.getAttribute("Username") != null) {
                httpSession.setAttribute("Username", storedSession.getAttribute("Username"));
            }

            LOGGER.info("{} : {}", aliasId, sessionId);
        }

        if (alias == null || "".equals(alias.trim())) {
            alias = sessionManager.getNewSessionAlias(httpRequest);
        }
        httpRequest.setAttribute("alias", alias);
    }
}
