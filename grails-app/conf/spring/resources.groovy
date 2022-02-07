import org.pucrs.ages.CustomUserDetailsService
import org.pucrs.ages.TeamConnection.UserPasswordEncoderListener

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    userDetailsService(CustomUserDetailsService)
}
