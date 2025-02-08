<template>
  <div class="max-w-4xl mx-auto p-6 bg-gray-100 min-h-screen">
    <h1 class="text-3xl font-bold text-gray-800 mb-6 text-center">Gestión de Usuarios</h1>

    <div class="flex justify-end mb-4">
      <router-link
        to="/crear"
        class="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-4 py-2 rounded-lg shadow-md transition duration-300"
      >
        Crear Usuario
      </router-link>
    </div>

    <div class="bg-white shadow-md rounded-lg p-4">
      <table class="min-w-full table-auto">
        <thead>
          <tr class="border-b">
            <th class="px-4 py-2 text-left text-sm font-medium text-gray-700">Nombre de Usuario</th>
            <th class="px-4 py-2 text-left text-sm font-medium text-gray-700">Correo</th>
            <th class="px-4 py-2 text-left text-sm font-medium text-gray-700">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="usuario in usuariosPaginados"
            :key="usuario.id"
            class="border-b hover:bg-gray-50"
          >
            <td class="px-4 py-2 text-sm text-gray-800">{{ usuario.nombreUsuario }}</td>
            <td class="px-4 py-2 text-sm text-gray-500">{{ usuario.correo }}</td>
            <td class="px-4 py-2 flex justify-start space-x-3">
              <router-link
                :to="`/editar/${usuario.id}`"
                class="text-yellow-500 hover:text-yellow-600 text-sm font-medium"
              >
                Editar
              </router-link>

              <button
                @click="eliminarUsuario(usuario.id)"
                class="text-red-500 hover:text-red-600 text-sm font-medium"
              >
                Eliminar
              </button>
            </td>
          </tr>
        </tbody>
      </table>

      <!-- Paginación -->
      <div class="mt-4 flex justify-between items-center">
        <button
          @click="paginaAnterior"
          :disabled="paginaActual === 1"
          class="px-4 py-2 text-sm font-medium text-white bg-blue-500 rounded-lg hover:bg-blue-600 disabled:bg-gray-300"
        >
          Anterior
        </button>
        <span class="text-sm font-medium text-gray-700">Página {{ paginaActual }} de {{ totalPaginas }}</span>
        <button
          @click="proximaPagina"
          :disabled="paginaActual === totalPaginas"
          class="px-4 py-2 text-sm font-medium text-white bg-blue-500 rounded-lg hover:bg-blue-600 disabled:bg-gray-300"
        >
          Siguiente
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUsuarioStore } from "../stores/Usuario";
import { onMounted, ref, computed } from "vue";

const store = useUsuarioStore();
const paginaActual = ref(1);
const filasPorPagina = 10;

const obtenerUsuarios = async () => {
  await store.obtenerUsuarios();
};

const eliminarUsuario = async (id) => {
  if (confirm("¿Seguro que quieres eliminar este usuario?")) {
    await store.eliminarUsuario(id);
    
  window.location.reload(); 
  }
  
};

const usuariosPaginados = computed(() => {
  const primerPagina = (paginaActual.value - 1) * filasPorPagina;
  const finalPagina = primerPagina + filasPorPagina;
  return store.usuarios.slice(primerPagina, finalPagina);
});

const totalPaginas = computed(() => {
  return Math.ceil(store.usuarios.length / filasPorPagina);
});

const paginaAnterior = () => {
  if (paginaActual.value > 1) {
    paginaActual.value--;
  }
};

const proximaPagina = () => {
  if (paginaActual.value < totalPaginas.value) {
    paginaActual.value++;
  }
};

onMounted(obtenerUsuarios);
</script>
