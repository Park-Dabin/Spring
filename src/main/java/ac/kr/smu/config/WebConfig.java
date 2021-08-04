package ac.kr.smu.config;

import ac.kr.smu.config.RootConfig;
import ac.kr.smu.config.ServletConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class WebConfig implements WebApplicationInitializer {
    public final static String UPLOAD_PATH = "/Users/parcdaxb1n/Desktop/Spring/files";
    private final long MAX_FILE_SIZE = 20971520; //파일 최대 크기 1024 * 1024 * 20 = 20MB
    private final long MAX_REQUEST_SIZE = 41943040; // 요청에서 받을 수 있는 최대 크기 40MB
    private final int FILE_SIZE_THRESHOLD = 20971520; // 파일이 디스크에 기록되는 크기 제한 20MB

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	/*
        	빈을 등록하는 xml에서의 root-context.xml 설정
        */
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
        /*
        	서블렛을 설정하는 xml설정에서의 servlet-context.xml 설정
        */
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ServletConfig.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
                new DispatcherServlet(applicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(UPLOAD_PATH, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        dispatcher.setMultipartConfig(multipartConfigElement);


        /*
        	인코딩 설정, 해주지 않으면 한글이 깨진다
        */
        FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER",
                CharacterEncodingFilter.class);
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, false, "/*"); // /경로로 들어오는 모든 요청에 필터를 적용

    }
}
