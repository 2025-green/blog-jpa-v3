package shop.mtcoding.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
public abstract class MyRestDoc {
    @Autowired
    protected MockMvc mvc;
    protected RestDocumentationResultHandler document;

    @BeforeEach
    public void documentSetUp() {
        this.document = MockMvcRestDocumentation.document("{class-name}/{method-name}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

        // MockMvc를 @Autowired 하면 필터들이 자동 세팅된다.
        // 직접 MockMvc를 수동 설정하면 필터를 직접 설정해줘야 한다.
        // 이때 모든 주소에서 발동하기 때문에 아래와 같이 /s/api 를 설정해주자. 직접 new해서 넣었기 때문에 FilterConfig 동작X
//        this.mvc = MockMvcBuilders.webAppContextSetup(context)
//                .addFilters((req, resp, chain) -> {
//                    HttpServletRequest request = (HttpServletRequest) req;
//                    if (request.getRequestURI().startsWith("/s/api")) {
//                        new AuthorizationFilter().doFilter(req, resp, chain);
//                    } else {
//                        chain.doFilter(req, resp);
//                    }
//                })
//                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
//                .addFilter(new CorsFilter())
//                .addFilter(new LogFilter())
//                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
//                // .apply(SecurityMockMvcConfigurers.springSecurity())
//                .alwaysDo(document)
//                .build();
    }
}