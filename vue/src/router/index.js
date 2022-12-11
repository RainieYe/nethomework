import { createRouter, createWebHistory } from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/login",
    children:[
      {
      path: '/selfinformation',
      name: 'Selfinformation',
      component: ()=>import("@/views/Selfinformation")
      },
      {
        path: '/myroom',
        name: 'myroom',
        component: ()=>import("@/views/myroom")
      },
      {
        path: 'user',
        name: 'User',
        component: ()=>import("@/views/User"),
      },
      {
        path: 'jurisdiction',
        name: 'Jurisdiction',
        component: ()=>import("@/views/Jurisdiction"),
      },

      {
        path: '/person',
        name: 'Person',
        component: ()=>import("@/views/Person")
      },
      {
        path: '/example',
        name: 'Room',
        component: ()=>import("@/views/Room")
      },
      {
        path: '/roomstandard',
        name: 'RoomStandard',
        component: ()=>import("@/views/RoomStandard")
      },
      {
        path: '/roomsee',
        name: 'RoomSee',
        component: ()=>import("@/views/RoomSee")
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import("@/views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: ()=>import("@/views/Register")
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
