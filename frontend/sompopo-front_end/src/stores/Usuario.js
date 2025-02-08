import { defineStore } from "pinia";
import axios from "axios";

export const useUsuarioStore = defineStore("usuarioStore", {
  state: () => ({
    usuarios: [],
    usuario: { nombreUsuario: "", correo: "", telefono: "", contraseña: "" },
  }),
  actions: {
    async obtenerUsuarios() {
      try {
        const respuesta = await axios.get("http://localhost:8080/api/usuarios");
        this.usuarios = respuesta.data;
      } catch (error) {
        console.error("Error al obtener usuarios:", error);
      }
    },
    async agregarUsuario(usuario) {
      try {
        await axios.post("http://localhost:8080/api/usuarios", usuario);
        this.obtenerUsuarios();
      } catch (error) {
        console.error("Error al agregar usuario:", error);
      }
    },
    async eliminarUsuario(id) {
      try {
        await axios.delete(`http://localhost:8080/api/usuarios/${id}`);
        this.obtenerUsuarios();
      } catch (error) {
        console.error("Error al eliminar usuario:", error);
      }
    },
    async obtenerUsuarioPorId(id) {
      try {
        const respuesta = await axios.get(`http://localhost:8080/api/usuarios/${id}`);
        this.usuario = respuesta.data;
      } catch (error) {
        console.error("Error al obtener usuario:", error);
      }
    },
    async actualizarUsuario(id, usuarioActualizado) {
      try {
        await axios.put(`http://localhost:8080/api/usuarios/${id}`, usuarioActualizado);
        this.obtenerUsuarios();
      } catch (error) {
        console.error("Error al actualizar usuario:", error);
      }
    },
  },
});
