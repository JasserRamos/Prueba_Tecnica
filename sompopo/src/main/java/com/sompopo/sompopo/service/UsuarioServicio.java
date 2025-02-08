package com.sompopo.sompopo.service;

import com.sompopo.sompopo.entity.Usuario;
import com.sompopo.sompopo.repository.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findByDeletedAtIsNull(); 
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombreUsuario(usuarioDetails.getNombreUsuario());
        usuario.setCorreo(usuarioDetails.getCorreo());
        usuario.setTelefono(usuarioDetails.getTelefono());
        usuario.setContraseña(usuarioDetails.getContraseña());
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    usuario.setEliminadoEn(LocalDateTime.now()); 
    usuarioRepository.save(usuario); //
}
}