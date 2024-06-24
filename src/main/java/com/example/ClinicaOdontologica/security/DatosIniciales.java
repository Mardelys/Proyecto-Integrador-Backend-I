package com.example.ClinicaOdontologica.security;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.ClinicaOdontologica.entity.Usuario;
import com.example.ClinicaOdontologica.entity.UsuarioRole;
import com.example.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar= "admin";
        String passCifrado= passwordEncoder.encode(passSinCifrar);
        Usuario usuario= new Usuario("jorge", UsuarioRole.ROLE_USER,passCifrado,"admin@admin.com","jpereyradh");
        Usuario usuario2= new Usuario("Mardelys", UsuarioRole.ROLE_ADMIN,passCifrado,"marde@admin.com","Marde");
        System.out.println("pass cifrado: "+passCifrado);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);
    }
}
