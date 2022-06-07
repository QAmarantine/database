import Vue from 'vue'
import Router from 'vue-router'
import AppIndex from '../components/home/AppIndex'
import Login from '../components/Login'
import Home from '../components/Home'
import PaperIndex from '../components/paper/PaperIndex.vue'
import PaperEditor from '../components/paper/PaperEditor.vue'
import Paper from '../components/paper/Paper.vue'
import AdminIndex from '../components/admin/AdminIndex.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面并不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: false
          }
        },
        {
          path: '/paperindex',
          name: 'PaperIndex',
          component: PaperIndex,
          meta: {
            requireAuth: false
          }
        },
        {
          path: '/papereditor',
          name: 'PaperEditor',
          component: PaperEditor,
          meta: {
            requireAuth: false
          }
        },
        {
          path: '/admin',
          name: 'AdminIndex',
          component: AdminIndex,
          meta: {
            requireAuth: false
          }
        }
      ]
    },
    {
      path: '/paper',
      name: 'Paper',
      component: Paper,
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/register',
      name: 'Register',
      component: () => import('../components/Register')
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})

export const createRouter = routes => new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面并不需要被访问
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'AppIndex',
          component: AppIndex,
          meta: {
            requireAuth: false
          }
        },
        {
          path: '/paperindex',
          name: 'PaperIndex',
          component: PaperIndex,
          meta: {
            requireAuth: false
          }
        },
        {
          path: '/admin',
          name: 'AdminIndex',
          component: AdminIndex,
          meta: {
            requireAuth: false
          }
        }
      ]
    },
    {
      path: '/register',
      name: 'Register',
      component: () => import('../components/Register')
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
