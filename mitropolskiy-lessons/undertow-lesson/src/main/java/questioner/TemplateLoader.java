package questioner;

        import freemarker.ext.beans.BeansWrapper;
        import freemarker.template.Configuration;
        import freemarker.template.Template;
        import freemarker.template.TemplateExceptionHandler;

        import java.io.IOException;

public class TemplateLoader {
    private TemplateLoader() {
    }

    public static Template getTemplate(final String basePackage, final String name) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassLoaderForTemplateLoading(Main.class.getClassLoader(), basePackage);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setObjectWrapper(new BeansWrapper(Configuration.VERSION_2_3_28));

        return cfg.getTemplate(name);
    }
}