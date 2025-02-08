<template>
  <div class="max-w-2xl mx-auto p-8 bg-white shadow-lg rounded-lg">
    <h1 class="text-3xl font-semibold text-gray-800 mb-6 text-center">
      {{ usuario.id ? "Editar" : "Crear" }} Usuario
    </h1>

    <form @submit.prevent="guardarUsuario" class="space-y-6">
      <div>
        <label for="nombreUsuario" class="block text-sm font-medium text-gray-700">Nombre de Usuario</label>
        <input
          v-model="usuario.nombreUsuario"
          type="text"
          id="nombreUsuario"
          placeholder="Nombre de Usuario"
          class="mt-2 w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
          required
        />
      </div>

      <div>
        <label for="correo" class="block text-sm font-medium text-gray-700">Correo</label>
        <input
          v-model="usuario.correo"
          type="email"
          id="correo"
          placeholder="Correo"
          class="mt-2 w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
          required
        />
      </div>

      <div>
        <label for="telefono" class="block text-sm font-medium text-gray-700">Teléfono</label>
        <input
          v-model="usuario.telefono"
          type="tel"
          id="telefono"
          placeholder="Teléfono"
          class="mt-2 w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
          required
        />
      </div>

      <div>
        <label for="contraseña" class="block text-sm font-medium text-gray-700">Contraseña</label>
        <input
          v-model="usuario.contraseña"
          type="password"
          id="contraseña"
          placeholder="Contraseña"
          class="mt-2 w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
          required
        />
      </div>

      <div class="flex justify-between items-center">
        <button
          type="submit"
          class="w-full sm:w-auto bg-green-500 text-white font-semibold py-2 px-6 rounded-lg shadow-md hover:bg-green-600 transition-colors"
        >
          {{ usuario.id ? "Actualizar" : "Crear" }} Usuario
        </button>
        <router-link to="/" class="text-blue-500 hover:text-blue-600 text-sm mt-4 sm:mt-0">
          Volver
        </router-link>
      </div>
    </form>
  </div>
</template>

<script setup>
import { useUsuarioStore } from "../stores/Usuario";
import { useRouter, useRoute } from "vue-router";
import { ref, onMounted } from "vue";

const store = useUsuarioStore();
const router = useRouter();
const route = useRoute();

const usuario = ref({
  id: null,
  nombreUsuario: "",
  correo: "",
  telefono: "",
  contraseña: "",
});

const guardarUsuario = async () => {
  if (usuario.value.id) {
    await store.actualizarUsuario(usuario.value.id, usuario.value);
  } else {
    await store.agregarUsuario(usuario.value);
  }
  router.push("/");
};

onMounted(async () => {
  if (route.params.id) {
    await store.obtenerUsuarioPorId(route.params.id);
    usuario.value = { ...store.usuario };
  }
});
</script>

