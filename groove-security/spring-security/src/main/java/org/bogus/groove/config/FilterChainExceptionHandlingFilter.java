package org.bogus.groove.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

// 필터 체인의 가장 앞에 추가
public class FilterChainExceptionHandlingFilter extends OncePerRequestFilter {
    private final ObjectMapper mapper;
    private final ExceptionTranslator exceptionTranslator;

    public FilterChainExceptionHandlingFilter(ObjectMapper mapper, ExceptionTranslator exceptionTranslator) {
        this.mapper = mapper;
        this.exceptionTranslator = exceptionTranslator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Throwable e) {
            response.setStatus(exceptionTranslator.translateToHttpStatus(e).value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(mapper.writeValueAsString(exceptionTranslator.translateToCommonResponse(e)));
        }
    }
}
