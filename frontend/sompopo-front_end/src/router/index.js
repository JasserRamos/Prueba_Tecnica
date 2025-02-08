import { createRouter, createWebHistory } from 'vue-router';
import ListaUsuarios from '../components/ListarUsuario.vue';
import FormularioUsuario from '../components/FormUsuario.vue';

const routes = [
  { path: '/', component: ListaUsuarios },
  { path: '/crear', component: FormularioUsuario },
  { path: '/editar/:id', component: FormularioUsuario, props: true },
];

const router = createRouter({
  history: createWebHistory(), 
  routes,
});

export default router;
