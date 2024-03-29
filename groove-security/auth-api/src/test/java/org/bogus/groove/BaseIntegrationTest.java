package org.bogus.groove;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
@ActiveProfiles("test")
@Disabled
@AutoConfigureMockMvc
@Transactional
public abstract class BaseIntegrationTest {
    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;
}
