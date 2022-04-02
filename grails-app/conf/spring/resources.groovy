import org.springframework.web.servlet.i18n.SessionLocaleResolver
import team5.grails.UtilisateurPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    utilisateurPasswordEncoderListener(UtilisateurPasswordEncoderListener)

    localResolver(SessionLocaleResolver){
        defaultLocale = new java.util.Locale('fr');
    }
}
